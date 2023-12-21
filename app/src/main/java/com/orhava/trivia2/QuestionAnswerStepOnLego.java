package com.orhava.trivia2;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;





public class QuestionAnswerStepOnLego extends AppCompatActivity {
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
                {context.getString(R.string.Bhutan), context.getString(R.string.Latvia), context.getString(R.string.Marshall_Islands), context.getString(R.string.Saint_Kitts_and_Nevis)},
                {context.getString(R.string.Cape_Verde), context.getString(R.string.Seychelles), context.getString(R.string.Sierra_Leone), context.getString(R.string.Solomon_Islands)},
                {context.getString(R.string.Denmark), context.getString(R.string.Finland), context.getString(R.string.Sweden), context.getString(R.string.Norway)},
                {context.getString(R.string.Bosnia_and_Herzegovina), context.getString(R.string.Botswana), context.getString(R.string.Eswatini), context.getString(R.string.Lesotho)},
                {context.getString(R.string.Zimbabwe), context.getString(R.string.Malawi), context.getString(R.string.Mozambique), context.getString(R.string.Guinea_Bissau)},
                {context.getString(R.string.Bhutan), context.getString(R.string.Laos), context.getString(R.string.Mongolia), context.getString(R.string.Myanmar)},
                {context.getString(R.string.Pakistan), context.getString(R.string.Algeria), context.getString(R.string.Tunisia), context.getString(R.string.Turkey)},
                {context.getString(R.string.Maldives), context.getString(R.string.Kuwait), context.getString(R.string.Comoros), context.getString(R.string.Western_Sahara)},
                {context.getString(R.string.Botswana), context.getString(R.string.Malawi), context.getString(R.string.Czech_Republic), context.getString(R.string.Costa_Rica)},
                {context.getString(R.string.Lesotho), context.getString(R.string.Zimbabwe), context.getString(R.string.Malawi), context.getString(R.string.Czech_Republic)},
        };

        correctAnswwrs = new String[]{
                context.getString(R.string.Bhutan),
                context.getString(R.string.Seychelles),
                context.getString(R.string.Norway),
                context.getString(R.string.Eswatini),
                context.getString(R.string.Mozambique),
                context.getString(R.string.Myanmar),
                context.getString(R.string.Algeria),
                context.getString(R.string.Comoros),
                context.getString(R.string.Malawi),
                context.getString(R.string.Lesotho),


        };

        images = new int[]{
                R.drawable.bhutan, R.drawable.seychelles, R.drawable.norway,
                R.drawable.eswatini, R.drawable.mozambique, R.drawable.myanmar,
                R.drawable.algeria, R.drawable.comoros, R.drawable.malawi,
                R.drawable.lesotho,
        };
    }
}


