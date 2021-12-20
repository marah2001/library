package com.marah.moblelibrarys.adapters;

import static com.marah.moblelibrarys.R.drawable.booklogo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marah.moblelibrarys.R;
import com.marah.moblelibrarys.holders.BookViewHolder;
import com.marah.moblelibrarys.holders.CategoryViewHolder;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BookViewHolder> {
    private ArrayList BooksDetailsList;
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booksitem, parent, false);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
//        holder.getTextView().setText(BooksDetailsList.indexOf(position));
        holder.bookFavouriteHeartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               holder.bookFavouriteHeartIcon.setImageDrawable(R.drawable.favoriteheart);
                Toast.makeText(v.getContext(), "Already added into favourite list! ❤", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return BooksDetailsList.size();
    }
}
