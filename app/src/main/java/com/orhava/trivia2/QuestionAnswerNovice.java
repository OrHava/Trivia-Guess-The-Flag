package com.orhava.trivia2;


import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionAnswerNovice extends AppCompatActivity {
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
                {context.getString(R.string.Argentina), context.getString(R.string.Botswana), context.getString(R.string.El_Salvador), context.getString(R.string.Israel)},
                {context.getString(R.string.Portugal), context.getString(R.string.Belgium), context.getString(R.string.Italy), context.getString(R.string.Denmark)},
                {context.getString(R.string.Jamaica), context.getString(R.string.Christmas_Island),context.getString(R.string.Nigeria), context.getString(R.string.Djibouti)},
                {context.getString(R.string.Brazil), context.getString(R.string.Bolivia), context.getString(R.string.Guatemala), context.getString(R.string.Colombia)},
                {context.getString(R.string.Seychelles), context.getString(R.string.Saint_Vincent_and_the_Grenadines), context.getString(R.string.New_Zealand), context.getString(R.string.Australia)},
                {context.getString(R.string.Myanmar), context.getString(R.string.China), context.getString(R.string.Laos), context.getString(R.string.Vietnam)},
                {context.getString(R.string.Belarus), context.getString(R.string.Russia), context.getString(R.string.Denmark), context.getString(R.string.Estonia)},
                {context.getString(R.string.China), context.getString(R.string.Belgium), context.getString(R.string.Laos), context.getString(R.string.Japan)},
                {context.getString(R.string.Saudi_Arabia), context.getString(R.string.Iraq),context.getString(R.string.Egypt),context.getString(R.string.Djibouti)},
                {context.getString(R.string.Iceland), context.getString(R.string.Switzerland), context.getString(R.string.Sweden), context.getString(R.string.Finland)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.Israel),
                context.getString(R.string.Italy),
                context.getString(R.string.Jamaica),
                context.getString(R.string.Brazil),
                context.getString(R.string.Australia),
                context.getString(R.string.China),
                context.getString(R.string.Russia),
                context.getString(R.string.Japan),
                context.getString(R.string.Saudi_Arabia),
                context.getString(R.string.Sweden)
        };

        images = new int[]{
                R.drawable.israel, R.drawable.italy, R.drawable.jamaica,
                R.drawable.brazil, R.drawable.australia, R.drawable.china,
                R.drawable.russia, R.drawable.japan, R.drawable.saudi_arabia,
                R.drawable.sweden
        };
    }
}

