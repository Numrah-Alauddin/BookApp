package com.example.bookapp;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BookList extends AppCompatActivity {

    RecyclerView book_rv;
    BookAdapter adapter;
    ArrayList<Book> books;
    FirebaseDatabase database;
    DatabaseReference bokREf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        book_rv=findViewById(R.id.book_rv);

        books=new ArrayList<>();

       // books.add(new Book("123","abc","price","zyxcfd"));
        adapter=new BookAdapter(books,this);
        book_rv.setLayoutManager(new LinearLayoutManager(this));
        book_rv.setAdapter(adapter);
        database=FirebaseDatabase.getInstance();
        bokREf=database.getReference("books");

        bokREf.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Book book=dataSnapshot.getValue(Book.class);
                books.add(book);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
