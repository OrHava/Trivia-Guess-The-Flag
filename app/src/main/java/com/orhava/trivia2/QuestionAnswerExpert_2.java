package com.orhava.trivia2;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;


public class QuestionAnswerExpert_2 extends AppCompatActivity {
    public static String[] question;
    public static String[][] choices;
    public static String[] correctAnswwrs;
    public static int[] images;

    public static void initializeData(Context context) {
        question = new String[]{
                context.getString(R.string.Guess_The_Country) + " 28", //Bosnia and Herzegovina Europe
                context.getString(R.string.Guess_The_Country)+ "  41", //Luxembourg Europe
                context.getString(R.string.Guess_The_Country)+ " 14", //Slovakia Europe
                context.getString(R.string.Guess_The_Country)+ " 36", //Laos Asia
                context.getString(R.string.Guess_The_Country)+ " 8", // Lesotho Africa
                context.getString(R.string.Guess_The_Country)+ " 13", // Uganda Africa
                context.getString(R.string.Guess_The_Country)+ " 8", //Nicaragua North America
                context.getString(R.string.Guess_The_Country)+ " 10", //Paraguay south america
                context.getString(R.string.Guess_The_Country)+ " 25", // Niger africa
                context.getString(R.string.Guess_The_Country)+ " 21", // Tajikistan Asia
        };

        choices = new String[][]{
                {context.getString(R.string.Bosnia), context.getString(R.string.Montenegro), context.getString(R.string.Albania), context.getString(R.string.Croatia)},
                {context.getString(R.string.Luxembourg), context.getString(R.string.Liechtenstein), context.getString(R.string.San_Marino), context.getString(R.string.Monaco)},
                {context.getString(R.string.Slovakia), context.getString(R.string.Slovakia),context.getString(R.string.Serbia), context.getString(R.string.Croatia)},
                {context.getString(R.string.Laos), context.getString(R.string.Cambodia), context.getString(R.string.Myanmar), context.getString(R.string.Vietnam)},
                {context.getString(R.string.Lesotho), context.getString(R.string.Eswatini), context.getString(R.string.Zimbabwe), context.getString(R.string.Malawi)},
                {context.getString(R.string.Uganda), context.getString(R.string.Rwanda), context.getString(R.string.Burundi), context.getString(R.string.Tanzania)},
                {context.getString(R.string.Nicaragua), context.getString(R.string.Honduras), context.getString(R.string.El_Salvador), context.getString(R.string.Guatemala)},
                {context.getString(R.string.Paraguay), context.getString(R.string.Bolivia), context.getString(R.string.Uruguay), context.getString(R.string.Peru)},
                {context.getString(R.string.Niger), context.getString(R.string.Chad),context.getString(R.string.Mali),context.getString(R.string.Burkina_Faso)},
                {context.getString(R.string.Tajikistan), context.getString(R.string.Kyrgyzstan), context.getString(R.string.Turkmenistan), context.getString(R.string.Uzbekistan)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.Bosnia),
                context.getString(R.string.Luxembourg),
                context.getString(R.string.Slovakia),
                context.getString(R.string.Laos),
                context.getString(R.string.Lesotho),
                context.getString(R.string.Uganda),
                context.getString(R.string.Nicaragua),
                context.getString(R.string.Paraguay),
                context.getString(R.string.Niger),
                context.getString(R.string.Tajikistan)
        };

        images = new int[]{
                R.drawable.europe, R.drawable.europe, R.drawable.europe,
                R.drawable.asia, R.drawable.africa, R.drawable.africa,
                R.drawable.north_america, R.drawable.south_america, R.drawable.africa,
                R.drawable.asia
        };
    }
}
