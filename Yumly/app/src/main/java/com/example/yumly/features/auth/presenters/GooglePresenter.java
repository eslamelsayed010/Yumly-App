package com.example.yumly.features.auth.presenters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.FragmentActivity;
import com.example.yumly.R;
import com.example.yumly.data.models.UserModel;
import com.example.yumly.features.auth.views.my_view.GoogleView;
import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class GooglePresenter {

    GoogleView googleView;
    private static final String TAG = "SignupUi";
    private static final int REQ_ONE_TAP = 2;
    private FirebaseAuth mAuth;
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private CallbackManager mCallbackManager;
    View view;
    FragmentActivity requireActivity;
    Context requireContext;

    public GooglePresenter(GoogleView googleView, View view, FragmentActivity requireActivity, Context requireContext) {
        this.googleView = googleView;
        this.view = view;
        this.requireActivity = requireActivity;
        this.requireContext = requireContext;
        initializeAuth();

        if (requireActivity != null) {
            googleSignInLauncher = requireActivity.registerForActivityResult(
                    new ActivityResultContracts.StartIntentSenderForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                            try {
                                SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
                                String idToken = credential.getGoogleIdToken();
                                if (idToken != null) {
                                    firebaseAuthWithGoogle(idToken);
                                }
                            } catch (ApiException e) {
                                Log.e(TAG, "Google Sign-In failed", e);
                            }
                        }
                    }
            );
        } else {
            Log.e(TAG, "Activity is null when initializing GooglePresenter");
        }
    }

    private void initializeAuth() {
        mAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();
    }

    public void setupGoogleSignIn() {
        oneTapClient = Identity.getSignInClient(requireActivity);
        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(requireContext.getString(R.string.default_web_client_id))
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .build();
    }

    public void signInWithGoogle() {
        oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(requireActivity, result -> {
                    try {
                        oneTapClient.beginSignIn(signInRequest)
                                .addOnSuccessListener(requireActivity, r-> {
                                    try {
                                        IntentSenderRequest intentSenderRequest = new IntentSenderRequest.Builder(result.getPendingIntent().getIntentSender()).build();
                                        googleSignInLauncher.launch(intentSenderRequest);
                                    } catch (Exception e) {
                                        Log.e(TAG, "Google Sign-In intent failed", e);
                                    }
                                })
                                .addOnFailureListener(e -> Log.w(TAG, "Google Sign-In failed", e));

                    } catch (Exception e) {
                        Log.e(TAG, "Google Sign-In intent failed", e);
                    }
                })
                .addOnFailureListener(requireActivity, e -> Log.w(TAG, "Google Sign-In failed", e));

    }

    private ActivityResultLauncher<IntentSenderRequest> googleSignInLauncher = requireActivity.registerForActivityResult(
            new ActivityResultContracts.StartIntentSenderForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    try {
                        SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
                        String idToken = credential.getGoogleIdToken();
                        if (idToken != null) {
                            firebaseAuthWithGoogle(idToken);
                        }
                    } catch (ApiException e) {
                        Log.e(TAG, "Google Sign-In failed", e);
                    }
                }
            });

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();

                        UserModel userModel = new UserModel(user.getDisplayName(), user.getEmail(),user.getUid());
                        googleView.onSuccess(userModel);

                    } else {
                        Log.w(TAG, "Google authentication failed", task.getException());
                        googleView.onError("Google authentication failed");
                    }
                });
    }

}
