package com.orhava.trivia2;

import static com.orhava.trivia2.MainMenu.isMuted;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsActivity extends AppCompatActivity {

    private static int Count = 0; //use private static int globally
    private SharedPreferences sharedPreferences2,prefs3;  //Declare Globally
    SharedPreferences.Editor editor2;      //Declare Globally
    Button EasterEggBtn;
    ImageButton returnAdsButton;
    private  MediaPlayer mp2=null;
    TextView tv;
    ImageButton imgViewAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700,getTheme())));
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        Button btnReset = findViewById(R.id.btnReset);
        Button btnAbout = findViewById(R.id.btnAbout);
        Button btnDeleteAccount = findViewById(R.id.btnDeleteAccount);
        EasterEggBtn = findViewById(R.id.EasterEggBtn);
        tv = findViewById(R.id.easterTxtView);
        tv.setVisibility(View.GONE);
        imgViewAvatar=findViewById(R.id.imgViewAvatar);
        returnAdsButton = findViewById(R.id.returnAdsButton);


        btnDeleteAccount.setOnClickListener(this::onDeleteAccountClick);



        sharedPreferences2 = getApplicationContext().getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        btnReset.setOnClickListener(view -> {


            AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
            alert.setTitle(R.string.delete);
            alert.setMessage(R.string.are_you_sure_you_want_to_delete_memory);
            alert.setPositiveButton(R.string.yes, (dialog, which) -> {
                SharedPreferences.Editor editor = getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE).edit();
                editor.clear();
                editor.apply();
                View rootLayout = findViewById(R.id.RlMainMenu);
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.game_memory_restarted, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
                dialog.dismiss();
            });

            alert.setNegativeButton(R.string.no, (dialog, which) -> {

                View rootLayout = findViewById(R.id.RlMainMenu);
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.game_memory_not_restarted, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();

                dialog.dismiss();
            });

            alert.show();

        });

        returnAdsButton.setOnClickListener(view -> { //here my man


            AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
            alert.setTitle(R.string.return_ads);
            alert.setMessage(R.string.are_you_sure_you_want_to_return_ads);
            alert.setPositiveButton(R.string.yes, (dialog, which) -> {
                PurchaseManager.setRemoveAdsPurchased(this, false);



                View rootLayout = findViewById(R.id.RlMainMenu);
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.game_ads_returned, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();


                dialog.dismiss();
            });

            alert.setNegativeButton(R.string.no, (dialog, which) -> {


                View rootLayout = findViewById(R.id.RlMainMenu);
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.game_ads_has_not_returned, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();


                dialog.dismiss();
            });

            alert.show();

        });

        btnAbout.setOnClickListener(view -> {

            putValueInSharedPrefs(++Count);
            AlertDialog.Builder alert2 = new AlertDialog.Builder(SettingsActivity.this);
            alert2.setTitle(R.string.about);
            alert2.setMessage(R.string.this_game_has_been_meticulously_crafted_by_a_solo_developer_or_hava_special_thanks_go_to_the_invaluable_resources_on_stack_overflow_insightful_content_from_indian_youtubers_and_the_unwavering_support_of_my_wonderful_wife_gisele_hava_for_inquiries_or_collaboration_opportunities_feel_free_to_contact_me_at_or6562_gmail_com_explore_more_of_my_creations_by_visiting_more_apps);
            alert2.setPositiveButton(R.string.dismiss, (dialog, which) -> dialog.dismiss());


            alert2.show();

        });

        mp2 = MediaPlayer.create(this, R.raw.goodresult);

        LottieAnimationView EasterEggAnim=findViewById(R.id.EasterEggAnim);
        EasterEggBtn.setOnClickListener(view -> {

            EasterEggAnim.playAnimation();
            if (!isMuted){
                mp2.setVolume(0,0);
            }
            else{
                mp2.setVolume(0,1);
            }

            mp2.start();
            RunAnimation();
            imgViewAvatar.setVisibility(View.VISIBLE);
            imgViewAvatar.setImageResource(R.mipmap.avatersecret_foreground);

            imgViewAvatar.setOnClickListener(view1 -> {
                prefs3=SettingsActivity.this.getSharedPreferences("myPrefsKeyAvatar", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs3.edit();
                editor.putInt("AvatarChoice", 19);
                editor.apply();

                View rootLayout = findViewById(R.id.RlMainMenu);
                Snackbar snackbar = Snackbar.make(rootLayout, R.string.you_successfully_applied_the_secret_avatar, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                snackbar.show();
            });

        });




    }

    // Add this in your activity's or fragment's onResume() method
    @Override
    protected void onResume() {
        super.onResume();

        // Check if the user is signed in
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        Button deleteAccountButton = findViewById(R.id.btnDeleteAccount);
        // User is signed in, enable the button
        // User is not signed in, disable the button
        deleteAccountButton.setEnabled(currentUser != null);
    }


    public void onDeleteAccountClick(View view) {
        showDeleteAccountConfirmationDialog();
    }

    private void showDeleteAccountConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirm_delete_account);
        builder.setMessage(R.string.are_you_sure_you_want_to_delete_your_account_this_action_cannot_be_undone);

        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            // Call a method to delete the account
            deleteAccount();
        });

        builder.setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    private void deleteAccount() {
        // Use Firebase Authentication to delete the account
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            user.delete()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Account deleted successfully
                            // You can also sign out the user if needed
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(SettingsActivity.this, SignIn.class));
                            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                            // Redirect to the sign-in screen or perform any other action
                        } else {
                            // Handle failure

                            View rootLayout = findViewById(R.id.RlMainMenu);

                            Snackbar snackbar = Snackbar.make(rootLayout, R.string.failed_to_delete_account, Snackbar.LENGTH_SHORT);
                            snackbar.setAction(R.string.ok, v -> snackbar.dismiss()); // Optional: Add an action for the user to dismiss the message
                            snackbar.show();
                        }
                    });
        }
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(SettingsActivity.this, MainMenu.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void putValueInSharedPrefs(int count)
    {
        int currentCounter = sharedPreferences2.getInt("DISMISS_BUTTON_CLICK_COUNT", 0); //0 is the default value
        if (count >currentCounter){
            editor2 = sharedPreferences2.edit();
            editor2.putInt("DISMISS_BUTTON_CLICK_COUNT", count);
            editor2.apply();
        }

        int newCurrentCounter = sharedPreferences2.getInt("DISMISS_BUTTON_CLICK_COUNT", 0); //0 is the default value
        if(newCurrentCounter>=15){
            EasterEggBtn.setVisibility(View.VISIBLE);

        }

    }

    private void RunAnimation()
    {
        tv.setVisibility(View.VISIBLE);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.slide_easter);
        a.reset();
        tv.clearAnimation();
        tv.startAnimation(a);
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

        }
    }
}