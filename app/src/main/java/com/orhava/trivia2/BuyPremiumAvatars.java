package com.orhava.trivia2;


import static com.orhava.trivia2.Avatar_Name.avatarPremiumChoice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.google.android.material.snackbar.Snackbar;
import com.google.common.collect.ImmutableList;

import java.util.Objects;

public class BuyPremiumAvatars extends AppCompatActivity {


   // private ActivityMainBinding binding;
    private BillingClient billingClient;
    private ImageButton navToMainMenu3;
    private ProductDetails productDetails;
    private Purchase purchase;
    private TextView textViewSell;
    private Button ButtonSell,ButtonUse;
    private ImageView imgViewAvatar;
    public static boolean avatarPremium14=false,avatarPremium15=false,avatarPremium16=false,avatarPremium17=false,avatarPremium18=false,avatarPremium19=false;
    static final String TAG = "InAppPurchaseTag";
    public View rootLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buypemiumavatars);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initialize();
        start();




    }

    void  initialize(){
        navToMainMenu3=findViewById(R.id.navToMainMenu3);
        imgViewAvatar=findViewById(R.id.imgViewAvatar);
        textViewSell=findViewById(R.id.textViewSell);
        ButtonSell=findViewById(R.id.ButtonSell);
        ButtonUse=findViewById(R.id.ButtonUse);
        ButtonUse.setVisibility(View.GONE);
        ButtonSell.setEnabled(false);
        rootLayout = findViewById(R.id.RlMainMenu);
    }
