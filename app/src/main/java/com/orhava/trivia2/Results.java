package com.orhava.trivia2;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Objects;

public class Results extends AppCompatActivity {

  private  MediaPlayer mp2=null, mp3=null,mp=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Objects.requireNonNull(getSupportActionBar()).hide();



        setResultText();
        configureNextButton();

    }







    @SuppressLint("SetTextI18n")
    private void setResultText(){
        @SuppressLint("WrongViewCast") View layout=findViewById(R.id.backgroundResults);
        LottieAnimationView win=findViewById(R.id.win);
        LottieAnimationView lose=findViewById(R.id.lose);
        ImageButton FinishQuiz=findViewById(R.id.FinishQuiz);
        ImageButton nextLevel=findViewById(R.id.nextLevel);
        ImageButton restartQuizBtn=findViewById(R.id.restartQuizBtn);
        String passStatus;
       mp2 = MediaPlayer.create(this, R.raw.goodresult);
       mp3 = MediaPlayer.create(this, R.raw.lose);
        if (Game.scoreNovice > Menu_Game.totalQuestions * 0.59 || Game.scoreLearner > Menu_Game.totalQuestions * 0.59 || Game.scoreApprentice > Menu_Game.totalQuestions * 0.59|| Game.scoreCompetent > Menu_Game.totalQuestions * 0.59 || Game.scoreChampion > Menu_Game.totalQuestions * 0.59 || Game.scoreExpert > Menu_Game.totalQuestions * 0.59 || Game.scoreMaster > Menu_Game.totalQuestions * 0.59 || Game.scoreLegendary > Menu_Game.totalQuestions * 0.59 || Game.scoreDivine > Menu_Game.totalQuestions * 0.59 || Game.scoreMasterYoda > Menu_Game.totalQuestions * 0.59 || Game.scoreBabyYoda > Menu_Game.totalQuestions * 0.59 || Game.scoreDeathMarch > Menu_Game.totalQuestions * 0.59 || Game.scoreStepOnLego > Menu_Game.totalQuestions * 0.59) {
            passStatus = "Passed";
            layout.setBackgroundColor(Color.BLUE);
                if (!MainMenu.flag){
                    mp2.setVolume(0,0);
                }
                else{
                    mp2.setVolume(0,1);
                }

                mp2.start();

            win.playAnimation();
            FinishQuiz.setBackgroundColor(Color.BLUE);
            nextLevel.setBackgroundColor(Color.BLUE);
            restartQuizBtn.setBackgroundColor(Color.BLUE);

        } else {
            passStatus = "Failed";
            layout.setBackgroundColor(Color.RED);
            if (!MainMenu.flag){
                mp3.setVolume(0,0);
            }
            else{
                mp3.setVolume(0,1);
            }

            mp3.start();
         lose.playAnimation();
            FinishQuiz.setBackgroundColor(Color.RED);
            nextLevel.setBackgroundColor(Color.RED);
            restartQuizBtn.setBackgroundColor(Color.RED);
        }

        TextView resultTxt=findViewById(R.id.resultTxt);
        TextView resultWINlOSETxt=findViewById(R.id.resultWINlOSETxt);
        resultWINlOSETxt.setText(passStatus);
        if ( Menu_Game.WhichGame==1){
            resultTxt.setText(String.format("Best Score %s", Game.scoreNovice));
        }
        if (Menu_Game.WhichGame==2){
            resultTxt.setText(String.format("Best Score %s", Game.scoreLearner));
        }
        if(Menu_Game.WhichGame==3){
            resultTxt.setText(String.format("Best Score %s", Game.scoreApprentice));

        }
        if(Menu_Game.WhichGame==4){
            resultTxt.setText(String.format("Best Score %s", Game.scoreCompetent));

        }
        if(Menu_Game.WhichGame==5){
            resultTxt.setText(String.format("Best Score %s", Game.scoreChampion));

        }
        if(Menu_Game.WhichGame==6){
            resultTxt.setText(String.format("Best Score %s", Game.scoreExpert));

        }
        if(Menu_Game.WhichGame==7){
            resultTxt.setText(String.format("Best Score %s", Game.scoreMaster));

        }
        if(Menu_Game.WhichGame==8){
            resultTxt.setText(String.format("Best Score %s", Game.scoreLegendary));

        }
        if(Menu_Game.WhichGame==9){
            resultTxt.setText(String.format("Best Score %s", Game.scoreDivine));

        }
        if(Menu_Game.WhichGame==10){
            resultTxt.setText(String.format("Best Score %s", Game.scoreMasterYoda));

        }
        if(Menu_Game.WhichGame==11){
            resultTxt.setText(String.format("Best Score %s", Game.scoreBabyYoda));

        }
        if(Menu_Game.WhichGame==12){
            resultTxt.setText(String.format("Best Score %s", Game.scoreDeathMarch));

        }
        if(Menu_Game.WhichGame==13){
            resultTxt.setText(String.format("Best Score %s", Game.scoreStepOnLego));

        }

    }







    private void configureNextButton() {
        mp = MediaPlayer.create(this, R.raw.arcadebtn);
        ImageButton restartQuizBtn = findViewById(R.id.restartQuizBtn);
        ImageButton FinishQuiz = findViewById(R.id.FinishQuiz);
        ImageButton nextLevel=findViewById(R.id.nextLevel);


        if(Game.scoreNovice < Menu_Game.totalQuestions * 0.59 && Menu_Game.WhichGame==1 ){
            nextLevel.setVisibility(View.GONE);
        }
        else if(Game.scoreLearner < Menu_Game.totalQuestions * 0.59 && Menu_Game.WhichGame==2 ){
            nextLevel.setVisibility(View.GONE);
        }
        else if(Game.scoreApprentice < Menu_Game.totalQuestions * 0.59 && Menu_Game.WhichGame==3 ){
            nextLevel.setVisibility(View.GONE);
        }
        else if(Game.scoreCompetent < Menu_Game.totalQuestions * 0.59 && Menu_Game.WhichGame==4 ){
            nextLevel.setVisibility(View.GONE);
        }

        else if(Game.scoreChampion < Menu_Game.totalQuestions * 0.59 && Menu_Game.WhichGame==5 ){
            nextLevel.setVisibility(View.GONE);
        }

        else  if(Game.scoreExpert < Menu_Game.totalQuestions * 0.59 && Menu_Game.WhichGame==6 ){
            nextLevel.setVisibility(View.GONE);
        }

        else if(Game.scoreMaster < Menu_Game.totalQuestions * 0.59 && Menu_Game.WhichGame==7 ){
            nextLevel.setVisibility(View.GONE);
        }

        else  if(Game.scoreLegendary < Menu_Game.totalQuestions * 0.59 && Menu_Game.WhichGame==8 ){
            nextLevel.setVisibility(View.GONE);
        }

        else if(Game.scoreDivine < Menu_Game.totalQuestions * 0.59 && Menu_Game.WhichGame==9 ){
            nextLevel.setVisibility(View.GONE);
        }

        else  if(Game.scoreMasterYoda < Menu_Game.totalQuestions * 0.59 && Menu_Game.WhichGame==10 ){
            nextLevel.setVisibility(View.GONE);
        }
        else  if(Game.scoreBabyYoda < Menu_Game.totalQuestions * 0.59 && Menu_Game.WhichGame==11 ){
            nextLevel.setVisibility(View.GONE);
        }

        else  if(Game.scoreDeathMarch < Menu_Game.totalQuestions * 0.59 && Menu_Game.WhichGame==12 ){
            nextLevel.setVisibility(View.GONE);
        }


        else  if (Menu_Game.WhichGame==13){
            nextLevel.setVisibility(View.GONE);
        }

        nextLevel.setOnClickListener(view -> {
            if (!MainMenu.flag){
                mp.setVolume(0,0);
            }
            else{
                mp.setVolume(0,1);
            }

            mp.start();

            if(mp3!=null){
                mp3.release();
            }
            if(mp2!=null){
                mp2.release();
            }


            if (Menu_Game.WhichGame==1){
                Menu_Game.WhichGame=2;
                startActivity(new Intent(Results.this, Game.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
            else if (Menu_Game.WhichGame==2){
                Menu_Game.WhichGame=3;
                startActivity(new Intent(Results.this, Game.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
            else if (Menu_Game.WhichGame==3){
                Menu_Game.WhichGame=4;
                startActivity(new Intent(Results.this, Game.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
            else if (Menu_Game.WhichGame==4){
                Menu_Game.WhichGame=5;
                startActivity(new Intent(Results.this, Game.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
            else if (Menu_Game.WhichGame==5){
                Menu_Game.WhichGame=6;
                startActivity(new Intent(Results.this, Game.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
            else if (Menu_Game.WhichGame==6){
                Menu_Game.WhichGame=7;
                startActivity(new Intent(Results.this, Game.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
            else if (Menu_Game.WhichGame==7){
                Menu_Game.WhichGame=8;
                startActivity(new Intent(Results.this, Game.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
            else if (Menu_Game.WhichGame==8){
                Menu_Game.WhichGame=9;
                startActivity(new Intent(Results.this, Game.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
            else if (Menu_Game.WhichGame==9){
                Menu_Game.WhichGame=10;
                startActivity(new Intent(Results.this, Game.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
            else if (Menu_Game.WhichGame==10){
                Menu_Game.totalQuestions = QuestionAnswerBabyYoda.question.length;
                Menu_Game.WhichGame=11;
                startActivity(new Intent(Results.this, Game.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
            else if (Menu_Game.WhichGame==11){
                Menu_Game.totalQuestions = QuestionAnswerDeathMarch.question.length;
                Menu_Game.WhichGame=12;
                startActivity(new Intent(Results.this, Game.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
            else {
                    Menu_Game.totalQuestions = QuestionAnswerStepOnLego.question.length;
                Menu_Game.WhichGame=13;
                startActivity(new Intent(Results.this, Game.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }


        });



        restartQuizBtn.setOnClickListener(view -> {
            if (!MainMenu.flag){
                mp.setVolume(0,0);
            }
            else{
                mp.setVolume(0,1);
            }

            mp.start();

            startActivity(new Intent(Results.this, Game.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
        });

        FinishQuiz.setOnClickListener(view -> {
            if (!MainMenu.flag){
                mp.setVolume(0,0);
            }
            else{
                mp.setVolume(0,1);
            }

            mp.start();

            startActivity(new Intent(Results.this, Menu_Game.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
        });




    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        // do something when the button is clicked
        // do something when the button is clicked
        new AlertDialog.Builder(this)
                .setMessage("Do you want to exit Game?")
                .setPositiveButton("Yes", (arg0, arg1) -> {


                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    startActivity(new Intent(Results.this, Menu_Game.class));


                })
                .setNegativeButton("No", (arg0, arg1) -> {
                })
                .show();

    }

}