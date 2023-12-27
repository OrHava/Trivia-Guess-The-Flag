package com.orhava.trivia2;


import static com.orhava.trivia2.MainMenu.isMuted;
import static com.orhava.trivia2.MultiPlayer.opponentUser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ResultsMultiPlayer extends AppCompatActivity {

    private  MediaPlayer mp2=null, mp3=null,mp=null,mp4=null;
    private FirebaseUser user;
    private TextView resultTxtPlayers;
    private int ScoreOtherUser=0 ;
    private int times=0,times2=0;
    private ImageButton FinishQuiz,restartQuizBtn;
    private  LottieAnimationView loadingAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_multi_player);
        Objects.requireNonNull(getSupportActionBar()).hide();
        resultTxtPlayers=findViewById(R.id.resultTxtPlayers);
        loadingAnim = findViewById(R.id.loadingAnim);
        FinishQuiz = findViewById(R.id.FinishQuiz);
        restartQuizBtn = findViewById(R.id.restartQuizBtn);
        loadingAnim.playAnimation();
        FinishQuiz.setVisibility(View.GONE);
        restartQuizBtn.setVisibility(View.GONE);
        user= FirebaseAuth.getInstance().getCurrentUser();
        configureNextButton();
        ResultsOfPlayers();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference presenceRef = FirebaseDatabase.getInstance().getReference("Test").child("usersCodes").child(userId);
            presenceRef.onDisconnect().removeValue();
        }


    }







    @SuppressLint("SetTextI18n")
    private void setResultText() {
        @SuppressLint("WrongViewCast") View layout = findViewById(R.id.backgroundResults);
        LottieAnimationView win = findViewById(R.id.win);
        LottieAnimationView lose = findViewById(R.id.lose);
        LottieAnimationView drawAnim = findViewById(R.id.drawAnim);
        loadingAnim.setVisibility(View.GONE);
        FinishQuiz.setVisibility(View.VISIBLE);
        restartQuizBtn.setVisibility(View.VISIBLE);
        mp2 = MediaPlayer.create(this, R.raw.goodresult);
        mp3 = MediaPlayer.create(this, R.raw.lose);
        mp4 = MediaPlayer.create(this, R.raw.drawsound);
        String passStatus;
        if (Game.scoreMultiPlayer > ScoreOtherUser ) {
            passStatus = "You Won";
            layout.setBackgroundColor(Color.BLUE);
            if (!isMuted) {
                mp2.setVolume(0, 0);
            } else {
                mp2.setVolume(0, 1);
            }

            mp2.start();

            win.playAnimation();
            FinishQuiz.setBackgroundColor(Color.BLUE);
            restartQuizBtn.setBackgroundColor(Color.BLUE);

        }
        else if(Game.scoreMultiPlayer == ScoreOtherUser){
            passStatus = "Its A Draw";
            layout.setBackgroundColor(Color.CYAN);
            if (!isMuted) {
                mp4.setVolume(0, 0);
            } else {
                mp4.setVolume(0, 1);
            }

            mp4.start();
            drawAnim.setVisibility(View.VISIBLE);
            drawAnim.playAnimation();
            FinishQuiz.setBackgroundColor(Color.CYAN);
            restartQuizBtn.setBackgroundColor(Color.CYAN);
        }
        else {
            passStatus = "You Lost";
            layout.setBackgroundColor(Color.RED);
            if (!isMuted) {
                mp3.setVolume(0, 0);
            } else {
                mp3.setVolume(0, 1);
            }

            mp3.start();
            lose.playAnimation();
            FinishQuiz.setBackgroundColor(Color.RED);
            restartQuizBtn.setBackgroundColor(Color.RED);
        }
        TextView resultWINlOSETxt = findViewById(R.id.resultWINlOSETxt);
        resultWINlOSETxt.setText(passStatus);
        

       // removeUser();



    }



    void ResultsOfPlayers(){

        user= FirebaseAuth.getInstance().getCurrentUser();
        String userId = null;
        if (user != null) {
            userId = user.getUid();
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = null;
        if (userId != null) {
            myRef = database.getReference("Test").child("users").child("Score").child(userId);
        }
        DatabaseReference myRef2 = null;
        if (userId != null) {
            myRef2 = database.getReference("Test").child("users").child("Score").child("GameEnd").child(userId);
        }
        if (myRef2 != null) {
            myRef2.setValue("true");
        }
        TextView resultTxt = findViewById(R.id.resultTxt);


        assert myRef != null;
        myRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                int score=  Integer.parseInt(String.valueOf(dataSnapshot.getValue()));

                resultTxt.setText(String.format("Your Score is: %s", score));

            }

            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {
                // Failed to read value

            }
        });



        if (user != null){
            myRef2 = database.getReference("Test");


            myRef2.addValueEventListener(new ValueEventListener() {

                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot dsp : dataSnapshot.child("users").child("Score").child("GameEnd").getChildren()) {

                        if( Objects.equals(dsp.getKey(), opponentUser) && String.valueOf(dsp.getValue()).equals("true") &&times==0  ){
                            ResultsOfPlayersHelper();
                            times++;

                            break;
                        }

                    }




                }

                @Override
                public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {
                    // Failed to read value
                    Toast.makeText(ResultsMultiPlayer.this, "wait for the results...", Toast.LENGTH_SHORT).show();
                }
            });


        }









    }

    void ResultsOfPlayersHelper(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        if (user != null){
            DatabaseReference myRef3 = database.getReference("Test");


            myRef3.addValueEventListener(new ValueEventListener() {

                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot dsp : dataSnapshot.child("users").child("Score").getChildren()) {

                        if( Objects.equals(dsp.getKey(), opponentUser) && times2==0){
                            resultTxtPlayers.setText("The score of the other player: "+ Objects.requireNonNull(dsp.getValue()));
                            ScoreOtherUser =  Integer.parseInt(String.valueOf(dsp.getValue()));
                            setResultText();
                            times2++;
                            break;
                        }

                    }




                }

                @Override
                public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {
                    // Failed to read value
                    Toast.makeText(ResultsMultiPlayer.this, "wait for the results...", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

    private void configureNextButton() {
        mp = MediaPlayer.create(this, R.raw.arcadebtn);
        ImageButton restartQuizBtn = findViewById(R.id.restartQuizBtn);
        ImageButton FinishQuiz = findViewById(R.id.FinishQuiz);




        restartQuizBtn.setOnClickListener(view -> {
            if (!isMuted){
                mp.setVolume(0,0);
            }
            else{
                mp.setVolume(0,1);
            }

            if(mp4!=null){
                mp4.release();
            }
            if(mp3!=null){
                mp3.release();
            }
            if(mp2!=null){
                mp2.release();
            }
            mp.start();
            removeUser();
            startActivity(new Intent(ResultsMultiPlayer.this, MultiPlayer.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
        });

        FinishQuiz.setOnClickListener(view -> {
            if (!isMuted){
                mp.setVolume(0,0);
            }
            else{
                mp.setVolume(0,1);
            }
            if(mp4!=null){
                mp4.release();
            }

            if(mp3!=null){
                mp3.release();
            }
            if(mp2!=null){
                mp2.release();
            }
            mp.start();
            removeUser();
            startActivity(new Intent(ResultsMultiPlayer.this, MainMenu.class));
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
                    if(mp4!=null){
                        mp4.release();
                    }
                    if(mp3!=null){
                        mp3.release();
                    }
                    if(mp2!=null){
                        mp2.release();
                    }
                    removeUser();
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    startActivity(new Intent(ResultsMultiPlayer.this, MainMenu.class));


                })
                .setNegativeButton("No", (arg0, arg1) -> {
                })
                .show();

    }
    public void onDestroy() {
        removeUser();
        super.onDestroy();

    }



    void removeUser(){
        String userId = user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database.getReference("Test").child("usersCodes").child(userId);
        myRef3.getRef().removeValue();


    }

}