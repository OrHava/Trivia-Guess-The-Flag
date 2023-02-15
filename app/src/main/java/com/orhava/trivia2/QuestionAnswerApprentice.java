package com.orhava.trivia2;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionAnswerApprentice extends AppCompatActivity {
    public static String[] question;
    public static String[][] choices;
    public static String[] correctAnswwrs;
    public static int[] images;

    public QuestionAnswerApprentice(Context context) {
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
                {getString(R.string.Kuwait), getString(R.string.Albania), getString(R.string.Sri_Lanka), getString(R.string.Poland)},
                {getString(R.string.Senegal), getString(R.string.Myanmar), getString(R.string.Cameroon), getString(R.string.Venezuela)},
                {getString(R.string.New_Zealand), getString(R.string.Fiji), getString(R.string.Tuvalu), getString(R.string.Australia)},
                {getString(R.string.Greece), getString(R.string.Uruguay), getString(R.string.Malaysia), getString(R.string.Liberia)},
                {getString(R.string.Iceland), getString(R.string.Norway), getString(R.string.Finland), getString(R.string.Denmark)},
                {getString(R.string.Burundi), getString(R.string.Georgia), getString(R.string.Dominica), getString(R.string.United_Kingdom)},
                {getString(R.string.Uzbekistan), getString(R.string.Brunei), getString(R.string.Maldives), getString(R.string.Malaysia)},
                {getString(R.string.Monaco), getString(R.string.Belarus), getString(R.string.Ukraine), getString(R.string.Indonesia)},
                {getString(R.string.Laos), getString(R.string.Niger), getString(R.string.Bangladesh), getString(R.string.Japan)},
                {getString(R.string.Lithuania), getString(R.string.Switzerland), getString(R.string.Netherlands), getString(R.string.Latvia)}
        };

        correctAnswwrs = new String[]{
                getString(R.string.Albania),
                getString(R.string.Venezuela),
                getString(R.string.New_Zealand),
                getString(R.string.Greece),
                getString(R.string.Finland),
                getString(R.string.Georgia),
                getString(R.string.Malaysia),
                getString(R.string.Belarus),
                getString(R.string.Bangladesh),
                getString(R.string.Netherlands)
        };

        images = new int[]{
                R.drawable.albania,  R.drawable.venezuela,  R.drawable.new_zealand,
                R.drawable.greece,  R.drawable.finland,  R.drawable.georgia,
                R.drawable.malaysia,  R.drawable.belarus,  R.drawable.bangladesh,
                R.drawable.netherlands
        };
    }
}
