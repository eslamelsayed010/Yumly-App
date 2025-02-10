package com.example.yumly.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.yumly.R;
import com.example.yumly.databinding.ActivitySignupUiBinding;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignupUi extends AppCompatActivity {

    ActivitySignupUiBinding binding;

    private static final int REQ_ONE_TAP = 2; // Unique request code
    private static final String TAG = "SignupUi";
    private FirebaseAuth mAuth;
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();  // Initialize Firebase Auth before use
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null)
//            startActivity(new Intent(SignupUi.this,Login.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        mAuth = FirebaseAuth.getInstance();
        binding = ActivitySignupUiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize One Tap client
        oneTapClient = Identity.getSignInClient(this);

        // Set up Google sign-in request
        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(getString(R.string.default_web_client_id))
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .build();

        // Handle "Log In" button
        binding.textButtonLogInId.setOnClickListener(v -> {
            Intent intent = new Intent(SignupUi.this, Login.class);
            startActivity(intent);
        });

        // Handle "Sign Up with Email" button
        binding.signupBtnId.setOnClickListener(v -> {
            Intent intent = new Intent(SignupUi.this, SignupWithEmail.class);
            startActivity(intent);
        });

        // Handle "Sign Up with Google" button
        binding.signupGoogleId.setOnClickListener(v -> {
            signInWithGoogle();
        });

        binding.signupFacebookId.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_ONE_TAP) {
            try {
                SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
                String idToken = credential.getGoogleIdToken();

                if (idToken != null) {
                    Log.d(TAG, "Got ID token.");
                    firebaseAuthWithGoogle(idToken);
                }
            } catch (ApiException e) {
                Log.e(TAG, "Sign-in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(firebaseCredential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(SignupUi.this,user.getDisplayName(),Toast.LENGTH_LONG).show();
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        // updateUI(null);
                    }
                });
    }

    public void signInWithGoogle(){
        oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(this, result -> {
                    try {
                        startIntentSenderForResult(result.getPendingIntent().getIntentSender(),
                                REQ_ONE_TAP, null, 0, 0, 0);
                    } catch (Exception e) {
                        Log.e(TAG, "Could not start Google Sign-In intent", e);
                    }
                })
                .addOnFailureListener(this, e -> Log.w(TAG, "Google Sign-In failed", e));
    }
}
