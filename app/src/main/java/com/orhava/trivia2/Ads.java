package com.orhava.trivia2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Objects;

import io.reactivex.annotations.NonNull;

/**
 * Main Activity. Inflates main activity xml.
 */
public class Ads extends AppCompatActivity {


    private static final String TAG = "Ads";
    public static InterstitialAd mInterstitialAd;

    private static Class<?> activityToStart; // Add this line
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);
        Objects.requireNonNull(getSupportActionBar()).hide();


        MobileAds.initialize(this, initializationStatus -> {});
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-8096185122491583/8714822405", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@androidx.annotation.NonNull @NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        StartAd();
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@androidx.annotation.NonNull @NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }






                });



    }


    public static void showInterstitialAd(Activity activity, Class<?> activityToStart) {
        Ads.activityToStart = activityToStart;
        if (mInterstitialAd != null) {
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    // Set the ad reference to null so you don't show the ad a second time.
                    Log.d(TAG, "Ad dismissed fullscreen content.");
                    mInterstitialAd = null;
                    startNewActivity(activity);
                }

                // ... (other callback methods)
            });

            mInterstitialAd.show(activity);
        } else {
            Log.d(TAG, "The interstitial ad wasn't ready yet.");
        }
    }
    private static void startNewActivity(Activity activity) {
        if (activityToStart != null) {
            activity.startActivity(new Intent(activity, activityToStart));
            // Add overridePendingTransition if you want to specify transition animations
        } else {
            Log.e(TAG, "activityToStart is null");
        }
    }

    public static boolean isInterstitialAdReady() {
        return mInterstitialAd != null;
    }
    public static void preloadInterstitialAd(Context context) {
        // Add this line
        Context applicationContext = context.getApplicationContext();
        MobileAds.initialize(applicationContext, initializationStatus -> {});

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(applicationContext, "ca-app-pub-8096185122491583/8714822405", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@androidx.annotation.NonNull @NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@androidx.annotation.NonNull @NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }

                });

    }


    private void StartAd() {

        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
            @Override
            public void onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d(TAG, "Ad was clicked.");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.
                Log.d(TAG, "Ad dismissed fullscreen content.");
                mInterstitialAd = null;
                startActivity(new Intent(Ads.this, Results.class));
            }

            @Override
            public void onAdFailedToShowFullScreenContent(@androidx.annotation.NonNull AdError adError) {
                // Called when ad fails to show.
                Log.e(TAG, "Ad failed to show fullscreen content.");
                mInterstitialAd = null;
            }

            @Override
            public void onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d(TAG, "Ad recorded an impression.");
            }

            @Override
            public void onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(TAG, "Ad showed fullscreen content.");
            }
        });


        if (mInterstitialAd != null) {
            mInterstitialAd.show(Ads.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }



    }




}