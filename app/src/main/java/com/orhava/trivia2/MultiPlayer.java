package com.orhava.trivia2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MultiPlayer extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    private FirebaseUser user;
    private ImageButton navToMain;
    private TextView amountOfUsers;
    EditText EnterCodeEdit;
    Button btnEnterCode;
    private String code="";
    private LottieAnimationView searchAnim;
    public static boolean isItMultiPlayer;
    public static  String opponentUser="";
    public static String codeHelper="";
    public boolean connected=false;
    public int times=0,times2=0;
    MyRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);
        Objects.requireNonNull(getSupportActionBar()).hide();
        user= FirebaseAuth.getInstance().getCurrentUser();
        isItMultiPlayer=true;
        amountOfUsers=findViewById(R.id.amountOfUsers);
        EnterCodeEdit=findViewById(R.id.EnterCodeEdit);
        btnEnterCode=findViewById(R.id.btnEnterCode);
        searchAnim = findViewById(R.id.searchAnim);
        navToMain=findViewById(R.id.navToMain);
        times=0;
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference presenceRef = FirebaseDatabase.getInstance().getReference("Test").child("usersCodes").child(userId);
            presenceRef.onDisconnect().removeValue();
        }
        if (user != null) {
            String userId = user.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            user = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference myRef1 = database.getReference("Test").child("users").child("Score").child("GameEnd").child(userId);
            myRef1.setValue("false");
            removeUser();

        }


        searchAnim.setVisibility(View.GONE);
        EnterCode();
        getAllCodes();
        UsersOnline();
        SaveNameAndAvatar();
        configureNextButton();












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

        new AlertDialog.Builder(this)
                .setMessage("Do you want to exit Game?")
                .setPositiveButton("Yes", (arg0, arg1) -> {
                    removeUser();
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    startActivity(new Intent(MultiPlayer.this, MainMenu.class));


                })
                .setNegativeButton("No", (arg0, arg1) -> {
                })
                .show();

    }

    private void configureNextButton() {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.modernclick);

        navToMain.setOnClickListener(view -> {


            if (!MainMenu.flag) {
                mp.setVolume(0, 0);
            } else {
                mp.setVolume(0, 1);
            }

            mp.start();

            removeUser();
            startActivity(new Intent(MultiPlayer.this, MainMenu.class));

            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

        });

    }

    void removeUser(){
        String userId = user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database.getReference("Test").child("usersCodes").child(userId);
        myRef3.getRef().removeValue();


        code="";

    }


    void SaveNameAndAvatar(){

        if (user != null){
            SharedPreferences prefs2 = PreferenceManager
                    .getDefaultSharedPreferences(this);
            SharedPreferences prefs3;
            prefs3 = MultiPlayer.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
            int oldAvatarChoice = prefs3.getInt("AvatarChoice", 0); //0 is the default value
            String userId = user.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            user= FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference myRef2 = database.getReference("Test").child("users").child("Score").child("Name").child(userId);
            myRef2.setValue(prefs2.getString("autoSave", ""));
            DatabaseReference myRef3 = database.getReference("Test").child("users").child("Score").child("Avatar").child(userId);
            myRef3.setValue(oldAvatarChoice);
        }

    }
    void MatchCodes(){
        if (user != null){
            String userId = user.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            user= FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference myRef3 = database.getReference("Test");
            DatabaseReference myRef2 = database.getReference("Test").child("users").child("Score").child("GameEnd").child(userId);
            myRef2.setValue("false");



            myRef3.addValueEventListener(new ValueEventListener() {

                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot dsp : dataSnapshot.child("usersCodes").getChildren()) {

                         if(String.valueOf(dsp.getValue()).equals(code) && !Objects.equals(dsp.getKey(), userId) && times==0){
                             codeHelper=code;
                             opponentUser=dsp.getKey();
                             configureNextButtonHelperSound();
                             Menu_Game.totalQuestions=10;
                             Menu_Game.WhichGame=12;
                             startActivity(new Intent(MultiPlayer.this, Game.class));
                             overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
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

    void  EnterCode(){
        EnterCodeEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                btnEnterCode.setOnClickListener(view -> {
                    EnterCodeEdit.clearFocus();
                    InputMethodManager imm = (InputMethodManager) view.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    code= EnterCodeEdit.getText().toString();
                    searchAnim.setVisibility(View.VISIBLE);
                    searchAnim.playAnimation();
                    codeHelper(code);
                    MatchCodes();
                });
            }
        });


    }

    void UsersOnline(){

        if (user != null){
            String userId = user.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Test").child("users").child("Score").child(userId);
            myRef.setValue(0);
            DatabaseReference myRef2 = database.getReference("Test").child("usersCodes");


            myRef2.addValueEventListener(new ValueEventListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                    // int hello=  Integer.parseInt(String.valueOf(dataSnapshot.getValue()));
                    int numberOfUsers = (int) dataSnapshot.getChildrenCount();
                    amountOfUsers.setText(getString(R.string.number_of_online_users)+" "+numberOfUsers);


                }

                @Override
                public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {
                    // Failed to read value

                }
            });

        }
    }




    void getAllCodes(){

        if (user != null){


            DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
            connectedRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                     connected = Boolean.TRUE.equals(snapshot.getValue(Boolean.class));
                     if(!connected && times2==0){
                         String userId = user.getUid();
                         FirebaseDatabase database = FirebaseDatabase.getInstance();
                         DatabaseReference myRef3 = database.getReference("Test").child("usersCodes").child(userId);
                         myRef3.getRef().removeValue();
                         times2++;
                     }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });








            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef3 = database.getReference("Test");
            myRef3.addValueEventListener(new ValueEventListener() {

                @SuppressLint("SetTextI18n")
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ArrayList<String> userlist = new ArrayList<>();

                    for (DataSnapshot dsp : dataSnapshot.child("usersCodes").getChildren() ) {
                            userlist.add(String.valueOf(dsp.getValue())); //add result into array list
                    }
//
                    // data to populate the RecyclerView with

                    // set up the RecyclerView
                    RecyclerView recyclerView = findViewById(R.id.ReadUsers);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MultiPlayer.this));
                    adapter = new MyRecyclerViewAdapter(MultiPlayer.this, userlist);
                    adapter.setClickListener(MultiPlayer.this);
                    recyclerView.setAdapter(adapter);

                }

                @Override
                public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {
                    // Failed to read value

                }
            });


        }

    }


    @Override
    public void onItemClick(View view, int position) {
      //  Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, R.string.To_Start_The_Game_Write_The_Same_Code, Toast.LENGTH_SHORT).show();

    }


    void codeHelper(String code){
        if (user != null){
            String userId = user.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Test").child("usersCodes").child(userId);
            myRef.setValue(code);



        }
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

    public void onDestroy() {
        removeUser();
        super.onDestroy();

    }

}