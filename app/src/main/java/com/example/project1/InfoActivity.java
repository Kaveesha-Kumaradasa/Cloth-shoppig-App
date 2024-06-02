/*IM/2020/076*/
package com.example.project1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    Button backbutton;
    Button addbutton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Retrieve the image number from the intent
        Intent intent = getIntent();
        int imageNumber = intent.getIntExtra("imageNumber", 1); // Default to 1 if not found

        // Find your image and price views
        ImageView imageView = findViewById(R.id.imageView);
        TextView priceTextView = findViewById(R.id.textView);

        // Set the image based on the image number
        switch (imageNumber) {
            case 1:
                imageView.setImageResource(R.drawable.image1);
                priceTextView.setText("Rs. 3000");
                break;
            case 2:
                imageView.setImageResource(R.drawable.image2);
                priceTextView.setText("Rs. 2500");
                break;
            case 3:
                imageView.setImageResource(R.drawable.image3);
                priceTextView.setText("Rs. 2650");
                break;
            case 4:
                imageView.setImageResource(R.drawable.image4);
                priceTextView.setText("Rs. 3200");
                break;
            case 5:
                imageView.setImageResource(R.drawable.image5);
                priceTextView.setText("Rs. 2000");
                break;
            case 6:
                imageView.setImageResource(R.drawable.image6);
                priceTextView.setText("Rs. 2100");
                break;
            case 7:
                imageView.setImageResource(R.drawable.image7);
                priceTextView.setText("Rs. 2000");
                break;
            case 8:
                imageView.setImageResource(R.drawable.image8);
                priceTextView.setText("Rs. 2500");
                break;

            default:
                // Handle the default case
                break;
        }



        addbutton =findViewById(R.id.addbtn);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfoActivity();
            }
        });

        backbutton = findViewById(R.id.backb);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });


    }


    private void openHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void openInfoActivity() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}
/*IM/2020/076*/