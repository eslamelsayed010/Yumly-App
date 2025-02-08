package com.example.yumly.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.yumly.R;

public class SignupUi extends AppCompatActivity {
    Button signupWithEmailBtn;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_ui);

        signupWithEmailBtn = findViewById(R.id.signup_id);
        textView = findViewById(R.id.text_button_log_in_id);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupUi.this, Login.class);
                startActivity(intent);
            }
        });

        signupWithEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupUi.this, SignupWithEmail.class);
                startActivity(intent);
            }
        });
    }
}