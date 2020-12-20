package com.example.myapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Faviort extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button backToSeeker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faviort);
        setTitle("Favorite");

        recyclerView = findViewById(R.id.offerFaviort);
        layoutManager = new LinearLayoutManager(this);
        adapter = new FaviortAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        backToSeeker = (Button) findViewById(R.id.backSeeker);

        setButtonsListner(backToSeeker);
    }

    private void setButtonsListner(final Button b) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doJob(b.getId());
            }
        });
    }

    private void doJob(int id) {
        Intent intent = null;
        if(id == backToSeeker.getId()) {
            intent =  new Intent(this, Seeker.class);
            startActivity(intent);
        }

    }
}