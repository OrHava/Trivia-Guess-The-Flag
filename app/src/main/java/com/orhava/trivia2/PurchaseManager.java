package com.orhava.trivia2;

import android.content.Context;
import android.content.SharedPreferences;

public class PurchaseManager {

    private static final String PREFS_NAME = "PurchasePrefs";
    private static final String KEY_REMOVE_ADS_PURCHASED = "removeAdsPurchased";

    public static void setRemoveAdsPurchased(Context context, boolean purchased) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_REMOVE_ADS_PURCHASED, purchased);
        editor.apply();
    }

    public static boolean isRemoveAdsPurchased(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(KEY_REMOVE_ADS_PURCHASED, false);
    }
}
