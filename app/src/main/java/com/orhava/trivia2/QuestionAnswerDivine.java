package com.orhava.trivia2;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionAnswerDivine extends AppCompatActivity {
    public static String[] question;
    public static String[][] choices;
    public static String[] correctAnswwrs;
    public static int[] images;

    public static void initializeData(Context context) {
        question = new String[]{
                context.getString(R.string.Guess),
                context.getString(R.string.Guess),
                context.getString(R.string.Guess),
                context.getString(R.string.Guess),
                context.getString(R.string.Guess),
                context.getString(R.string.Guess),
                context.getString(R.string.Guess),
                context.getString(R.string.Guess),
                context.getString(R.string.Guess),
                context.getString(R.string.Guess)
        };

        choices = new String[][]{
                {context.getString(R.string.Eritrea), context.getString(R.string.Antigua_and_Barbuda), context.getString(R.string.Vanuatu), context.getString(R.string.Philippines)},
                {context.getString(R.string.Democratic_Republic_of_the_Congo), context.getString(R.string.Solomon_Islands), context.getString(R.string.Saint_Kitts_And_Nevis), context.getString(R.string.Papua_New_Guinea)},
                {context.getString(R.string.Central_African_Republic), context.getString(R.string.Bahamas), context.getString(R.string.South_Sudan), context.getString(R.string.Mozambique)},
                {context.getString(R.string.Djibouti), context.getString(R.string.Ivory_Coast), context.getString(R.string.San_Marino), context.getString(R.string.Vatican_City)},
                {context.getString(R.string.Seychelles), context.getString(R.string.Saint_Vincent_and_the_Grenadines), context.getString(R.string.New_Zealand), context.getString(R.string.Liechtenstein)},
                {context.getString(R.string.Mozambique), context.getString(R.string.Comoros),context.getString(R.string.Central_African_Republic),context.getString(R.string.Suriname)},
                {context.getString(R.string.Equatorial_Guinea), context.getString(R.string.Saint_Lucia), context.getString(R.string.Djibouti), context.getString(R.string.Sao_Tome_and_Príncipe)},
                {context.getString(R.string.Togo), context.getString(R.string.Tonga), context.getString(R.string.Benin), context.getString(R.string.Madagascar)},
                {context.getString(R.string.Kyrgyzstan), context.getString(R.string.Turkmenistan), context.getString(R.string.Tajikistan), context.getString(R.string.Kazakhstan)},
                {context.getString(R.string.Iceland), context.getString(R.string.Switzerland), context.getString(R.string.Sweden), context.getString(R.string.Norway)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.Vanuatu),
                context.getString(R.string.Saint_Kitts_And_Nevis),
                context.getString(R.string.Mozambique),
                context.getString(R.string.Ivory_Coast),
                context.getString(R.string.Liechtenstein),
                context.getString(R.string.Central_African_Republic),
                context.getString(R.string.Sao_Tome_and_Príncipe),
                context.getString(R.string.Benin),
                context.getString(R.string.Kazakhstan),
                context.getString(R.string.Norway),

        };

        images = new int[]{
                R.drawable.vanuatu, R.drawable.saint_kitts_and_nevis, R.drawable.mozambique,
                R.drawable.c_te_d_ivoire, R.drawable.liechtenstein, R.drawable.central_african_republic,
                R.drawable.sao_tom__and_pr_ncipe, R.drawable.benin, R.drawable.kazakhstan,
                R.drawable.norway
        };
    }
}



