package com.orhava.trivia2;

import static com.orhava.trivia2.Game.prefs;

import static com.orhava.trivia2.MainMenu.isMuted;
import static com.orhava.trivia2.Menu_Game.totalQuestions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class Menu_Geography_Game extends AppCompatActivity {
    private ImageButton navToMainMenu,btnMute;
    private Button nextButton1,nextButton2,nextButton3,nextButton4,nextButton5,nextButton6,nextButton7,nextButton8;

    public  TextView bestScoreNovice;
    public  TextView bestScoreLearner;
    public  TextView bestScoreApprentice;
    public TextView bestScoreCompetent;
    public TextView bestScoreChampion;
    public TextView bestScoreExpert;
    public TextView bestScoreMaster;
    public TextView bestScoreLegendary ;
    public View rootLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(Menu_Geography_Game.this, MainMenu.class);
                startActivity(intent);
                finish(); // Optional: If you want to finish the current activity
            }
        });

        setContentView(R.layout.activity_menu_geography_game);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initialize();
        Menu_Game.WhichGame=0;
        if (isMuted) {


            btnMute.setImageResource(R.drawable.unmute_50);

        } else {

            btnMute.setImageResource( R.drawable.mute_50);
        }

        navToMainMenu.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            startActivity(new Intent(Menu_Geography_Game.this, MainMenu.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);




        });
        configureNextButton();
        Mute_UnMute();
        savePrefs();
        showAd();

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
        bestScoreNovice =findViewById(R.id.bestScoreNovice);
        bestScoreLearner =findViewById(R.id.bestScoreLearner);
        bestScoreApprentice =findViewById(R.id.bestScoreApprentice);
        bestScoreCompetent =findViewById(R.id.bestScoreCompetent);
        bestScoreChampion =findViewById(R.id.bestScoreChampion);
        bestScoreExpert =findViewById(R.id.bestScoreExpert);
        bestScoreMaster =findViewById(R.id.bestScoreMaster);
        bestScoreLegendary =findViewById(R.id.bestScoreLegendary);

        rootLayout = findViewById(R.id.RlMainMenu);

    }
    void savePrefs(){

        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        int scoreNewNovice = prefs.getInt("scoreNovice_2", 0); //0 is the default value
        bestScoreNovice.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewNovice));


        int scoreNewLearner = prefs.getInt("scoreLearner_2", 0); //0 is the default value
        bestScoreLearner.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewLearner));

        int scoreNewApprentice = prefs.getInt("scoreApprentice_2", 0); //0 is the default value
        bestScoreApprentice.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewApprentice));

        int scoreNewCompetent = prefs.getInt("scoreCompetent_2", 0); //0 is the default value
        bestScoreCompetent.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewCompetent));

        int scoreNewChampion = prefs.getInt("scoreChampion_2", 0); //0 is the default value
        bestScoreChampion.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewChampion));

        int scoreNewExpert = prefs.getInt("scoreExpert_2", 0); //0 is the default value
        bestScoreExpert.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewExpert));

        int scoreNewMaster = prefs.getInt("scoreMaster_2", 0); //0 is the default value
        bestScoreMaster.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewMaster));

        int scoreNewLegendary = prefs.getInt("scoreLegendary_2", 0); //0 is the default value
        bestScoreLegendary.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewLegendary));


    }


    private void configureNextButtonHelper(int score)
    {
        if(score > 10 * 0.59 ){
            startActivity(new Intent(Menu_Geography_Game.this, Game.class));
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
            Menu_Game.WhichGame=20;
            startActivity(new Intent(Menu_Geography_Game.this, Game.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

        });

        nextButton2.setOnClickListener(view -> {

            configureNextButtonHelperSound();
            totalQuestions = 10;
            Menu_Game.WhichGame=21;


            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreNovice = prefs.getInt("scoreNovice_2", 0); //0 is the default value
            configureNextButtonHelper(oldScoreNovice);



        });


        nextButton3.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 10;
            Menu_Game.WhichGame=22;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreLearner = prefs.getInt("scoreLearner_2", 0); //0 is the default value
            configureNextButtonHelper(oldScoreLearner);



        });

        nextButton4.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 10;
            Menu_Game.WhichGame=23;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreApprentice = prefs.getInt("scoreApprentice_2", 0); //0 is the default value
            configureNextButtonHelper(oldScoreApprentice);

        });
        nextButton5.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 10;
            Menu_Game.WhichGame=24;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreCompetent = prefs.getInt("scoreCompetent_2", 0); //0 is the default value
            configureNextButtonHelper(oldScoreCompetent);

        });

        nextButton6.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 10;
            Menu_Game.WhichGame=25;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreChampion = prefs.getInt("scoreChampion_2", 0); //0 is the default value
            configureNextButtonHelper(oldScoreChampion);

        });

        nextButton7.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 10;
            Menu_Game.WhichGame=26;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreExpert = prefs.getInt("scoreExpert_2", 0); //0 is the default value
            configureNextButtonHelper(oldScoreExpert);

        });

        nextButton8.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            totalQuestions = 10;
            Menu_Game.WhichGame=27;
            prefs = view.getContext().getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int oldScoreMaster = prefs.getInt("scoreMaster_2", 0); //0 is the default value
            configureNextButtonHelper(oldScoreMaster);

        });


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


    void showAd(){

        if (!PurchaseManager.isRemoveAdsPurchased(this)) {
            // Show ads


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

}