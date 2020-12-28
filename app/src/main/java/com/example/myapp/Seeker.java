package com.example.myapp;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Seeker extends AppCompatActivity implements SeekerAdapter.OnOfferListner {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button backToMainActivity;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_sucher,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Angebote");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker);

        recyclerView = findViewById(R.id.angeboteSucher);
        layoutManager = new LinearLayoutManager(this);
        adapter = new SeekerAdapter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        backToMainActivity = (Button) findViewById(R.id.backSucher);

        setButtonsListner(backToMainActivity);
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
        if(id == backToMainActivity.getId()) {
            intent =  new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.favorit:
                intent   = new Intent(this, Faviort.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onOfferClick(int position) {
        Bundle extras = new Bundle();
        extras.putInt("position", position);
        Intent intent =  new Intent(this, LookupOffer.class);
        intent.putExtra("class",this.getClass());
        intent.putExtras(extras);
        startActivity(intent);
    }
}