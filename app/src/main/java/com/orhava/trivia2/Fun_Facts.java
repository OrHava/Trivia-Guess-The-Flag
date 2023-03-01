package com.orhava.trivia2;

import static com.orhava.trivia2.MainMenu.flag;
import static com.orhava.trivia2.MainMenu.i;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Fun_Facts extends AppCompatActivity {
   private  ImageButton navToMain,mute_unmute,Btn_Next_Fact;
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
            mute_unmute.setImageResource(R.mipmap.mutenewproblem11);

        } else {
            mute_unmute.setImageResource(R.mipmap.mutenewproblem22);
        }

        NextFact();


        ButtonsNav();
        Mute_UnMute();

    }

    private void NextFact() {


        MyFacts = new ArrayList<>(Arrays.asList("1. The Vatican City is the smallest country in the world, with an area of just 0.17 square miles.","2. The Maldives is the flattest country in the world, with no land rising more than six feet above sea level.", "3. The world's longest recorded name for a place is Taumatawhakatangihangakoauauotamateaturipukakapikimaungahoronukupokaiwhenuakitanatahu, which is a hill in New Zealand.", "4. The United States has more tornadoes than any other country in the world.","5. Bhutan is the only country in the world that uses Gross National Happiness (GNH) as an official measure of success.","6. The largest castle in the world is Malbork Castle in Poland, which covers an area of over 52 acres.", "7. The highest waterfall in the world is Angel Falls in Venezuela, which drops over 3,200 feet.", "8. The only country in the world with no rivers is Saudi Arabia.","9. Russia is the largest country in the world by land area, covering over 17 million square kilometers.","10. The world's largest diamond was found in South Africa in 1905 and weighed over 3,000 carats.", "11. Canada has the longest coastline in the world, stretching over 202,000 kilometers.", "12. The world's largest desert is the Sahara, covering over 3.6 million square miles across North Africa.","13. The world's oldest university still in operation is the University of Bologna in Italy, which was founded in 1088.","14. The highest point in Africa is Mount Kilimanjaro, which stands at 19,341 feet.", "15. The Great Barrier Reef in Australia is the world's largest coral reef system, covering over 2,300 kilometers.", "16. The world's largest man-made structure is the Great Wall of China, which stretches over 13,000 miles.","17. The only country in the world that is also a continent is Australia.","18. The world's largest island is Greenland, which covers over 840,000 square miles.", "19. The country with the most languages spoken is Papua New Guinea, with over 820 different languages.", "20. The largest city in the world by population is Tokyo, Japan, with over 37 million people.","21. The smallest mammal in the world is the bumblebee bat, which is found in Thailand and weighs less than a penny.","22. The driest place on earth is the Atacama Desert in Chile, where some areas have not seen rain in over 400 years.", "23. The world's largest oil reserves are located in Venezuela, with over 300 billion barrels.", "24. The largest temple in the world is Angkor Wat in Cambodia, covering over 162 hectares.","25. The fastest land animal in the world is the cheetah, which can run up to 70 miles per hour.","26. The country with the highest life expectancy is Japan, where people can expect to live an average of 84 years.", "27. The world's largest cave system is the Mammoth Cave National Park in Kentucky, USA.", "28. The largest freshwater lake in the world by volume is Lake Baikal in Russia, which contains over 20% of the world's freshwater.","29. The tallest building in the world is the Burj Khalifa in Dubai, standing at over 828 meters tall.","30. The world's largest producer of cocoa is Cote d'Ivoire (Ivory Coast), which produces over 40% of the world's cocoa beans."));
        Btn_Next_Fact.setOnClickListener(view -> {

            currentIndex++;
            if (currentIndex >= MyFacts.size()) {
                currentIndex = 0; // start over if we've reached the end
            }
            Txt_Fact.setText(MyFacts.get(currentIndex));


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

    private void  Mute_UnMute() {



        mute_unmute.setOnClickListener(view -> {
            i[0]++;
            new Handler();

            if (i[0] % 2==0) {

                Toast.makeText(Fun_Facts.this, "UnMute", Toast.LENGTH_SHORT).show();
                flag = true;
                mute_unmute.setImageResource(R.mipmap.mutenewproblem11);

            } else {
                Toast.makeText(Fun_Facts.this, "Mute", Toast.LENGTH_SHORT).show();
                flag = false;
                mute_unmute.setImageResource(R.mipmap.mutenewproblem22);
            }
        });

    }


}