package com.orhava.trivia2;


import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;





public class QuestionAnswerDeathMarch extends AppCompatActivity {
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
                context.getString(R.string.Guess),

        };

        choices = new String[][]{
                {context.getString(R.string.Cameroon),context.getString(R.string.Senegal), context.getString(R.string.Myanmar),context.getString(R.string.Burkina_Faso)},
                {context.getString(R.string.Serbia),context.getString(R.string.Slovakia),context.getString(R.string.Slovenia),context.getString(R.string.Bulgaria)},
                {context.getString(R.string.Malta),context.getString(R.string.Mona),context.getString(R.string.Poland),context.getString(R.string.Indonesia)},
                {context.getString(R.string.Malawi),context.getString(R.string.Kenya),context.getString(R.string.South_Sudan),context.getString(R.string.Suriname)},
                {context.getString(R.string.Cape_Verde),context.getString(R.string.Nauru),context.getString(R.string.Marshall_islands),context.getString(R.string.Belize)},
                {context.getString(R.string.Angola),context.getString(R.string.East_Timor),context.getString(R.string.Antigua_and_Barbuda),context.getString(R.string.Papua_New_Guinea)},
                {context.getString(R.string.El_Salvador),context.getString(R.string.Honduras),context.getString(R.string.Nicaragua),context.getString(R.string.Guatemala)},
                {context.getString(R.string.Comoros),context.getString(R.string.Grenada),context.getString(R.string.Burundi),context.getString(R.string.Barbados)},
                {context.getString(R.string.Namibia),context.getString(R.string.Seychelles),context.getString(R.string.Solomon_Islands),context.getString(R.string.Congo)},
                {context.getString(R.string.Croatia),context.getString(R.string.Luxembourg),context.getString(R.string.Dominican_Republic),context.getString(R.string.Monaco)},

        };

        correctAnswwrs = new String[]{
                context.getString(R.string.Cameroon),
                context.getString(R.string.Serbia),
                context.getString(R.string.Malta),
                context.getString(R.string.South_Sudan),
                context.getString(R.string.Marshall_islands),
                context.getString(R.string.Papua_New_Guinea),
                context.getString(R.string.Guatemala),
                context.getString(R.string.Barbados),
                context.getString(R.string.Namibia),
                context.getString(R.string.Luxembourg),


        };

        images = new int[]{
                R.drawable.cameroon, R.drawable.serbia, R.drawable.malta, //monaco flag look likes Indonesia so i changed the answer to Indonesia
                R.drawable.southsudan, R.drawable.marshall_islands, R.drawable.papua_new_guinea,
                R.drawable.guatemala, R.drawable.barbados, R.drawable.namibia,
                R.drawable.luxembourg,
        };
    }
}


