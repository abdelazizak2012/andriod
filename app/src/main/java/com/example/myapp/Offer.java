package com.example.myapp;

import android.widget.ImageView;

import java.util.ArrayList;

public class Offer {
    private String title;
    private String descreption;
    private double preis;
    private int roomsNumber;
    private boolean roll; // das Angebot ist entwerder zum 1 Mieten oder 0 Verkaufen
    private boolean faviort;
    private ArrayList<ImageView> images = new ArrayList<>();



    private ImageView image;


    public Offer(String title, String descreption, double preis, int roomsNumber, boolean roll, ImageView image, ArrayList<ImageView> images) {
        this.title = title;
        this.descreption = descreption;
        this.preis = preis;
        this.roomsNumber = roomsNumber;
        this.roll = roll;
        this.image = image;
        this.images = images;
    }

    public ImageView getImage() {
        return image;
    }
    public String getDescreption() {
        return descreption;
    }

    public double getPreis() {
        return preis;
    }

    public int getRoomsNumber() {
        return roomsNumber;
    }

    public boolean isRoll() {
        return roll;
    }

    public void setFaviort(boolean faviort) {
        this.faviort = faviort;
    }

    public boolean isFaviort() {
        return faviort;
    }


    public String getTitle() {
        return title;
    }

    public ArrayList<ImageView> getImages() {
        return images;
    }

}
