package com.orhava.trivia2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Objects;

public class Splash extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();

        lottieAnimationView=findViewById(R.id.lottie);



        lottieAnimationView.animate().translationY(-1600).setDuration(2000).setStartDelay(4000);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(() -> {
            Intent intent= new Intent(Splash.this, SignIn.class);
            startActivity(intent);
            finish();

        },4000);
    }
}