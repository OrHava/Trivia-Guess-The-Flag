package com.orhava.trivia2;


import static com.orhava.trivia2.Ads.isInterstitialAdReady;
import static com.orhava.trivia2.BuyPremiumAvatars.TAG;
import static com.orhava.trivia2.Utils.amountOfGeneralPoints;
import static com.orhava.trivia2.Utils.makeItFalse;
import static com.orhava.trivia2.Utils.saveTimestamp;
import static com.orhava.trivia2.Utils.setLocale;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.material.snackbar.Snackbar;
import com.google.common.collect.ImmutableList;
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
    public static boolean isMuted = true;


    private Button btnLeaderBoard,SignOut, flags_GameBtn,name_AvatarBtn,btnMultiPlayer;
    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private FirebaseAuth mAuth;
    private TextView txtNameAndPoints,name_of_language;
    private ImageView imgViewShowAvatar;
    private FirebaseUser user;
    private Button btnShareGame,btnRateUs,btnFunFacts,btnMoreApps, btnRemoveAds, btnLearnFlags, Geography_GameBtn;
    private ImageButton btnHebrew, btnEnglish, Philippines_Language, India_Language, Indonesia_Language, Malaysia_Language, Spain_Language, bangladesh_Language,Brazil_Language;
    private BillingClient billingClient;
    private ProductDetails productDetails;



    //./gradlew signingReport to find hash
 //to release the game ./gradlew bundleRelease
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showCustomPopupExit();
            }
        });

        // Retrieve the language code from SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String langCode = preferences.getString("langCode", "");

        // Set the locale if a language code is available
        if (!langCode.isEmpty()) {
            setLocale(langCode, this);
        }

        setContentView(R.layout.activity_main_menu);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initialize();
        if (isMuted) {

            btnMute.setImageResource(R.drawable.unmute_50);

        } else {
            btnMute.setImageResource( R.drawable.mute_50);
        }


        signOut();
        setPicturesName();
        Mute_UnMute();
        settingNext();
        StartButtons();
        SaveScore();
        ChangeLanguage();
        ShareApp();
        RateApp();
        MoreApps();
        FunFacts();



        if (isNetworkConnected(this)){
            initializeBillingClient();
            Ads.preloadInterstitialAd(this);
            RewardAd();
            RewardAdSlotMachine();
        }










    }

    private void RewardAd() {

        ImageView RewardBtn = findViewById(R.id.RewardBtn);

        // Load the shine animation
        Animation shineAnimation = AnimationUtils.loadAnimation(this, R.anim.shine_animation);

        // Apply the animation to the ImageButton
        RewardBtn.startAnimation(shineAnimation);

        RewardBtn.setOnClickListener(v -> showCustomPopup());
    }

    private void RewardAdSlotMachine() {

        ImageView RewardBtn = findViewById(R.id.RewardBtn2);

        // Load the shine animation
        Animation shineAnimation = AnimationUtils.loadAnimation(this, R.anim.shine_animation);

        // Apply the animation to the ImageButton
        RewardBtn.startAnimation(shineAnimation);

        RewardBtn.setOnClickListener(v -> showCustomPopupSlot_Machine());
    }

    @SuppressLint("SetTextI18n")
    private void showCustomPopup() {
        // Create a Dialog without a title
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_layout);
      // Initialize views

        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);


        // Handle button clicks
        btnYes.setOnClickListener(v -> {
            // Call the method to show the ad and give the reward
            showAdAndGiveReward();
            dialog.dismiss();
        });

        btnNo.setOnClickListener(v -> dialog.dismiss());

        // Show the dialog
        dialog.show();
    }

    private void showAdAndGiveReward() {
        if(isInterstitialAdReady()){
            saveTimestamp(this);
            Ads.showInterstitialAd(this, MainMenu.class);
        }
        else{
            View rootLayout = findViewById(R.id.RlMainMenu);

            Snackbar snackbar = Snackbar.make(rootLayout, R.string.ad_not_been_loaded_yet, Snackbar.LENGTH_SHORT);
            snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
            snackbar.show();
        }


    }



    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ChangeLanguage();
    }

    @SuppressLint("SetTextI18n")
    private void showCustomPopupSlot_Machine() {
        // Create a Dialog without a title
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_layout);
        // Initialize views

        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);
        TextView textTitle = dialog.findViewById(R.id.textTitle);
        TextView textMessage = dialog.findViewById(R.id.textMessage);
        ImageView imagePopup = dialog.findViewById(R.id.imagePopup);

        textTitle.setText(R.string.slot_machine);
        textMessage.setText(R.string.do_you_want_to_play_a_game_of_chance_to_win_premium_avatar_in_exchange_of_watching_5_seconds_ad_click_yes);
        imagePopup.setImageResource(R.drawable.slot_machine_icon);

        // Handle button clicks
        btnYes.setOnClickListener(v -> {
            // Call the method to show the ad and give the reward

            dialog.dismiss();

            if(isInterstitialAdReady()){
                Ads.showInterstitialAd(this, Slot_Machine.class);
            }
            else{
                View rootLayout = findViewById(R.id.RlMainMenu);

                Snackbar snackbar = Snackbar.make(rootLayout, R.string.ad_not_been_loaded_yet, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, view -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }



        });

        btnNo.setOnClickListener(v -> dialog.dismiss());

        // Show the dialog
        dialog.show();
    }

    @SuppressLint("SetTextI18n")
    private void showCustomPopupExit() {
        // Create a Dialog without a title
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_layout);
        // Initialize views

        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);
        TextView textTitle = dialog.findViewById(R.id.textTitle);
        TextView textMessage = dialog.findViewById(R.id.textMessage);
        ImageView imagePopup = dialog.findViewById(R.id.imagePopup);

        textTitle.setText(R.string.are_you_sure_you_want_to_exit_app);
        textMessage.setText(R.string.come_back_soon_we_will_miss_you);
        imagePopup.setImageResource(R.drawable.new_icon_flag_circle);

        // Handle button clicks
        btnYes.setOnClickListener(v -> {
            // Call the method to show the ad and give the reward

            dialog.dismiss();
            finishAffinity();
        });

        btnNo.setOnClickListener(v -> dialog.dismiss());

        // Show the dialog
        dialog.show();
    }


    // Add this line at the top of your class
    private final AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener = billingResult -> {
        // Handle the result of the acknowledgment
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
            // Acknowledgment successful
            Log.d("BillingClient", "Purchase acknowledged successfully");
        } else {
            // Acknowledgment failed, handle the error
            Log.e("BillingClient", "Error acknowledging purchase: " + billingResult.getResponseCode());
        }
    };


    public void onRemoveAdsButtonClick(View view) {


        QueryProductDetailsParams queryProductDetailsParams =
                QueryProductDetailsParams.newBuilder()
                        .setProductList(
                                ImmutableList.of(
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("remove_ads")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build()))
                        .build();

        billingClient.queryProductDetailsAsync(
                queryProductDetailsParams,
                (billingResult, productDetailsList) -> {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && !productDetailsList.isEmpty()) {
                        runOnUiThread(() -> {
                            productDetails = productDetailsList.get(0);

                            BillingFlowParams billingFlowParams =
                                    BillingFlowParams.newBuilder()
                                            .setProductDetailsParamsList(
                                                    ImmutableList.of(
                                                            BillingFlowParams.ProductDetailsParams.newBuilder()
                                                                    .setProductDetails(productDetails)
                                                                    .build()
                                                    )
                                            )
                                            .build();

                            billingClient.launchBillingFlow(this, billingFlowParams);
                        });
                    } else {
                        Log.e("TAG", "onProductDetailsResponse: No products or an error occurred");
                    }
                }
        );
    }


    private void initializeBillingClient() {
        billingClient = BillingClient.newBuilder(this)
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();

        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // BillingClient is ready
                    Log.d("BillingClient", "Billing client setup finished");
                } else {
                    // Handle error
                    Log.e("BillingClient", "Error code: " + billingResult.getResponseCode());
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
                Log.w("BillingClient", "Billing service disconnected");
            }
        });
    }

    // Define a PurchasesUpdatedListener
    private final PurchasesUpdatedListener purchasesUpdatedListener = (billingResult, purchases) -> {
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (Purchase purchase : purchases) {
                handlePurchase(purchase);
            }
        }
        else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
            PurchaseManager.setRemoveAdsPurchased(this, true);

            // Replace the rootLayout with the id of the root layout in your activity
            View rootLayout = findViewById(R.id.RlMainMenu);

            Snackbar snackbar = Snackbar.make(rootLayout, R.string.ads_are_already_removed, Snackbar.LENGTH_SHORT);
            snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
            snackbar.show();
        }
        else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            // User canceled the purchase
            Log.d("BillingClient", "User canceled the purchase");
        } else {
            // Handle other errors
            Log.e("BillingClient", "Error code: " + billingResult.getResponseCode());
        }
    };

    private void handlePurchase(Purchase purchase) {
        if ( purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            PurchaseManager.setRemoveAdsPurchased(this, true);

            acknowledgePurchase(purchase);

            // Update your app's state to remove ads
            Log.d("BillingClient", "Remove Ads purchased successfully");
        }
    }

    private void acknowledgePurchase(Purchase purchase) {
        AcknowledgePurchaseParams acknowledgePurchaseParams =
                AcknowledgePurchaseParams.newBuilder()
                        .setPurchaseToken(purchase.getPurchaseToken())
                        .build();

        billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
    }


    private void FunFacts() {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.modernclick);
        btnFunFacts.setOnClickListener(view -> {
            if (!isMuted){
                mp.setVolume(0,0);
            }
            else{
                mp.setVolume(0,1);
            }

            mp.start();

            startActivity(new Intent(MainMenu.this, Fun_Facts.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

        });
    }

    private void MoreApps() {
        btnMoreApps.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=7010355545573406247&pli=1"));
            startActivity(browserIntent);

        });

    }

    private void RateApp() {
        btnRateUs.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.orhava.trivia2"));
            startActivity(browserIntent);
        });

    }

    private void ShareApp() {

        btnShareGame.setOnClickListener(view -> {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String shareMessage= "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.orhava.trivia2" +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }

        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        Utils.setLocale(Utils.getCurrentLangCode(this), this);
    }



    private void ChangeLanguage() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String langCode = preferences.getString("langCode", "");
        boolean localeSet = preferences.getBoolean("localeSet", false); // Retrieve the flag

        if (!localeSet && !langCode.isEmpty()) {


            setLocale(langCode, this);
            // Update the flag in the SharedPreferences
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("localeSet", true);
            editor.apply();
        }
        // Retrieve the language name from the SharedPreferences
        String langName = preferences.getString("langName", "");
        name_of_language.setText(langName);
        btnHebrew.setOnClickListener(view -> {

            makeItFalse(this);

            setLocale("iw", MainMenu.this);

            name_of_language.setText(R.string.Hebrew);

        });

        btnEnglish.setOnClickListener(view -> {
            makeItFalse(this);

            setLocale("en", MainMenu.this);

            name_of_language.setText(R.string.English);

        });


        Philippines_Language.setOnClickListener(view -> {
            makeItFalse(this);

            setLocale("fil", MainMenu.this);
            name_of_language.setText(R.string.Filipino);

        });

        India_Language.setOnClickListener(view -> {
            makeItFalse(this);

            setLocale("hi", MainMenu.this);

            name_of_language.setText(R.string.Hindi);

        });
        Indonesia_Language.setOnClickListener(view -> {
            makeItFalse(this);

            setLocale("in", MainMenu.this);

            name_of_language.setText(R.string.Indonesian);

        });
        Malaysia_Language.setOnClickListener(view -> {
            makeItFalse(this);

            setLocale("ms", MainMenu.this);

            name_of_language.setText(R.string.Malay);

        });
        Spain_Language.setOnClickListener(view -> {
            makeItFalse(this);

            setLocale("gl", MainMenu.this);

            name_of_language.setText(R.string.Spanish);

        });
        bangladesh_Language.setOnClickListener(view -> {
            makeItFalse(this);

            setLocale("bn", MainMenu.this);

            name_of_language.setText(R.string.Bengali);

        });
        Brazil_Language.setOnClickListener(view -> {
            makeItFalse(this);

            setLocale("pt", MainMenu.this);

            name_of_language.setText(R.string.Portuguese);

        });


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


            myRef1.setValue(new Score(prefs2.getString("autoSave", ""),amountOfGeneralPoints(this), oldAvatarChoice));
        }
    }


    void StartButtons(){

    final MediaPlayer mp = MediaPlayer.create(this, R.raw.modernclick);
    flags_GameBtn.setOnClickListener(view -> {

        if (!isMuted){
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
        if (!isMuted){
            mp.setVolume(0,0);
        }
        else{
            mp.setVolume(0,1);
        }

        mp.start();

        startActivity(new Intent(MainMenu.this, Avatar_Name.class));
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);


    });

        btnLearnFlags.setOnClickListener(view -> {
            if (!isMuted){
                mp.setVolume(0,0);
            }
            else{
                mp.setVolume(0,1);
            }

            mp.start();

            startActivity(new Intent(MainMenu.this, LearnFlagsActivity.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);


        });

        Geography_GameBtn.setOnClickListener(view -> {
            if (!isMuted){
                mp.setVolume(0,0);
            }
            else{
                mp.setVolume(0,1);
            }

            mp.start();

            startActivity(new Intent(MainMenu.this, Menu_Geography_Game.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);


        });


         btnRemoveAds.setOnClickListener(view -> {
            if (!isMuted){
                mp.setVolume(0,0);
            }
            else{
                mp.setVolume(0,1);
            }

            mp.start();
             if( isNetworkConnected(this)) {
                 onRemoveAdsButtonClick(view);
             }

             else {

                 View rootLayout = findViewById(R.id.RlMainMenu);
                 Snackbar snackbar = Snackbar.make(rootLayout, "Connect To Internet", Snackbar.LENGTH_SHORT);
                 snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                 snackbar.show();



             }



        });




    btnLeaderBoard.setOnClickListener(view -> {

        if(user != null && isNetworkConnected(this)) {

            if (!isMuted){
                mp.setVolume(0,0);
            }
            else{
                mp.setVolume(0,1);
            }

            mp.start();
            startActivity(new Intent(MainMenu.this, LeaderBoard.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

        }

        else if (!isNetworkConnected(this)){


            View rootLayout = findViewById(R.id.RlMainMenu);

            Snackbar snackbar = Snackbar.make(rootLayout, R.string.Connect_to_Internet, Snackbar.LENGTH_SHORT);
            snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
            snackbar.show();



        }

        else{



            View rootLayout = findViewById(R.id.RlMainMenu);

            Snackbar snackbar = Snackbar.make(rootLayout, R.string.Connect_to_a_User, Snackbar.LENGTH_SHORT);
            snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
            snackbar.show();


        }


    });


        btnMultiPlayer.setOnClickListener(view -> {


            if(user != null && isNetworkConnected(this) ){
                if (!isMuted){
                    mp.setVolume(0,0);
                }
                else{
                    mp.setVolume(0,1);
                }

                mp.start();
                startActivity(new Intent(MainMenu.this, MultiPlayer.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            }

            else if (!isNetworkConnected(this)){

                View rootLayout = findViewById(R.id.RlMainMenu);

                Snackbar snackbar = Snackbar.make(rootLayout, R.string.Please_Connect_to_Internet_To_Play_MultiPLayer, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();



            }


            else{

                View rootLayout = findViewById(R.id.RlMainMenu);

                Snackbar snackbar = Snackbar.make(rootLayout, R.string.Please_Connect_to_a_User_To_Play_MultiPLayer, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            }


        });






}

    private boolean isNetworkConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            } else return activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
        } else {
            return false;
        }
    }




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
                    Log.d(TAG, "Got ID token.");
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
            SignOut.setText(R.string.Sign_In);
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

        txtNameAndPoints.setText(getString(R.string.Hello2)+" "+prefs2.getString("autoSave", "")+ " "+getString(R.string.you_have)+"\n" +amountOfGeneralPoints(this) + " " +getString(R.string.Points2));
        SharedPreferences prefs3;
        prefs3 = MainMenu.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
        int oldAvatarChoice = prefs3.getInt("AvatarChoice", 0); //0 is the default value

        if(oldAvatarChoice==1){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_1);
        }
        else if(oldAvatarChoice==2){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_2);
        }
        else if(oldAvatarChoice==3){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_3);
        }



        else if(oldAvatarChoice==4){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_4);
        }

        else if(oldAvatarChoice==5){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_5);
        }

        else if(oldAvatarChoice==6){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_6);
        }

        else if(oldAvatarChoice==7){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_7);
        }

        else if(oldAvatarChoice==8){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_8);
        }

        else if(oldAvatarChoice==9){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_9);
        }

        else if(oldAvatarChoice==10){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_10);
        }

        else if(oldAvatarChoice==11){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_11);
        }
        else if(oldAvatarChoice==12){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_12);
        }
        else if(oldAvatarChoice==13 ){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_19);
        }

        else if(oldAvatarChoice==14 ){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_14);
        }
        else if(oldAvatarChoice==15 ){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_15);
        }
        else if(oldAvatarChoice==16 ){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_16);
        }

        else if(oldAvatarChoice==17 ){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_17);
        }
        else if(oldAvatarChoice==18 ){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_18);
        }
        else if(oldAvatarChoice==19){
            imgViewShowAvatar.setImageResource(R.mipmap.avatersecret_foreground);
        }

        else if(oldAvatarChoice==20){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_13);
        }
        else if(oldAvatarChoice==21){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_20);
        }
        else if(oldAvatarChoice==22){
            imgViewShowAvatar.setImageResource(R.drawable.avatar_21);
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
        btnShareGame= findViewById(R.id.btnShareGame);
        btnRateUs=findViewById(R.id.btnRateUs);
        btnFunFacts=findViewById(R.id.btnFunFacts);
        btnHebrew= findViewById(R.id.Israel_Language);
        btnEnglish= findViewById(R.id.England_Language);
        btnMoreApps= findViewById(R.id.btnMoreApps);
        btnRemoveAds = findViewById(R.id.btnRemoveAds);
        name_of_language= findViewById(R.id.name_of_language);
        Geography_GameBtn = findViewById(R.id.Geography_GameBtn);
        Philippines_Language=findViewById(R.id.Philippines_Language);
        India_Language=findViewById(R.id.India_Language);
        Indonesia_Language=findViewById(R.id.Indonesia_Language);
        Malaysia_Language=findViewById(R.id.Malaysia_Language);
        Spain_Language=findViewById(R.id.Spain_Language);
        bangladesh_Language=findViewById(R.id.bangladesh_Language);
        Brazil_Language=findViewById(R.id.Brazil_Language);

        btnLearnFlags= findViewById(R.id.btnLearnFlags);


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        Log.d(TAG, "currentUser "+ currentUser);
    }




    private void  Mute_UnMute() {



        btnMute.setOnClickListener(view -> {
            isMuted = !isMuted;
            new Handler();
            if (isMuted) {

                btnMute.setImageResource(R.drawable.unmute_50);

            } else {


                btnMute.setImageResource( R.drawable.mute_50);
            }
        });

    }

    private void settingNext(){

        settingsBtn.setOnClickListener(view -> startActivity(new Intent(MainMenu.this, SettingsActivity.class)));
        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);


    }



}