package com.orhava.trivia2;


import static com.orhava.trivia2.MainMenu.flag;
import static com.orhava.trivia2.MainMenu.i;
import static com.orhava.trivia2.MultiPlayer.opponentUser;
import com.orhava.trivia2.QuestionAnswerApprentice;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Game extends AppCompatActivity implements View.OnClickListener {


    private TextView questionTextView,totalQuestionsTextView,mTextField,whichGameTxt,whichGame2Txt;  //mTextField to use for the timer
    private Button ansA, ansB, ansC, ansD,submitBtn;
    private String selectedAns = " ";
    protected static int scoreMultiPlayer=0,scoreNovice = 0,scoreLearner = 0,scoreApprentice = 0,scoreCompetent = 0,scoreChampion = 0,scoreExpert = 0,scoreMaster = 0,scoreLegendary = 0,scoreDivine = 0,scoreMasterYoda = 0,scoreBabyYoda = 0;
    private static int indexOfQuestions;
    private ImageView iv,CorrectOrWrong,whichGameImage,whichGameImage2,whichGameImage3;
    private final int[] randomNumbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private final int[] randomNumbersQuestions = {0, 1, 2, 3};
    private ImageButton btnMute;
    private final int[] randomNumbersBabyYoda = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19};
    private MyCountDownTimer countDownTimer = new MyCountDownTimer(10000 /* 10 Sec */, 1000);
    public static SharedPreferences prefs;
    private int multiPlayerHelper=0;
    public MediaPlayer mp3 = null;
    public int times=0,times2=0;
    public MediaPlayer mp2 = null;
    public int avatarMultiPlayerChoice=0;
    public String NameMultiPlayer="";
    private FirebaseUser user;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flag_game);
        Objects.requireNonNull(getSupportActionBar()).hide();
        user= FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference presenceRef = FirebaseDatabase.getInstance().getReference("Test").child("usersCodes").child(userId);
            presenceRef.onDisconnect().removeValue();
        }
        restart();
        initialize();
        if (i[0] % 2==0) {
            btnMute.setImageResource(R.mipmap.mutenewproblem11);

        } else {
            btnMute.setImageResource(R.mipmap.mutenewproblem22);
        }
        whichGame();
        Mute_UnMute();
        configureNextButton();
        loadNewQuestion();



    }

    private void  Mute_UnMute() {



        btnMute.setOnClickListener(view -> {
            i[0]++;
            new Handler();

            if (i[0] % 2==0) {

                Toast.makeText(Game.this, "UnMute", Toast.LENGTH_SHORT).show();
                flag = true;
                btnMute.setImageResource(R.mipmap.mutenewproblem11);

            } else {
                Toast.makeText(Game.this, "Mute", Toast.LENGTH_SHORT).show();
                flag = false;
                btnMute.setImageResource(R.mipmap.mutenewproblem22);
            }
        });

    }

    void initialize(){
        iv = findViewById(R.id.flags);
        totalQuestionsTextView = findViewById(R.id.total_questions);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        whichGameTxt = findViewById(R.id.whichGameTxt);
        whichGame2Txt = findViewById(R.id.whichGame2Txt);
        whichGameImage = findViewById(R.id.whichGameImage);
        submitBtn = findViewById(R.id.submit_btn);
        mTextField = findViewById(R.id.timerText);
        CorrectOrWrong=findViewById(R.id.CorrectOrWrong);
        whichGameImage2=findViewById(R.id.whichGameImage2);
        whichGameImage3=findViewById(R.id.whichGameImage3);
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        btnMute=findViewById(R.id.mute_unmute);
        mp3 = MediaPlayer.create(this, R.raw.losesound);
        mp2 = MediaPlayer.create(this, R.raw.winsound);
        multiPlayerHelper=0;
        QuestionAnswerApprentice.initializeData(this);
    }
    void restart(){
        countDownTimer.cancel();
        shuffleArray(randomNumbers);
        shuffleArray(randomNumbersBabyYoda);
        shuffleArray(randomNumbersQuestions);
        indexOfQuestions = 0;
        scoreNovice = 0;
        scoreLearner = 0;
        scoreApprentice = 0;
        scoreCompetent = 0;
        scoreChampion = 0;
        scoreExpert = 0;
        scoreMaster = 0;
        scoreLegendary = 0;
        scoreDivine = 0;
        scoreMasterYoda = 0;
        scoreBabyYoda = 0;
        scoreMultiPlayer=0;
    }


    void setName_AvatarHelper(){
        if (user != null){
            SharedPreferences prefs2 = PreferenceManager
                    .getDefaultSharedPreferences(this);
            SharedPreferences prefs3;
            String currentUserName=prefs2.getString("autoSave", "");
            prefs3 = Game.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
            int oldAvatarChoice = prefs3.getInt("AvatarChoice", 0); //0 is the default value
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            user= FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference myRef4 = database.getReference("Test");


            myRef4.addValueEventListener(new ValueEventListener() {

                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot dsp : dataSnapshot.child("users").child("Score").child("Avatar").getChildren()) {

                        if(Objects.equals(dsp.getKey(), opponentUser)  && times2==0){

                            avatarMultiPlayerChoice=dsp.getValue(Integer.class);
                            times2++;
                            if(oldAvatarChoice==1){
                                whichGameImage2.setImageResource(R.mipmap.avater1_foreground);
                            }
                            else if(oldAvatarChoice==2){
                                whichGameImage2.setImageResource(R.mipmap.ic_avatar3_foreground);
                            }
                            else if(oldAvatarChoice==3){
                                whichGameImage2.setImageResource(R.mipmap.ic_avatar4_foreground);
                            }

                            else if(oldAvatarChoice==4){
                                whichGameImage2.setImageResource(R.mipmap.ic_avatar6_foreground);
                            }

                            else if(oldAvatarChoice==5){
                                whichGameImage2.setImageResource(R.mipmap.ic_avatar7_foreground);
                            }

                            else if(oldAvatarChoice==6){
                                whichGameImage2.setImageResource(R.mipmap.ic_avatar8_foreground);
                            }

                            else if(oldAvatarChoice==7){
                                whichGameImage2.setImageResource(R.mipmap.ic_avatar10_foreground);
                            }

                            else if(oldAvatarChoice==8){
                                whichGameImage2.setImageResource(R.mipmap.ic_avatar11_foreground);
                            }

                            else if(oldAvatarChoice==9){
                                whichGameImage2.setImageResource(R.mipmap.ic_avatar12_foreground);
                            }

                            else if(oldAvatarChoice==10){
                                whichGameImage2.setImageResource(R.mipmap.ic_avatar9_foreground);
                            }

                            else if(oldAvatarChoice==11){
                                whichGameImage2.setImageResource(R.mipmap.ic_avatar13_foreground);
                            }
                            else if(oldAvatarChoice==12){
                                whichGameImage2.setImageResource(R.mipmap.ic_avatar14_foreground);
                            }
                            else if(oldAvatarChoice==13 ){
                                whichGameImage2.setImageResource(R.mipmap.ic_avatar15_foreground);
                            }

                            else if(oldAvatarChoice==14){
                                whichGameImage2.setImageResource(R.mipmap.ic_avatar16_foreground);
                            }
                            else if(oldAvatarChoice==15 ){
                                whichGameImage2.setImageResource(R.mipmap.ic_avatar17_foreground);
                            }
                            else if(oldAvatarChoice==16 ){
                                whichGameImage2.setImageResource(R.mipmap.avaterprem1_foreground);
                            }

                            else if(oldAvatarChoice==17){
                                whichGameImage2.setImageResource(R.mipmap.avaterprem3_foreground);
                            }
                            else if(oldAvatarChoice==18 ){
                                whichGameImage2.setImageResource(R.mipmap.avaterprem2_foreground);
                            }
                            else if(oldAvatarChoice==19){
                                whichGameImage2.setImageResource(R.mipmap.avatersecret_foreground);
                            }
                            else{
                                whichGameImage2.setImageResource(R.mipmap.ic_emptyavatar_foreground);
                            }

                            if(avatarMultiPlayerChoice==1){
                                whichGameImage3.setImageResource(R.mipmap.avater1_foreground);
                            }
                            else if(avatarMultiPlayerChoice==2){
                                whichGameImage3.setImageResource(R.mipmap.ic_avatar3_foreground);
                            }
                            else if(avatarMultiPlayerChoice==3){
                                whichGameImage3.setImageResource(R.mipmap.ic_avatar4_foreground);
                            }

                            else if(avatarMultiPlayerChoice==4){
                                whichGameImage3.setImageResource(R.mipmap.ic_avatar6_foreground);
                            }

                            else if(avatarMultiPlayerChoice==5){
                                whichGameImage3.setImageResource(R.mipmap.ic_avatar7_foreground);
                            }

                            else if(avatarMultiPlayerChoice==6){
                                whichGameImage3.setImageResource(R.mipmap.ic_avatar8_foreground);
                            }

                            else if(avatarMultiPlayerChoice==7){
                                whichGameImage3.setImageResource(R.mipmap.ic_avatar10_foreground);
                            }

                            else if(avatarMultiPlayerChoice==8){
                                whichGameImage3.setImageResource(R.mipmap.ic_avatar11_foreground);
                            }

                            else if(avatarMultiPlayerChoice==9){
                                whichGameImage3.setImageResource(R.mipmap.ic_avatar12_foreground);
                            }

                            else if(avatarMultiPlayerChoice==10){
                                whichGameImage3.setImageResource(R.mipmap.ic_avatar9_foreground);
                            }

                            else if(avatarMultiPlayerChoice==11){
                                whichGameImage3.setImageResource(R.mipmap.ic_avatar13_foreground);
                            }
                            else if(avatarMultiPlayerChoice==12){
                                whichGameImage3.setImageResource(R.mipmap.ic_avatar14_foreground);
                            }
                            else if(avatarMultiPlayerChoice==13 ){
                                whichGameImage3.setImageResource(R.mipmap.ic_avatar15_foreground);
                            }

                            else if(avatarMultiPlayerChoice==14){
                                whichGameImage3.setImageResource(R.mipmap.ic_avatar16_foreground);
                            }
                            else if(avatarMultiPlayerChoice==15 ){
                                whichGameImage3.setImageResource(R.mipmap.ic_avatar17_foreground);
                            }
                            else if(avatarMultiPlayerChoice==16 ){
                                whichGameImage3.setImageResource(R.mipmap.avaterprem1_foreground);
                            }

                            else if(avatarMultiPlayerChoice==17){
                                whichGameImage3.setImageResource(R.mipmap.avaterprem3_foreground);
                            }
                            else if(avatarMultiPlayerChoice==18 ){
                                whichGameImage3.setImageResource(R.mipmap.avaterprem2_foreground);
                            }
                            else if(avatarMultiPlayerChoice==19){
                                whichGameImage3.setImageResource(R.mipmap.avatersecret_foreground);
                            }
                            else{
                                whichGameImage3.setImageResource(R.mipmap.ic_emptyavatar_foreground);
                            }





                            break;


                        }

                    }

                    for (DataSnapshot dsp : dataSnapshot.child("users").child("Score").child("Name").getChildren()) {

                        if(Objects.equals(dsp.getKey(), opponentUser)  && times==0){
                            NameMultiPlayer= Objects.requireNonNull(dsp.getValue()).toString();
                            whichGame2Txt.setText(currentUserName+" VS "+ NameMultiPlayer);
                            times++;
                            break;

                        }

                    }




                }

                @Override
                public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {
                    // Failed to read value

                }
            });




        }

    }



