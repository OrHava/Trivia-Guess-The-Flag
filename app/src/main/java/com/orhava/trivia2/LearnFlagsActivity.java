package com.orhava.trivia2;

import static com.orhava.trivia2.MainMenu.flag;
import static com.orhava.trivia2.MainMenu.i;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Objects;

public class LearnFlagsActivity extends AppCompatActivity {

    private VideoView flagsVideoView;
    private ImageButton pausePlayButton, navToMain, mute_unmute;
    private boolean isPaused = false;
    private int currentPosition = 0;
    private static final int SEEK_TIME = 5000; // 5 seconds for forward/backward


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_flags);
        Objects.requireNonNull(getSupportActionBar()).hide();

        navToMain = findViewById(R.id.navToMain);
        mute_unmute = findViewById(R.id.mute_unmute);
        flagsVideoView = findViewById(R.id.flagsVideoView);
        pausePlayButton = findViewById(R.id.pausePlayButton);

        ImageButton forwardButton = findViewById(R.id.forwardButton);
        ImageButton backwardButton = findViewById(R.id.backwardButton);

        forwardButton.setOnClickListener(view -> forwardVideo());
        backwardButton.setOnClickListener(view -> backwardVideo());

        // Set the video path or URI
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.flags_animation);
        flagsVideoView.setVideoURI(videoUri);

        // Prepare the VideoView and set the MediaPlayer instance as a tag
        flagsVideoView.setOnPreparedListener(mp -> {
            mp.setVolume(1.0f, 1.0f); // Set initial volume
            flagsVideoView.setTag(mp);
            flagsVideoView.start();
        });

        pausePlayButton.setOnClickListener(view -> togglePausePlay());
        Mute_UnMute();
        ButtonsNav();
        showAd();
    }

    void showAd(){
        if (!PurchaseManager.isRemoveAdsPurchased(this)) {
            // Show ads
            // Your ad display logic here

            // Find the AdView element in your layout
            AdView adView = findViewById(R.id.adView);

            // Create an ad request
            AdRequest adRequest = new AdRequest.Builder().build();

            // Load the ad into the AdView
            adView.loadAd(adRequest);
        }


    }
    private void forwardVideo() {
        int newPosition = flagsVideoView.getCurrentPosition() + SEEK_TIME;
        if (newPosition < flagsVideoView.getDuration()) {
            flagsVideoView.seekTo(newPosition);
        }
    }

    private void backwardVideo() {
        int newPosition = flagsVideoView.getCurrentPosition() - SEEK_TIME;
        if (newPosition >= 0) {
            flagsVideoView.seekTo(newPosition);
        } else {
            flagsVideoView.seekTo(0);
        }
    }

    private void ButtonsNav() {
        navToMain.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            startActivity(new Intent(LearnFlagsActivity.this, MainMenu.class));
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        });
    }

    private void configureNextButtonHelperSound() {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.modernclick);

        if (!MainMenu.flag) {
            mp.setVolume(0, 0);
        } else {
            mp.setVolume(0, 1);
        }

        mp.start();
    }

    private void Mute_UnMute() {
        mute_unmute.setOnClickListener(view -> {
            i[0]++;
            new Handler();

            if (i[0] % 2 == 0) {
                flag = true;
                mute_unmute.setImageResource(R.drawable.unmute_50);
                // Unmute the video
                if (flagsVideoView != null) {
                    MediaPlayer mediaPlayer = (MediaPlayer) flagsVideoView.getTag();
                    if (mediaPlayer != null) {
                        mediaPlayer.setVolume(1.0f, 1.0f); // 1.0f means full volume
                    }
                }
            } else {
                flag = false;
                mute_unmute.setImageResource(R.drawable.mute_50);
                // Mute the video
                if (flagsVideoView != null) {
                    MediaPlayer mediaPlayer = (MediaPlayer) flagsVideoView.getTag();
                    if (mediaPlayer != null) {
                        mediaPlayer.setVolume(0.0f, 0.0f); // 0.0f means mute
                    }
                }
            }
        });
    }


    private void togglePausePlay() {
        if (isPaused) {
            // Resume video playback
            flagsVideoView.seekTo(currentPosition);
            flagsVideoView.start();
            pausePlayButton.setImageResource(R.drawable.ic_pause);
        } else {
            // Pause video playback
            flagsVideoView.pause();
            currentPosition = flagsVideoView.getCurrentPosition();
            pausePlayButton.setImageResource(R.drawable.ic_play);
        }

        isPaused = !isPaused;
    }

    @Override
    protected void onResume() {
        super.onResume();
        flagsVideoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        flagsVideoView.pause();
        currentPosition = flagsVideoView.getCurrentPosition();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flagsVideoView.stopPlayback();
    }
}