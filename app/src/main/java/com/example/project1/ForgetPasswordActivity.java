package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    Button resetBtn;
    EditText email;
    FirebaseAuth mAuth;
    String resetEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_forget);

        resetBtn = findViewById(R.id.btnReset);
        email = findViewById(R.id.emailbox);

        mAuth = FirebaseAuth.getInstance();

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetEmail = email.getText().toString().trim();

                if (!TextUtils.isEmpty(resetEmail)) {
                    ResetPassword();
                }else{
                    email.setError("Email field Can't be empty");
                }
            }
        });

    }
    private void ResetPassword(){
        resetBtn.setVisibility(View.INVISIBLE);

        mAuth.sendPasswordResetEmail(resetEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ForgetPasswordActivity.this,"Reset password link to the email", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgetPasswordActivity.this,loginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ForgetPasswordActivity.this,"Error :- " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        resetBtn.setVisibility(View.VISIBLE);
                    }
                });
    }
    public void fpBack(View view) {
        startActivity(new Intent(ForgetPasswordActivity.this, loginActivity.class));
    }



    }
}
