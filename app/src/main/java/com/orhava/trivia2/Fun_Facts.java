package com.orhava.trivia2;

import static com.orhava.trivia2.MainMenu.flag;
import static com.orhava.trivia2.MainMenu.i;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Fun_Facts extends AppCompatActivity {
   private  ImageButton navToMain,mute_unmute;
    private Button Btn_Next_Fact;
    private TextView Txt_Fact;
    private ArrayList<String> MyFacts;
    int currentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);
        Objects.requireNonNull(getSupportActionBar()).hide();
        navToMain= findViewById(R.id.navToMain);
        mute_unmute= findViewById(R.id.mute_unmute);
        Txt_Fact= findViewById(R.id.Txt_Fact);
        Btn_Next_Fact= findViewById(R.id.Btn_Next_Fact);
        if (i[0] % 2==0) {
            mute_unmute.setImageResource(R.drawable.unmute_50);


        } else {
            mute_unmute.setImageResource(R.drawable.unmute_50);

        }

        NextFact();

        // Find the AdView element in your layout
        AdView adView = findViewById(R.id.adView);

        // Create an ad request
        AdRequest adRequest = new AdRequest.Builder().build();

        // Load the ad into the AdView
        adView.loadAd(adRequest);


        ButtonsNav();
        Mute_UnMute();

    }

    private void NextFact() {


        MyFacts = new ArrayList<>(Arrays.asList("1. The Vatican City is the smallest country in the world, with an area of just 0.17 square miles.","2. The Maldives is the flattest country in the world, with no land rising more than six feet above sea level.", "3. The world's longest recorded name for a place is Taumatawhakatangihangakoauauotamateaturipukakapikimaungahoronukupokaiwhenuakitanatahu, which is a hill in New Zealand.", "4. The United States has more tornadoes than any other country in the world.","5. Bhutan is the only country in the world that uses Gross National Happiness (GNH) as an official measure of success.","6. The largest castle in the world is Malbork Castle in Poland, which covers an area of over 52 acres.",
                "7. The highest waterfall in the world is Angel Falls in Venezuela, which drops over 3,200 feet.", "8. The only country in the world with no rivers is Saudi Arabia.","9. Russia is the largest country in the world by land area, covering over 17 million square kilometers.","10. The world's largest diamond was found in South Africa in 1905 and weighed over 3,000 carats.", "11. Canada has the longest coastline in the world, stretching over 202,000 kilometers.",
                "12. The world's largest desert is the Sahara, covering over 3.6 million square miles across North Africa.","13. The world's oldest university still in operation is the University of Bologna in Italy, which was founded in 1088.",
                "14. The highest point in Africa is Mount Kilimanjaro, which stands at 19,341 feet.", "15. The Great Barrier Reef in Australia is the world's largest coral reef system, covering over 2,300 kilometers.", "16. The world's largest man-made structure is the Great Wall of China, which stretches over 13,000 miles.","17. The only country in the world that is also a continent is Australia.","18. The world's largest island is Greenland, which covers over 840,000 square miles.", "19. The country with the most languages spoken is Papua New Guinea, with over 820 different languages.", "20. The largest city in the world by population is Tokyo, Japan, with over 37 million people.","21. The smallest mammal in the world is the bumblebee bat, which is found in Thailand and weighs less than a penny.","22. The driest place on earth is the Atacama Desert in Chile, where some areas have not seen rain in over 400 years.", "23. The world's largest oil reserves are located in Venezuela, with over 300 billion barrels.",
                "24. The largest temple in the world is Angkor Wat in Cambodia, covering over 162 hectares.","25. The fastest land animal in the world is the cheetah, which can run up to 70 miles per hour.","26. The country with the highest life expectancy is Japan, where people can expect to live an average of 84 years.", "27. The world's largest cave system is the Mammoth Cave National Park in Kentucky, USA.", "28. The largest freshwater lake in the world by volume is Lake Baikal in Russia, which contains over 20% of the world's freshwater.","29. The tallest building in the world is the Burj Khalifa in Dubai, standing at over 828 meters tall.","30. The world's largest producer of cocoa is Cote d'Ivoire (Ivory Coast), which produces over 40% of the world's cocoa beans."));


        MyFacts.addAll(Arrays.asList(
                "31. Norway is home to the Midnight Sun, where the sun doesn't set for about 76 days during summer.",
                "32. The national animal of Scotland is the unicorn.",
                "33. Iceland is a country with no mosquitoes.",
                "34. Singapore is the only country in the world without farmland.",
                "35. The world's most remote island is Tristan da Cunha, located in the South Atlantic Ocean.",
                "36. The country with the most pyramids is Sudan, not Egypt.",
                "37. There are more than 200 islands in Finland archipelago.",
                "38. The oldest known city in the world is Damascus, Syria.",
                "39. Nepal is the only country without a rectangular flag.",
                "40. The Philippines is the only Asian country where Roman Catholicism is the predominant religion.",
                "41. The world's highest railway is in Peru, running through the Andes.",
                "42. The national symbol of Canada is the maple leaf.",
                "43. Thailand is the only Southeast Asian country never colonized by a European power.",
                "44. The world's largest sand island is Fraser Island in Australia.",
                "45. The longest fence in the world is the Dingo Fence in Australia, stretching over 5,600 kilometers.",
                "46. Greenland has the world's lowest population density.",
                "47. Madagascar is home to the world's smallest chameleon.",
                "48. The longest river in the world, by discharge, is the Amazon River.",
                "49. The world's most densely populated country is Monaco.",
                "50. The country with the most time zones is France, due to its overseas territories.",
                "51. Hawaii is the only U.S. state made up entirely of islands.",
                "52. The world's largest flower, the Rafflesia arnoldii, is found in Indonesia.",
                "53. The first country to grant women the right to vote was New Zealand in 1893.",
                "54. The national symbol of North Korea is the Kimjongilia flower.",
                "55. Finland has the highest coffee consumption per capita in the world.",
                "56. The world's largest tea producer is China.",
                "57. The national sport of Afghanistan is Buzkashi, where horse-mounted players attempt to place a goat carcass in a goal.",
                "58. The country with the most islands is Sweden.",
                "59. New Zealand is the only country with two official national anthems.",
                "60. The world's largest exporter of bananas is Ecuador.",
                "61. The currency of Cambodia is the Riel.",
                "62. The national bird of India is the Indian Peafowl, also known as the peacock.",
                "63. The world's highest battlefield is the Siachen Glacier in the Himalayas.",
                "64. The world's largest gold producer is China.",
                "65. The country with the most lakes is Canada.",
                "66. The national symbol of Mexico is the golden eagle.",
                "67. The world's largest salt flat is the Salar de Uyuni in Bolivia.",
                "68. The national flower of Japan is the cherry blossom.",
                "69. The world's largest producer of wine is Italy.",
                "70. The oldest known flag still in use is the flag of Denmark, adopted in 1219."
        ));




        Btn_Next_Fact.setOnClickListener(view -> {

            currentIndex++;
            if (currentIndex >= MyFacts.size()) {
                currentIndex = 0; // start over if we've reached the end
            }
            Txt_Fact.setText(MyFacts.get(currentIndex));

            fetchImageForFact((MyFacts.get(currentIndex)));


        });

    }

    private void configureNextButtonHelperSound()
    {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.modernclick);

        if (!MainMenu.flag){
            mp.setVolume(0,0);
        }
        else{
            mp.setVolume(0,1);
        }

        mp.start();
    }

    private void ButtonsNav() {
        navToMain.setOnClickListener(view -> {
            configureNextButtonHelperSound();
            startActivity(new Intent(Fun_Facts.this, MainMenu.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);


        });


    }
 //countries i didnt use yet for the quizs: greenland,peru,
 // maldives,syria,thailand,chile,,iceland,singapore,ecuador,cambodia, mexico,bolivia,
    //peru,
    private void fetchImageForFact(String fact) {
        // Convert the fact to a lowercase filename-friendly format
        String factLower = fact.toLowerCase().replaceAll("[.,]", "");
        // Split the fact into words
        String[] words = factLower.split("\\s") ;

        // Reference to the ImageView
        ImageView factImageView = findViewById(R.id.factImageView);

        // Iterate over each word in the fact and check if it contains the image name
        for (String word : words) {
            try {
                // Get the resource ID for the corresponding image
                int imageResId = getResources().getIdentifier(word, "drawable", getPackageName());

                // If the image resource exists, load it locally
                if (imageResId != 0) {
                    factImageView.setImageResource(imageResId);
                    return; // Stop checking once an image is found
                }
            } catch (Resources.NotFoundException e) {
                // Handle exception, e.g., log it
                e.printStackTrace();
            }
        }

        // If no matching image is found, set a default image or leave the ImageView blank
        factImageView.setImageResource(R.drawable.default_flag); // Set to a transparent color or your default image
    }


    private void  Mute_UnMute() {



        mute_unmute.setOnClickListener(view -> {
            i[0]++;
            new Handler();

            if (i[0] % 2==0) {

                flag = true;
                mute_unmute.setImageResource( R.drawable.unmute_50);

            } else {
                flag = false;
                mute_unmute.setImageResource( R.drawable.mute_50);
            }
        });

    }


}