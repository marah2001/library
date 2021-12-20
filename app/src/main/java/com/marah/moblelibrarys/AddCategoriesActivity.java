package com.marah.moblelibrarys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//ضايل الحفظ في قاعدة البيانات و العرض في الريسايكلر في الواجهة التانية

public class AddCategoriesActivity extends AppCompatActivity {
private EditText categoryTitleET ;
private Button categoryBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_categories);
        categoryBTN = findViewById(R.id.categoryBTN);
        categoryTitleET = findViewById(R.id.categoryTitleET);
        String categoryTitle = categoryTitleET.getText().toString();
        categoryBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(categoryTitle.length()!=0){
                    Toast.makeText(getApplicationContext(), categoryTitle + " is Already Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"you must add a category", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}