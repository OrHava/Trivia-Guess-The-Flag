package com.orhava.trivia2;

import static com.orhava.trivia2.Game.prefs;
import static com.orhava.trivia2.MainMenu.flag;
import static com.orhava.trivia2.MainMenu.i;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Objects;

public class Menu_Game extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static TextView bestScoreNovice, bestScoreLearner, bestScoreApprentice,bestScoreCompetent,bestScoreChampion,bestScoreExpert,bestScoreMaster,bestScoreLegendary,bestScoreDivine,bestScoreMasterYoda,bestScoreBabyYoda, bestScoreDeathMarch, BestScoreStepOnLego;
    public static int WhichGame=0;
    public static int totalQuestions = 0;
    private Button nextButton1,nextButton2,nextButton3,nextButton4,nextButton5,nextButton6,nextButton7,nextButton8,nextButton9,nextButton10,nextButton11,nextButton12,nextButton13;
    private ImageButton navToMainMenu,btnMute;
    public static int NovicePointsForDataBase= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initialize();
        if (i[0] % 2==0) {

            flag = true;
            btnMute.setImageResource(R.drawable.unmute_50);

        } else {
            flag = false;
            btnMute.setImageResource( R.drawable.mute_50);
        }
        Mute_UnMute();
        savePrefs();
        configureNextButton();

        // Find the AdView element in your layout
        AdView adView = findViewById(R.id.adView);

        // Create an ad request
        AdRequest adRequest = new AdRequest.Builder().build();

        // Load the ad into the AdView
        adView.loadAd(adRequest);


    }

    @Override
    public void onBackPressed() {
        // Start the new activity when the back button is pressed
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
        finish(); // Optional: If you want to finish the current activity
    }


    private void  Mute_UnMute() {



        btnMute.setOnClickListener(view -> {
            i[0]++;
            new Handler();

            if (i[0] % 2==0) {
                flag = true;
                btnMute.setImageResource(R.drawable.unmute_50);

            } else {
                flag = false;
                btnMute.setImageResource( R.drawable.mute_50);
            }
        });

    }

void savePrefs(){

    SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
    int scoreNewNovice = prefs.getInt("scoreNovice", 0); //0 is the default value
    bestScoreNovice.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewNovice));
    NovicePointsForDataBase= scoreNewNovice;
    int scoreNewLearner = prefs.getInt("scoreLearner", 0); //0 is the default value
    bestScoreLearner.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewLearner));

    int scoreNewApprentice = prefs.getInt("scoreApprentice", 0); //0 is the default value
    bestScoreApprentice.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewApprentice));

    int scoreNewCompetent = prefs.getInt("scoreCompetent", 0); //0 is the default value
    bestScoreCompetent.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewCompetent));

    int scoreNewChampion = prefs.getInt("scoreChampion", 0); //0 is the default value
    bestScoreChampion.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewChampion));

    int scoreNewExpert = prefs.getInt("scoreExpert", 0); //0 is the default value
    bestScoreExpert.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewExpert));

    int scoreNewMaster = prefs.getInt("scoreMaster", 0); //0 is the default value
    bestScoreMaster.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewMaster));

    int scoreNewLegendary = prefs.getInt("scoreLegendary", 0); //0 is the default value
    bestScoreLegendary.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewLegendary));

    int scoreNewDivine = prefs.getInt("scoreDivine", 0); //0 is the default value
    bestScoreDivine.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewDivine));

    int scoreNewMasterYoda = prefs.getInt("scoreMasterYoda", 0); //0 is the default value
    bestScoreMasterYoda.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewMasterYoda));

    int scoreNewBabyYoda = prefs.getInt("scoreBabyYoda", 0); //0 is the default value
    bestScoreBabyYoda.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewBabyYoda));

    int scoreNewDeathMarch = prefs.getInt("scoreDeathMarch", 0); //0 is the default value
    bestScoreDeathMarch.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewDeathMarch));

    int scoreNewStepOnLego = prefs.getInt("scoreStepOnLego", 0); //0 is the default value
    BestScoreStepOnLego.setText(String.format(getString(R.string.Best_Score2)+" %s", scoreNewStepOnLego));
}
   void initialize(){

       bestScoreNovice =findViewById(R.id.bestScoreNovice);
       bestScoreLearner =findViewById(R.id.bestScoreLearner);
       bestScoreApprentice =findViewById(R.id.bestScoreApprentice);
       bestScoreCompetent =findViewById(R.id.bestScoreCompetent);
       bestScoreChampion =findViewById(R.id.bestScoreChampion);
       bestScoreExpert =findViewById(R.id.bestScoreExpert);
       bestScoreMaster =findViewById(R.id.bestScoreMaster);
       bestScoreLegendary =findViewById(R.id.bestScoreLegendary);
       bestScoreDivine =findViewById(R.id.bestScoreDivine);
       bestScoreMasterYoda =findViewById(R.id.bestScoreMasterYoda);
       bestScoreBabyYoda =findViewById(R.id.bestScoreBabyYoda);
       bestScoreDeathMarch =findViewById(R.id.bestScoreDeathMarch);
       BestScoreStepOnLego =findViewById(R.id.bestScoreStepOnLego);
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
    }



    private void configureNextButtonHelperSound()
    {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.modernclick);

            if (!MainMenu.flag){
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
            Toast.makeText(Menu_Game.this, ""+getString(R.string.pass_six_points)+" "+score, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Game.this, ""+getString(R.string.pass_six_points)+" " + oldScoreMasterYoda, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Menu_Game.this, ""+getString(R.string.pass_six_points)+" "+oldScoreBabyYoda, Toast.LENGTH_SHORT).show();
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