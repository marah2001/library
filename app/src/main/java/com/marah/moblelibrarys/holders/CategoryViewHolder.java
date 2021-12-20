package com.marah.moblelibrarys.holders;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marah.moblelibrarys.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    private final TextView categoryItemTitle;
    final RelativeLayout categoriesItem;
    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryItemTitle = itemView.findViewById(R.id.categoryItemTitle);
        categoriesItem = itemView.findViewById(R.id.categoriesItem);

    }
    public TextView getTextView() {
        return categoryItemTitle;
    }
}