void start(){
    if (isConnected()){
        billingSetup();
    }
    else{

        Snackbar snackbar = Snackbar.make(rootLayout, R.string.no_internet_access_to_buy_avatar, Snackbar.LENGTH_SHORT);
        snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
        snackbar.show();
    }


    ButtonSell.setOnClickListener(this::makePurchase);


    ButtonUse.setOnClickListener(this::consumePurchase);


    navToMainMenu3.setOnClickListener(view -> goToMain());

}
    public boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return ( networkInfo != null && networkInfo.isConnectedOrConnecting());
    }
    void goToMain(){

            startActivity(new Intent(BuyPremiumAvatars.this, Avatar_Name.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);

    }
    private void billingSetup() {

        billingClient = BillingClient.newBuilder(this)
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();
        billingClient.startConnection(new BillingClientStateListener() {

            @Override
            public void onBillingSetupFinished(
                    @NonNull BillingResult billingResult) {

                if (billingResult.getResponseCode() ==
                        BillingClient.BillingResponseCode.OK) {
                    Log.i(TAG, "OnBillingSetupFinish connected");
                    queryProduct();
                } else {
                    Log.i(TAG, "OnBillingSetupFinish failed");
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                Log.i(TAG, "OnBillingSetupFinish connection lost");
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void queryProduct() {

        QueryProductDetailsParams queryProductDetailsParams =
                QueryProductDetailsParams.newBuilder()
                        .setProductList(
                                ImmutableList.of(
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("avatarpremium1")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build(),
                                        //Product 2 = index is 1
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("avatarpremium2")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("avatarpremium3")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("avatarpremium4")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("avatarpremium5")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build(),
                                        QueryProductDetailsParams.Product.newBuilder()
                                                .setProductId("avatarpremium6")
                                                .setProductType(BillingClient.ProductType.INAPP)
                                                .build()
                                        )


                        )
                        .build();

        billingClient.queryProductDetailsAsync(
                queryProductDetailsParams,
                (billingResult, productDetailsList) -> {
                    if (!productDetailsList.isEmpty()) {

                        runOnUiThread(() -> {
                            //binding.ButtonSell.setEnabled(true);
                            ButtonSell.setEnabled(true);
                            if (avatarPremiumChoice==1){
                                productDetails = productDetailsList.get(0);
                                ButtonSell.setText(getString(R.string.price)+ Objects.requireNonNull(productDetailsList.get(0).getOneTimePurchaseOfferDetails()).getFormattedPrice());
                              //  binding.textViewSell.setText(productDetails.getName());
                                textViewSell.setText(productDetails.getName());
                                imgViewAvatar.setImageResource(R.drawable.avatar_19);
                            }
                            else if(avatarPremiumChoice==2){
                                productDetails = productDetailsList.get(1);
                                ButtonSell.setText(getString(R.string.price)+ Objects.requireNonNull(productDetailsList.get(1).getOneTimePurchaseOfferDetails()).getFormattedPrice());
                               // binding.textViewSell.setText(productDetails.getName());
                                textViewSell.setText(productDetails.getName());
                                imgViewAvatar.setImageResource(R.drawable.avatar_14);
                            }
                            else if(avatarPremiumChoice==3){
                                productDetails = productDetailsList.get(2);
                                ButtonSell.setText(getString(R.string.price)+ Objects.requireNonNull(productDetailsList.get(2).getOneTimePurchaseOfferDetails()).getFormattedPrice());
                               // binding.textViewSell.setText(productDetails.getName());
                                textViewSell.setText(productDetails.getName());
                                imgViewAvatar.setImageResource(R.drawable.avatar_15);
                            }
                            else if(avatarPremiumChoice==4){
                                productDetails = productDetailsList.get(3);
                                ButtonSell.setText(getString(R.string.price)+ Objects.requireNonNull(productDetailsList.get(3).getOneTimePurchaseOfferDetails()).getFormattedPrice());
                                // binding.textViewSell.setText(productDetails.getName());
                                textViewSell.setText(productDetails.getName());
                                imgViewAvatar.setImageResource(R.drawable.avatar_16);
                            }
                            else if(avatarPremiumChoice==5){
                                productDetails = productDetailsList.get(4);
                                ButtonSell.setText(getString(R.string.price)+ Objects.requireNonNull(productDetailsList.get(4).getOneTimePurchaseOfferDetails()).getFormattedPrice());
                                // binding.textViewSell.setText(productDetails.getName());
                                textViewSell.setText(productDetails.getName());
                                imgViewAvatar.setImageResource(R.drawable.avatar_17);
                            }
                            else if(avatarPremiumChoice==6){
                                productDetails = productDetailsList.get(5);
                                ButtonSell.setText(getString(R.string.price)+ Objects.requireNonNull(productDetailsList.get(5).getOneTimePurchaseOfferDetails()).getFormattedPrice());
                                // binding.textViewSell.setText(productDetails.getName());
                                textViewSell.setText(productDetails.getName());
                                imgViewAvatar.setImageResource(R.drawable.avatar_18);
                            }
                        });
                    } else {
                        Log.i(TAG, "onProductDetailsResponse: No products");
                    }
                }
        );
    }

    public void makePurchase(View view) {

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
    }

    private final PurchasesUpdatedListener purchasesUpdatedListener = (billingResult, purchases) -> {

        if (billingResult.getResponseCode() ==
                BillingClient.BillingResponseCode.OK
                && purchases != null) {
            for (Purchase purchase : purchases) {
                completePurchase(purchase);
            }
        } else if (billingResult.getResponseCode() ==
                BillingClient.BillingResponseCode.USER_CANCELED) {
            Log.i(TAG, "onPurchasesUpdated: Purchase Canceled");
        } else {
            Log.i(TAG, "onPurchasesUpdated: Error");
        }
    };

    @SuppressLint("SetTextI18n")
    private void completePurchase(Purchase item) {

        purchase = item;

        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED)
            runOnUiThread(() -> {
                //binding.ButtonSell.setEnabled(true);
                ButtonSell.setEnabled(true);
              //  binding.textViewSell.setText("Purchase Complete");
                textViewSell.setText("Purchase Complete");
                ButtonUse.setVisibility(View.VISIBLE);
                navToMainMenu3.setVisibility(View.GONE);
            });
    }

    public void consumePurchase(View view) {
        ConsumeParams consumeParams =
                ConsumeParams.newBuilder()
                        .setPurchaseToken(purchase.getPurchaseToken())
                        .build();

        @SuppressLint("SetTextI18n") ConsumeResponseListener listener = (billingResult, purchaseToken) -> {
            if (billingResult.getResponseCode() ==
                    BillingClient.BillingResponseCode.OK) {
                runOnUiThread(() -> {
                    ButtonSell.setEnabled(false);
                    textViewSell.setText("Purchase consumed");

                    if (avatarPremiumChoice==1){
                        avatarPremium14=true;
                        SharedPreferences prefs4 = BuyPremiumAvatars.this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = prefs4.edit();
                        editor2.putInt("PremiumPoints1", 1);
                        editor2.apply();
                        SharedPreferences prefs3 = BuyPremiumAvatars.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs3.edit();
                        editor.putInt("AvatarChoice", 13);
                        editor.apply();
                        startActivity(new Intent(BuyPremiumAvatars.this, Avatar_Name.class));

                    }
                    else if(avatarPremiumChoice==2){
                        avatarPremium15=true;
                        SharedPreferences prefs4 = BuyPremiumAvatars.this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = prefs4.edit();
                        editor2.putInt("PremiumPoints2", 1);
                        editor2.apply();
                        SharedPreferences prefs3 = BuyPremiumAvatars.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs3.edit();
                        editor.putInt("AvatarChoice", 14);
                        editor.apply();
                        startActivity(new Intent(BuyPremiumAvatars.this, Avatar_Name.class));

                    }
                    else if(avatarPremiumChoice==3){
                        avatarPremium16=true;
                        SharedPreferences prefs4 = BuyPremiumAvatars.this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = prefs4.edit();
                        editor2.putInt("PremiumPoints3", 1);
                        editor2.apply();
                        SharedPreferences prefs3 = BuyPremiumAvatars.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs3.edit();
                        editor.putInt("AvatarChoice", 15);
                        editor.apply();
                        startActivity(new Intent(BuyPremiumAvatars.this, Avatar_Name.class));
                    }
                    else if(avatarPremiumChoice==4){
                        avatarPremium17=true;
                        SharedPreferences prefs4 = BuyPremiumAvatars.this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = prefs4.edit();
                        editor2.putInt("PremiumPoints4", 1);
                        editor2.apply();
                        SharedPreferences prefs3 = BuyPremiumAvatars.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs3.edit();
                        editor.putInt("AvatarChoice", 16);
                        editor.apply();
                        startActivity(new Intent(BuyPremiumAvatars.this, Avatar_Name.class));
                    }
                    else if(avatarPremiumChoice==5){
                        avatarPremium18=true;
                        SharedPreferences prefs4 = BuyPremiumAvatars.this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = prefs4.edit();
                        editor2.putInt("PremiumPoints5", 1);
                        editor2.apply();
                        SharedPreferences prefs3 = BuyPremiumAvatars.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs3.edit();
                        editor.putInt("AvatarChoice", 17);
                        editor.apply();
                        startActivity(new Intent(BuyPremiumAvatars.this, Avatar_Name.class));
                    }
                    else if(avatarPremiumChoice==6){
                        avatarPremium19=true;
                        SharedPreferences prefs4 = BuyPremiumAvatars.this.getSharedPreferences("myPrefsAvatarPremium", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = prefs4.edit();
                        editor2.putInt("PremiumPoints6", 1);
                        editor2.apply();
                        SharedPreferences prefs3 = BuyPremiumAvatars.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs3.edit();
                        editor.putInt("AvatarChoice", 18);
                        editor.apply();
                        startActivity(new Intent(BuyPremiumAvatars.this, Avatar_Name.class));
                    }


                });
            }
        };
        billingClient.consumeAsync(consumeParams, listener);
    }
}




