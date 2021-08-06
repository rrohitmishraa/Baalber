package com.trikown.baalber.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.trikown.baalber.R;
import com.trikown.baalber.Utils.CircularScreenReveal;
import com.trikown.baalber.databinding.ActivityLoginBinding;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding b;

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    String accountType;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = b.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        //Circular reveal code
        CircularScreenReveal circularScreenReveal = new CircularScreenReveal(this);
        circularScreenReveal.layoutCheck(savedInstanceState, b.loginRootLayout);

        //Receiving account type from selection screen
        accountType = getIntent().getStringExtra("accountType");

        //different screens for different account types
        if (accountType.equalsIgnoreCase("customer")) {
            b.loginRootLayout.setBackgroundResource(R.drawable.black_splash_screen);
        } else if(accountType.equalsIgnoreCase("shopOwner")) {
            b.loginRootLayout.setBackgroundResource(R.drawable.blue_splash_screen);
        }

        b.btnGoogleLogin.setOnClickListener(v -> createGoogleSignInRequest());
    }

    private void createGoogleSignInRequest() {

        //Configuring Google sign in request
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //Build a GoogleSignInClient with the options specific by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signIn();
    }

    private void signIn() {
        Intent singInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(singInIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Getting the result returned after clicking the G-mail
        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                //Google Sign in Success now authenticate with firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((task) -> {
                    if (task.isSuccessful()) {

                        //if login successful check if user already exist in database
                        db.collection(accountType).document(account.getId()).get()
                                .addOnCompleteListener(task1 -> {
                                    if (task1.
                                            getResult().exists()) { /*check existence of googleId */
                                        Toast.makeText(this, "Welcome Back", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Map<String, Object> userDetails = new HashMap<>();
                                        userDetails.put("UserName", account.getDisplayName());
                                        userDetails.put("Email", account.getEmail());
                                        userDetails.put("ProfilePic", account.getPhotoUrl().toString());

                                        db.collection(accountType)
                                                .document(account.getId())
                                                .set(userDetails)
                                                .addOnSuccessListener(aVoid -> {
                                                    Toast.makeText(LoginActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                                });
                                    }

                                    //save googleId in Shared Preference
                                    SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.sharedData), MODE_PRIVATE).edit();
                                    editor.putString("googleId", account.getId());
                                    editor.putString("accountType", accountType);
                                    editor.apply();
                                });

                        //if login successful, open dashboard
                        startActivity(new Intent(this, DashboardActivity.class));
                        finish();
                    } else {
                        Snackbar.make(findViewById(R.id.loginRootLayout), "Authentication Failed", Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        //Calling reverse reveal from CircularReveal class
        CircularScreenReveal circularScreenReveal = new CircularScreenReveal(LoginActivity.this);
        circularScreenReveal.reverseCircularReveal(b.loginRootLayout);
    }
}