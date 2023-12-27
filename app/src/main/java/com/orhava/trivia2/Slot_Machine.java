package com.orhava.trivia2;

import static com.orhava.trivia2.MainMenu.isMuted;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Slot_Machine extends AppCompatActivity {
    private static final double PROBABILITY_FIRST_AVATAR = 5.0;
    private static final double PROBABILITY_SECOND_AVATAR = 5.0;
    private static final double PROBABILITY_THIRD_AVATAR = 4.0;
    private static final double PROBABILITY_FOURTH_AVATAR = 3.0;
    private static final double PROBABILITY_FIFTH_AVATAR = 2.0;
    private static final double PROBABILITY_SIXTH_AVATAR = 1.0;
    private static final double PROBABILITY_LOSE = 80.0;
    private static int avatar_num = 6; // Default value
    private MediaPlayer soundPlayer;
    private ImageView slot1, slot2, slot3;
    private Button startButton;
    private ImageButton navToMain;
    private Boolean Start;

    private final int[] rewards = {
            R.drawable.avatar_19,
            R.drawable.avatar_14,
            R.drawable.avatar_15,
            R.drawable.avatar_16,
            R.drawable.avatar_17,
            R.drawable.avatar_18,
            R.drawable.new_icon_flag_circle
    };

    private final Handler handler = new Handler();
    private boolean continueAnimation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_machine);
        Objects.requireNonNull(getSupportActionBar()).hide();
        navToMain= findViewById(R.id.navToMain);
        slot1 = findViewById(R.id.slot1);
        slot2 = findViewById(R.id.slot2);
        slot3 = findViewById(R.id.slot3);
        startButton = findViewById(R.id.startButton);
        ButtonsNav();
        Start= false;
        // Initialize MediaPlayer
        soundPlayer = MediaPlayer.create(this, R.raw.slot_machine1_sound);  // Replace with your sound file
        soundPlayer.setLooping(false);  // Do not loop the sound

    }
    private void configureNextButtonHelperSound()
    {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.modernclick);

        if (!isMuted){
            mp.setVolume(0,0);
        }
        else{
            mp.setVolume(0,1);
        }

        mp.start();
    }
    private void ButtonsNav() {
        navToMain.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            startActivity(new Intent(Slot_Machine.this, MainMenu.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);


        });


    }
    public void startAnimation(View view) {
        startButton.setEnabled(false);
        handler.removeCallbacksAndMessages(null);
        getWeightedRandomRewardIndex();
        Start=true;
        // Reset the continueAnimation flag
        continueAnimation = true;
        playSoundForFiveSeconds();
        showRandomAvatars();

        handler.postDelayed(() -> {

            stopSound();

            // Check if the animation should continue
            if (!continueAnimation) {
                startButton.setEnabled(true);
                return;
            }

            // Set the final result avatars
            slot1.setImageResource(rewards[avatar_num]);
            slot2.setImageResource(rewards[avatar_num]);
            slot3.setImageResource(rewards[avatar_num]);

            if (avatar_num != 6) {
                startButton.setVisibility(View.GONE);
                // Display "You win" message and show the winning avatars
                showCustomPopupSlot_Machine(getString(R.string.you_win), getString(R.string.congratulation_go_to_profile_to_put_your_new_avatar), rewards[avatar_num]);
                if (avatar_num==0){

                    SharedPreferences prefs4 = this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = prefs4.edit();
                    editor2.putInt("PremiumPoints1", 1);
                    editor2.apply();

                }
                else if(avatar_num==1){

                    SharedPreferences prefs4 = this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = prefs4.edit();
                    editor2.putInt("PremiumPoints2", 1);
                    editor2.apply();


                }
                else if(avatar_num==2){

                    SharedPreferences prefs4 = this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = prefs4.edit();
                    editor2.putInt("PremiumPoints3", 1);
                    editor2.apply();


                }
                else if(avatar_num==3){

                    SharedPreferences prefs4 = this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = prefs4.edit();
                    editor2.putInt("PremiumPoints4", 1);
                    editor2.apply();

                }
                else if(avatar_num==4){

                    SharedPreferences prefs4 =this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = prefs4.edit();
                    editor2.putInt("PremiumPoints5", 1);
                    editor2.apply();

                }
                else if(avatar_num==5){

                    SharedPreferences prefs4 = this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = prefs4.edit();
                    editor2.putInt("PremiumPoints6", 1);
                    editor2.apply();

                }

            } else {
                startButton.setVisibility(View.GONE);
                // Display "You lose" message
                showCustomPopupSlot_Machine(getString(R.string.you_lose),getString(R.string.you_lost_this_time_but_don_t_worry_you_unlimited_chances_go_to_home_page_and_try_again) ,rewards[avatar_num]);
            }
            Start=false;

            startButton.setEnabled(true);
        }, 3000); // Adjust the delay as needed
    }

    private void showRandomAvatars() {
        handler.postDelayed(() -> {
            if (!Start) {
                // Stop the animation when continueAnimation is false
                return;
            }

            // Update images with random avatars
            updateRandomAvatar(slot1);
            updateRandomAvatar(slot2);
            updateRandomAvatar(slot3);

            // Continue showing random avatars
            showRandomAvatars();
        }, 100); // Adjust the delay between updates as needed
    }

    private void updateRandomAvatar(ImageView imageView) {
        int randomIndex = (int) (Math.random() * rewards.length);
        imageView.setImageResource(rewards[randomIndex]);

    }


    private void playSoundForFiveSeconds() {

        if (isMuted) {
            soundPlayer.start();
            handler.postDelayed(this::stopSound, 5000);  // Stop sound after 5 seconds
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release MediaPlayer resources
        if (soundPlayer != null) {
            soundPlayer.release();
        }
    }
    private void stopSound() {
        if (soundPlayer.isPlaying()) {
            soundPlayer.pause();
            soundPlayer.seekTo(0);  // Rewind to the beginning
        }
    }
    @SuppressLint("SetTextI18n")
    private void showCustomPopupSlot_Machine(String title, String message,int avatarResourceId) {
        // Create a Dialog without a title
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_layout);
        // Initialize views

        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);
        btnYes.setText(R.string.go_to_home);
        btnNo.setText(R.string.go_to_profile);
        TextView textTitle = dialog.findViewById(R.id.textTitle);
        TextView textMessage = dialog.findViewById(R.id.textMessage);
        ImageView imagePopup = dialog.findViewById(R.id.imagePopup);

        textTitle.setText(title);
        textMessage.setText( message );
        imagePopup.setImageResource(avatarResourceId);

        // Handle button clicks
        btnYes.setOnClickListener(v -> {
            // Call the method to show the ad and give the reward

            dialog.dismiss();

            startActivity(new Intent(Slot_Machine.this, MainMenu.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);


        });

        btnNo.setOnClickListener(v ->

                startActivity(new Intent(Slot_Machine.this, Avatar_Name.class)));
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

        // Show the dialog
        dialog.show();
    }

    private void getWeightedRandomRewardIndex() {
        double randomValue = Math.random() * 100; // Generate a random number between 0 and 100

        if (randomValue < PROBABILITY_FIRST_AVATAR) {
            avatar_num = 0;
        } else if (randomValue < PROBABILITY_FIRST_AVATAR + PROBABILITY_SECOND_AVATAR) {
            avatar_num = 1;
        } else if (randomValue < PROBABILITY_FIRST_AVATAR + PROBABILITY_SECOND_AVATAR + PROBABILITY_THIRD_AVATAR) {
            avatar_num = 2;
        } else if (randomValue < PROBABILITY_FIRST_AVATAR + PROBABILITY_SECOND_AVATAR + PROBABILITY_THIRD_AVATAR
                + PROBABILITY_FOURTH_AVATAR) {
            avatar_num = 3;
        } else if (randomValue < PROBABILITY_FIRST_AVATAR + PROBABILITY_SECOND_AVATAR + PROBABILITY_THIRD_AVATAR
                + PROBABILITY_FOURTH_AVATAR + PROBABILITY_FIFTH_AVATAR) {
            avatar_num = 4;
        } else if (randomValue < PROBABILITY_FIRST_AVATAR + PROBABILITY_SECOND_AVATAR + PROBABILITY_THIRD_AVATAR
                + PROBABILITY_FOURTH_AVATAR + PROBABILITY_FIFTH_AVATAR + PROBABILITY_SIXTH_AVATAR) {
            avatar_num = 5;
        } else if (randomValue < PROBABILITY_FIRST_AVATAR + PROBABILITY_SECOND_AVATAR + PROBABILITY_THIRD_AVATAR
                + PROBABILITY_FOURTH_AVATAR + PROBABILITY_FIFTH_AVATAR + PROBABILITY_SIXTH_AVATAR + PROBABILITY_LOSE) {
            avatar_num = 6; // Lose
        } else {
            avatar_num = 6;
        }
    }
}