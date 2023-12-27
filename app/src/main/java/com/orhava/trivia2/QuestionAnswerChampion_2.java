package com.orhava.trivia2;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;


public class QuestionAnswerChampion_2 extends AppCompatActivity {
    public static String[] question;
    public static String[][] choices;
    public static String[] correctAnswwrs;
    public static int[] images;

    public static void initializeData(Context context) {
        question = new String[]{
                context.getString(R.string.Guess_The_Country) + " 14", //Slovakia Europe
                context.getString(R.string.Guess_The_Country)+ "  34", //Switzerland Europe
                context.getString(R.string.Guess_The_Country)+ " 30", //Albania Europe
                context.getString(R.string.Guess_The_Country)+ " 22", //Afghanistan Asia
                context.getString(R.string.Guess_The_Country)+ " 26", // Nigeria Africa
                context.getString(R.string.Guess_The_Country)+ " 19", // Chad Africa
                context.getString(R.string.Guess_The_Country)+ " 6", //El Salvador North America
                context.getString(R.string.Guess_The_Country)+ " 3", //Guyana south america
                context.getString(R.string.Guess_The_Country)+ " 3", // Philippines all Australia
                context.getString(R.string.Guess_The_Country)+ " 45", // Djibouti Africa
        };

        choices = new String[][]{
                {context.getString(R.string.Slovakia), context.getString(R.string.Hungary), context.getString(R.string.Czech_Republic), context.getString(R.string.Austria)},
                {context.getString(R.string.Switzerland), context.getString(R.string.Belgium), context.getString(R.string.Netherlands), context.getString(R.string.Liechtenstein)},
                {context.getString(R.string.Albania), context.getString(R.string.Kosovo),context.getString(R.string.Macedonia), context.getString(R.string.Montenegro)},
                {context.getString(R.string.Afghanistan), context.getString(R.string.Tajikistan), context.getString(R.string.Kyrgyzstan), context.getString(R.string.Iran)},
                {context.getString(R.string.Nigeria), context.getString(R.string.Niger), context.getString(R.string.Cameroon), context.getString(R.string.Benin)},
                {context.getString(R.string.Chad), context.getString(R.string.Sudan), context.getString(R.string.Niger), context.getString(R.string.Central_African_Republic)},
                {context.getString(R.string.El_Salvador), context.getString(R.string.Nicaragua), context.getString(R.string.Costa_Rica), context.getString(R.string.Belize)},
                {context.getString(R.string.Guyana), context.getString(R.string.French_Guiana), context.getString(R.string.Suriname), context.getString(R.string.Ecuador)},
                {context.getString(R.string.Philippines), context.getString(R.string.Brunei),context.getString(R.string.Indonesia),context.getString(R.string.Malaysia)},
                {context.getString(R.string.Djibouti), context.getString(R.string.Eritrea), context.getString(R.string.Somalia), context.getString(R.string.Tanzania)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.Slovakia),
                context.getString(R.string.Switzerland),
                context.getString(R.string.Albania),
                context.getString(R.string.Afghanistan),
                context.getString(R.string.Nigeria),
                context.getString(R.string.Chad),
                context.getString(R.string.El_Salvador),
                context.getString(R.string.Guyana),
                context.getString(R.string.Philippines),
                context.getString(R.string.Djibouti)
        };

        images = new int[]{
                R.drawable.europe, R.drawable.europe, R.drawable.europe,
                R.drawable.asia, R.drawable.africa, R.drawable.africa,
                R.drawable.north_america, R.drawable.south_america, R.drawable.all_australia,
                R.drawable.africa
        };
    }
}
