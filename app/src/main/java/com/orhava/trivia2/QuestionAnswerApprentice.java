package com.orhava.trivia2;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionAnswerApprentice extends AppCompatActivity {
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
                {context.getString(R.string.Kuwait), context.getString(R.string.Albania), context.getString(R.string.Sri_Lanka), context.getString(R.string.Poland)},
                {context.getString(R.string.Senegal), context.getString(R.string.Myanmar), context.getString(R.string.Cameroon), context.getString(R.string.Venezuela)},
                {context.getString(R.string.New_Zealand), context.getString(R.string.Fiji), context.getString(R.string.Tuvalu), context.getString(R.string.Australia)},
                {context.getString(R.string.Greece), context.getString(R.string.Uruguay), context.getString(R.string.Malaysia), context.getString(R.string.Liberia)},
                {context.getString(R.string.Iceland), context.getString(R.string.Norway), context.getString(R.string.Finland), context.getString(R.string.Denmark)},
                {context.getString(R.string.Burundi), context.getString(R.string.Georgia), context.getString(R.string.Dominica), context.getString(R.string.United_Kingdom)},
                {context.getString(R.string.Uzbekistan), context.getString(R.string.Brunei), context.getString(R.string.Maldives), context.getString(R.string.Malaysia)},
                {context.getString(R.string.Monaco), context.getString(R.string.Belarus), context.getString(R.string.Ukraine), context.getString(R.string.Indonesia)},
                {context.getString(R.string.Laos), context.getString(R.string.Niger), context.getString(R.string.Bangladesh), context.getString(R.string.Japan)},
                {context.getString(R.string.Lithuania), context.getString(R.string.Switzerland), context.getString(R.string.Netherlands), context.getString(R.string.Latvia)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.Albania),
                context.getString(R.string.Venezuela),
                context.getString(R.string.New_Zealand),
                context.getString(R.string.Greece),
                context.getString(R.string.Finland),
                context.getString(R.string.Georgia),
                context.getString(R.string.Malaysia),
                context.getString(R.string.Belarus),
                context.getString(R.string.Bangladesh),
                context.getString(R.string.Netherlands)
        };

        images = new int[]{
                R.drawable.albania, R.drawable.venezuela, R.drawable.new_zealand,
                R.drawable.greece, R.drawable.finland, R.drawable.georgia,
                R.drawable.malaysia, R.drawable.belarus, R.drawable.bangladesh,
                R.drawable.netherlands
        };
    }
}
