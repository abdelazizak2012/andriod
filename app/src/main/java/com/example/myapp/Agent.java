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

public class Agent extends AppCompatActivity implements AgentAdapter.OnOfferListner{



    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button backToMainActivity;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menue_makler,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent);
        setTitle("Angbote");


        recyclerView = findViewById(R.id.angeboteMakler);
        layoutManager = new LinearLayoutManager(this);
        adapter = new AgentAdapter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        backToMainActivity = (Button) findViewById(R.id.backMakler);

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
        adapter.notifyDataSetChanged();
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.angeboteErstellen:
                intent   = new Intent(this, newOffer.class);
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
        intent.putExtras(extras);
        startActivity(intent);
    }

}



