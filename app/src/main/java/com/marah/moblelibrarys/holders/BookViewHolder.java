package com.marah.moblelibrarys.holders;

import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marah.moblelibrarys.R;

public class BookViewHolder extends RecyclerView.ViewHolder {
    private final ImageView bookImageItem;
    public final ImageView bookFavouriteHeartIcon;
    private final TextView  bookTitleItem;
    private final TextView  bookCreatedYearItem;


    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        bookImageItem = itemView.findViewById(R.id.bookImageItem);
        bookFavouriteHeartIcon = itemView.findViewById(R.id.bookFavouriteHeartIcon);
        bookTitleItem = itemView.findViewById(R.id.bookTitleItem);
        bookCreatedYearItem = itemView.findViewById(R.id.bookCreatedYearItem);
    }
}
