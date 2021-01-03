package com.example.myapp;

import android.widget.ImageView;

import java.util.ArrayList;

public class Offer {
    private String title;
    private String description;
    private double price;
    private int roomsNumber;
    private boolean roll; // das Angebot ist entwerder zum 1 Mieten oder 0 Verkaufen
    private boolean favourite;
    private ArrayList<ImageView> images = new ArrayList<>();



    private ImageView image;


    public Offer(String title, String descreption, double preis, int roomsNumber, boolean roll, ImageView image, ArrayList<ImageView> images) {
        this.title = title;
        this.description = descreption;
        this.price = preis;
        this.roomsNumber = roomsNumber;
        this.roll = roll;
        this.image = image;
        this.images = images;
    }

    public ImageView getImage() {
        return image;
    }
    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getRoomsNumber() {
        return roomsNumber;
    }

    public boolean isRoll() {
        return roll;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public boolean isFavourite() {
        return favourite;
    }


    public String getTitle() {
        return title;
    }

    public ArrayList<ImageView> getImages() {
        return images;
    }

}
