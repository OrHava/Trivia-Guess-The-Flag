package com.orhava.trivia2;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;


public class QuestionAnswerLearner_2 extends AppCompatActivity {
    public static String[] question;
    public static String[][] choices;
    public static String[] correctAnswwrs;
    public static int[] images;

    public static void initializeData(Context context) {
        question = new String[]{
                context.getString(R.string.Guess_The_Country) + " 19", //Germany Europe
                context.getString(R.string.Guess_The_Country)+ "  12", //Poland Europe
                context.getString(R.string.Guess_The_Country)+ " 39", //Ireland Europe
                context.getString(R.string.Guess_The_Country)+ " 28", //Mongolia Asia
                context.getString(R.string.Guess_The_Country)+ " 4", // Madagascar Africa
                context.getString(R.string.Guess_The_Country)+ " 10", // Yemen Middle east
                context.getString(R.string.Guess_The_Country)+ " 11", //Cuba North America
                context.getString(R.string.Guess_The_Country)+ " 12", //Argentina south america
                context.getString(R.string.Guess_The_Country)+ " 9", // New_Zealand all Australia
                context.getString(R.string.Guess_The_Country)+ " 10", // Panama North America
        };

        choices = new String[][]{
                {context.getString(R.string.France), context.getString(R.string.Spain), context.getString(R.string.Italy), context.getString(R.string.Germany)},
                {context.getString(R.string.Russia), context.getString(R.string.Ukraine), context.getString(R.string.Poland), context.getString(R.string.Denmark)},
                {context.getString(R.string.Norway), context.getString(R.string.Iceland),context.getString(R.string.United_Kingdom), context.getString(R.string.Ireland)},
                {context.getString(R.string.China), context.getString(R.string.Mongolia), context.getString(R.string.India), context.getString(R.string.Indonesia)},
                {context.getString(R.string.Somalia), context.getString(R.string.Tanzania), context.getString(R.string.Mozambique), context.getString(R.string.Madagascar)},
                {context.getString(R.string.Saudi_Arabia), context.getString(R.string.Yemen), context.getString(R.string.United_Arab_Emirates), context.getString(R.string.Oman)},
                {context.getString(R.string.United_States_Of_America), context.getString(R.string.Canada), context.getString(R.string.Mexico), context.getString(R.string.Cuba)},
                {context.getString(R.string.Brazil), context.getString(R.string.Colombia), context.getString(R.string.Argentina), context.getString(R.string.Peru)},
                {context.getString(R.string.Australia), context.getString(R.string.New_Zealand),context.getString(R.string.Papua_New_Guinea),context.getString(R.string.Malaysia)},
                {context.getString(R.string.Honduras), context.getString(R.string.Panama), context.getString(R.string.Costa_Rica), context.getString(R.string.Nicaragua)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.Germany),
                context.getString(R.string.Poland),
                context.getString(R.string.Ireland),
                context.getString(R.string.Mongolia),
                context.getString(R.string.Madagascar),
                context.getString(R.string.Yemen),
                context.getString(R.string.Cuba),
                context.getString(R.string.Argentina),
                context.getString(R.string.New_Zealand),
                context.getString(R.string.Panama)
        };

        images = new int[]{
                R.drawable.europe, R.drawable.europe, R.drawable.europe,
                R.drawable.asia, R.drawable.africa, R.drawable.middle_east,
                R.drawable.north_america, R.drawable.south_america, R.drawable.all_australia,
                R.drawable.north_america
        };
    }
}

