package com.orhava.trivia2;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;



public class QuestionAnswerCompetent_2 extends AppCompatActivity {
    public static String[] question;
    public static String[][] choices;
    public static String[] correctAnswwrs;
    public static int[] images;

    public static void initializeData(Context context) {
        question = new String[]{
                context.getString(R.string.Guess_The_Country) + " 21", //Hungary Europe
                context.getString(R.string.Guess_The_Country)+ "  3", //Estonia Europe
                context.getString(R.string.Guess_The_Country)+ " 5", //Latvia Europe
                context.getString(R.string.Guess_The_Country)+ " 25", //Nepal Asia
                context.getString(R.string.Guess_The_Country)+ " 38", // Western Sahara Africa
                context.getString(R.string.Guess_The_Country)+ " 9", //Kuwait Middle east
                context.getString(R.string.Guess_The_Country)+ " 13", //Haiti North America
                context.getString(R.string.Guess_The_Country)+ " 2", //Venezuela south america
                context.getString(R.string.Guess_The_Country)+ " 6", // East_Timor all Australia
                context.getString(R.string.Guess_The_Country)+ " 21", // Congo africa
        };

        choices = new String[][]{
                {context.getString(R.string.Hungary), context.getString(R.string.Romania), context.getString(R.string.Serbia), context.getString(R.string.Bulgaria)},
                {context.getString(R.string.Sweden), context.getString(R.string.Finland), context.getString(R.string.Estonia), context.getString(R.string.Denmark)},
                {context.getString(R.string.Lithuania), context.getString(R.string.Latvia),context.getString(R.string.Finland), context.getString(R.string.Estonia)},
                {context.getString(R.string.Bangladesh), context.getString(R.string.Myanmar), context.getString(R.string.Buthan), context.getString(R.string.Nepal)},
                {context.getString(R.string.Western_Sahara), context.getString(R.string.Mauritania), context.getString(R.string.Senegal), context.getString(R.string.Morocco)},
                {context.getString(R.string.Qatar), context.getString(R.string.Kuwait), context.getString(R.string.Bahrain), context.getString(R.string.Oman)},
                {context.getString(R.string.Dominican_Republic), context.getString(R.string.Haiti), context.getString(R.string.Jamaica), context.getString(R.string.Cuba)},
                {context.getString(R.string.Venezuela), context.getString(R.string.Colombia), context.getString(R.string.Ecuador), context.getString(R.string.Guyana)},
                {context.getString(R.string.Malaysia), context.getString(R.string.Brunei),context.getString(R.string.Indonesia),context.getString(R.string.East_Timor)},
                {context.getString(R.string.Congo), context.getString(R.string.Angola), context.getString(R.string.Tanzania), context.getString(R.string.South_Sudan)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.Hungary),
                context.getString(R.string.Estonia),
                context.getString(R.string.Latvia),
                context.getString(R.string.Nepal),
                context.getString(R.string.Western_Sahara),
                context.getString(R.string.Kuwait),
                context.getString(R.string.Haiti),
                context.getString(R.string.Venezuela),
                context.getString(R.string.East_Timor),
                context.getString(R.string.Congo)
        };

        images = new int[]{
                R.drawable.europe, R.drawable.europe, R.drawable.europe,
                R.drawable.asia, R.drawable.africa, R.drawable.middle_east,
                R.drawable.north_america, R.drawable.south_america, R.drawable.all_australia,
                R.drawable.africa
        };
    }
}

