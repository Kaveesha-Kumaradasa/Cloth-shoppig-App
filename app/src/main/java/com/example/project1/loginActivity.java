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

public class loginActivity extends AppCompatActivity {
    private Button Regbtn;
    private EditText Email, Password;
    private Button Loginbtn;
    private FirebaseAuth firebaseAuth;
    private TextView ForgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Loginbtn = findViewById(R.id.loginbtn);
        ForgotPassword = findViewById(R.id.forgotpassword);

        firebaseAuth = FirebaseAuth.getInstance();

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        Regbtn = findViewById(R.id.regbtn);
        Regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupActivity();

            }
        });

    }

    private void loginUser() {
            String email = Email.getText().toString().trim();
            String password = Password.getText().toString().trim();

            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(loginActivity.this, "Login successful",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(loginActivity.this,
                                    HomeActivity.class));
                            finish();
                        } else {
                            Toast.makeText(loginActivity.this, "Login failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

    }

    public void openSignupActivity() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);

        /*ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_forget,null);
                EditText emailBox = dialogView.findViewById(R.id.emailbox);

                builder.setView(dialogView);
                AlertDialog dialog = builder.create();

                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userEmail = emailBox.getText().toString();

                        if (TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                            Toast.makeText(loginActivity.this, "Enter your registered email id", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(loginActivity.this, "Check your email", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(loginActivity.this, "Unable to send, Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

                dialogView.findViewById(R.id.btncancle).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                if (dialog.getWindow() != null){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                dialog.show();
            }
        });*/

    }
}