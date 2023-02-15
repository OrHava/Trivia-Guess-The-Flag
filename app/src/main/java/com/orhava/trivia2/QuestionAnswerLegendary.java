package com.orhava.trivia2;


import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionAnswerLegendary extends AppCompatActivity {
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
                {context.getString(R.string.Hungary), context.getString(R.string.Tajikistan), context.getString(R.string.Bulgaria), context.getString(R.string.Guinea)},
                {context.getString(R.string.Serbia), context.getString(R.string.Belarus), context.getString(R.string.Slovenia), context.getString(R.string.Moldova)},
                {context.getString(R.string.Monaco), context.getString(R.string.Malta), context.getString(R.string.Qatar), context.getString(R.string.Bahrain)},
                {context.getString(R.string.Nauru), context.getString(R.string.San_Marino), context.getString(R.string.Liechtenstein), context.getString(R.string.Haiti)},
                {context.getString(R.string.Senegal),context.getString(R.string.Myanmar), context.getString(R.string.Ghana), context.getString(R.string.Cameroon)},
                {context.getString(R.string.Gabon), context.getString(R.string.Luxembourg), context.getString(R.string.Austria), context.getString(R.string.Sierra_Leone)},
                {context.getString(R.string.Comoros),context.getString(R.string.Guinea_Bissau), context.getString(R.string.Equatorial_Guinea), context.getString(R.string.Antigua_and_Barbuda)},
                {context.getString(R.string.Uganda),context.getString(R.string.Uzbekistan), context.getString(R.string.Botswana), context.getString(R.string.Costa_Rica)},
                {context.getString(R.string.Liberia), context.getString(R.string.Samoa), context.getString(R.string.Togo), context.getString(R.string.Chile)},
                {context.getString(R.string.Cameroon), context.getString(R.string.Rwanda),context.getString(R.string.Armenia), context.getString(R.string.Estonia)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.Bulgaria),
                context.getString(R.string.Moldova),
                context.getString(R.string.Qatar),
                context.getString(R.string.San_Marino),
                context.getString(R.string.Myanmar),
                context.getString(R.string.Sierra_Leone),
                context.getString(R.string.Equatorial_Guinea),
                context.getString(R.string.Costa_Rica),
                context.getString(R.string.Liberia),
                context.getString(R.string.Rwanda)
        };

        images = new int[]{
                R.drawable.bulgaria, R.drawable.moldova, R.drawable.qatar,
                R.drawable.san_marino, R.drawable.myanmar, R.drawable.sierra_leone,
                R.drawable.equatorial_guinea, R.drawable.costa_rica, R.drawable.liberia,
                R.drawable.rwanda
        };
    }
}


