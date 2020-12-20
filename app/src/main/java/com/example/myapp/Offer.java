package com.example.myapp;

import android.widget.ImageView;

public class Offer {
    private String descreption;
    private double preis;
    private int roomsNumber;
    private boolean roll; // das Angebot ist entwerder zum 1 Mieten oder 0 Verkaufen
    private boolean faviort;
    private ImageView image;




    public Offer(String descreption, double preis, int roomsNumber, boolean roll, ImageView image) {
        this.descreption = descreption;
        this.preis = preis;
        this.roomsNumber = roomsNumber;
        this.roll = roll;
        this.image = image;
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
}
