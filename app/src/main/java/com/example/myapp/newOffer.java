package com.example.myapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class newOffer extends AppCompatActivity {

    //Request code for camera
    private static final int CAMERA_REQUEST = 11;
    //Request code gallery
    private static final int GALLERY_REQUEST = 9;
    private Button addButton;
    private Button cancelbutton;
    private Button addImageButton;
    private Button takePhotoButton;
    private EditText descerption;
    private EditText preis;
    private EditText title;
    private EditText roomsNumber;
    private ImageView imageView;
    private Buffer buffer;
    private Switch OfferStatus;
    private ArrayList<ImageView> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_offer);
        setTitle("Angebotserstellung");
        elements();

        setButtonsListner(addButton);
        setButtonsListner(cancelbutton);
        setButtonsListner(addImageButton);
        setButtonsListner(takePhotoButton);
        setSwitchListner();

    }

    private void elements() {
        buffer = new Buffer();
        addButton = (Button) findViewById(R.id.add);
        cancelbutton = (Button) findViewById(R.id.cancel);
        addImageButton = (Button) findViewById(R.id.imageAdd);
        takePhotoButton = (Button) findViewById(R.id.takephoto);
        descerption = (EditText) findViewById(R.id.descreption);
        preis = (EditText) findViewById(R.id.preis);
        roomsNumber = (EditText) findViewById(R.id.roomsNumber);
        title = (EditText) findViewById(R.id.title);
        imageView = (ImageView) findViewById(R.id.imageViewNewOffer);
        OfferStatus = (Switch) findViewById(R.id.switch2);
    }

    private void setSwitchListner() {
        OfferStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (OfferStatus.isChecked()) {
                    OfferStatus.setText("Verkaufen");
                } else {
                    OfferStatus.setText("Vermieten");
                }
            }
        });
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
        Intent intent = null;
        if(id == addButton.getId()) { //add new offer to the agent offersList
            Offer offer;
            if(!OfferStatus.isChecked()){
                offer  = new Offer(title.getText().toString(),descerption.getText().toString(),Double.parseDouble(preis.getText().toString()),Integer.parseInt(roomsNumber.getText().toString()),false,imageView,images);
            } else  {
                offer  = new Offer(title.getText().toString(),descerption.getText().toString(),Double.parseDouble(preis.getText().toString()),Integer.parseInt(roomsNumber.getText().toString()),true,imageView, images);
            }
            buffer.addOffer(offer);
            intent   = new Intent(this, Agent.class);
            startActivity(intent);

        } else if(id == cancelbutton.getId()) { // back button
            intent   = new Intent(this, Agent.class);
            startActivity(intent);
        } else if(id == addImageButton.getId()){ // get photo from gallery
            getImageFromGallery();

        } else if(id == takePhotoButton.getId()) {
            //Capture image from camera
            capturePictureFromCamera();
            ImageView image = new ImageView(this);
            image.setImageDrawable(imageView.getDrawable());
            images.add(image);
        }

    }

    private void warningMessage() {
        AlertDialog alertDialog = new AlertDialog.Builder(newOffer.this).create();
        alertDialog.setTitle("Warnung");
        alertDialog.setMessage("Bitte entweder Zum Verkaufen oder Vermieten auswählen");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }




    //Open phone gallery
    private void getImageFromGallery(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageData = data.getData();
            imageView.setImageURI(imageData);

            ImageView image = new ImageView(this);
            image.setImageDrawable(imageView.getDrawable());
            images.add(image);

        } //Handle camera request
        else if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){

            //We need a bitmap variable
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);

            ImageView image = new ImageView(this);
            image.setImageDrawable(imageView.getDrawable());
            images.add(image);
        }
    }

    private void capturePictureFromCamera(){
        Intent cameraIntent = new Intent();
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
}