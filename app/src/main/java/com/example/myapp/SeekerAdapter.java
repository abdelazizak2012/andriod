package com.example.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SeekerAdapter extends RecyclerView.Adapter<SeekerAdapter.MyViewHolder> {


    OnOfferListner onOfferListner;
    List<Offer> offers;



    public SeekerAdapter(OnOfferListner onOfferListner) {
        this.onOfferListner = onOfferListner;
        if(Buffer.getOfferArt() == 0) {
            offers = Buffer.getOffers().stream()
                    .filter(o -> (o.getPrice() <= Buffer.getPrice() || Buffer.getPrice() == 0) && (o.getRoomsNumber() <= Buffer.getRoomsNumber() || Buffer.getRoomsNumber() == 0)).collect(Collectors.toList());
        } else if (Buffer.getOfferArt() == 1) {
            offers = Buffer.getOffers().stream()
                    .filter(o -> o.isRoll() == false && (o.getPrice() <= Buffer.getPrice() || Buffer.getPrice() == 0) && (o.getRoomsNumber() <= Buffer.getRoomsNumber() || Buffer.getRoomsNumber() == 0)).collect(Collectors.toList());
        } else {
            offers =  Buffer.getOffers().stream()
                    .filter(o -> o.isRoll() == true && (o.getPrice() <= Buffer.getPrice() || Buffer.getPrice() == 0) && (o.getRoomsNumber() <= Buffer.getRoomsNumber() || Buffer.getRoomsNumber() == 0)).collect(Collectors.toList());
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public TextView title;
        public TextView price;
        public TextView roomsNumber;
        public TextView roll;
        public ImageView favourite;
        public ImageView objectPhoto;
        OnOfferListner onOfferListner;

        public MyViewHolder(View itemView, OnOfferListner onOfferListner) {
            super(itemView);
            title = itemView.findViewById(R.id.cardDescreptionSeeker);
            price = itemView.findViewById(R.id.cardpreisSeeker);
            roomsNumber = itemView.findViewById(R.id.cardRoomsNumberSeeker);
            roll = itemView.findViewById(R.id.cardRollSeeker);
            favourite = itemView.findViewById(R.id.faviortSeeker);
            objectPhoto = itemView.findViewById(R.id.imageViewSeeker);
            this.onOfferListner = onOfferListner;
            itemView.setOnClickListener(this);

            favourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Buffer buffer = new Buffer();
                    if(Buffer.getOffers().get(getAdapterPosition()).isFavourite()){
                        Buffer.getOffers().get(getAdapterPosition()).setFavourite(false);
                        favourite.setImageResource(R.drawable.heart);
                    }else {
                        Buffer.getOffers().get(getAdapterPosition()).setFavourite(true);
                        favourite.setImageResource(R.drawable.heart_clicked);
                    }
                }
            });


        }
        @Override
        public void onClick(View v) {
            onOfferListner.onOfferClick(getAdapterPosition());
        }
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.seeker_offers, parent, false);
        MyViewHolder evh = new MyViewHolder(v,onOfferListner);
        return evh;
    }

    @Override
    public void onBindViewHolder(SeekerAdapter.MyViewHolder holder, int position) {
        Offer offer = offers.get(position);
        if(!offer.isFavourite()){
            holder.favourite.setImageResource(R.drawable.heart);
        }else {
            holder.favourite.setImageResource(R.drawable.heart_clicked);
        }
        holder.objectPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.objectPhoto.setImageDrawable(offer.getImage().getDrawable());
        holder.title.setText(offer.getTitle());
        holder.price.setText(String.valueOf(offer.getPrice()));
        holder.roomsNumber.setText(String.valueOf(offer.getRoomsNumber()));
        if(offer.isRoll()) {
            holder.roll.setText("Kaufen");
        } else {
            holder.roll.setText("Mieten");
        }

    }

    @Override
    public int getItemCount() {
        int size = 0;
        if(Buffer.getOfferArt() == 0) {
            for (Offer offer:
                    Buffer.getOffers()) {
                if((offer.getPrice() <= Buffer.getPrice() || Buffer.getPrice() == 0) && (offer.getRoomsNumber() <= Buffer.getRoomsNumber() || Buffer.getRoomsNumber() == 0)) {
                    size++;
                }
            }
        } else if (Buffer.getOfferArt() == 1) {
            for (Offer offer:
                 Buffer.getOffers()) {
                if(!offer.isRoll() && (offer.getPrice() <= Buffer.getPrice() || Buffer.getPrice() == 0) && (offer.getRoomsNumber() <= Buffer.getRoomsNumber() || Buffer.getRoomsNumber() == 0)) {
                    size++;
                }
            }
        } else {
            for (Offer offer:
                    Buffer.getOffers()) {
                if(offer.isRoll() && (offer.getPrice() <= Buffer.getPrice() || Buffer.getPrice() == 0) && (offer.getRoomsNumber() <= Buffer.getRoomsNumber() || Buffer.getRoomsNumber() == 0)) {
                    size++;
                }
            }
        }
        return size;
    }

    public interface OnOfferListner{
        void onOfferClick(int position);
    }
}
