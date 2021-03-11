package com.trikown.baalber.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

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
import com.trikown.baalber.R;
import com.trikown.baalber.Utils.CircularScreenReveal;

public class LoginActivity extends AppCompatActivity {

    CoordinatorLayout mLoginRootLayout;
    TextView mBtnGoogleLogin;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mLoginRootLayout = findViewById(R.id.xLoginRootLayout);
        mBtnGoogleLogin = findViewById(R.id.xBtnGoogleLogin);

        //Circular reveal code
        CircularScreenReveal circularScreenReveal = new CircularScreenReveal(this);
        circularScreenReveal.layoutCheck(savedInstanceState, mLoginRootLayout);

        //Receiving account type from selection screen
        String accountType = getIntent().getStringExtra("accountType");

        if (accountType.equalsIgnoreCase("customer")) {
            mLoginRootLayout.setBackgroundResource(R.drawable.black_splash_screen);
        } else {
            mLoginRootLayout.setBackgroundResource(R.drawable.blue_splash_screen);
        }

        mBtnGoogleLogin.setOnClickListener(v -> {
            createGoogleSignInRequest();
        });
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Getting the result returned after clicking the G-mail
        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                //Google Sign in Success now authenticate with firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((task) -> {
                    if(task.isSuccessful()) {
                        startActivity(new Intent(this, DashboardActivity.class));
                        finish();
                    } else {
                        Snackbar.make(findViewById(R.id.xLoginRootLayout), "Authentication Failde", Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Calling reverse reveal from CircularReveal class
            CircularScreenReveal circularScreenReveal = new CircularScreenReveal(LoginActivity.this);
            circularScreenReveal.reverseCircularReveal(mLoginRootLayout);
        } else {
            super.onBackPressed();
        }
    }
}