package com.orhava.trivia2;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionAnswerMaster extends AppCompatActivity {
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
                {context.getString(R.string.Kazakhstan), context.getString(R.string.Afghanistan), context.getString(R.string.Pakistan), context.getString(R.string.Kyrgyzstan)},
                {context.getString(R.string.Christmas_Island), context.getString(R.string.Tonga), context.getString(R.string.Tuvalu), context.getString(R.string.Fiji)},
                {context.getString(R.string.Nicaragua), context.getString(R.string.El_Salvador), context.getString(R.string.Paraguay), context.getString(R.string.Ecuador)},
                {context.getString(R.string.Haiti), context.getString(R.string.San_Marino), context.getString(R.string.Lesotho), context.getString(R.string.Burkina_Faso)},
                {context.getString(R.string.Indonesia), context.getString(R.string.Monaco), context.getString(R.string.Poland), context.getString(R.string.Malta)},
                {context.getString(R.string.Tonga), context.getString(R.string.Vatican_City),context.getString(R.string.Liechtenstein), context.getString(R.string.San_Marino)},
                {context.getString(R.string.Zimbabwe), context.getString(R.string.Guinea_Bissau),context.getString(R.string.Samoa), context.getString(R.string.Chile)},
                {context.getString(R.string.Denmark), context.getString(R.string.Iceland), context.getString(R.string.Norway), context.getString(R.string.Finland)},
                {context.getString(R.string.Senegal), context.getString(R.string.Kenya), context.getString(R.string.Rwanda), context.getString(R.string.Ghana)},
                {context.getString(R.string.Mali), context.getString(R.string.Guinea), context.getString(R.string.Niger), context.getString(R.string.Nigeria)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.Afghanistan),
                context.getString(R.string.Tonga),
                context.getString(R.string.Paraguay),
                context.getString(R.string.Lesotho),
                context.getString(R.string.Poland),
                context.getString(R.string.Vatican_City),
                context.getString(R.string.Samoa),
                context.getString(R.string.Denmark),
                context.getString(R.string.Kenya),
                context.getString(R.string.Nigeria)
        };

        images = new int[]{
                R.drawable.afghanistan, R.drawable.tonga, R.drawable.paraguay,
                R.drawable.lesotho, R.drawable.poland, R.drawable.vatican_city,
                R.drawable.samoa, R.drawable.denmark, R.drawable.kenya,
                R.drawable.nigeria
        };
    }
}

