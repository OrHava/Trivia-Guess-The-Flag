package com.orhava.trivia2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Objects;

import io.reactivex.annotations.NonNull;

/**
 * Main Activity. Inflates main activity xml.
 */
public class Ads extends AppCompatActivity {

    private static final long GAME_LENGTH_MILLISECONDS = 3000;
    //private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/8691691433"; //just testing not real ad, video
    //private static final String AD_UNIT_ID = "ca-app-pub-8096185122491583/8714822405"; //real ad video
    private static final String TAG = "Ads";
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);
        Objects.requireNonNull(getSupportActionBar()).hide();


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@androidx.annotation.NonNull InitializationStatus initializationStatus) {}
        });
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