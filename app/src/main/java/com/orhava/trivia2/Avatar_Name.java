package com.orhava.trivia2;

import static com.orhava.trivia2.BuyPremiumAvatars.avatarPremium14;
import static com.orhava.trivia2.BuyPremiumAvatars.avatarPremium15;
import static com.orhava.trivia2.BuyPremiumAvatars.avatarPremium16;
import static com.orhava.trivia2.BuyPremiumAvatars.avatarPremium17;
import static com.orhava.trivia2.BuyPremiumAvatars.avatarPremium18;
import static com.orhava.trivia2.BuyPremiumAvatars.avatarPremium19;

import static com.orhava.trivia2.MainMenu.isMuted;
import static com.orhava.trivia2.Utils.amountOfGeneralPoints;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class Avatar_Name extends AppCompatActivity {

    private ImageButton navToMainMenu2;
    private   ImageButton avatar20,avatar21,avatar22,btnPen,avatar3,avatar2,avatar4,avatar5,avatar6,avatar7,avatar8,avatar9,avatar10,avatar11,avatar12,avatar13,avatar14,avatar15,avatar16,avatar17,avatar18,avatar19;
    private ImageView profilePic;
    private  EditText txtName;
    private TextView txtPoints,txtAvatar14,txtAvatar15,txtAvatar16,txtAvatar17,txtAvatar18,txtAvatar19;
    public static int avatarPremiumChoice=0;
    private SharedPreferences prefs3;
    public View rootLayout ;

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
            txtAvatar14.setText(R.string.Avatar_is_Usable2);
            txtAvatar14.setTextSize(6);
        }

        int PremiumPoints2 = prefs4.getInt("PremiumPoints2", 0); //0 is the default value
        if(PremiumPoints2==1){
            txtAvatar15.setText(R.string.Avatar_is_Usable2);
            txtAvatar15.setTextSize(6);
        }
        int PremiumPoints3 = prefs4.getInt("PremiumPoints3", 0); //0 is the default value
        if(PremiumPoints3==1){
            txtAvatar16.setText(R.string.Avatar_is_Usable2);
            txtAvatar16.setTextSize(6);
        }
        int PremiumPoints4 = prefs4.getInt("PremiumPoints4", 0); //0 is the default value
        if(PremiumPoints4==1){
            txtAvatar17.setText(R.string.Avatar_is_Usable2);
            txtAvatar17.setTextSize(6);
        }

        int PremiumPoints5 = prefs4.getInt("PremiumPoints5", 0); //0 is the default value
        if(PremiumPoints5==1){
            txtAvatar18.setText(R.string.Avatar_is_Usable2);
            txtAvatar18.setTextSize(6);
        }
        int PremiumPoints6 = prefs4.getInt("PremiumPoints6", 0); //0 is the default value
        if(PremiumPoints6==1){
            txtAvatar19.setText(R.string.Avatar_is_Usable2);
            txtAvatar19.setTextSize(6);
        }


        txtPoints.setText(getString(R.string.Pointss)+" "+ amountOfGeneralPoints(this));
        prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
        int oldAvatarChoice = prefs3.getInt("AvatarChoice", 0); //0 is the default value
        if(oldAvatarChoice==1){
            profilePic.setImageResource(R.drawable.avatar_1);
        }
        else if(oldAvatarChoice==2){
            profilePic.setImageResource(R.drawable.avatar_2);
        }
        else if(oldAvatarChoice==3){
            profilePic.setImageResource(R.drawable.avatar_3);
        }

        else if(oldAvatarChoice==4){
            profilePic.setImageResource(R.drawable.avatar_4);
        }

        else if(oldAvatarChoice==5){
            profilePic.setImageResource(R.drawable.avatar_5);
        }

        else if(oldAvatarChoice==6){
            profilePic.setImageResource(R.drawable.avatar_6);
        }

        else if(oldAvatarChoice==7){
            profilePic.setImageResource(R.drawable.avatar_7);
        }

        else if(oldAvatarChoice==8){
            profilePic.setImageResource(R.drawable.avatar_8);
        }

        else if(oldAvatarChoice==9){
            profilePic.setImageResource(R.drawable.avatar_9);
        }

        else if(oldAvatarChoice==10){
            profilePic.setImageResource(R.drawable.avatar_10);
        }

        else if(oldAvatarChoice==11){
            profilePic.setImageResource(R.drawable.avatar_11);
        }
        else if(oldAvatarChoice==12){
            profilePic.setImageResource(R.drawable.avatar_12);
        }
        else if(oldAvatarChoice==13 && PremiumPoints1==1){
            profilePic.setImageResource(R.drawable.avatar_19);
        }

        else if(oldAvatarChoice==14 && PremiumPoints2==1){
            profilePic.setImageResource(R.drawable.avatar_14);
        }
        else if(oldAvatarChoice==15 && PremiumPoints3==1){
            profilePic.setImageResource(R.drawable.avatar_15);
        }
        else if(oldAvatarChoice==16 && PremiumPoints4==1){
            profilePic.setImageResource(R.drawable.avatar_16);
        }

        else if(oldAvatarChoice==17 && PremiumPoints5==1){
            profilePic.setImageResource(R.drawable.avatar_17);
        }
        else if(oldAvatarChoice==18 && PremiumPoints6==1){
            profilePic.setImageResource(R.drawable.avatar_18);
        }
        else if(oldAvatarChoice==19){
            profilePic.setImageResource(R.mipmap.avatersecret_foreground);
        }

        else if(oldAvatarChoice==20){
            profilePic.setImageResource(R.drawable.avatar_13);
        }
        else if(oldAvatarChoice==21){
            profilePic.setImageResource(R.drawable.avatar_20);
        }
        else if(oldAvatarChoice==22){
            profilePic.setImageResource(R.drawable.avatar_21);
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
        avatar20=findViewById(R.id.avatar20);
        avatar21=findViewById(R.id.avatar21);
        avatar22=findViewById(R.id.avatar22);
        btnPen=findViewById(R.id.btnPen);
        txtName=findViewById(R.id.txtName); //maybe can make a problem
        txtPoints=findViewById(R.id.txtPoints);
        rootLayout = findViewById(R.id.RlMainMenu);
    }
    void buyAvatar(){

        avatar3.setOnClickListener(view -> {
            if (amountOfGeneralPoints(this) >= 10) {
                prefs3=Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 1);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_1);
            }

            else {


                Snackbar snackbar = Snackbar.make(rootLayout, R.string.You_Dont_have_Enough_Points, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }


        });

        avatar2.setOnClickListener(view -> {
            if (amountOfGeneralPoints(this)>=20){

                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 2);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_2);



            }
            else{


                Snackbar snackbar = Snackbar.make(rootLayout, R.string.You_Dont_have_Enough_Points, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }
        });

        avatar4.setOnClickListener(view -> {
            if (amountOfGeneralPoints(this) >= 30) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 3);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_3);


            }
            else{
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.You_Dont_have_Enough_Points, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }
        });
        avatar6.setOnClickListener(view -> {
            if (amountOfGeneralPoints(this) >= 40) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 4);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_4);

            }
            else{
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.You_Dont_have_Enough_Points, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }
        });
        avatar5.setOnClickListener(view -> {

            if (amountOfGeneralPoints(this) >= 50) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 5);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_5);

            }
            else{
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.You_Dont_have_Enough_Points, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }
        });
        avatar7.setOnClickListener(view -> {
            if (amountOfGeneralPoints(this) >= 60) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 6);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_6);

            }
            else{
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.You_Dont_have_Enough_Points, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }
        });
        avatar9.setOnClickListener(view -> {
            if (amountOfGeneralPoints(this) >= 70) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 7);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_7);

            }
            else{
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.You_Dont_have_Enough_Points, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }
        });
        avatar8.setOnClickListener(view -> {
            if (amountOfGeneralPoints(this) >= 80) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 8);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_8);

            }
            else{
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.You_Dont_have_Enough_Points, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }
        });
        avatar10.setOnClickListener(view -> {
            if (amountOfGeneralPoints(this) >= 90) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 9);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_9);

            }
            else{
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.You_Dont_have_Enough_Points, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }
        });
        avatar12.setOnClickListener(view -> {
            if (amountOfGeneralPoints(this) >= 100) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 10);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_10);

            }
            else{
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.You_Dont_have_Enough_Points, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }
        });
        avatar11.setOnClickListener(view -> {
            if (amountOfGeneralPoints(this) >= 110) {
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 11);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_11);

            }
            else{
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.You_Dont_have_Enough_Points, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }
        });
        avatar13.setOnClickListener(view -> {
            if (amountOfGeneralPoints(this)>=120){

                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 12);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_12);
            }
            else{
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.You_Dont_have_Enough_Points, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }
        });


        avatar21.setOnClickListener(view -> {
            if (amountOfGeneralPoints(this)>=180){

                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 20);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_13);
            }
            else{
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.You_Dont_have_Enough_Points, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }
        });

        avatar20.setOnClickListener(view -> {
            if (amountOfGeneralPoints(this)>=200){

                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 21);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_20);
            }
            else{
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.You_Dont_have_Enough_Points, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }
        });

        avatar22.setOnClickListener(view -> {
            if (amountOfGeneralPoints(this)>=220){

                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 22);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_21);
            }
            else{
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.You_Dont_have_Enough_Points, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
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
                profilePic.setImageResource(R.drawable.avatar_19);
            }
            }
            else {
                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 13);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_19);


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
                    profilePic.setImageResource(R.drawable.avatar_14);
                }
            }
            else {
                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 14);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_14);


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
                    profilePic.setImageResource(R.drawable.avatar_15);
                }
            }
            else {
                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 15);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_15);


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
                    profilePic.setImageResource(R.drawable.avatar_16);
                }
            }
            else {
                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 16);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_16);


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
                    profilePic.setImageResource(R.drawable.avatar_17);
                }
            }
            else {
                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 17);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_17);


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
                    profilePic.setImageResource(R.drawable.avatar_18);
                }
            }
            else {
                SharedPreferences prefs3 = Avatar_Name.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 18);
                editor.apply();
                profilePic.setImageResource(R.drawable.avatar_18);


            }
        });


    }



    void goToMain(){

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.modernclick);

        navToMainMenu2.setOnClickListener(view -> {

            if (!isMuted){
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

                    Snackbar snackbar = Snackbar.make(rootLayout, getString(R.string.Hello)+ " "+UserName, Snackbar.LENGTH_SHORT);
                    snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                    snackbar.show();


                });
            }

        });


        String UserName = prefs2.getString("autoSave", ""); //0 is the default value

        btnPen.setOnClickListener(view -> {
            txtName.clearFocus();
            InputMethodManager imm = (InputMethodManager) view.getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


            Snackbar snackbar = Snackbar.make(rootLayout, getString(R.string.Hello)+ UserName, Snackbar.LENGTH_SHORT);
            snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
            snackbar.show();




        });



    }


}