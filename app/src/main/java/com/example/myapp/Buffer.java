package com.example.myapp;

import java.util.ArrayList;

public class Buffer {

    private static ArrayList<Offer> offers = new ArrayList<Offer>();
    private static int offerArt = 0;
    private static double price = 0;
    private static int roomsNumber = 0;

    public static void setRoomsNumber(int roomsNumber) {
        Buffer.roomsNumber = roomsNumber;
    }

    public static int getRoomsNumber() {
        return roomsNumber;
    }

    public static void setPrice(double price) {
        Buffer.price = price;
    }

    public static double getPrice() {
        return price;
    }

    public static void setOfferArt(int offerArt) {
        Buffer.offerArt = offerArt;
    }

    public static int getOfferArt() {
        return offerArt;
    }

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
