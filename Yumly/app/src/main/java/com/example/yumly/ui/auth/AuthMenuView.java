package com.example.yumly.ui.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.yumly.R;
import com.example.yumly.databinding.FragmentAuthMenuBinding;
import com.example.yumly.models.UserModel;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthMenuView extends Fragment {

    FragmentAuthMenuBinding binding;

    private static final String TAG = "SignupUi";
    private static final int REQ_ONE_TAP = 2;
    private FirebaseAuth mAuth;
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private CallbackManager mCallbackManager;
    View view;

    UserModel user;

    public AuthMenuView() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAuthMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        initializeAuth();
        setupFacebookLogin();
        setupGoogleSignIn();
        setupUIActions();
    }

    private void initializeAuth() {
        mAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();
    }

    private final ActivityResultLauncher<Intent> facebookLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> mCallbackManager.onActivityResult(result.getResultCode(), result.getResultCode(), result.getData()));

    private void setupFacebookLogin() {
        binding.loginButtonWithFacebookId.setReadPermissions("email", "public_profile");
        binding.loginButtonWithFacebookId.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "Facebook login cancelled");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "Facebook login error", error);
            }
        });
    }

    private void setupGoogleSignIn() {
        oneTapClient = Identity.getSignInClient(requireActivity());
        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(getString(R.string.default_web_client_id))
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .build();
    }

    private void setupUIActions() {
        binding.textButtonLogInId.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_authMenu_to_loginView));
        binding.signupBtnId.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_authMenu_to_signupView));
        binding.signupGoogleId.setOnClickListener(v -> signInWithGoogle());
    }

    private void signInWithGoogle() {
        oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(requireActivity(), result -> {
                    try {
                        oneTapClient.beginSignIn(signInRequest)
                                .addOnSuccessListener(requireActivity(), r-> {
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
                .addOnFailureListener(requireActivity(), e -> Log.w(TAG, "Google Sign-In failed", e));

    }

    private final ActivityResultLauncher<IntentSenderRequest> googleSignInLauncher = registerForActivityResult(
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


    private final ActivityResultLauncher<Intent> facebookSignInLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> mCallbackManager.onActivityResult(result.getResultCode(), result.getResultCode(), result.getData()));



    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(requireContext(), "Welcome, " + user.getDisplayName(), Toast.LENGTH_LONG).show();
                        this.user = new UserModel(user.getDisplayName(), user.getEmail(),user.getUid());
                        AuthMenuViewDirections.ActionAuthMenuToHomeView action = AuthMenuViewDirections.actionAuthMenuToHomeView(this.user);
                        Navigation.findNavController(view).navigate(action);
                        //Navigation.findNavController(view).navigate(R.id.action_authMenu_to_homeView);
                    } else {
                        Log.w(TAG, "Google authentication failed", task.getException());
                    }
                });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(requireContext(), "Welcome, " + user.getDisplayName(), Toast.LENGTH_LONG).show();
                    } else {
                        Log.w(TAG, "Facebook authentication failed", task.getException());
                        Toast.makeText(requireContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}