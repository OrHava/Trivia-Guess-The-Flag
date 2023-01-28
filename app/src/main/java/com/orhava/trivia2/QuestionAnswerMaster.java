package com.orhava.trivia2;

public class QuestionAnswerMaster {

    public static String[] question = {
            "Guess the flag",
            "Guess the flag",
            "Guess the flag",
            "Guess the flag",
            "Guess the flag",
            "Guess the flag",
            "Guess the flag",
            "Guess the flag",
            "Guess the flag",
            "Guess the flag"

    };

    public static String[][] choices = {

            {"Kazakhstan", "Afghanistan", "Pakistan", "Kyrgyzstan"},
            {"Christmas Island", "Tonga", "Tuvalu", "Fiji"},
            {"Nicaragua", "El Salvador", "Paraguay", "Ecuador"},
            {"Haiti", "San Marino", "Lesotho", "Burkina Faso"},
            {"Indonesia", "Monaco", "Poland", "Malta"},
            {"Tonga", "Vatican City", "Liechtenstein", "San Marino"},
            {"Zimbabwe", "Guinea Bissau", "Samoa", "Chile"},
            {"Denmark", "Iceland", "Norway", "Finland"},
            {"Senegal", "Kenya", "Rwanda", "Ghana"},
            {"Mali", "Guinea", "Niger", "Nigeria"}
    };

    public static String[] correctAnswwrs = {
            "Afghanistan",
            "Tonga",
            "Paraguay",
            "Lesotho",
            "Poland",
            "Vatican City",
            "Samoa",
            "Denmark",
            "Kenya",
            "Nigeria"

    };

    public static int[] images = {
            R.drawable.afghanistan, R.drawable.tonga, R.drawable.paraguay,
            R.drawable.lesotho, R.drawable.poland, R.drawable.vatican_city,
            R.drawable.samoa, R.drawable.denmark, R.drawable.kenya,
            R.drawable.nigeria
    };
}
