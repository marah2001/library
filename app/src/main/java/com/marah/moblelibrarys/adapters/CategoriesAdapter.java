package com.marah.moblelibrarys.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marah.moblelibrarys.AddBooksActivity;
import com.marah.moblelibrarys.BooksOfCategoriesActivity;
import com.marah.moblelibrarys.Category;
import com.marah.moblelibrarys.R;
import com.marah.moblelibrarys.holders.CategoryViewHolder;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private ArrayList<Category> categoriesTitlesList;
    private Context context;

    public CategoriesAdapter(ArrayList<Category> categoriesTitlesList, Context context) {
        this.categoriesTitlesList = categoriesTitlesList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categoriesitem, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
     //   holder.getTextView().setText(categoriesTitlesList.indexOf(position));
//        holder.categoriesItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Context, BooksOfCategoriesActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return categoriesTitlesList.size();
    }

}
