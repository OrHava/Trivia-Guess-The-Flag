package com.orhava.trivia2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.preference.PreferenceManager;

import java.util.Locale;

public class Utils {


    public static boolean isRewardValid(Context context) {
        // Retrieve the saved timestamp from a unique SharedPreferences file
        SharedPreferences preferences = context.getSharedPreferences("MyRewardPreferences", Context.MODE_PRIVATE);
        long savedTimestamp = preferences.getLong("rewardTimestamp", 0);

        // Calculate the time difference in milliseconds (current time - saved time)
        long currentTimeMillis = System.currentTimeMillis();
        long timeDifference = currentTimeMillis - savedTimestamp;

        // Check if the time difference is less than 24 hours (24 * 60 * 60 * 1000 milliseconds)
        return timeDifference < (24 * 60 * 60 * 1000);
    }

    public static void saveTimestamp(Context context) {
        // Save the current timestamp in a unique SharedPreferences file
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferences preferences = context.getSharedPreferences("MyRewardPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("rewardTimestamp", currentTimeMillis);
        editor.apply();
    }



    public static void makeItFalse(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean("localeSet", false);
        editor.apply();

    }

    public static void setLocale(String langCode, Context context) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());

        // Save the language code and name in the SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("langCode", langCode);
        editor.putString("langName", getLanguageName(langCode, context));
        editor.apply();

        // Check if the locale has not been set yet
        boolean localeSet = preferences.getBoolean("localeSet", false);
        if (!localeSet) {
            // Set the flag and restart the activity
            editor.putBoolean("localeSet", true);
            editor.apply();

            ((Activity) context).recreate();
        } else {
            editor.apply();
        }
    }

    private static String getLanguageName(String langCode, Context context) {
        switch (langCode) {
            case "iw":
                return context.getString(R.string.Hebrew);
            case "en":
                return context.getString(R.string.English);
            case "bn":
                return context.getString(R.string.Bengali);
            case "fil":
                return context.getString(R.string.Filipino);
            case "gl":
                return context.getString(R.string.Spanish);
            case "hi":
                return context.getString(R.string.Hindi);
            case "in":
                return context.getString(R.string.Indonesian);
            case "ms":
                return context.getString(R.string.Malay);
            case "pt":
                return context.getString(R.string.Portuguese);
            default:
                return langCode;
        }
    }

    public static String getCurrentLangCode(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("langCode", "en"); // Default to English if not found
    }

    public static int amountOfGeneralPoints(Context context){
        int points;


        SharedPreferences prefs = context.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
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
        int scoreNewDeathMarch = prefs.getInt("scoreDeathMarch", 0); //0 is the default value
        int scoreNewStepOnLego = prefs.getInt("scoreStepOnLego", 0); //0 is the default value

        int scoreNewNovice_2 = prefs.getInt("scoreNovice_2", 0); //0 is the default value
        int scoreNewLearner_2 = prefs.getInt("scoreLearner_2", 0); //0 is the default value
        int scoreNewApprentice_2 = prefs.getInt("scoreApprentice_2", 0); //0 is the default value
        int scoreNewCompetent_2 = prefs.getInt("scoreCompetent_2", 0); //0 is the default value
        int scoreNewChampion_2 = prefs.getInt("scoreChampion_2", 0); //0 is the default value
        int scoreNewExpert_2 = prefs.getInt("scoreExpert_2", 0); //0 is the default value
        int scoreNewMaster_2 = prefs.getInt("scoreMaster_2", 0); //0 is the default value
        int scoreNewLegendary_2 = prefs.getInt("scoreLegendary_2", 0); //0 is the default value


        points=scoreNewNovice_2 + scoreNewLearner_2+scoreNewApprentice_2+
                scoreNewCompetent_2+scoreNewChampion_2+scoreNewExpert_2+
                scoreNewMaster_2+scoreNewLegendary_2+
                scoreNewDeathMarch +scoreNewStepOnLego +
                scoreNewNovice+scoreNewLearner+scoreNewApprentice+scoreNewCompetent+
                scoreNewChampion+scoreNewExpert+scoreNewMaster+scoreNewLegendary+
                scoreNewDivine+scoreNewMasterYoda+scoreNewBabyYoda;
        return points;
    }
}
