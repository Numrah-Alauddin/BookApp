package com.example.bookapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookDetail extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    ImageView image;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        image=findViewById(R.id.detail_image);
        name=findViewById(R.id.detail_name);

        Bundle bundle=getIntent().getExtras();
        final String bookId=bundle.getString("bookId");

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("books").child(bookId);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Book book=dataSnapshot.getValue(Book.class);
                name.setText(book.getName());
                Glide.with(BookDetail.this).load(book.getImage()).into(image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
