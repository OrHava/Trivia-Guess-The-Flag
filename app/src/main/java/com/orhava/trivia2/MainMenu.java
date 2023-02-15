package com.orhava.trivia2;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.Nullable;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class MainMenu extends AppCompatActivity  {


    private ImageButton settingsBtn;
    @SuppressLint("StaticFieldLeak")
    public static ImageButton btnMute;
    public static boolean flag=true;
    public static final int[] i = {0};
    private Button btnLeaderBoard,SignOut, flags_GameBtn,name_AvatarBtn,btnMultiPlayer;
    //public  static  String playerName;
    private static final int RC_LEADERBOARD_UI = 9004;
    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private FirebaseAuth mAuth;
   public BeginSignInRequest signInRequest ;
   private TextView txtNameAndPoints;
   private ImageView imgViewShowAvatar;
    private FirebaseUser user;





    //./gradlew signingReport to find hash
 //to release the game ./gradlew bundleRelease
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initialize();
        if (i[0] % 2==0) {
            btnMute.setImageResource(R.mipmap.mutenewproblem11);

        } else {
            btnMute.setImageResource(R.mipmap.mutenewproblem22);
        }



        signOut();
        setPicturesName();
        Mute_UnMute();
        settingNext();
        StartButtons();
        SaveScore();








    }

    private void SaveScore() {
        SharedPreferences prefs2 = PreferenceManager
                .getDefaultSharedPreferences(this);
        if (user != null) {
            String userId = user.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            user = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference myRef1 = database.getReference("Scores").child(userId);
            SharedPreferences prefs3;
            prefs3 = MainMenu.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
            int oldAvatarChoice = prefs3.getInt("AvatarChoice", 0); //0 is the default value


            myRef1.setValue(new Score(prefs2.getString("autoSave", ""),amountOfGeneralPoints(), oldAvatarChoice));
        }
    }


    void StartButtons(){

    final MediaPlayer mp = MediaPlayer.create(this, R.raw.modernclick);
    flags_GameBtn.setOnClickListener(view -> {

        if (!flag){
            mp.setVolume(0,0);
        }
        else{
            mp.setVolume(0,1);
        }

        mp.start();

        startActivity(new Intent(MainMenu.this, Menu_Game.class));
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
    });

    name_AvatarBtn.setOnClickListener(view -> {
        if (!flag){
            mp.setVolume(0,0);
        }
        else{
            mp.setVolume(0,1);
        }

        mp.start();

        startActivity(new Intent(MainMenu.this, Avatar_Name.class));
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);


    });

    btnLeaderBoard.setOnClickListener(view -> {

        if(user != null){

            if (!flag){
                mp.setVolume(0,0);
            }
            else{
                mp.setVolume(0,1);
            }

            mp.start();
            startActivity(new Intent(MainMenu.this, LeaderBoard.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

        }

        else{
            Toast.makeText(this, "Please Connect to a User To Watch LeaderBoard", Toast.LENGTH_SHORT).show();

        }


    });


        btnMultiPlayer.setOnClickListener(view -> {

            if(user != null){
                if (!flag){
                    mp.setVolume(0,0);
                }
                else{
                    mp.setVolume(0,1);
                }

                mp.start();
                startActivity(new Intent(MainMenu.this, MultiPlayer.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }
            else{
                Toast.makeText(this, "Please Connect to a User To Play MultiPLayer", Toast.LENGTH_SHORT).show();
            }


        });






}
//void connectToPlayGamesAnSaveScores(){
//    mAuth = FirebaseAuth.getInstance();
//    signInRequest = BeginSignInRequest.builder()
//            .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                    .setSupported(true)
//                    // Your server's client ID, not your Android client ID.
//                    .setServerClientId(getString(R.string.Google_id))
//                    // Only show accounts previously used to sign in.
//                    .setFilterByAuthorizedAccounts(true)
//                    .build())
//            .build();
//    onStart();
//
//    PlayGamesSdk.initialize(this);
//    GamesSignInClient gamesSignInClient = PlayGames.getGamesSignInClient(this);
//
//    gamesSignInClient.isAuthenticated().addOnCompleteListener(isAuthenticatedTask -> {
//        boolean isAuthenticated =
//                (isAuthenticatedTask.isSuccessful() &&
//                        isAuthenticatedTask.getResult().isAuthenticated());
//
//        if (isAuthenticated) {
//            // Continue with Play Games Services
//        } else {
//
//            // Disable your integration with Play Games Services or show a
//            // login button to ask  players to sign-in. Clicking it should
//            // call GamesSignInClient.signIn().
//        }
//    });
//
//
//
//    PlayGames.getLeaderboardsClient(this)
//            .submitScore(getString(R.string.leaderboard_id), amountOfGeneralPoints());
//
//
//
//
//
//}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SignInClient oneTapClient = Identity.getSignInClient(this);

        if (requestCode == REQ_ONE_TAP) {
            try {
                SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
                String idToken = credential.getGoogleIdToken();
                if (idToken != null) {
                    // Got an ID token from Google. Use it to authenticate
                    // with Firebase.
                    //Log.d(TAG, "Got ID token.");
                }
            } catch (ApiException e) {
                // ...
            }
        }


        SignInCredential googleCredential = null;
        try {
            googleCredential = oneTapClient.getSignInCredentialFromIntent(data);
        } catch (ApiException e) {
            e.printStackTrace();
        }
       // assert googleCredential != null;
        String idToken = null;
        if (googleCredential != null) {
            idToken = googleCredential.getGoogleIdToken();
        }
        if (idToken !=  null) {
            // Got an ID token from Google. Use it to authenticate
            // with Firebase.
            AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
            mAuth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //  Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    });
        }

    }



    @SuppressLint("SetTextI18n")
    void signOut(){
        user=FirebaseAuth.getInstance().getCurrentUser();
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        if (user != null || acct!=null) {

            SignOut.setVisibility(View.VISIBLE);
            SignOut.setOnClickListener(view -> {








                if(user != null){
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(MainMenu.this, SignIn.class));
                    overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                    finish();
                }

                if(acct!=null){
                    FirebaseAuth.getInstance().signOut();
                    mGoogleSignInClient.signOut();
                    startActivity(new Intent(MainMenu.this, SignIn.class));
                    overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                    finish();
                }



            });






        }

        else {
            SignOut.setText("Sign In");
            SignOut.setVisibility(View.VISIBLE);
            SignOut.setOnClickListener(view -> {
                startActivity(new Intent(MainMenu.this, SignIn.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            });

        }



    }





    @SuppressLint("SetTextI18n")
    void setPicturesName(){
        SharedPreferences prefs2 = PreferenceManager
                .getDefaultSharedPreferences(this);

        txtNameAndPoints.setText("Hello "+prefs2.getString("autoSave", "")+ " you have " +amountOfGeneralPoints() +" Points");
        SharedPreferences prefs3;
        prefs3 = MainMenu.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
        int oldAvatarChoice = prefs3.getInt("AvatarChoice", 0); //0 is the default value
        if(oldAvatarChoice==1){
            imgViewShowAvatar.setImageResource(R.mipmap.avater1_foreground);
        }
        else if(oldAvatarChoice==2){
            imgViewShowAvatar.setImageResource(R.mipmap.ic_avatar3_foreground);
        }
        else if(oldAvatarChoice==3){
            imgViewShowAvatar.setImageResource(R.mipmap.ic_avatar4_foreground);
        }

        else if(oldAvatarChoice==4){
            imgViewShowAvatar.setImageResource(R.mipmap.ic_avatar6_foreground);
        }

        else if(oldAvatarChoice==5){
            imgViewShowAvatar.setImageResource(R.mipmap.ic_avatar7_foreground);
        }

        else if(oldAvatarChoice==6){
            imgViewShowAvatar.setImageResource(R.mipmap.ic_avatar8_foreground);
        }

        else if(oldAvatarChoice==7){
            imgViewShowAvatar.setImageResource(R.mipmap.ic_avatar10_foreground);
        }

        else if(oldAvatarChoice==8){
            imgViewShowAvatar.setImageResource(R.mipmap.ic_avatar11_foreground);
        }

        else if(oldAvatarChoice==9){
            imgViewShowAvatar.setImageResource(R.mipmap.ic_avatar12_foreground);
        }

        else if(oldAvatarChoice==10){
            imgViewShowAvatar.setImageResource(R.mipmap.ic_avatar9_foreground);
        }

        else if(oldAvatarChoice==11){
            imgViewShowAvatar.setImageResource(R.mipmap.ic_avatar13_foreground);
        }
        else if(oldAvatarChoice==12){
            imgViewShowAvatar.setImageResource(R.mipmap.ic_avatar14_foreground);
        }
        else if(oldAvatarChoice==13 ){
            imgViewShowAvatar.setImageResource(R.mipmap.ic_avatar15_foreground);
        }

        else if(oldAvatarChoice==14){
            imgViewShowAvatar.setImageResource(R.mipmap.ic_avatar16_foreground);
        }
        else if(oldAvatarChoice==15 ){
            imgViewShowAvatar.setImageResource(R.mipmap.ic_avatar17_foreground);
        }
        else if(oldAvatarChoice==16 ){
            imgViewShowAvatar.setImageResource(R.mipmap.avaterprem1_foreground);
        }

        else if(oldAvatarChoice==17){
            imgViewShowAvatar.setImageResource(R.mipmap.avaterprem3_foreground);
        }
        else if(oldAvatarChoice==18 ){
            imgViewShowAvatar.setImageResource(R.mipmap.avaterprem2_foreground);
        }
        else if(oldAvatarChoice==19){
            imgViewShowAvatar.setImageResource(R.mipmap.avatersecret_foreground);
        }
        else{
            imgViewShowAvatar.setImageResource(R.mipmap.ic_emptyavatar_foreground);
        }

    }
    void initialize(){
        flags_GameBtn = findViewById(R.id.Flags_GameBtn);
         name_AvatarBtn = findViewById(R.id.Name_AvatarBtn);
        settingsBtn=findViewById(R.id.settingsBtn);
        btnMute = findViewById(R.id.mute_unmute);
        btnLeaderBoard=findViewById(R.id.btnLeaderBoard);
        SignOut=findViewById(R.id.SignOut);
        txtNameAndPoints =findViewById(R.id.txtNameandPoints);
        imgViewShowAvatar = findViewById(R.id.imgViewShowAvatar);
        btnMultiPlayer= findViewById(R.id.btnMultiPlayer);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }




    private void  Mute_UnMute() {



        btnMute.setOnClickListener(view -> {
            i[0]++;
            new Handler();

            if (i[0] % 2==0) {

                Toast.makeText(MainMenu.this, "UnMute", Toast.LENGTH_SHORT).show();
                flag = true;
                btnMute.setImageResource(R.mipmap.mutenewproblem11);

            } else {
                Toast.makeText(MainMenu.this, "Mute", Toast.LENGTH_SHORT).show();
                flag = false;
                btnMute.setImageResource(R.mipmap.mutenewproblem22);
            }
        });

    }

    private void settingNext(){

        settingsBtn.setOnClickListener(view -> startActivity(new Intent(MainMenu.this, SettingsActivity.class)));
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);


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


}