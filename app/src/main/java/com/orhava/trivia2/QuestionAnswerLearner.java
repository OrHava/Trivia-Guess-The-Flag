package com.orhava.trivia2;


import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionAnswerLearner extends AppCompatActivity {
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
                {context.getString(R.string.Bolivia), context.getString(R.string.Argentina), context.getString(R.string.Gabon), context.getString(R.string.Peru)},
                {context.getString(R.string.Kiribati), context.getString(R.string.Albania), context.getString(R.string.Cyprus), context.getString(R.string.Sri_Lanka)},
                {context.getString(R.string.Zambia), context.getString(R.string.Lebanon), context.getString(R.string.Bahrain), context.getString(R.string.Kuwait)},
                {context.getString(R.string.Belgium), context.getString(R.string.Italy), context.getString(R.string.France), context.getString(R.string.Chad)},
                {context.getString(R.string.Vietnam), context.getString(R.string.Morocco), context.getString(R.string.China), context.getString(R.string.East_Timor)},
                {context.getString(R.string.Egypt), context.getString(R.string.Iran), context.getString(R.string.India), context.getString(R.string.Vietnam)},
                {context.getString(R.string.Zambia), context.getString(R.string.Sri_Lanka), context.getString(R.string.Eswatini), context.getString(R.string.Nepal)},
                {context.getString(R.string.Malta), context.getString(R.string.Canada), context.getString(R.string.Haiti), context.getString(R.string.Singapore)},
                {context.getString(R.string.South_Korea), context.getString(R.string.Bangladesh), context.getString(R.string.Laos), context.getString(R.string.Palau)},
                {context.getString(R.string.Antigua_and_Barbuda), context.getString(R.string.Vanuatu),context.getString(R.string.Philippines), context.getString(R.string.Czech_Republic)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.Argentina),
                context.getString(R.string.Cyprus),
                context.getString(R.string.Lebanon),
                context.getString(R.string.France),
                context.getString(R.string.Morocco),
                context.getString(R.string.India),
                context.getString(R.string.Nepal),
                context.getString(R.string.Canada),
                context.getString(R.string.South_Korea),
                context.getString(R.string.Czech_Republic)
        };

        images = new int[]{
                R.drawable.argentina, R.drawable.cyprus, R.drawable.lebanon,
                R.drawable.france, R.drawable.morocco, R.drawable.india,
                R.drawable.nepal, R.drawable.canada, R.drawable.south_korea,
                R.drawable.czech_republic
        };
    }
}

