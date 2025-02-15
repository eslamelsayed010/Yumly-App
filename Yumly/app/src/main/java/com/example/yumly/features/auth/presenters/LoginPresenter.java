//package com.example.yumly.features.auth.presenters;
//
//import android.app.Activity;
//import android.content.Context;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.IntentSenderRequest;
//import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.fragment.app.FragmentActivity;
//import androidx.navigation.Navigation;
//
//import com.example.yumly.R;
//import com.example.yumly.data.models.UserModel;
//import com.example.yumly.features.auth.views.LoginView;
//import com.example.yumly.features.auth.views.LoginViewDirections;
//import com.example.yumly.features.auth.views.my_view.GoogleView;
//import com.example.yumly.features.auth.views.my_view.MyLoginView;
//import com.facebook.CallbackManager;
//import com.google.android.gms.auth.api.identity.BeginSignInRequest;
//import com.google.android.gms.auth.api.identity.Identity;
//import com.google.android.gms.auth.api.identity.SignInClient;
//import com.google.android.gms.auth.api.identity.SignInCredential;
//import com.google.android.gms.common.api.ApiException;
//import com.google.firebase.auth.AuthCredential;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.GoogleAuthProvider;
//
//
//public class LoginPresenter {
//
//    MyLoginView view;
//    private FirebaseAuth mAuth;
//
//
//    public LoginPresenter(MyLoginView view) {
//        this.view = view;
//        mAuth = FirebaseAuth.getInstance();
//    }
//
//    private void loginUser(View view) {
//        view.
//        String email = binding.loginEmailTextFieldId.getEditText().getText().toString().trim();
//        String password = binding.loginPasswordTextFieldId.getEditText().getText().toString().trim();
//
//        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
//            Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Firebase login
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(requireActivity(), task -> {
//                    if (task.isSuccessful()) {
//                        FirebaseUser user = mAuth.getCurrentUser();
//                        Toast.makeText(requireContext(), "Login successful!", Toast.LENGTH_SHORT).show();
//                        String name = email.split("@")[0];
//                        this.user = new UserModel(name, user.getEmail(),user.getUid());
//                        LoginViewDirections.ActionLoginViewToHomeView action = LoginViewDirections.actionLoginViewToHomeView(this.user);
//                        Navigation.findNavController(view).navigate(action);
//                    } else {
//                        Toast.makeText(requireContext(), "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//}
