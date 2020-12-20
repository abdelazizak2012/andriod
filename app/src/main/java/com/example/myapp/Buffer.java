package com.example.myapp;

import java.util.ArrayList;

public class Buffer {

    private static ArrayList<Offer> offers = new ArrayList<Offer>();



    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    public void deleteOffer(Offer offer) {
        offers.remove(offer);
    }

    public static  ArrayList<Offer> getOffers() {
        return offers;
    }
}
