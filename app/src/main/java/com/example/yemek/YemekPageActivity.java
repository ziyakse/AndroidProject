package com.example.yemek;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class YemekPageActivity extends AppCompatActivity {


    ImageView yemekImageView;
    TextView descriptionTextView;
    Button showCommentsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yemek_page);

        yemekImageView = findViewById(R.id.yemekImageView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        showCommentsButton = findViewById(R.id.showCommentsButton);

        Intent intent = getIntent();
        String kategori = intent.getStringExtra("category");
        String yemek = intent.getStringExtra("yemek");

        setTitle(yemek);

        if (kategori.equals("Soup")) {
            if (yemek.equals("Chicken Soup")) {
                yemekImageView.setImageResource(R.drawable.chicken);
                descriptionTextView.setText((getString(R.string.chicken_soup_description)));
                showCommentsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleYemekClick("Chicken Soup","Soup");
                    }
                });
            } else if (yemek.equals("Tomato Soup")) {
                yemekImageView.setImageResource(R.drawable.tomato);
                descriptionTextView.setText((getString(R.string.tomato_soup_description)));
                showCommentsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleYemekClick("Tomato Soup","Soup");
                    }
                });
            }
        } else if (kategori.equals("Dessert")) {
            if (yemek.equals("Cake")) {
                yemekImageView.setImageResource(R.drawable.cake);
                descriptionTextView.setText((getString(R.string.cake_description)));
                showCommentsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleYemekClick("Cake","Dessert");
                    }
                });
            } else if (yemek.equals("Pancake")) {
                yemekImageView.setImageResource(R.drawable.pancake);
                descriptionTextView.setText((getString(R.string.pancake_description)));
                showCommentsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleYemekClick("Pancake","Dessert");
                    }
                });
            }
        }
    }


    private void handleYemekClick(String yemek, String kategori) {
        Intent intent = new Intent(YemekPageActivity.this, Comment.class);
        intent.putExtra("yemek", yemek);
        intent.putExtra("category", kategori); 

        startActivity(intent);
    }

}

