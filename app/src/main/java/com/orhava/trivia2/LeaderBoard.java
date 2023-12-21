package com.orhava.trivia2;

import static com.orhava.trivia2.MainMenu.flag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class LeaderBoard extends AppCompatActivity  {
    private FirebaseUser user;
    ListView listView;
    ImageButton navToMainMenu2;
    TextView ThirdPlace, FirstPlace,SecondPlace;
    ImageView ImgCountry_top_3_First_Place,ImgCountry_top_3_Second_Place,ImgCountry_top_3_Third_Place, Personal_ImgCountry;
    TextView FirstPlaceScore, SecondPlaceScore, ThirdPlaceScore;
    TextView Personal_place_textView,Personal_username_textView, Personal_score_textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        Objects.requireNonNull(getSupportActionBar()).hide();
        user= FirebaseAuth.getInstance().getCurrentUser();
        listView= findViewById(R.id.ReadUsers);
        navToMainMenu2= findViewById(R.id.navToMainMenu2);
        ThirdPlace= findViewById(R.id.ThirdPlace);
        FirstPlace= findViewById(R.id.FirstPlace);
        SecondPlace= findViewById(R.id.SecondPlace);


        ImgCountry_top_3_First_Place= findViewById(R.id.ImgCountry_top_3_First_Place);
        ImgCountry_top_3_Second_Place= findViewById(R.id.ImgCountry_top_3_Second_Place);
        ImgCountry_top_3_Third_Place= findViewById(R.id.ImgCountry_top_3_Third_Place);
        Personal_ImgCountry= findViewById(R.id.Personal_ImgCountry);
        FirstPlaceScore= findViewById(R.id.FirstPlaceScore);
        ThirdPlaceScore= findViewById(R.id.ThirdPlaceScore);
        SecondPlaceScore= findViewById(R.id.SecondPlaceScore);
        Personal_place_textView= findViewById(R.id.Personal_place_textView);
        Personal_username_textView= findViewById(R.id.Personal_username_textView);
        Personal_score_textView= findViewById(R.id.Personal_score_textView);


        getAllCodes();
        StartButtons();

    }





    private void censorUsername(Score score) {
        HashSet<String> badWords = new HashSet<>(Arrays.asList("fuck","Dick","rape","weed", "cocaine"));
        StringBuilder censoredUsername = new StringBuilder(score.getUsername());
        for (int i = 0; i < censoredUsername.length(); i++) {
            for (String badWord : badWords) {
                if (i+ badWord.length() <= censoredUsername.length() && censoredUsername.substring(i, i + badWord.length()).equalsIgnoreCase(badWord))
                {
                    censoredUsername.replace(i, i + badWord.length(), "*****");
                    break;
                }
            }
        }
        score.setUsername(censoredUsername.toString());
    }

    void getAllCodes(){

        List<Integer> scores = new ArrayList<>();
        int personalScore = amountOfGeneralPoints();

        if (user != null){


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef3 = database.getReference("Scores");
            Query myQuery = myRef3.orderByChild("score");
            String userId = user.getUid();
            myQuery.addValueEventListener(new ValueEventListener() {

                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ArrayList<Score> userlist = new ArrayList<>();
                    scores.clear();

                    for (DataSnapshot dsp : dataSnapshot.getChildren() ) {
                        Score score = dsp.getValue(Score.class);
                        if (score != null) {
                            scores.add(score.getScore());
                        }
                        if (score != null) {
                            censorUsername(score);
                            userlist.add(score);
                            if(Objects.equals(dsp.getKey(), userId)){
                                Personal_username_textView.setText(score.getUsername());
                                Personal_score_textView.setText(String.valueOf(score.getScore()));
                                setPicture(Personal_ImgCountry,score.getIndex_pic());

                            }

                        }



                    }
                    Collections.sort(scores, Collections.reverseOrder());
                    int personalPlace = scores.indexOf(personalScore) + 1;
                    Personal_place_textView.setText(String.valueOf(personalPlace));

                    // Sort the ArrayList by score in descending order
                    Collections.sort(userlist, (o1, o2) -> o2.getScore() - o1.getScore());

                    // Add the place of each person
                    for (int i = 0; i < userlist.size(); i++) {
                        userlist.get(i).setPlace(i + 1);
                    }

                    ArrayAdapter<Score> adapter = new ArrayAdapter<Score>(LeaderBoard.this, R.layout.row_layout, R.id.place_textView, userlist) {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);
                            TextView placeTextView = view.findViewById(R.id.place_textView);
                            TextView usernameTextView = view.findViewById(R.id.username_textView);
                            TextView scoreTextView = view.findViewById(R.id.score_textView);
                            ImageView ImgCountry = view.findViewById(R.id.ImgCountry);
                            Score score = userlist.get(position);
                            if(score.getPlace()==1){
                                FirstPlace.setText(score.getUsername());
                                FirstPlaceScore.setText(String.valueOf(score.getScore()));
                                setPicture(ImgCountry_top_3_First_Place,score.getIndex_pic());

                            }
                            else if(score.getPlace()==2){
                                SecondPlace.setText(score.getUsername());
                                SecondPlaceScore.setText(String.valueOf(score.getScore()));
                                setPicture(ImgCountry_top_3_Second_Place,score.getIndex_pic());
                            }
                            else if(score.getPlace()==3){
                                ThirdPlace.setText(score.getUsername());
                                ThirdPlaceScore.setText(String.valueOf(score.getScore()));
                                setPicture(ImgCountry_top_3_Third_Place,score.getIndex_pic());
                            }
                            placeTextView.setText(String.valueOf(score.getPlace()));
                            placeTextView.setTextColor(Color.BLACK);
                            usernameTextView.setText(score.getUsername());
                            setPicture(ImgCountry,score.getIndex_pic());
                            usernameTextView.setTextColor(Color.BLACK);
                            scoreTextView.setText(String.valueOf(score.getScore()));
                            return view;
                        }
                    };

                    listView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {
                    // Failed to read value
                }
            });


        }

}
    void StartButtons(){

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.modernclick);
        navToMainMenu2.setOnClickListener(view -> {

            if (!flag){
                mp.setVolume(0,0);
            }
            else{
                mp.setVolume(0,1);
            }

            mp.start();

            startActivity(new Intent(LeaderBoard.this, MainMenu.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
        });}

    int amountOfGeneralPoints(){
        int points;


        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        int scoreNewNovice = prefs.getInt("scoreNovice", 0); //0 is the default value
        int scoreNewLearner = prefs.getInt("scoreLearner", 0); //0 is the default value
        int scoreNewApprentice = prefs.getInt("scoreApprentice", 0); //0 is the default value
        int scoreNewCompetent = prefs.getInt("scoreCompetent", 0); //0 is the default value
        int scoreNewChampion = prefs.getInt("scoreChampion", 0); //0 is the default value
        int scoreNewExpert = prefs.getInt("scoreExpert", 0); //0 is the default value
        int scoreNewMaster = prefs.getInt("scoreMaster", 0); //0 is the default value
        int scoreNewLegendary = prefs.getInt("scoreLegendary", 0); //0 is the default value
        int scoreNewDivine = prefs.getInt("scoreDivine", 0); //0 is the default value
        int scoreNewMasterYoda = prefs.getInt("scoreMasterYoda", 0); //0 is the default value
        int scoreNewBabyYoda = prefs.getInt("scoreBabyYoda", 0); //0 is the default value
        int scoreNewDeathMarch = prefs.getInt("scoreDeathMarch", 0); //0 is the default value
        int scoreNewStepOnLego = prefs.getInt("scoreStepOnLego", 0); //0 is the default value
        points= scoreNewDeathMarch +scoreNewStepOnLego + scoreNewNovice+scoreNewLearner+scoreNewApprentice+scoreNewCompetent+scoreNewChampion+scoreNewExpert+scoreNewMaster+scoreNewLegendary+scoreNewDivine+scoreNewMasterYoda+scoreNewBabyYoda;
        return points;
    }

    void setPicture(ImageView image, int choice){

        if(choice==1){
            image.setImageResource(R.mipmap.avater1_foreground);
        }
        else if(choice==2){
            image.setImageResource(R.mipmap.ic_avatar3_foreground);
        }
        else if(choice==3){
            image.setImageResource(R.mipmap.ic_avatar4_foreground);
        }

        else if(choice==4){
            image.setImageResource(R.mipmap.ic_avatar6_foreground);
        }

        else if(choice==5){
            image.setImageResource(R.mipmap.ic_avatar7_foreground);
        }

        else if(choice==6){
            image.setImageResource(R.mipmap.ic_avatar8_foreground);
        }

        else if(choice==7){
            image.setImageResource(R.mipmap.ic_avatar10_foreground);
        }

        else if(choice==8){
            image.setImageResource(R.mipmap.ic_avatar11_foreground);
        }

        else if(choice==9){
            image.setImageResource(R.mipmap.ic_avatar12_foreground);
        }

        else if(choice==10){
            image.setImageResource(R.mipmap.ic_avatar9_foreground);
        }

        else if(choice==11){
            image.setImageResource(R.mipmap.ic_avatar13_foreground);
        }
        else if(choice==12){
            image.setImageResource(R.mipmap.ic_avatar14_foreground);
        }
        else if(choice==13 ){
            image.setImageResource(R.mipmap.ic_avatar15_foreground);
        }

        else if(choice==14){
            image.setImageResource(R.mipmap.ic_avatar16_foreground);
        }
        else if(choice==15 ){
            image.setImageResource(R.mipmap.ic_avatar17_foreground);
        }
        else if(choice==16 ){
            image.setImageResource(R.mipmap.avaterprem1_foreground);
        }

        else if(choice==17){
            image.setImageResource(R.mipmap.avaterprem3_foreground);
        }
        else if(choice==18 ){
            image.setImageResource(R.mipmap.avaterprem2_foreground);
        }
        else if(choice==19){
            image.setImageResource(R.mipmap.avatersecret_foreground);
        }
        else{
            image.setImageResource(R.mipmap.ic_emptyavatar_foreground);
        }

    }

}