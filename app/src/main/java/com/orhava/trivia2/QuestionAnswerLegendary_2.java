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
                context.getString(R.string.Guess_The_Country) + " 44", //Monaco Europe
                context.getString(R.string.Guess_The_Country)+ "  24", //Macedonia Europe
                context.getString(R.string.Guess_The_Country)+ " 29", //Gabon
                context.getString(R.string.Guess_The_Country)+ " 30", //Buthan Asia
                context.getString(R.string.Guess_The_Country)+ " 23", // Namibia Africa
                context.getString(R.string.Guess_The_Country)+ " 16", // Zambia Africa
                context.getString(R.string.Guess_The_Country)+ " 48", //Malawi Africa
                context.getString(R.string.Guess_The_Country)+ " 4", //Suriname south america
                context.getString(R.string.Guess_The_Country)+ " 44", // Liberia all Africa
                context.getString(R.string.Guess_The_Country)+ " 8", //  Lesotho Africa
        };

        choices = new String[][]{
                {context.getString(R.string.San_Marino), context.getString(R.string.Liechtenstein), context.getString(R.string.Monaco), context.getString(R.string.Luxembourg)},
                {context.getString(R.string.Montenegro), context.getString(R.string.Albania), context.getString(R.string.Kosovo), context.getString(R.string.Macedonia)},
                {context.getString(R.string.Equatorial_Guinea), context.getString(R.string.Gabon),context.getString(R.string.Sao_Tome_and_Príncipe), context.getString(R.string.Cameroon)},
                {context.getString(R.string.Buthan), context.getString(R.string.Bangladesh), context.getString(R.string.Nepal), context.getString(R.string.Myanmar)},
                {context.getString(R.string.Namibia), context.getString(R.string.Angola), context.getString(R.string.Zambia), context.getString(R.string.Mozambique)},
                {context.getString(R.string.Burundi), context.getString(R.string.Rwanda), context.getString(R.string.Zambia), context.getString(R.string.Mozambique)},
                {context.getString(R.string.Malawi), context.getString(R.string.Guinea), context.getString(R.string.Comoros), context.getString(R.string.Djibouti)},
                {context.getString(R.string.French_Guiana), context.getString(R.string.Suriname), context.getString(R.string.Ecuador), context.getString(R.string.Guyana)},

                {context.getString(R.string.Sierra_Leone), context.getString(R.string.Guinea_Bissau),context.getString(R.string.Gambia),context.getString(R.string.Liberia)},
                {context.getString(R.string.Sao_Tome_and_Príncipe), context.getString(R.string.Equatorial_Guinea), context.getString(R.string.Lesotho), context.getString(R.string.Burkina_Faso)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.Monaco),
                context.getString(R.string.Macedonia),
                context.getString(R.string.Gabon),
                context.getString(R.string.Buthan),
                context.getString(R.string.Namibia),
                context.getString(R.string.Zambia),
                context.getString(R.string.Malawi),
                context.getString(R.string.Suriname),
                context.getString(R.string.Liberia),
                context.getString(R.string.Lesotho)
        };

        images = new int[]{
                R.drawable.europe, R.drawable.europe, R.drawable.africa,
                R.drawable.asia, R.drawable.africa, R.drawable.africa,
                R.drawable.africa, R.drawable.south_america, R.drawable.africa,
                R.drawable.africa
        };
    }
}
