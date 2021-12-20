package com.marah.moblelibrarys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {
private CardView libraryCard , favouriteCard , createBookCard , createCategoryCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        libraryCard = findViewById(R.id.libraryCard);
        favouriteCard = findViewById(R.id.favouriteCard);
        createBookCard = findViewById(R.id.createBookCard);
        createCategoryCard = findViewById(R.id.createCategoryCard);
        libraryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LibraryActivity.class);
                startActivity(intent);
            }
        });
        favouriteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FavouriteActivity.class);
                startActivity(intent);
            }
        });
        createBookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddBooksActivity.class);
                startActivity(intent);
            }
        });
        createCategoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCategoriesActivity.class);
                startActivity(intent);
            }
        });
    }
}