package com.example.myapp;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class LookupOffer extends AppCompatActivity  {

    private int position;
    private TextView title;
    private TextView discreption;
    private ImageSwitcher image;
    private TextView preis;
    private TextView roomsNumber;
    private TextView roll;
    private Button back;
    private Button nextImage;
    private Button backImage;
    private  Offer offer ;
    private int nextCounter = 0;
    private Class<?> backToActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Angebot Sicht");
        setContentView(R.layout.activity_lookup_offer);
        getExtraData();

        attribute();
        setOfferAttribute();
        setButtonsListner(back);
        setButtonsListner(nextImage);
        setButtonsListner(backImage);




    }

    private void getExtraData() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        backToActivity = (Class<?>) extras.get("class"); // get which class send the data
        position = extras.getInt("position"); // get data sent from Activity
        if(backToActivity.getSimpleName().equals("Faviort")) {
            ArrayList<Offer> offers = new ArrayList<>();
            for (Offer offer:
                 Buffer.getOffers()) {
                if (offer.isFavourite()){
                    offers.add(offer);
                }
            }
            this.offer = offers.get(position);
        } else {
            this.offer = Buffer.getOffers().get(position);
        }
    }

    private void attribute() {
        title = (TextView) findViewById(R.id.OfferTitle);
        discreption = (TextView) findViewById(R.id.DescriptionOffer);
        preis = (TextView) findViewById(R.id.preisOffer);
        roomsNumber = (TextView) findViewById(R.id.roomsNumberOffer);
        roll = (TextView) findViewById(R.id.rollOffer);
        image = (ImageSwitcher) findViewById(R.id.imageOffer);
        back = (Button)  findViewById(R.id.backToOffers);
        nextImage = (Button)  findViewById(R.id.nextPhoto);
        backImage = (Button)  findViewById(R.id.backPhoto);
    }


    private void setOfferAttribute() {
        image.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                myView.setLayoutParams(new
                        ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));


                return myView;
            }
        });

        title.setText(offer.getTitle());
        discreption.setText(offer.getDescription());
        preis.setText(String.valueOf(offer.getPrice()));
        roomsNumber.setText(String.valueOf(offer.getRoomsNumber()));
        image.setImageDrawable(offer.getImages().get(0).getDrawable());
        if(offer.isRoll()){
            roll.setText("Zum Kaufen");
        } else {
            roll.setText("Zum Vermieten");
        }




    }

    private void setButtonsListner(final Button b) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doJob(b.getId());
            }
        });
    }

    /**
     * doJob method set the action that's occur when button is clicked
     * @param id Button id
     */
    private void doJob(int id) {
       if(id == back.getId()) {
           Intent intent =  new Intent(this, backToActivity);
           startActivity(intent);
       } else if ( id == nextImage.getId()) {
           nextCounter++;
           if(nextCounter == offer.getImages().size()){
               nextCounter = 0;
           }
           image.setImageDrawable(offer.getImages().get(nextCounter).getDrawable());
       } else if (id == backImage.getId()) {
           nextCounter--;
           if(nextCounter < 0) {
               nextCounter = offer.getImages().size() - 1;
           }
           image.setImageDrawable(offer.getImages().get(nextCounter).getDrawable());
       }


    }



}