package com.example.project1;

import static com.example.project1.R.id.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    private boolean userIsLoggedIn = true; // Replace with your authentication logic

    private TextView loginMessage;
    private LinearLayout profileDetailsLayout;
    private TextView nameTextView;
    private TextView emailTextView;
    private Button logoutButton;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(profile);

        loginMessage = findViewById(R.id.loginMessage);
        profileDetailsLayout = findViewById(R.id.profileDetailsLayout);
        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        logoutButton = findViewById(R.id.logoutButton);

        if (userIsLoggedIn) {
            loginMessage.setVisibility(View.GONE);
            profileDetailsLayout.setVisibility(View.VISIBLE);

            // Replace with actual user data
            String name = "John Doe";
            String email = "john@example.com";

            nameTextView.setText("Name: " + name);
            emailTextView.setText("Email: " + email);

            // Add more user details as needed

            logoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openLoginActivity();
                    // Handle logout and navigate to login screen
                }
            });
        } else {
            loginMessage.setVisibility(View.VISIBLE);
            profileDetailsLayout.setVisibility(View.GONE);
        }


        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (itemId == R.id.cart) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                overridePendingTransition(0, 0);
                finish();
                return true;
            } else if (itemId == R.id.profile) {
                return true;
            } else {
                return false;
            }
        });
    }

    private void openLoginActivity() {

        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }
}