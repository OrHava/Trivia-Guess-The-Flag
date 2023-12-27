package com.orhava.trivia2;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;


public class QuestionAnswerApprentice_2 extends AppCompatActivity {
    public static String[] question;
    public static String[][] choices;
    public static String[] correctAnswwrs;
    public static int[] images;

    public static void initializeData(Context context) {
        question = new String[]{
                context.getString(R.string.Guess_The_Country) + " 10", //Sweden Europe
                context.getString(R.string.Guess_The_Country)+ "  15", //Romania Europe
                context.getString(R.string.Guess_The_Country)+ " 33", //Belgium Europe
                context.getString(R.string.Guess_The_Country)+ " 17", //Kazakhstan Asia
                context.getString(R.string.Guess_The_Country)+ " 2", // Ethiopia Africa
                context.getString(R.string.Guess_The_Country)+ " 11", // Oman Middle east
                context.getString(R.string.Guess_The_Country)+ " 14", //the Dominican Republic North America
                context.getString(R.string.Guess_The_Country)+ " 11", //Chile south america
                context.getString(R.string.Guess_The_Country)+ " 7", // Papua New Guinea all Australia
                context.getString(R.string.Guess_The_Country)+ " 9", // Costa_Rica North America
        };

        choices = new String[][]{
                {context.getString(R.string.Denmark), context.getString(R.string.Finland), context.getString(R.string.Norway), context.getString(R.string.Sweden)},
                {context.getString(R.string.Romania), context.getString(R.string.Bulgaria), context.getString(R.string.Moldova), context.getString(R.string.Macedonia)},
                {context.getString(R.string.Liechtenstein), context.getString(R.string.Belgium),context.getString(R.string.Netherlands), context.getString(R.string.Bulgaria)},
                {context.getString(R.string.Kyrgyzstan), context.getString(R.string.Turkmenistan), context.getString(R.string.Kazakhstan), context.getString(R.string.Uzbekistan)},
                {context.getString(R.string.Somalia), context.getString(R.string.Eritrea), context.getString(R.string.Sudan), context.getString(R.string.Ethiopia)},
                {context.getString(R.string.Kuwait), context.getString(R.string.Yemen), context.getString(R.string.United_Arab_Emirates), context.getString(R.string.Oman)},
                {context.getString(R.string.Dominican_Republic), context.getString(R.string.Jamaica), context.getString(R.string.Haiti), context.getString(R.string.Cuba)},
                {context.getString(R.string.Bolivia), context.getString(R.string.Chile), context.getString(R.string.Argentina), context.getString(R.string.Peru)},
                {context.getString(R.string.East_Timor), context.getString(R.string.New_Zealand),context.getString(R.string.Papua_New_Guinea),context.getString(R.string.Malaysia)},
                {context.getString(R.string.Costa_Rica), context.getString(R.string.Belize), context.getString(R.string.Canada), context.getString(R.string.Honduras)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.Sweden),
                context.getString(R.string.Romania),
                context.getString(R.string.Belgium),
                context.getString(R.string.Kazakhstan),
                context.getString(R.string.Ethiopia),
                context.getString(R.string.Oman),
                context.getString(R.string.Dominican_Republic),
                context.getString(R.string.Chile),
                context.getString(R.string.Papua_New_Guinea),
                context.getString(R.string.Costa_Rica)
        };

        images = new int[]{
                R.drawable.europe, R.drawable.europe, R.drawable.europe,
                R.drawable.asia, R.drawable.africa, R.drawable.middle_east,
                R.drawable.north_america, R.drawable.south_america, R.drawable.all_australia,
                R.drawable.north_america
        };
    }
}

