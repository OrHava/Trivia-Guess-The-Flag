package com.orhava.trivia2;

import static com.orhava.trivia2.BuyPremiumAvatars.avatarPremium14;
import static com.orhava.trivia2.BuyPremiumAvatars.avatarPremium15;
import static com.orhava.trivia2.BuyPremiumAvatars.avatarPremium16;
import static com.orhava.trivia2.BuyPremiumAvatars.avatarPremium17;
import static com.orhava.trivia2.BuyPremiumAvatars.avatarPremium18;
import static com.orhava.trivia2.BuyPremiumAvatars.avatarPremium19;
import static com.orhava.trivia2.MainMenu.flag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.Objects;

public class Avatar_Name extends AppCompatActivity {

    private ImageButton navToMainMenu2;
    private   ImageButton btnPen,avatar3,avatar2,avatar4,avatar5,avatar6,avatar7,avatar8,avatar9,avatar10,avatar11,avatar12,avatar13,avatar14,avatar15,avatar16,avatar17,avatar18,avatar19;
    private ImageView profilePic;
    private  EditText txtName;
    private TextView txtPoints,txtAvatar14,txtAvatar15,txtAvatar16,txtAvatar17,txtAvatar18,txtAvatar19;
    public static int avatarPremiumChoice=0;
    private SharedPreferences prefs3;
    public static String UserNameForDataBase= null;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_name);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initialize();
        setImages();
        goToMain();
        userName();
        buyAvatar();



    }

    @SuppressLint("SetTextI18n")
    void setImages(){

        SharedPreferences prefs4 = Avatar_Name.this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
        int PremiumPoints1 = prefs4.getInt("PremiumPoints1", 0); //0 is the default value
        if(PremiumPoints1==1){
            txtAvatar14.setText("Avatar is Usable");
            txtAvatar14.setTextSize(6);
        }

        int PremiumPoints2 = prefs4.getInt("PremiumPoints2", 0); //0 is the default value
        if(PremiumPoints2==1){
            txtAvatar15.setText("Avatar is Usable");
            txtAvatar15.setTextSize(6);
        }
        int PremiumPoints3 = prefs4.getInt("PremiumPoints3", 0); //0 is the default value
        if(PremiumPoints3==1){
            txtAvatar16.setText("Avatar is Usable");
            txtAvatar16.setTextSize(6);
        }
        int PremiumPoints4 = prefs4.getInt("PremiumPoints4", 0); //0 is the default value
        if(PremiumPoints4==1){
            txtAvatar17.setText("Avatar is Usable");
            txtAvatar17.setTextSize(6);
        }

        int PremiumPoints5 = prefs4.getInt("PremiumPoints5", 0); //0 is the default value
        if(PremiumPoints5==1){
            txtAvatar18.setText("Avatar is Usable");
            txtAvatar18.setTextSize(6);
        }
        int PremiumPoints6 = prefs4.getInt("PremiumPoints6", 0); //0 is the default value
        if(PremiumPoints6==1){
            txtAvatar19.setText("Avatar is Usable");
            txtAvatar19.setTextSize(6);
        }


        txtPoints.setText("Points: "+ amountOfGeneralPoints());
        prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
        int oldAvatarChoice = prefs3.getInt("AvatarChoice", 0); //0 is the default value
        if(oldAvatarChoice==1){
            profilePic.setImageResource(R.mipmap.avater1_foreground);
        }
        else if(oldAvatarChoice==2){
            profilePic.setImageResource(R.mipmap.ic_avatar3_foreground);
        }
        else if(oldAvatarChoice==3){
            profilePic.setImageResource(R.mipmap.ic_avatar4_foreground);
        }

        else if(oldAvatarChoice==4){
            profilePic.setImageResource(R.mipmap.ic_avatar6_foreground);
        }

        else if(oldAvatarChoice==5){
            profilePic.setImageResource(R.mipmap.ic_avatar7_foreground);
        }

        else if(oldAvatarChoice==6){
            profilePic.setImageResource(R.mipmap.ic_avatar8_foreground);
        }

        else if(oldAvatarChoice==7){
            profilePic.setImageResource(R.mipmap.ic_avatar10_foreground);
        }

        else if(oldAvatarChoice==8){
            profilePic.setImageResource(R.mipmap.ic_avatar11_foreground);
        }

        else if(oldAvatarChoice==9){
            profilePic.setImageResource(R.mipmap.ic_avatar12_foreground);
        }

        else if(oldAvatarChoice==10){
            profilePic.setImageResource(R.mipmap.ic_avatar9_foreground);
        }

        else if(oldAvatarChoice==11){
            profilePic.setImageResource(R.mipmap.ic_avatar13_foreground);
        }
        else if(oldAvatarChoice==12){
            profilePic.setImageResource(R.mipmap.ic_avatar14_foreground);
        }
        else if(oldAvatarChoice==13 && PremiumPoints1==1){
            profilePic.setImageResource(R.mipmap.ic_avatar15_foreground);
        }

        else if(oldAvatarChoice==14 && PremiumPoints2==1){
            profilePic.setImageResource(R.mipmap.ic_avatar16_foreground);
        }
        else if(oldAvatarChoice==15 && PremiumPoints3==1){
            profilePic.setImageResource(R.mipmap.ic_avatar17_foreground);
        }
        else if(oldAvatarChoice==16 && PremiumPoints4==1){
            profilePic.setImageResource(R.mipmap.avaterprem1_foreground);
        }

        else if(oldAvatarChoice==17 && PremiumPoints5==1){
            profilePic.setImageResource(R.mipmap.avaterprem3_foreground);
        }
        else if(oldAvatarChoice==18 && PremiumPoints6==1){
            profilePic.setImageResource(R.mipmap.avaterprem2_foreground);
        }
        else if(oldAvatarChoice==19){
            profilePic.setImageResource(R.mipmap.avatersecret_foreground);
        }
        else{
            profilePic.setImageResource(R.mipmap.ic_emptyavatar_foreground);
        }

    }

    void initialize(){

        navToMainMenu2=findViewById(R.id.navToMainMenu2);
        profilePic=findViewById(R.id.profilePic);
        txtAvatar14=findViewById(R.id.txtAvatar14);
        txtAvatar15=findViewById(R.id.txtAvatar15);
        txtAvatar16=findViewById(R.id.txtAvatar16);
        txtAvatar17=findViewById(R.id.txtAvatar17);
        txtAvatar18=findViewById(R.id.txtAvatar18);
        txtAvatar19=findViewById(R.id.txtAvatar19);
        avatar3=findViewById(R.id.avatar3);
        avatar2=findViewById(R.id.avatar2);
        avatar4=findViewById(R.id.avatar4);
        avatar5=findViewById(R.id.avatar5);
        avatar6=findViewById(R.id.avatar6);
        avatar7=findViewById(R.id.avatar7);
        avatar8=findViewById(R.id.avatar8);
        avatar9=findViewById(R.id.avatar9);
        avatar10=findViewById(R.id.avatar10);
        avatar11=findViewById(R.id.avatar11);
        avatar12=findViewById(R.id.avatar12);
        avatar13=findViewById(R.id.avatar13);
        avatar14=findViewById(R.id.avatar14);
        avatar15=findViewById(R.id.avatar15);
        avatar16=findViewById(R.id.avatar16);
        avatar17=findViewById(R.id.avatar17);
        avatar18=findViewById(R.id.avatar18);
        avatar19=findViewById(R.id.avatar19);
        btnPen=findViewById(R.id.btnPen);
        txtName=findViewById(R.id.txtName); //maybe can make a problem
        txtPoints=findViewById(R.id.txtPoints);
    }
    void buyAvatar(){

        avatar3.setOnClickListener(view -> {
            if (amountOfGeneralPoints() >= 10) {
                prefs3=Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 1);
                editor.apply();
                profilePic.setImageResource(R.mipmap.avater1_foreground);
            }

            else {
                Toast.makeText(Avatar_Name.this, "You Don't have Enough Points", Toast.LENGTH_SHORT).show();
            }
        });

        avatar2.setOnClickListener(view -> {
            if (amountOfGeneralPoints()>=20){

                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 2);
                editor.apply();
                profilePic.setImageResource(R.mipmap.ic_avatar3_foreground);
            }
            else{
                Toast.makeText(Avatar_Name.this, "You Don't have Enough Points", Toast.LENGTH_SHORT).show();
            }
        });

        avatar4.setOnClickListener(view -> {
            if (amountOfGeneralPoints() >= 30) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 3);
                editor.apply();
                profilePic.setImageResource(R.mipmap.ic_avatar4_foreground);

            }
            else{
                Toast.makeText(Avatar_Name.this, "You Don't have Enough Points", Toast.LENGTH_SHORT).show();
            }
        });
        avatar6.setOnClickListener(view -> {
            if (amountOfGeneralPoints() >= 40) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 4);
                editor.apply();
                profilePic.setImageResource(R.mipmap.ic_avatar6_foreground);

            }
            else{
                Toast.makeText(Avatar_Name.this, "You Don't have Enough Points", Toast.LENGTH_SHORT).show();
            }
        });
        avatar5.setOnClickListener(view -> {

            if (amountOfGeneralPoints() >= 50) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 5);
                editor.apply();
                profilePic.setImageResource(R.mipmap.ic_avatar7_foreground);

            }
            else{
                Toast.makeText(Avatar_Name.this, "You Don't have Enough Points", Toast.LENGTH_SHORT).show();
            }
        });
        avatar7.setOnClickListener(view -> {
            if (amountOfGeneralPoints() >= 60) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 6);
                editor.apply();
                profilePic.setImageResource(R.mipmap.ic_avatar8_foreground);

            }
            else{
                Toast.makeText(Avatar_Name.this, "You Don't have Enough Points", Toast.LENGTH_SHORT).show();
            }
        });
        avatar9.setOnClickListener(view -> {
            if (amountOfGeneralPoints() >= 70) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 7);
                editor.apply();
                profilePic.setImageResource(R.mipmap.ic_avatar10_foreground);

            }
            else{
                Toast.makeText(Avatar_Name.this, "You Don't have Enough Points", Toast.LENGTH_SHORT).show();
            }
        });
        avatar8.setOnClickListener(view -> {
            if (amountOfGeneralPoints() >= 80) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 8);
                editor.apply();
                profilePic.setImageResource(R.mipmap.ic_avatar11_foreground);

            }
            else{
                Toast.makeText(Avatar_Name.this, "You Don't have Enough Points", Toast.LENGTH_SHORT).show();
            }
        });
        avatar10.setOnClickListener(view -> {
            if (amountOfGeneralPoints() >= 90) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 9);
                editor.apply();
                profilePic.setImageResource(R.mipmap.ic_avatar12_foreground);

            }
            else{
                Toast.makeText(Avatar_Name.this, "You Don't have Enough Points", Toast.LENGTH_SHORT).show();
            }
        });
        avatar12.setOnClickListener(view -> {
            if (amountOfGeneralPoints() >= 100) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 10);
                editor.apply();
                profilePic.setImageResource(R.mipmap.ic_avatar9_foreground);

            }
            else{
                Toast.makeText(Avatar_Name.this, "You Don't have Enough Points", Toast.LENGTH_SHORT).show();
            }
        });
        avatar11.setOnClickListener(view -> {
            if (amountOfGeneralPoints() >= 110) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 11);
                editor.apply();
                profilePic.setImageResource(R.mipmap.ic_avatar13_foreground);

            }
            else{
                Toast.makeText(Avatar_Name.this, "You Don't have Enough Points", Toast.LENGTH_SHORT).show();
            }
        });
        avatar13.setOnClickListener(view -> {
            if (amountOfGeneralPoints()>=120){

                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 12);
                editor.apply();
                profilePic.setImageResource(R.mipmap.ic_avatar3_foreground);
            }
            else{
                Toast.makeText(Avatar_Name.this, "You Don't have Enough Points", Toast.LENGTH_SHORT).show();
            }
        });
        avatar15.setOnClickListener(view -> {
            avatarPremiumChoice=1;
            SharedPreferences prefs4 = Avatar_Name.this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
            int PremiumPoints1 = prefs4.getInt("PremiumPoints1", 0); //0 is the default value
            if (PremiumPoints1==0){
            startActivity(new Intent(Avatar_Name.this, BuyPremiumAvatars.class));
            if(avatarPremium14){
                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 13);
                editor.apply();
                profilePic.setImageResource(R.mipmap.ic_avatar15_foreground);
            }
            }
            else {
                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 13);
                editor.apply();
                profilePic.setImageResource(R.mipmap.ic_avatar15_foreground);


            }

        });
        avatar14.setOnClickListener(view -> {
            avatarPremiumChoice=2;

            SharedPreferences prefs4 = Avatar_Name.this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
            int PremiumPoints2 = prefs4.getInt("PremiumPoints2", 0); //0 is the default value
            if (PremiumPoints2==0){
                startActivity(new Intent(Avatar_Name.this, BuyPremiumAvatars.class));
                if(avatarPremium15){
                    SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs3.edit();
                    editor.putInt("AvatarChoice", 14);
                    editor.apply();
                    profilePic.setImageResource(R.mipmap.ic_avatar16_foreground);
                }
            }
            else {
                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 14);
                editor.apply();
                profilePic.setImageResource(R.mipmap.ic_avatar16_foreground);


            }
        });
        avatar16.setOnClickListener(view -> {
            avatarPremiumChoice=3;

            SharedPreferences prefs4 = Avatar_Name.this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
            int PremiumPoints3 = prefs4.getInt("PremiumPoints3", 0); //0 is the default value
            if (PremiumPoints3==0){
                startActivity(new Intent(Avatar_Name.this, BuyPremiumAvatars.class));
                if(avatarPremium16){
                    SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs3.edit();
                    editor.putInt("AvatarChoice", 15);
                    editor.apply();
                    profilePic.setImageResource(R.mipmap.ic_avatar17_foreground);
                }
            }
            else {
                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 15);
                editor.apply();
                profilePic.setImageResource(R.mipmap.ic_avatar17_foreground);


            }
        });

        avatar18.setOnClickListener(view -> {
            avatarPremiumChoice=4;
            SharedPreferences prefs4 = Avatar_Name.this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
            int PremiumPoints4 = prefs4.getInt("PremiumPoints4", 0); //0 is the default value
            if (PremiumPoints4==0){
                startActivity(new Intent(Avatar_Name.this, BuyPremiumAvatars.class));
                if(avatarPremium17){
                    SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs3.edit();
                    editor.putInt("AvatarChoice", 16);
                    editor.apply();
                    profilePic.setImageResource(R.mipmap.avaterprem1_foreground);
                }
            }
            else {
                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 16);
                editor.apply();
                profilePic.setImageResource(R.mipmap.avaterprem1_foreground);


            }

        });
        avatar17.setOnClickListener(view -> {
            avatarPremiumChoice=5;

            SharedPreferences prefs4 = Avatar_Name.this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
            int PremiumPoints5 = prefs4.getInt("PremiumPoints5", 0); //0 is the default value
            if (PremiumPoints5==0){
                startActivity(new Intent(Avatar_Name.this, BuyPremiumAvatars.class));
                if(avatarPremium18){
                    SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs3.edit();
                    editor.putInt("AvatarChoice", 17);
                    editor.apply();
                    profilePic.setImageResource(R.mipmap.avaterprem3_foreground);
                }
            }
            else {
                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 17);
                editor.apply();
                profilePic.setImageResource(R.mipmap.avaterprem3_foreground);


            }
        });
        avatar19.setOnClickListener(view -> {
            avatarPremiumChoice=6;

            SharedPreferences prefs4 = Avatar_Name.this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
            int PremiumPoints6 = prefs4.getInt("PremiumPoints6", 0); //0 is the default value
            if (PremiumPoints6==0){
                startActivity(new Intent(Avatar_Name.this, BuyPremiumAvatars.class));
                if(avatarPremium19){
                    SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs3.edit();
                    editor.putInt("AvatarChoice", 18);
                    editor.apply();
                    profilePic.setImageResource(R.mipmap.avaterprem2_foreground);
                }
            }
            else {
                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 18);
                editor.apply();
                profilePic.setImageResource(R.mipmap.avaterprem2_foreground);


            }
        });


    }

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
        points=scoreNewNovice+scoreNewLearner+scoreNewApprentice+scoreNewCompetent+scoreNewChampion+scoreNewExpert+scoreNewMaster+scoreNewLegendary+scoreNewDivine+scoreNewMasterYoda+scoreNewBabyYoda;
       return points;
    }

    void goToMain(){

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.modernclick);

        navToMainMenu2.setOnClickListener(view -> {

            if (!flag){
                mp.setVolume(0,0);
            }
            else{
                mp.setVolume(0,1);
            }

            mp.start();




            startActivity(new Intent(Avatar_Name.this, MainMenu.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);




        });


    }

    @SuppressLint("SetTextI18n")
    void userName(){
        final SharedPreferences prefs2 = PreferenceManager
                .getDefaultSharedPreferences(this);


        txtName.setText(prefs2.getString("autoSave", ""));

        txtName.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            @Override
            public void afterTextChanged(Editable s){
                prefs2.edit().putString("autoSave", s.toString()).apply();


                String UserName = prefs2.getString("autoSave", ""); //0 is the default value

                btnPen.setOnClickListener(view -> {
                    txtName.clearFocus();
                    InputMethodManager imm = (InputMethodManager) view.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    Toast.makeText(Avatar_Name.this, "Hello "+ UserName, Toast.LENGTH_SHORT).show(); //fix because it show the old
                    UserNameForDataBase= txtName.getText().toString();
                });
            }

        });


        String UserName = prefs2.getString("autoSave", ""); //0 is the default value

        btnPen.setOnClickListener(view -> {
            txtName.clearFocus();
            InputMethodManager imm = (InputMethodManager) view.getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            Toast.makeText(Avatar_Name.this, "Hello "+ UserName, Toast.LENGTH_SHORT).show();




        });



    }


}