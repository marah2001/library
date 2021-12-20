package com.marah.moblelibrarys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.marah.moblelibrarys.adapters.CategoriesAdapter;

import java.util.ArrayList;

public class LibraryActivity extends AppCompatActivity {
    private RecyclerView libraryCategoriesRecyclerView;
    ArrayList<Category> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        arrayList = new ArrayList<>();
        arrayList.add(0,new Category(1,"A"));
        arrayList.add(1,new Category(2,"b"));
        arrayList.add(2,new Category(3,"c"));
        arrayList.add(3,new Category(4,"d"));
        arrayList.add(4,new Category(5,"e"));
        arrayList.add(5,new Category(6,"f"));
        arrayList.add(6,new Category(7,"g"));
        arrayList.add(7,new Category(8,"h"));
        arrayList.add(8,new Category(9,"i"));
        arrayList.add(9,new Category(10,"j"));
        arrayList.add(10,new Category(11,"k"));
        arrayList.add(11,new Category(12,"l"));
        arrayList.add(12,new Category(13,"m"));
        libraryCategoriesRecyclerView = findViewById(R.id.libraryCategoriesRecyclerView);
        RecyclerView.LayoutManager recyclerLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        libraryCategoriesRecyclerView.setLayoutManager(recyclerLayoutManager);
        CategoriesAdapter cAdapter = new CategoriesAdapter(arrayList,this);
        libraryCategoriesRecyclerView.setAdapter(cAdapter);

    }
}