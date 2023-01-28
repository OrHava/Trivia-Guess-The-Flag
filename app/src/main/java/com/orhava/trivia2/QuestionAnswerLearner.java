package com.orhava.trivia2;

public class QuestionAnswerLearner {


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

            {"Bolivia", "Argentina", "Gabon", "Peru"},
            {"Kiribati", "Albania", "Cyprus", "Sri Lanka"},
            {"Zambia", "Lebanon", "Bahrain", "Kuwait"},
            {"Belgium", "Italy", "France", "Chad"},
            {"Vietnam", "Morocco", "China", "East Timor"},
            {"Egypt", "Iran", "India", "Vietnam"},
            {"Zambia", "Sri Lanka", " Eswatini", "Nepal"},
            {"Malta", "Canada", "Haiti", "Singapore"},
            {"South Korea", "Bangladesh", "Laos", "Palau"},
            {"Antigua and Barbuda", "Vanuatu", "Philippines", "Czech Republic"}
    };

    public static String[] correctAnswwrs = {
            "Argentina",
            "Cyprus",
            "Lebanon",
            "France",
            "Morocco",
            "India",
            "Nepal",
            "Canada",
            "South Korea",
            "Czech Republic"

    };

    public static int[] images = {
            R.drawable.argentina, R.drawable.cyprus, R.drawable.lebanon,
            R.drawable.france, R.drawable.morocco, R.drawable.india,
            R.drawable.nepal, R.drawable.canada, R.drawable.south_korea,
            R.drawable.czech_republic
    };
}
