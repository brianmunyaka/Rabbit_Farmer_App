package com.example.rabbitfarmerapp.ui.AdminTasks;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rabbitfarmerapp.R;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegistrationActivity extends AppCompatActivity {

    private EditText nameEditText, phoneEditText, emailEditText, passwordEditText, idNumberEditText;
    private Button registerButton;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        // Initialize Firebase Realtime Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        idNumberEditText = findViewById(R.id.idNumberEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        final String name = nameEditText.getText().toString().trim();
        final String phone = phoneEditText.getText().toString().trim();
        final String email = emailEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();
        final String idNumber = idNumberEditText.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(idNumber)) {
            Toast.makeText(UserRegistrationActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
        } else if (idNumber.length() != 8) {
            Toast.makeText(UserRegistrationActivity.this, "ID number must be 8 digits", Toast.LENGTH_SHORT).show();
        } else {
            // Create user with email and password
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Save user details to Firebase Realtime Database
                                DatabaseReference currentUserDB = mDatabase.child(email.replace(".", ","));
                                currentUserDB.child("name").setValue(name);
                                currentUserDB.child("phone").setValue(phone);
                                currentUserDB.child("idNumber").setValue(idNumber);

                                Toast.makeText(UserRegistrationActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                // Optionally, navigate to the next screen or perform other actions after successful registration
                            } else {
                                Toast.makeText(UserRegistrationActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
