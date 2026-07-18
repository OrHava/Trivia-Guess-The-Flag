package com.orhava.trivia2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Splash extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;

    private FirebaseAuth mAuth;
    // Guards against routing twice (once when anonymous sign-in finishes, once on timeout).
    private boolean routed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();

        lottieAnimationView=findViewById(R.id.lottie);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth = FirebaseAuth.getInstance();

        // No login is required to play. If the user already has an account
        // (e.g. a returning Google user) we keep it so their existing data stays
        // intact; otherwise we sign in anonymously to get a stable Firebase UID so
        // every feature (scores, leaderboard, multiplayer, avatars) keeps working.
        if (mAuth.getCurrentUser() == null) {
            mAuth.signInAnonymously().addOnCompleteListener(this, task -> {
                if (!task.isSuccessful()) {
                    Log.w("Splash", "Anonymous sign-in failed", task.getException());
                }
            });
        }

        // Sign-in runs in parallel; show the splash for a fixed, comfortable
        // moment, then continue even if sign-in is still in flight (the main
        // menu also tolerates a null user).
        new Handler().postDelayed(this::goToMainMenu, 2800);
    }

    private synchronized void goToMainMenu() {
        if (routed) return;
        routed = true;
        Intent intent = new Intent(Splash.this, MainMenu.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        finish();
    }
}