package com.orhava.trivia2;

import static com.orhava.trivia2.Game.prefs;
import static com.orhava.trivia2.MainMenu.isMuted;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class Menu_Game extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static int WhichGame=0;
    public static int totalQuestions = 0;
    private Button nextButton1,nextButton2,nextButton3,nextButton4,nextButton5,nextButton6,nextButton7,nextButton8,nextButton9,nextButton10,nextButton11,nextButton12,nextButton13;
    private ImageButton navToMainMenu,btnMute;
    public View rootLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.menu);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initialize();
        if (isMuted) {
            btnMute.setImageResource(R.drawable.unmute_50);

        } else {

            btnMute.setImageResource( R.drawable.mute_50);
        }
        Mute_UnMute();
        savePrefs();
        configureNextButton();
        showAd();


    }


    void showAd(){

        if (!PurchaseManager.isRemoveAdsPurchased(this)) {
            // Show ads
            // Your ad display logic here

            // Find the AdView element in your layout
            AdView adView = findViewById(R.id.adView);
            adView.setVisibility(View.VISIBLE);
            // Create an ad request
            AdRequest adRequest = new AdRequest.Builder().build();

            // Load the ad into the AdView
            adView.loadAd(adRequest);
        }
        else{
            AdView adView = findViewById(R.id.adView);
            adView.setVisibility(View.GONE);
        }
    }





    private void  Mute_UnMute() {



        btnMute.setOnClickListener(view -> {
            isMuted = !isMuted;
            new Handler();
            if (isMuted) {

                btnMute.setImageResource(R.drawable.unmute_50);

            } else {
                btnMute.setImageResource( R.drawable.mute_50);
            }
        });

    }
    void displayStarRating(int score, LinearLayout starsLayout) {
        starsLayout.removeAllViews(); // Clear existing views if any

        // Define the size of the stars (adjust as needed)
        int starSize = getResources().getDimensionPixelSize(R.dimen.star_size);

        if(score>10){

            score=score/2;
        }

        int numStars = score ;

        for (int i = 0; i < 10; i++) {
            ImageView starImageView = new ImageView(this);

            if (i < numStars) {
                // Show a filled star
                starImageView.setImageResource(R.drawable.ic_star);
            } else {
                // Show an empty star
                starImageView.setImageResource(R.drawable.ic_star_empty);
            }

            // Set layout parameters for the star icon
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(starSize, starSize);
            starImageView.setLayoutParams(layoutParams);

            // Add the star icon to the layout
            starsLayout.addView(starImageView);
        }
    }



    void savePrefs(){

    SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);


        displayStarRating(prefs.getInt("scoreNovice", 0), findViewById(R.id.bestScoreNovice));
        displayStarRating(prefs.getInt("scoreLearner", 0), findViewById(R.id.bestScoreLearner));
        displayStarRating(prefs.getInt("scoreApprentice", 0), findViewById(R.id.bestScoreApprentice));
        displayStarRating(prefs.getInt("scoreCompetent", 0), findViewById(R.id.bestScoreCompetent));
        displayStarRating(prefs.getInt("scoreChampion", 0), findViewById(R.id.bestScoreChampion));
        displayStarRating(prefs.getInt("scoreExpert", 0), findViewById(R.id.bestScoreExpert));
        displayStarRating(prefs.getInt("scoreMaster", 0), findViewById(R.id.bestScoreMaster));
        displayStarRating(prefs.getInt("scoreLegendary", 0), findViewById(R.id.bestScoreLegendary));
        displayStarRating(prefs.getInt("scoreDivine", 0), findViewById(R.id.bestScoreDivine));
        displayStarRating(prefs.getInt("scoreMasterYoda", 0), findViewById(R.id.bestScoreMasterYoda));
        displayStarRating(prefs.getInt("scoreBabyYoda", 0), findViewById(R.id.bestScoreBabyYoda));
        displayStarRating(prefs.getInt("scoreDeathMarch", 0), findViewById(R.id.bestScoreDeathMarch));
        displayStarRating(prefs.getInt("scoreStepOnLego", 0), findViewById(R.id.BestScoreStepOnLego));


}
   void initialize(){


       navToMainMenu=findViewById(R.id.navToMainMenu);
       btnMute=findViewById(R.id.mute_unmute);
       nextButton1= findViewById(R.id.navToGame1);
       nextButton2= findViewById(R.id.navToGame2);
       nextButton3= findViewById(R.id.navToGame3);
       nextButton4= findViewById(R.id.navToGame4);
       nextButton5= findViewById(R.id.navToGame5);
       nextButton6= findViewById(R.id.navToGame6);
       nextButton7= findViewById(R.id.navToGame7);
       nextButton8= findViewById(R.id.navToGame8);
       nextButton9= findViewById(R.id.navToGame9);
       nextButton10= findViewById(R.id.navToGame10);
       nextButton11= findViewById(R.id.navToGame11);
       nextButton12= findViewById(R.id.navToGame12);
       nextButton13= findViewById(R.id.navToGame13);
       rootLayout = findViewById(R.id.RlMainMenu);
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

    private void configureNextButtonHelper(int score)
    {
        if(score > totalQuestions * 0.59 ){
            startActivity(new Intent(Menu_Game.this, Game.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
        }
        else{


            Snackbar snackbar = Snackbar.make(rootLayout, getString(R.string.pass_six_points)+" "+score, Snackbar.LENGTH_SHORT);
            snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
            snackbar.show();
        }
    }




    private void configureNextButton(){

        nextButton1.setOnClickListener(view -> {
            configureNextButtonHelperSound();
             totalQuestions = 10;
            WhichGame=1;
            startActivity(new Intent(Menu_Game.this, Game.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

        });

        nextButton2.setOnClickListener(view -> {

            configureNextButtonHelperSound();
            totalQuestions = 10;
            WhichGame=2;


            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreNovice = prefs.getInt("scoreNovice", 0); //0 is the default value
            configureNextButtonHelper(oldScoreNovice);



        });


        nextButton3.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 10;
            WhichGame=3;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreLearner = prefs.getInt("scoreLearner", 0); //0 is the default value
            configureNextButtonHelper(oldScoreLearner);



        });

        nextButton4.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 10;
            WhichGame=4;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreApprentice = prefs.getInt("scoreApprentice", 0); //0 is the default value
            configureNextButtonHelper(oldScoreApprentice);

        });
        nextButton5.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 10;
            WhichGame=5;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreCompetent = prefs.getInt("scoreCompetent", 0); //0 is the default value
            configureNextButtonHelper(oldScoreCompetent);

        });

        nextButton6.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 10;
            WhichGame=6;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreChampion = prefs.getInt("scoreChampion", 0); //0 is the default value
            configureNextButtonHelper(oldScoreChampion);

        });

        nextButton7.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 10;
            WhichGame=7;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreExpert = prefs.getInt("scoreExpert", 0); //0 is the default value
            configureNextButtonHelper(oldScoreExpert);

        });

        nextButton8.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 10;
            WhichGame=8;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreMaster = prefs.getInt("scoreMaster", 0); //0 is the default value
            configureNextButtonHelper(oldScoreMaster);

        });

        nextButton9.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 10;
            WhichGame=9;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreLegendary = prefs.getInt("scoreLegendary", 0); //0 is the default value
            configureNextButtonHelper(oldScoreLegendary);


        });

        nextButton10.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 10;
            WhichGame=10;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreDivine = prefs.getInt("scoreDivine", 0); //0 is the default value
            configureNextButtonHelper(oldScoreDivine);

        });

        nextButton11.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 20;
            WhichGame=11;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreMasterYoda = prefs.getInt("scoreMasterYoda", 0); //0 is the default value
            if(oldScoreMasterYoda > 10 * 0.59 ){
                startActivity(new Intent(Menu_Game.this, Game.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
            else{

                Snackbar snackbar = Snackbar.make(rootLayout, getString(R.string.pass_six_points)+" "+oldScoreMasterYoda, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }


        });

        nextButton12.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 10;
            WhichGame=12;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreBabyYoda = prefs.getInt("scoreBabyYoda", 0); //0 is the default value
            if(oldScoreBabyYoda > 20 * 0.59 ){
                startActivity(new Intent(Menu_Game.this, Game.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
            else{


                Snackbar snackbar = Snackbar.make(rootLayout, getString(R.string.pass_six_points)+" "+oldScoreBabyYoda, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }

        });

        nextButton13.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 10;
            WhichGame=13;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreDeathMarch = prefs.getInt("scoreDeathMarch", 0); //0 is the default value
            configureNextButtonHelper(oldScoreDeathMarch);

        });



        navToMainMenu.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            startActivity(new Intent(Menu_Game.this, MainMenu.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);




        });
}}