package com.orhava.trivia2;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;


public class QuestionAnswerMaster_2 extends AppCompatActivity {
    public static String[] question;
    public static String[][] choices;
    public static String[] correctAnswwrs;
    public static int[] images;

    public static void initializeData(Context context) {
        question = new String[]{
                context.getString(R.string.Guess_The_Country) + " 35", //San_Marino Europe
                context.getString(R.string.Guess_The_Country)+ "  29", //Montenegro Europe
                context.getString(R.string.Guess_The_Country)+ " 28", //Equatorial Guinea africa
                context.getString(R.string.Guess_The_Country)+ " 19", //Turkmenistan Asia
                context.getString(R.string.Guess_The_Country)+ " 35", // Togo Africa
                context.getString(R.string.Guess_The_Country)+ " 15", // Burundi Africa
                context.getString(R.string.Guess_The_Country)+ " 7", //Honduras North America
                context.getString(R.string.Guess_The_Country)+ " 5", //French_Guiana south america
                context.getString(R.string.Guess_The_Country)+ " 43", // Sierra Leone all Africa
                context.getString(R.string.Guess_The_Country)+ " 51", //  São Tomé and Príncip Africa
        };

        choices = new String[][]{
                {context.getString(R.string.San_Marino), context.getString(R.string.Liechtenstein), context.getString(R.string.Vatican_City), context.getString(R.string.Luxembourg)},
                {context.getString(R.string.Montenegro), context.getString(R.string.Albania), context.getString(R.string.Kosovo), context.getString(R.string.Macedonia)},
                {context.getString(R.string.Equatorial_Guinea), context.getString(R.string.Gabon),context.getString(R.string.Sao_Tome_and_Príncipe), context.getString(R.string.Cameroon)},
                {context.getString(R.string.Tajikistan), context.getString(R.string.Kyrgyzstan), context.getString(R.string.Turkmenistan), context.getString(R.string.Uzbekistan)},
                {context.getString(R.string.Togo), context.getString(R.string.Benin), context.getString(R.string.Gabon), context.getString(R.string.Burkina_Faso)},
                {context.getString(R.string.Burundi), context.getString(R.string.Rwanda), context.getString(R.string.Zambia), context.getString(R.string.Mozambique)},
                {context.getString(R.string.Nicaragua), context.getString(R.string.Honduras), context.getString(R.string.El_Salvador), context.getString(R.string.Guatemala)},
                {context.getString(R.string.French_Guiana), context.getString(R.string.Suriname), context.getString(R.string.Ecuador), context.getString(R.string.Guyana)},

                {context.getString(R.string.Sierra_Leone), context.getString(R.string.Guinea_Bissau),context.getString(R.string.Gambia),context.getString(R.string.Liberia)},
                {context.getString(R.string.Sao_Tome_and_Príncipe), context.getString(R.string.Equatorial_Guinea), context.getString(R.string.Lesotho), context.getString(R.string.Burkina_Faso)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.San_Marino),
                context.getString(R.string.Montenegro),
                context.getString(R.string.Equatorial_Guinea),
                context.getString(R.string.Turkmenistan),
                context.getString(R.string.Togo),
                context.getString(R.string.Burundi),
                context.getString(R.string.Honduras),
                context.getString(R.string.French_Guiana),
                context.getString(R.string.Sierra_Leone),
                context.getString(R.string.Sao_Tome_and_Príncipe)
        };

        images = new int[]{
                R.drawable.europe, R.drawable.europe, R.drawable.africa,
                R.drawable.asia, R.drawable.africa, R.drawable.africa,
                R.drawable.north_america, R.drawable.south_america, R.drawable.africa,
                R.drawable.africa
        };
    }
}
