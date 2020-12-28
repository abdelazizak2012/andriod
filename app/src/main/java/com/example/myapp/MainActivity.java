package com.example.myapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button sucherButton;
    private Button MaklerButton;
    private static int addOneTime = 0; // for test


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home page");
        sucherButton = (Button) findViewById(R.id.sucher);
        MaklerButton = (Button) findViewById(R.id.makler);

        if(addOneTime == 0) { // for test
            ImageView offerImage = new ImageView(this);
            offerImage.setImageResource(R.drawable.bsp1);
            ImageView offerImage2 = new ImageView(this);
            offerImage2.setImageResource(R.drawable.bsp2);
            ImageView offerImage3 = new ImageView(this);
            offerImage3.setImageResource(R.drawable.bsp3);
            ArrayList<ImageView> offerImages = new ArrayList<ImageView>();
            offerImages.add(offerImage);
            offerImages.add(offerImage2);
            offerImages.add(offerImage3);

            Buffer.getOffers().add(new Offer("Wohnung mit 2 Zimmers", "Wir vermieten in einem Mehrfamilienhaus eine drei Zimmer Wohnung im 3 .OG. Eine bereits eingebaute Küche erspart Ihnen beim Einzug Zeit und Mühe, sodass Sie gewissermaßen sofort mit dem Kochen beginnen können.Ausstattung Erstbezug, 3-Zimmer-Wohnung , Wohn- und Schlafzimmer mit Laminatboden, Küche mit Spülmaschine, Herd, Backofen Kühlschrank. Das Badezimmer ist mit begehbarer Dusche, WC, Waschbecken ausgestattet.", 1000, 3, false,
                    offerImage, offerImages));
            addOneTime++;
        }

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