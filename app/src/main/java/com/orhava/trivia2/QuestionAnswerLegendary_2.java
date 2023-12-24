package com.orhava.trivia2;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;



public class QuestionAnswerLegendary_2 extends AppCompatActivity {
    public static String[] question;
    public static String[][] choices;
    public static String[] correctAnswwrs;
    public static int[] images;

    public static void initializeData(Context context) {
        question = new String[]{
                context.getString(R.string.Guess_The_Country) + " 43", //France Europe
                context.getString(R.string.Guess_The_Country)+ "  1", //Russia Europe
                context.getString(R.string.Guess_The_Country)+ " 17", //Turkey Europe
                context.getString(R.string.Guess_The_Country)+ " 29", //China Asia
                context.getString(R.string.Guess_The_Country)+ " 10", // Egypt Africa
                context.getString(R.string.Guess_The_Country)+ " 7", // Suadai arbia Middle east
                context.getString(R.string.Guess_The_Country)+ " 2", //USA North America
                context.getString(R.string.Guess_The_Country)+ " 9", //Brazil south america
                context.getString(R.string.Guess_The_Country)+ " 8", // Australia all Australia
                context.getString(R.string.Guess_The_Country)+ " 3", // mexcio North America
        };

        choices = new String[][]{
                {context.getString(R.string.France), context.getString(R.string.Spain), context.getString(R.string.Italy), context.getString(R.string.Germany)},
                {context.getString(R.string.Russia), context.getString(R.string.Ukraine), context.getString(R.string.Poland), context.getString(R.string.Denmark)},
                {context.getString(R.string.Turkey), context.getString(R.string.Greece),context.getString(R.string.Iran), context.getString(R.string.Russia)},
                {context.getString(R.string.China), context.getString(R.string.Mongolia), context.getString(R.string.India), context.getString(R.string.Indonesia)},
                {context.getString(R.string.Egypt), context.getString(R.string.Algeria), context.getString(R.string.Sudan), context.getString(R.string.Libya)},
                {context.getString(R.string.Saudi_Arabia), context.getString(R.string.Yemen), context.getString(R.string.United_Arab_Emirates), context.getString(R.string.Oman)},
                {context.getString(R.string.United_States_Of_America), context.getString(R.string.Canada), context.getString(R.string.Mexico), context.getString(R.string.Papua_New_Guinea)},
                {context.getString(R.string.Brazil), context.getString(R.string.Colombia), context.getString(R.string.Argentina), context.getString(R.string.Peru)},
                {context.getString(R.string.Australia), context.getString(R.string.New_Zealand),context.getString(R.string.Papua_New_Guinea),context.getString(R.string.Malaysia)},
                {context.getString(R.string.Mexico), context.getString(R.string.United_States_Of_America), context.getString(R.string.Canada), context.getString(R.string.Honduras)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.France),
                context.getString(R.string.Russia),
                context.getString(R.string.Turkey),
                context.getString(R.string.China),
                context.getString(R.string.Egypt),
                context.getString(R.string.Saudi_Arabia),
                context.getString(R.string.United_States_Of_America),
                context.getString(R.string.Brazil),
                context.getString(R.string.Australia),
                context.getString(R.string.Mexico)
        };

        images = new int[]{
                R.drawable.europe, R.drawable.europe, R.drawable.europe,
                R.drawable.asia, R.drawable.africa, R.drawable.middle_east,
                R.drawable.north_america, R.drawable.south_america, R.drawable.all_australia,
                R.drawable.north_america
        };
    }
}
