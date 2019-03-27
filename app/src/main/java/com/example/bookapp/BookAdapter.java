package com.example.bookapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {

    ArrayList<Book> books;
    Context context;
    FirebaseDatabase database;
    DatabaseReference reference;

    public BookAdapter(ArrayList<Book> books, Context context) {
        this.books = books;
        this.context = context;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.book_item,viewGroup,false);
        BookViewHolder bookViewHolder=new BookViewHolder(view);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BookViewHolder bookViewHolder, int i) {

        final Book book=books.get(i);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("books").child(book.getBookId());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DecimalFormat decimalFormat=new DecimalFormat("#.#");
                float avg=dataSnapshot.child("avgRating").getValue(Float.class);
                bookViewHolder.price.setText(decimalFormat.format(avg));

                bookViewHolder.view.setText(String.valueOf(dataSnapshot.child("viewCount").getValue(Long.class)));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        bookViewHolder.name.setText(book.getName());


        Glide.with(context).load(book.getImage()).into(bookViewHolder.image);
        bookViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("bookId",book.getBookId());
                Intent intent=new Intent(context,BookDetail.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
