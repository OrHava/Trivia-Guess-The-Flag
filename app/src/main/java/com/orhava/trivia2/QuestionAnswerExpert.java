package com.orhava.trivia2;






import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionAnswerExpert extends AppCompatActivity {
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
                {context.getString(R.string.Argentina),context.getString(R.string.Ecuador), context.getString(R.string.El_Salvador), context.getString(R.string.Nicaragua)},
                {context.getString(R.string.Romania), context.getString(R.string.Chad), context.getString(R.string.Ireland), context.getString(R.string.Ivory_Coast)},
                {context.getString(R.string.Latvia), context.getString(R.string.Guinea), context.getString(R.string.Bulgaria), context.getString(R.string.Hungary)},
                {context.getString(R.string.Serbia), context.getString(R.string.Slovenia), context.getString(R.string.Slovakia), context.getString(R.string.Croatia)},
                {context.getString(R.string.Tajikistan), context.getString(R.string.Turkmenistan), context.getString(R.string.Kyrgyzstan), context.getString(R.string.Kazakhstan)},
                {context.getString(R.string.Rwanda), context.getString(R.string.Malawi), context.getString(R.string.Germany), context.getString(R.string.Armenia)},
                {context.getString(R.string.Gabon), context.getString(R.string.Austria), context.getString(R.string.Luxembourg), context.getString(R.string.Estonia)},
                {context.getString(R.string.Sudan), context.getString(R.string.Iraq), context.getString(R.string.Egypt), context.getString(R.string.Iran)},
                {context.getString(R.string.Saint_Kitts_And_Nevis), context.getString(R.string.Bosnia), context.getString(R.string.Solomon_Islands), context.getString(R.string.Djibouti)},
                {context.getString(R.string.Mongolia), context.getString(R.string.Andorra), context.getString(R.string.Spain), context.getString(R.string.Mexico)}
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.El_Salvador),
                context.getString(R.string.Ireland),
                context.getString(R.string.Hungary),
                context.getString(R.string.Slovakia),
                context.getString(R.string.Tajikistan),
                context.getString(R.string.Armenia),
                context.getString(R.string.Luxembourg),
                context.getString(R.string.Iraq),
                context.getString(R.string.Bosnia),
                context.getString(R.string.Andorra)
        };

        images = new int[]{
                R.drawable.el_salvador, R.drawable.ireland, R.drawable.hungary,
                R.drawable.slovakia, R.drawable.tajikistan, R.drawable.armenia,
                R.drawable.luxembourg, R.drawable.iraq, R.drawable.bosnia,
                R.drawable.andorra
        };
    }
}














