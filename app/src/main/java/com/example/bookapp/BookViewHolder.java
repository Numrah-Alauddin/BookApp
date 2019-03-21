package com.example.bookapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

public class BookViewHolder extends RecyclerView.ViewHolder {

    ImageView image;
    TextView name;
    TextView price;

    public BookViewHolder(@NonNull View itemView) {
        super(itemView);

        image=itemView.findViewById(R.id.book_item_image);
        name=itemView.findViewById(R.id.book_item_name);
        price=itemView.findViewById(R.id.book_item_price);
    }
}
