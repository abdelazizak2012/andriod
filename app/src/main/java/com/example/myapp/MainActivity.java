package com.example.myapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Button sucherButton;
    private Button MaklerButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home page");
        sucherButton = (Button) findViewById(R.id.sucher);
        MaklerButton = (Button) findViewById(R.id.makler);

        setButtonsListner(sucherButton);
        setButtonsListner(MaklerButton);




    }

    private void setButtonsListner(final Button b) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchActivity(b.getId());
            }
        });
    }

    private void launchActivity(int id) {
        Intent intent = null;
        if(id == sucherButton.getId()) {
            intent   = new Intent(this, Seeker.class);
        } else if(id == MaklerButton.getId()) {
            intent   = new Intent(this, Agent.class);
        }

        startActivity(intent);
    }
}