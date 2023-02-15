package com.orhava.trivia2;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class SignIn extends AppCompatActivity {
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    private TextInputEditText Email,password;
    private Button signIn, forgotPass,btnEnterNormal;
    private MaterialButton login;
    private ImageView sign_in_button;


    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private GoogleSignInClient mGoogleSignInClient;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initialize();
        start();






    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() <= 0;
    }


    void initialize(){

        signIn =findViewById(R.id.signin);
        sign_in_button=findViewById(R.id.sign_in_button);
        login =findViewById(R.id.loginbtn);
        Email=findViewById(R.id.Email);
        password=findViewById(R.id.password1);
        forgotPass =findViewById(R.id.forgotpass);
        btnEnterNormal=findViewById(R.id.btnEnterNormal);

    }

    void start(){

        btnEnterNormal.setOnClickListener(view -> {
            startActivity(new Intent(SignIn.this, MainMenu.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
        });

        forgotPass.setOnClickListener(view -> {
            if(Email == null || isEmpty(Email)){
                Toast.makeText(SignIn.this, ""+R.string.Please_Provide_Email, Toast.LENGTH_SHORT).show();
            }
            else{

                FirebaseAuth auth = FirebaseAuth.getInstance();
                String emailAddress = Objects.requireNonNull(Email.getText()).toString().trim();

                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Email sent.");
                                Toast.makeText(SignIn.this, ""+getString(R.string.Password_Sent_to_Email)+Email.getText().toString()+" Make Sure to Check Your Spam Folder", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(SignIn.this,""+R.string.Password_Not_Sending_to_Email, Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null || user != null){
            gotoProfile();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.Google_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // [END config_signin]

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]


        sign_in_button.setOnClickListener(view -> {
            //signIn();
            ResultLauncher.launch(new Intent(mGoogleSignInClient.getSignInIntent()));
        });

        signIn.setOnClickListener(view -> {
            startActivity(new Intent(SignIn.this, SignUp.class));
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
        });

        login.setOnClickListener(view -> signupEmail());
    }


    public void signupEmail(){
        if(Email==null || Objects.requireNonNull(Email.getText()).toString().isEmpty() ){
            Toast.makeText(this, ""+R.string.Enter_Email, Toast.LENGTH_SHORT).show();
        }
        if(password==null || Objects.requireNonNull(password.getText()).toString().isEmpty() ) {
            Toast.makeText(this, ""+R.string.Enter_Password, Toast.LENGTH_SHORT).show();
        }
        if(password!=null && Email!=null && !(Objects.requireNonNull(password.getText()).toString().isEmpty()) && !(Objects.requireNonNull(Email.getText()).toString().isEmpty())){

            mAuth.signInWithEmailAndPassword(Objects.requireNonNull(Email.getText()).toString().trim(), Objects.requireNonNull(password.getText()).toString().trim())
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            startActivity(new Intent(SignIn.this, MainMenu.class));
                            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignIn.this, ""+R.string.Authentication_failed,
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    });
        }

    }
    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    // [END on_start_check_user]

    // [START onactivityresult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                Toast.makeText(this, ""+getString(R.string.firebaseAuthWithGoogle) + account.getId(), Toast.LENGTH_SHORT).show();
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this, ""+R.string.Google_sign_in_failed, Toast.LENGTH_SHORT).show();
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                        //gotoProfile();
                        startActivity(new Intent(SignIn.this, MainMenu.class));
                        overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        updateUI(null);
                    }
                });
    }
    // [END auth_with_google]

    // [START signin]

    ActivityResultLauncher<Intent> ResultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if(result.getResultCode()== Activity.RESULT_OK){
            Intent intent=result.getData();

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    });



    private void updateUI(FirebaseUser user) {

    }
    private void gotoProfile(){
        Intent intent = new Intent(SignIn.this, MainMenu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}