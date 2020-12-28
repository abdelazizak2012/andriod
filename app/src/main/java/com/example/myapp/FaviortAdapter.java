package com.example.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FaviortAdapter extends RecyclerView.Adapter<FaviortAdapter.MyViewHolder> {

    OnOfferListner onOfferListner;
    private ArrayList<Offer> favoritOffers = new ArrayList<>(); // important to get the right Offer-Object from Buffer
    public FaviortAdapter(OnOfferListner onOfferListner) {
        this.onOfferListner = onOfferListner;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
        public TextView preis;
        public TextView roomsNumber;
        public TextView roll;
        public ImageView objectPhoto;
        OnOfferListner onOfferListner;

        public MyViewHolder(View itemView , OnOfferListner onOfferListner) {
            super(itemView);
            title = itemView.findViewById(R.id.cardDescreptionFaviort);
            preis = itemView.findViewById(R.id.cardpreisFaviort);
            roomsNumber = itemView.findViewById(R.id.cardRoomsNumberFaviort);
            roll = itemView.findViewById(R.id.cardRollFaviort);
            objectPhoto = itemView.findViewById(R.id.imageViewFaviort);
            this.onOfferListner = onOfferListner;
            for (int i = 0; i < Buffer.getOffers().size(); i++) {
                if(Buffer.getOffers().get(i).isFaviort()) {
                    favoritOffers.add(Buffer.getOffers().get(i));
                }
            }
            itemView.setOnClickListener(this);


        }
        @Override
        public void onClick(View v) {
            onOfferListner.onOfferClick(getAdapterPosition());
        }
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.seeker_faviort_offer, parent, false);
        MyViewHolder evh = new MyViewHolder(v , onOfferListner);
        return evh;
    }

    @Override
    public void onBindViewHolder(FaviortAdapter.MyViewHolder holder, int position) {
        Offer offer = favoritOffers.get(position);

        holder.objectPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.objectPhoto.setImageDrawable(offer.getImage().getDrawable());
        holder.title.setText(offer.getTitle());
        holder.preis.setText(String.valueOf(offer.getPreis()));
        holder.roomsNumber.setText(String.valueOf(offer.getRoomsNumber()));
        if(offer.isRoll()) {
            holder.roll.setText("Kaufen");
        } else {
            holder.roll.setText("Mieten");
        }

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

    public interface OnOfferListner{
        void onOfferClick(int position);
    }
}