void whichGame(){
if( Menu_Game.WhichGame==1){
    whichGameTxt.setText(R.string.novice);
    whichGame2Txt.setText(R.string.Level1);
    whichGameImage.setImageResource(R.drawable.learner);
}
else if(Menu_Game.WhichGame==2){
    whichGameTxt.setText(R.string.learner);
    whichGame2Txt.setText(R.string.Level2);
    whichGameImage.setImageResource(R.drawable.apprenticee);
}
else if(Menu_Game.WhichGame==3){
    whichGameTxt.setText(R.string.apprentice);
    whichGame2Txt.setText(R.string.Level3);
    whichGameImage.setImageResource(R.drawable.novice12);
}
else if(Menu_Game.WhichGame==4){
    whichGameTxt.setText(R.string.competent);
    whichGame2Txt.setText(R.string.Level4);
    whichGameImage.setImageResource(R.drawable.competent123);
}
else if(Menu_Game.WhichGame==5){
    whichGameTxt.setText(R.string.champion);
    whichGame2Txt.setText(R.string.Level5);
    whichGameImage.setImageResource(R.drawable.champion);
}
else if(Menu_Game.WhichGame==6){
    whichGameTxt.setText(R.string.expert);
    whichGame2Txt.setText(R.string.Level6);
    whichGameImage.setImageResource(R.drawable.expert);
}
else if(Menu_Game.WhichGame==7){
    whichGameTxt.setText(R.string.master);
    whichGame2Txt.setText(R.string.Level7);
    whichGameImage.setImageResource(R.drawable.master);
}
else if(Menu_Game.WhichGame==8){
    whichGameTxt.setText(R.string.legendary);
    whichGame2Txt.setText(R.string.Level8);
    whichGameImage.setImageResource(R.drawable.legendary);
}
else if(Menu_Game.WhichGame==9){
    whichGameTxt.setText(R.string.divine);
    whichGame2Txt.setText(R.string.Level9);
    whichGameImage.setImageResource(R.drawable.divinie);
}
else if(Menu_Game.WhichGame==10){
    whichGameTxt.setText(R.string.master_yoda);
    whichGame2Txt.setText(R.string.Level10);
    whichGameImage.setImageResource(R.drawable.masteryoda);
}
else if(Menu_Game.WhichGame==11){
    whichGameTxt.setText(R.string.baby_yoda);
    whichGame2Txt.setText(R.string.Level11);
    whichGameImage.setImageResource(R.drawable.babyyoda);
}
else if(Menu_Game.WhichGame==12){
    whichGameTxt.setText(R.string.MultiPlayer);
    whichGameImage.setImageResource(R.mipmap.vs_foreground);
    setName_AvatarHelper();

}


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
                .setMessage(R.string.Do_you_want_to_exit_Game)
                .setPositiveButton(R.string.Yes, (arg0, arg1) -> {

                    countDownTimer.cancel();
                    if (countDownTimer != null) {
                        countDownTimer = null;
                    }
                    countDownTimer = new MyCountDownTimer(10000 /* 10 Sec */, 1000);
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

                    if(mp3!=null){
                        mp3.release();
                    }
                    if(mp2!=null){
                        mp2.release();
                    }
                    indexOfQuestions = 0;
                    shuffleArray(randomNumbers);
                    shuffleArray(randomNumbersBabyYoda);
                    shuffleArray(randomNumbersQuestions);
                    if(Menu_Game.WhichGame==12){
                        removeUser();
                        startActivity(new Intent(Game.this, ResultsMultiPlayer.class));

                    }
                    else{
                        startActivity(new Intent(Game.this, Menu_Game.class));
                    }
                    overridePendingTransition(R.anim.slide_in,R.anim.slide_out);


                })
                .setNegativeButton(R.string.No, (arg0, arg1) -> {
                })
                .show();

    }



    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onTick(long millisUntilFinished) {
            mTextField.setText((millisUntilFinished / 1000) + "");
            mTextField.setTextColor(Color.WHITE);
            Log.e("Second Gone", "Another Second Gone");
            Log.e("Time Remaining", "seconds remaining: " + millisUntilFinished / 1000);

            if (millisUntilFinished / 1000<4){
                mTextField.setTextColor(Color.RED);
            }


        }

        @Override
        public void onFinish() {
            Log.e("Times up", "Times up");
            if ((indexOfQuestions > 9 || indexOfQuestions < 0) && ( Menu_Game.WhichGame!=11)) {
                cancel();
                finishQuiz();

            }
            else if(indexOfQuestions > 19 || indexOfQuestions < 0){
                cancel();
                finishQuiz();

            }
            else{
                mTextField.setTextColor(Color.WHITE);
                indexOfQuestions++;
                cancel();
                final MediaPlayer mp3 = MediaPlayer.create(Game.this, R.raw.losesound);
                CorrectOrWrong.setImageResource(R.drawable.cancel);
                if (!MainMenu.flag){
                    mp3.setVolume(0,0);
                }
                else{
                    mp3.setVolume(0,1);
                }

                mp3.start();
                submitBtn.setEnabled(false);
                final Handler handler3 = new Handler();
                handler3.postDelayed(() -> {
                    countDownTimer.cancel();
                    loadNewQuestion();
                }, 1000);
            }

        }
    }

    // Implementing Fisher–Yates shuffle
    static void shuffleArray(int[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int j = ar.length - 1; j > 0; j--) {
            int index = rnd.nextInt(j + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[j];
            ar[j] = a;
        }
    }


    private void configureNextButton() {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.modernclick);
        ImageButton nextButton = findViewById(R.id.navToMain);
        nextButton.setOnClickListener(view -> exitByBackKey());

    }




    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {


        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);
        Button clickedButton = (Button) view;



        if ((indexOfQuestions > 9 || indexOfQuestions < 0) && ( Menu_Game.WhichGame!=11)) {
            finishQuiz();

        }
        else if(indexOfQuestions > 19 || indexOfQuestions < 0){

            finishQuiz();
        }
         else  if (clickedButton.getId() == R.id.submit_btn && Menu_Game.WhichGame!=11 &&  (Objects.equals(selectedAns, ansA.getText().toString()) || Objects.equals(selectedAns, ansB.getText().toString()) || Objects.equals(selectedAns, ansC.getText().toString()) || Objects.equals(selectedAns, ansD.getText().toString()))) {

                if(Menu_Game.WhichGame==1){
                    loadClass(QuestionAnswerNovice.correctAnswwrs[randomNumbers[indexOfQuestions]],1);
                }
                else if(Menu_Game.WhichGame==2){
                    loadClass(QuestionAnswerLearner.correctAnswwrs[randomNumbers[indexOfQuestions]],2);
                }
                else if(Menu_Game.WhichGame==3){
                    loadClass(QuestionAnswerApprentice.correctAnswwrs[randomNumbers[indexOfQuestions]],3);
                }
                else if(Menu_Game.WhichGame==4){
                    loadClass(QuestionAnswerCompetent.correctAnswwrs[randomNumbers[indexOfQuestions]],4);
                }
                else if(Menu_Game.WhichGame==5){
                    loadClass(QuestionAnswerChampion.correctAnswwrs[randomNumbers[indexOfQuestions]],5);
                }
                else if(Menu_Game.WhichGame==6){
                    loadClass(QuestionAnswerExpert.correctAnswwrs[randomNumbers[indexOfQuestions]],6);
                }
                else if(Menu_Game.WhichGame==7){
                    loadClass(QuestionAnswerMaster.correctAnswwrs[randomNumbers[indexOfQuestions]],7);
                }
                else if(Menu_Game.WhichGame==8){
                    loadClass(QuestionAnswerLegendary.correctAnswwrs[randomNumbers[indexOfQuestions]],8);
                }
                else if(Menu_Game.WhichGame==9){
                    loadClass(QuestionAnswerDivine.correctAnswwrs[randomNumbers[indexOfQuestions]],9);
                }
                else if(Menu_Game.WhichGame==10){
                    loadClass(QuestionAnswerMasterYoda.correctAnswwrs[randomNumbers[indexOfQuestions]],10);
                }

                else if(Menu_Game.WhichGame==12){
                    if(multiPlayerHelper==1){
                        loadClass(QuestionAnswerNovice.correctAnswwrs[randomNumbers[indexOfQuestions]],12);

                    }
                    else if(multiPlayerHelper==2){
                        loadClass(QuestionAnswerLearner.correctAnswwrs[randomNumbers[indexOfQuestions]],12);
                    }
                    else if(multiPlayerHelper==3){
                        loadClass(QuestionAnswerApprentice.correctAnswwrs[randomNumbers[indexOfQuestions]],12);

                    }
                    else if(multiPlayerHelper==4){
                        loadClass(QuestionAnswerCompetent.correctAnswwrs[randomNumbers[indexOfQuestions]],12);

                    }
                    else if(multiPlayerHelper==5){
                        loadClass(QuestionAnswerChampion.correctAnswwrs[randomNumbers[indexOfQuestions]],12);

                    }
                    else if(multiPlayerHelper==6){
                        loadClass(QuestionAnswerExpert.correctAnswwrs[randomNumbers[indexOfQuestions]],12);

                    }
                    else if(multiPlayerHelper==7){
                        loadClass(QuestionAnswerMaster.correctAnswwrs[randomNumbers[indexOfQuestions]],12);

                    }
                    else if(multiPlayerHelper==8){
                        loadClass(QuestionAnswerLegendary.correctAnswwrs[randomNumbers[indexOfQuestions]],12);

                    }
                    else if(multiPlayerHelper==9){
                        loadClass(QuestionAnswerDivine.correctAnswwrs[randomNumbers[indexOfQuestions]],12);


                    }
                    else if(multiPlayerHelper==10){
                        loadClass(QuestionAnswerMasterYoda.correctAnswwrs[randomNumbers[indexOfQuestions]],12);
                    }
                }

                indexOfQuestions++;
            submitBtn.setEnabled(false);
                final Handler handler3 = new Handler();
                handler3.postDelayed(() -> {
                    countDownTimer.cancel();
                    loadNewQuestion();
                }, 1000);


            }
            else if (clickedButton.getId() == R.id.submit_btn && Menu_Game.WhichGame==11 ){
                loadClass(QuestionAnswerBabyYoda.correctAnswwrs[randomNumbersBabyYoda[indexOfQuestions]],11);
               submitBtn.setEnabled(false);
                indexOfQuestions++;
                final Handler handler4 = new Handler();
                handler4.postDelayed(() -> {
                    countDownTimer.cancel();
                    loadNewQuestion();
                }, 1000);
            }

        else if(clickedButton.getId() == R.id.submit_btn ){
            Toast.makeText(this, ""+R.string.Chose_Option, Toast.LENGTH_SHORT).show();
        }

        else{
            selectedAns = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }


    }

    void loadClass(String newAnswer,int newWhichGame){

        mp3 = MediaPlayer.create(this, R.raw.losesound);
        mp2 = MediaPlayer.create(this, R.raw.winsound);

        if (selectedAns.equals(newAnswer) && Menu_Game.WhichGame==newWhichGame) {
            if (!MainMenu.flag){
                mp2.setVolume(0,0);
            }
            else{
                mp2.setVolume(0,1);
            }

            mp2.start();

            if(Menu_Game.WhichGame==1){
                scoreNovice++;
            }
            else if(Menu_Game.WhichGame==2){
                scoreLearner++;
            }
            else if(Menu_Game.WhichGame==3){
                scoreApprentice++;
            }
            else if(Menu_Game.WhichGame==4){
                scoreCompetent++;
            }
            else if(Menu_Game.WhichGame==5){
                scoreChampion++;
            }
            else if(Menu_Game.WhichGame==6){
                scoreExpert++;
            }
            else if(Menu_Game.WhichGame==7){
                scoreMaster++;
            }
            else if(Menu_Game.WhichGame==8){
                scoreLegendary++;
            }
            else if(Menu_Game.WhichGame==9){
                scoreDivine++;
            }
            else if(Menu_Game.WhichGame==10){
                scoreMasterYoda++;
            }
            else if(Menu_Game.WhichGame==11){
                scoreBabyYoda++;
            }
            else if(Menu_Game.WhichGame==12){
                scoreMultiPlayer++;
            }


            CorrectOrWrong.setImageResource(R.drawable.check);
            if(ansA.getText().toString().equals(selectedAns)){
                ansA.setBackgroundColor(Color.GREEN);
            }
            else if(ansB.getText().toString().equals(selectedAns)){
                ansB.setBackgroundColor(Color.GREEN);
            }
            else if(ansC.getText().toString().equals(selectedAns)){
                ansC.setBackgroundColor(Color.GREEN);
            }
            else if(ansD.getText().toString().equals(selectedAns)){
                ansD.setBackgroundColor(Color.GREEN);
            }

        }
        else if(Menu_Game.WhichGame==newWhichGame){
            if (!MainMenu.flag){
                mp3.setVolume(0,0);
            }
            else{
                mp3.setVolume(0,1);
            }

            mp3.start();
            CorrectOrWrong.setImageResource(R.drawable.cancel);

            if(ansA.getText().toString().equals(selectedAns)){
                ansA.setBackgroundColor(Color.RED);
            }
            else if(ansB.getText().toString().equals(selectedAns)){
                ansB.setBackgroundColor(Color.RED);
            }
            else if(ansC.getText().toString().equals(selectedAns)){
                ansC.setBackgroundColor(Color.RED);
            }
            else if(ansD.getText().toString().equals(selectedAns)){
                ansD.setBackgroundColor(Color.RED);
            }

            if(ansA.getText().toString().equals(newAnswer)){
                ansA.setBackgroundColor(Color.GREEN);
            }
            else if(ansB.getText().toString().equals(newAnswer)){
                ansB.setBackgroundColor(Color.GREEN);
            }
            else if(ansC.getText().toString().equals(newAnswer)){
                ansC.setBackgroundColor(Color.GREEN);
            }
            else if(ansD.getText().toString().equals(newAnswer)){
                ansD.setBackgroundColor(Color.GREEN);
            }



        }
    }





    @SuppressLint("SetTextI18n")
    void loadNewQuestion() {
        submitBtn.setEnabled(true);
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);
        CorrectOrWrong.setImageResource(R.color.teal_200);
        countDownTimer.cancel();
        countDownTimer.start();



        if (indexOfQuestions == Menu_Game.totalQuestions) {

            finishQuiz();
            return;
        }

        totalQuestionsTextView.setText(getString(R.string.Question) +" "+(indexOfQuestions+1)   +"/"+ Menu_Game.totalQuestions);
        if ( Menu_Game.WhichGame==1){

            questionTextView.setText(QuestionAnswerNovice.question[randomNumbers[indexOfQuestions]]);
            ansA.setText(QuestionAnswerNovice.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
            ansB.setText(QuestionAnswerNovice.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
            ansC.setText(QuestionAnswerNovice.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
            ansD.setText(QuestionAnswerNovice.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
            iv.setImageResource(QuestionAnswerNovice.images[randomNumbers[indexOfQuestions]]);

        }
         if (Menu_Game.WhichGame==2){
            questionTextView.setText(QuestionAnswerLearner.question[randomNumbers[indexOfQuestions]]);
            ansA.setText(QuestionAnswerLearner.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
             ansB.setText(QuestionAnswerLearner.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
             ansC.setText(QuestionAnswerLearner.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
             ansD.setText(QuestionAnswerLearner.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
            iv.setImageResource(QuestionAnswerLearner.images[randomNumbers[indexOfQuestions]]);
        }


         if(Menu_Game.WhichGame==3){
            questionTextView.setText(QuestionAnswerApprentice.question[randomNumbers[indexOfQuestions]]);
             ansA.setText(QuestionAnswerApprentice.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
             ansB.setText(QuestionAnswerApprentice.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
             ansC.setText(QuestionAnswerApprentice.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
             ansD.setText(QuestionAnswerApprentice.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
            iv.setImageResource(QuestionAnswerApprentice.images[randomNumbers[indexOfQuestions]]);
        }
        if(Menu_Game.WhichGame==4){

            questionTextView.setText(QuestionAnswerCompetent.question[randomNumbers[indexOfQuestions]]);
            ansA.setText(QuestionAnswerCompetent.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
            ansB.setText(QuestionAnswerCompetent.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
            ansC.setText(QuestionAnswerCompetent.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
            ansD.setText(QuestionAnswerCompetent.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
            iv.setImageResource(QuestionAnswerCompetent.images[randomNumbers[indexOfQuestions]]);
        }

        if(Menu_Game.WhichGame==5){

            questionTextView.setText(QuestionAnswerChampion.question[randomNumbers[indexOfQuestions]]);
            ansA.setText(QuestionAnswerChampion.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
            ansB.setText(QuestionAnswerChampion.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
            ansC.setText(QuestionAnswerChampion.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
            ansD.setText(QuestionAnswerChampion.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
            iv.setImageResource(QuestionAnswerChampion.images[randomNumbers[indexOfQuestions]]);
        }

        if(Menu_Game.WhichGame==6){

            questionTextView.setText(QuestionAnswerExpert.question[randomNumbers[indexOfQuestions]]);
            ansA.setText(QuestionAnswerExpert.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
            ansB.setText(QuestionAnswerExpert.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
            ansC.setText(QuestionAnswerExpert.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
            ansD.setText(QuestionAnswerExpert.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
            iv.setImageResource(QuestionAnswerExpert.images[randomNumbers[indexOfQuestions]]);
        }

        if(Menu_Game.WhichGame==7){

            questionTextView.setText(QuestionAnswerMaster.question[randomNumbers[indexOfQuestions]]);
            ansA.setText(QuestionAnswerMaster.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
            ansB.setText(QuestionAnswerMaster.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
            ansC.setText(QuestionAnswerMaster.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
            ansD.setText(QuestionAnswerMaster.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
            iv.setImageResource(QuestionAnswerMaster.images[randomNumbers[indexOfQuestions]]);
        }

        if(Menu_Game.WhichGame==8){

            questionTextView.setText(QuestionAnswerLegendary.question[randomNumbers[indexOfQuestions]]);
            ansA.setText(QuestionAnswerLegendary.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
            ansB.setText(QuestionAnswerLegendary.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
            ansC.setText(QuestionAnswerLegendary.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
            ansD.setText(QuestionAnswerLegendary.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
            iv.setImageResource(QuestionAnswerLegendary.images[randomNumbers[indexOfQuestions]]);
        }

        if(Menu_Game.WhichGame==9){

            questionTextView.setText(QuestionAnswerDivine.question[randomNumbers[indexOfQuestions]]);
            ansA.setText(QuestionAnswerDivine.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
            ansB.setText(QuestionAnswerDivine.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
            ansC.setText(QuestionAnswerDivine.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
            ansD.setText(QuestionAnswerDivine.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
            iv.setImageResource(QuestionAnswerDivine.images[randomNumbers[indexOfQuestions]]);
        }

        if(Menu_Game.WhichGame==10){

            questionTextView.setText(QuestionAnswerMasterYoda.question[randomNumbers[indexOfQuestions]]);
            ansA.setText(QuestionAnswerMasterYoda.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
            ansB.setText(QuestionAnswerMasterYoda.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
            ansC.setText(QuestionAnswerMasterYoda.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
            ansD.setText(QuestionAnswerMasterYoda.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
            iv.setImageResource(QuestionAnswerMasterYoda.images[randomNumbers[indexOfQuestions]]);
        }

        if(Menu_Game.WhichGame==11){

            questionTextView.setText(QuestionAnswerBabyYoda.question[randomNumbersBabyYoda[indexOfQuestions]]);
            ansA.setText(QuestionAnswerBabyYoda.choices[randomNumbersBabyYoda[indexOfQuestions]][randomNumbersQuestions[0]]);
            ansB.setText(QuestionAnswerBabyYoda.choices[randomNumbersBabyYoda[indexOfQuestions]][randomNumbersQuestions[1]]);
            ansC.setText(QuestionAnswerBabyYoda.choices[randomNumbersBabyYoda[indexOfQuestions]][randomNumbersQuestions[2]]);
            ansD.setText(QuestionAnswerBabyYoda.choices[randomNumbersBabyYoda[indexOfQuestions]][randomNumbersQuestions[3]]);
            iv.setImageResource(QuestionAnswerBabyYoda.images[randomNumbersBabyYoda[indexOfQuestions]]);
        }
        if(Menu_Game.WhichGame==12){
            if(multiPlayerHelper==0){
                questionTextView.setText(QuestionAnswerNovice.question[randomNumbers[indexOfQuestions]]);
                ansA.setText(QuestionAnswerNovice.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
                ansB.setText(QuestionAnswerNovice.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
                ansC.setText(QuestionAnswerNovice.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
                ansD.setText(QuestionAnswerNovice.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
                iv.setImageResource(QuestionAnswerNovice.images[randomNumbers[indexOfQuestions]]);
            }
            else if(multiPlayerHelper==1){
                questionTextView.setText(QuestionAnswerLearner.question[randomNumbers[indexOfQuestions]]);
                ansA.setText(QuestionAnswerLearner.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
                ansB.setText(QuestionAnswerLearner.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
                ansC.setText(QuestionAnswerLearner.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
                ansD.setText(QuestionAnswerLearner.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
                iv.setImageResource(QuestionAnswerLearner.images[randomNumbers[indexOfQuestions]]);
            }
            else if(multiPlayerHelper==2){
                questionTextView.setText(QuestionAnswerApprentice.question[randomNumbers[indexOfQuestions]]);
                ansA.setText(QuestionAnswerApprentice.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
                ansB.setText(QuestionAnswerApprentice.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
                ansC.setText(QuestionAnswerApprentice.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
                ansD.setText(QuestionAnswerApprentice.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
                iv.setImageResource(QuestionAnswerApprentice.images[randomNumbers[indexOfQuestions]]);
            }
            else if(multiPlayerHelper==3){
                questionTextView.setText(QuestionAnswerCompetent.question[randomNumbers[indexOfQuestions]]);
                ansA.setText(QuestionAnswerCompetent.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
                ansB.setText(QuestionAnswerCompetent.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
                ansC.setText(QuestionAnswerCompetent.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
                ansD.setText(QuestionAnswerCompetent.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
                iv.setImageResource(QuestionAnswerCompetent.images[randomNumbers[indexOfQuestions]]);
            }
            else if(multiPlayerHelper==4){
                questionTextView.setText(QuestionAnswerChampion.question[randomNumbers[indexOfQuestions]]);
                ansA.setText(QuestionAnswerChampion.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
                ansB.setText(QuestionAnswerChampion.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
                ansC.setText(QuestionAnswerChampion.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
                ansD.setText(QuestionAnswerChampion.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
                iv.setImageResource(QuestionAnswerChampion.images[randomNumbers[indexOfQuestions]]);
            }
            else if(multiPlayerHelper==5){
                questionTextView.setText(QuestionAnswerExpert.question[randomNumbers[indexOfQuestions]]);
                ansA.setText(QuestionAnswerExpert.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
                ansB.setText(QuestionAnswerExpert.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
                ansC.setText(QuestionAnswerExpert.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
                ansD.setText(QuestionAnswerExpert.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
                iv.setImageResource(QuestionAnswerExpert.images[randomNumbers[indexOfQuestions]]);
            }
            else if(multiPlayerHelper==6){
                questionTextView.setText(QuestionAnswerMaster.question[randomNumbers[indexOfQuestions]]);
                ansA.setText(QuestionAnswerMaster.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
                ansB.setText(QuestionAnswerMaster.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
                ansC.setText(QuestionAnswerMaster.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
                ansD.setText(QuestionAnswerMaster.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
                iv.setImageResource(QuestionAnswerMaster.images[randomNumbers[indexOfQuestions]]);
            }
            else if(multiPlayerHelper==7){
                questionTextView.setText(QuestionAnswerLegendary.question[randomNumbers[indexOfQuestions]]);
                ansA.setText(QuestionAnswerLegendary.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
                ansB.setText(QuestionAnswerLegendary.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
                ansC.setText(QuestionAnswerLegendary.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
                ansD.setText(QuestionAnswerLegendary.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
                iv.setImageResource(QuestionAnswerLegendary.images[randomNumbers[indexOfQuestions]]);
            }
            else if(multiPlayerHelper==8){
                questionTextView.setText(QuestionAnswerDivine.question[randomNumbers[indexOfQuestions]]);
                ansA.setText(QuestionAnswerDivine.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
                ansB.setText(QuestionAnswerDivine.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
                ansC.setText(QuestionAnswerDivine.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
                ansD.setText(QuestionAnswerDivine.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
                iv.setImageResource(QuestionAnswerDivine.images[randomNumbers[indexOfQuestions]]);

            }
            else if(multiPlayerHelper==9){
                questionTextView.setText(QuestionAnswerMasterYoda.question[randomNumbers[indexOfQuestions]]);
                ansA.setText(QuestionAnswerMasterYoda.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[0]]);
                ansB.setText(QuestionAnswerMasterYoda.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[1]]);
                ansC.setText(QuestionAnswerMasterYoda.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[2]]);
                ansD.setText(QuestionAnswerMasterYoda.choices[randomNumbers[indexOfQuestions]][randomNumbersQuestions[3]]);
                iv.setImageResource(QuestionAnswerMasterYoda.images[randomNumbers[indexOfQuestions]]);
            }




            multiPlayerHelper++;
        }










    }





    void finishQuiz() {

        countDownTimer.cancel();
        if (countDownTimer!=null){
            countDownTimer=null;
        }
        countDownTimer = new MyCountDownTimer(10000 /* 10 Sec */, 1000);
        prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);

        int oldScoreNovice = prefs.getInt("scoreNovice", 0); //0 is the default value
        int oldScoreLearner = prefs.getInt("scoreLearner", 0); //0 is the default value
        int oldScoreApprentice = prefs.getInt("scoreApprentice", 0); //0 is the default value
        int oldScoreCompetent = prefs.getInt("scoreCompetent", 0); //0 is the default value
        int oldScoreChampion = prefs.getInt("scoreChampion", 0); //0 is the default value
        int oldScoreExpert = prefs.getInt("scoreExpert", 0); //0 is the default value
        int oldScoreMaster = prefs.getInt("scoreMaster", 0); //0 is the default value
        int oldScoreLegendary = prefs.getInt("scoreLegendary", 0); //0 is the default value
        int oldScoreDivine = prefs.getInt("scoreDivine", 0); //0 is the default value
        int oldScoreMasterYoda = prefs.getInt("scoreMasterYoda", 0); //0 is the default value
        int oldScoreBabyYoda = prefs.getInt("scoreBabyYoda", 0); //0 is the default value

        if (scoreNovice >oldScoreNovice){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("scoreNovice", scoreNovice);
            editor.apply();
        }
        if (scoreLearner >oldScoreLearner){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("scoreLearner", scoreLearner);
            editor.apply();
        }
        if (scoreApprentice >oldScoreApprentice){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("scoreApprentice", scoreApprentice);
            editor.apply();
        }
        if (scoreCompetent >oldScoreCompetent){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("scoreCompetent", scoreCompetent);
            editor.apply();
        }
        if (scoreChampion >oldScoreChampion){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("scoreChampion", scoreChampion);
            editor.apply();
        }
        if (scoreExpert >oldScoreExpert){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("scoreExpert", scoreExpert);
            editor.apply();
        }
        if (scoreMaster >oldScoreMaster){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("scoreMaster", scoreMaster);
            editor.apply();
        }
        if (scoreLegendary >oldScoreLegendary){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("scoreLegendary", scoreLegendary);
            editor.apply();
        }
        if (scoreDivine >oldScoreDivine){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("scoreDivine", scoreDivine);
            editor.apply();
        }
        if (scoreMasterYoda >oldScoreMasterYoda){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("scoreMasterYoda", scoreMasterYoda);
            editor.apply();
        }
        if (scoreBabyYoda >oldScoreBabyYoda){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("scoreBabyYoda", scoreBabyYoda);
            editor.apply();
        }


        if(mp3!=null){
            mp3.release();
        }
        if(mp2!=null){
            mp2.release();
        }
        indexOfQuestions = 0;
        shuffleArray(randomNumbers);
        shuffleArray(randomNumbersBabyYoda);
        shuffleArray(randomNumbersQuestions);
        countDownTimer.cancel();


        if(Menu_Game.WhichGame==12){
            user= FirebaseAuth.getInstance().getCurrentUser();
            if (user != null){
                String userId = user.getUid();//check if its null!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Test").child("users").child("Score").child(userId);
                myRef.setValue(scoreMultiPlayer);

                startActivity(new Intent(Game.this, ResultsMultiPlayer.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
            else{
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

            }


        }
       else{
            saveScore();
            if(Game.scoreNovice > Menu_Game.totalQuestions * 0.59 || Game.scoreLearner > Menu_Game.totalQuestions * 0.59 || Game.scoreApprentice > Menu_Game.totalQuestions * 0.59|| Game.scoreCompetent > Menu_Game.totalQuestions * 0.59 || Game.scoreChampion > Menu_Game.totalQuestions * 0.59 || Game.scoreExpert > Menu_Game.totalQuestions * 0.59 || Game.scoreMaster > Menu_Game.totalQuestions * 0.59 || Game.scoreLegendary > Menu_Game.totalQuestions * 0.59 || Game.scoreDivine > Menu_Game.totalQuestions * 0.59 || Game.scoreMasterYoda > Menu_Game.totalQuestions * 0.59 || Game.scoreBabyYoda > Menu_Game.totalQuestions * 0.59){
                startActivity(new Intent(Game.this, Results.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
            else{
                startActivity(new Intent(Game.this, Ads.class));

            }

        }





    }

    void saveScore( ){

        //getting preferences
        if(Menu_Game.bestScoreNovice!=null && Menu_Game.bestScoreLearner!=null&& Menu_Game.bestScoreApprentice!=null&& Menu_Game.bestScoreCompetent!=null&& Menu_Game.bestScoreChampion!=null&& Menu_Game.bestScoreExpert!=null&& Menu_Game.bestScoreMaster!=null&& Menu_Game.bestScoreLegendary!=null&& Menu_Game.bestScoreDivine!=null&& Menu_Game.bestScoreMasterYoda!=null&& Menu_Game.bestScoreBabyYoda!=null){
            SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            int scoreNewNovice = prefs.getInt("scoreNovice", 0); //0 is the default value
            Menu_Game.bestScoreNovice.setText(String.format(getString(R.string.Best_Score)+"%s", scoreNewNovice));

            int scoreNewLearner = prefs.getInt("scoreLearner", 0); //0 is the default value
            Menu_Game.bestScoreLearner.setText(String.format(getString(R.string.Best_Score)+"%s", scoreNewLearner));

            int scoreNewApprentice = prefs.getInt("scoreApprentice", 0); //0 is the default value
            Menu_Game.bestScoreApprentice.setText(String.format(getString(R.string.Best_Score)+"%s", scoreNewApprentice));

            int scoreNewCompetent = prefs.getInt("scoreCompetent", 0); //0 is the default value
            Menu_Game.bestScoreCompetent.setText(String.format(getString(R.string.Best_Score)+"%s", scoreNewCompetent));

            int scoreNewChampion = prefs.getInt("scoreChampion", 0); //0 is the default value
            Menu_Game.bestScoreChampion.setText(String.format(getString(R.string.Best_Score)+"%s", scoreNewChampion));

            int scoreNewExpert = prefs.getInt("scoreExpert", 0); //0 is the default value
            Menu_Game.bestScoreExpert.setText(String.format(getString(R.string.Best_Score)+"%s", scoreNewExpert));

            int scoreNewMaster = prefs.getInt("scoreMaster", 0); //0 is the default value
            Menu_Game.bestScoreMaster.setText(String.format(getString(R.string.Best_Score)+"%s", scoreNewMaster));

            int scoreNewLegendary = prefs.getInt("scoreLegendary", 0); //0 is the default value
            Menu_Game.bestScoreLegendary.setText(String.format(getString(R.string.Best_Score)+"%s", scoreNewLegendary));

            int scoreNewDivine = prefs.getInt("scoreDivine", 0); //0 is the default value
            Menu_Game.bestScoreDivine.setText(String.format(getString(R.string.Best_Score)+"%s", scoreNewDivine));

            int scoreNewMasterYoda = prefs.getInt("scoreMasterYoda", 0); //0 is the default value
            Menu_Game.bestScoreMasterYoda.setText(String.format(getString(R.string.Best_Score)+"%s", scoreNewMasterYoda));

            int scoreNewBabyYoda = prefs.getInt("scoreBabyYoda", 0); //0 is the default value
            Menu_Game.bestScoreBabyYoda.setText(String.format(getString(R.string.Best_Score)+"%s", scoreNewBabyYoda));

        }


    }



    void removeUser(){
        if(user != null){
            String userId = user.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef3 = database.getReference("Test").child("usersCodes").child(userId);
            myRef3.getRef().removeValue();
        }

    }
    public void onDestroy() {
        removeUser();
        super.onDestroy();

    }

}



