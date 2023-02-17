package com.orhava.trivia2;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionAnswerChampion extends AppCompatActivity {
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
                {context.getString(R.string.Myanmar),context.getString(R.string.Cambodia),context.getString(R.string.Mongolia),context.getString(R.string.Laos)},
                {context.getString(R.string.South_Korea),context.getString(R.string.North_Korea),context.getString(R.string.Palau),context.getString(R.string.Taiwan)},
                {context.getString(R.string.Spain),context.getString(R.string.Paraguay),context.getString(R.string.Portugal),context.getString(R.string.Ecuador)},
                {context.getString(R.string.Romania),context.getString(R.string.Moldova),context.getString(R.string.Chad),context.getString(R.string.Andorra)},
                {context.getString(R.string.Eritrea),context.getString(R.string.Zimbabwe),context.getString(R.string.South_Africa),context.getString(R.string.Vanuatu)},
                {context.getString(R.string.Montenegro),context.getString(R.string.Albania),context.getString(R.string.Denmark),context.getString(R.string.Switzerland)},
                {context.getString(R.string.Tonga),context.getString(R.string.Turkey),context.getString(R.string.Tunisia),context.getString(R.string.Libya)},
                {context.getString(R.string.Zambia),context.getString(R.string.Pakistan),context.getString(R.string.Turkmenistan),context.getString(R.string.Azerbaijan)},
                {context.getString(R.string.United_Arab_Emirates),context.getString(R.string.Sudan),context.getString(R.string.Kuwait),context.getString(R.string.Jordan)},
                {context.getString(R.string.Micronesia),context.getString(R.string.South_Sudan),context.getString(R.string.Congo),context.getString(R.string.Somalia)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.Mongolia),
                context.getString(R.string.North_Korea),
                context.getString(R.string.Portugal),
                context.getString(R.string.Romania),
                context.getString(R.string.South_Africa),
                context.getString(R.string.Switzerland),
                context.getString(R.string.Tunisia),
                context.getString(R.string.Turkmenistan),
                context.getString(R.string.United_Arab_Emirates),
                context.getString(R.string.Somalia)
        };

        images = new int[]{
                R.drawable.mongolia, R.drawable.north_korea, R.drawable.portugal,
                R.drawable.romania, R.drawable.south_africa, R.drawable.switzerland,
                R.drawable.tunisia, R.drawable.turkmenistan, R.drawable.uae,
                R.drawable.somalia
        };
    }
}


