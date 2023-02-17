package com.orhava.trivia2;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionAnswerCompetent extends AppCompatActivity {
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

                {context.getString(R.string.Sudan), context.getString(R.string.Jordan), context.getString(R.string.South_Sudan), context.getString(R.string.Comoros)},
                {context.getString(R.string.Benin), context.getString(R.string.Togo), context.getString(R.string.Madagascar), context.getString(R.string.Samoa)},
                {context.getString(R.string.Cape_Verde), context.getString(R.string.Tuvalu), context.getString(R.string.Fiji), context.getString(R.string.Tonga)},
                {context.getString(R.string.Vanuatu), context.getString(R.string.Eritrea), context.getString(R.string.Saint_Lucia), context.getString(R.string.Ethiopia)},
                {context.getString(R.string.Seychelles), context.getString(R.string.Bhutan), context.getString(R.string.Brunei), context.getString(R.string.Micronesia)},
                {context.getString(R.string.China), context.getString(R.string.Angola),  context.getString(R.string.Dominican_Republic), context.getString(R.string.Dominica)},
                {context.getString(R.string.Pakistan), context.getString(R.string.United_Arab_Emirates), context.getString(R.string.Oman), context.getString(R.string.Tunisia)},
                {context.getString(R.string.Central_African_Republic), context.getString(R.string.Uganda), context.getString(R.string.Gambia), context.getString(R.string.Costa_Rica)},
                {context.getString(R.string.Syria),context.getString(R.string.Azerbaijan), context.getString(R.string.Libya), context.getString(R.string.Djibouti)},
                {context.getString(R.string.Brunei), context.getString(R.string.Mauritania), context.getString(R.string.Micronesia),  context.getString(R.string.Somalia)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.Sudan),
                context.getString(R.string.Madagascar),
                context.getString(R.string.Fiji),
                context.getString(R.string.Eritrea),
                context.getString(R.string.Bhutan),
                context.getString(R.string.Dominica),
                context.getString(R.string.Oman),
                context.getString(R.string.Uganda),
                context.getString(R.string.Libya),
                context.getString(R.string.Micronesia)
        };

        images = new int[]{
                R.drawable.sudan, R.drawable.madagascar, R.drawable.fiji,
                R.drawable.eritrea, R.drawable.bhutan, R.drawable.dominica,
                R.drawable.oman, R.drawable.uganda, R.drawable.libyanew,
                R.drawable.micronesia
        };
    }
}

