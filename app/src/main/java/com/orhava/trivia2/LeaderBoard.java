package com.orhava.trivia2;

import static com.orhava.trivia2.MainMenu.flag;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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
import java.util.Objects;

public class LeaderBoard extends AppCompatActivity  {
    private FirebaseUser user;
    ListView listView;
    ImageButton navToMainMenu2;
    TextView ThirdPlace, FirstPlace,SecondPlace;
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
        getAllCodes();
        StartButtons();
    }


    private void censorUsername(Score score) {
        HashSet<String> badWords = new HashSet<>(Arrays.asList("Fuck", "fuck"));
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

        if (user != null){


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef3 = database.getReference("Scores");
            Query myQuery = myRef3.orderByChild("score").limitToFirst(100);
            myQuery.addValueEventListener(new ValueEventListener() {

                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ArrayList<Score> userlist = new ArrayList<>();

                    for (DataSnapshot dsp : dataSnapshot.getChildren() ) {
                        Score score = dsp.getValue(Score.class);
                        if (score != null) {
                            censorUsername(score);
                            userlist.add(score);
                        }


                    }

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
                            Score score = userlist.get(position);
                            if(score.getPlace()==1){
                                FirstPlace.setText(score.getUsername());
                            }
                            else if(score.getPlace()==2){
                                SecondPlace.setText(score.getUsername());
                            }
                            else if(score.getPlace()==3){
                                ThirdPlace.setText(score.getUsername());
                            }
                            placeTextView.setText(String.valueOf(score.getPlace()));
                            placeTextView.setTextColor(Color.BLACK);
                            usernameTextView.setText(score.getUsername());
                            usernameTextView.setTextColor(Color.BLACK);
                            scoreTextView.setText(String.valueOf(score.getScore()));
//                            scoreTextView.setTextColor(Color.BLACK);
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

}