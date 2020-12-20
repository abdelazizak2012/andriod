package com.example.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class FaviortAdapter extends RecyclerView.Adapter<FaviortAdapter.MyViewHolder> {





    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView descreption;
        public TextView preis;
        public TextView roomsNumber;
        public TextView roll;
        public ImageView objectPhoto;

        public MyViewHolder(View itemView) {
            super(itemView);
            descreption = itemView.findViewById(R.id.cardDescreptionFaviort);
            preis = itemView.findViewById(R.id.cardpreisFaviort);
            roomsNumber = itemView.findViewById(R.id.cardRoomsNumberFaviort);
            roll = itemView.findViewById(R.id.cardRollFaviort);
            objectPhoto = itemView.findViewById(R.id.imageViewFaviort);




        }
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.seeker_faviort_offer, parent, false);
        MyViewHolder evh = new MyViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(FaviortAdapter.MyViewHolder holder, int position) {
        Offer offer = Buffer.getOffers().get(position);
        if(offer.isFaviort()){

        holder.objectPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.objectPhoto.setImageDrawable(offer.getImage().getDrawable());
        holder.descreption.setText(offer.getDescreption());
        holder.preis.setText(String.valueOf(offer.getPreis()));
        holder.roomsNumber.setText(String.valueOf(offer.getRoomsNumber()));
        if(offer.isRoll()) {
            holder.roll.setText("Kaufen");
        } else {
            holder.roll.setText("Mieten");
        } }

    }

    @Override
    public int getItemCount() {
        int count =0;
        for (Offer offer:
                Buffer.getOffers()) {
            if (offer.isFaviort()){
                count++;
            }
        }
        return count;
    }
}
