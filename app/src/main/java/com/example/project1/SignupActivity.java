package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class SignupActivity extends AppCompatActivity {
    private TextView Logintext;
    private EditText Name, Email, Password;
    private Button signupbtn;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    public SignupActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Name = findViewById(R.id.name);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        signupbtn = findViewById(R.id.Signupbtn);
        Logintext = findViewById(R.id.logintext);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        Logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();

            }
        });

        }

    private void openLoginActivity() {
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);

    }

    private void registerUser() {
        final String name_a = Name.getText().toString().trim();
        final String email_a = Email.getText().toString().trim();
        final String password_a = Password.getText().toString().trim();

        firebaseAuth.createUserWithEmailAndPassword(email_a, password_a)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userId = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                        DatabaseReference currentUser = databaseReference.child(userId);
                        currentUser.child("Name").setValue(name_a);
                        currentUser.child("Email").setValue(email_a);

                        Toast.makeText(SignupActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignupActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

