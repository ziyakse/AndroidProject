package com.example.yemek;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;




public class YemekCategoryActivity extends AppCompatActivity {


    TextView yemekTextView1;
    TextView yemekTextView2;


    ImageView yemekImageView1;
    ImageView yemekImageView2;

    String category;

    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemek_category);

        yemekTextView1 = findViewById(R.id.yemekTextView1);
        yemekTextView2 = findViewById(R.id.yemekTextView2);


        yemekImageView1 = findViewById(R.id.yemekImageView1);
        yemekImageView2 = findViewById(R.id.yemekImageView2);


        imageButton = findViewById(R.id.imageButton);


        category = getIntent().getStringExtra("category");
        setTitle(category);



        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(YemekCategoryActivity.this, MainActivity.class));
            }
        });


        String[] yemekler;


        if (category.equals("Soup")) {
            yemekler = new String[]{"Chicken Soup", "Tomato Soup"};

            yemekTextView1.setText(yemekler[0]);
            yemekTextView2.setText(yemekler[1]);


            yemekTextView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleYemekClick("Chicken Soup");
                }
            });

            yemekTextView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleYemekClick("Tomato Soup");
                }
            });



        } else if (category.equals("Dessert")) {
            yemekler = new String[]{"Cake", "Pancake"};

            yemekTextView1.setText(yemekler[0]);
            yemekTextView2.setText(yemekler[1]);


            yemekTextView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleYemekClick("Cake");
                }

            });

            yemekTextView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleYemekClick("Pancake");
                }
            });



        }


         else {
            yemekler = new String[]{};
        }
    }

    private void handleYemekClick(String yemek) {
        Intent intent = new Intent(YemekCategoryActivity.this, YemekPageActivity.class);
        intent.putExtra("yemek", yemek);
        intent.putExtra("category", category);

        startActivity(intent);
    }
}