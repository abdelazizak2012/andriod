package com.example.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class SeekerAdapter extends RecyclerView.Adapter<SeekerAdapter.MyViewHolder> {


    OnOfferListner onOfferListner;

    public SeekerAdapter(OnOfferListner onOfferListner) {
        this.onOfferListner = onOfferListner;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public TextView title;
        public TextView preis;
        public TextView roomsNumber;
        public TextView roll;
        public ImageView faviort;
        public ImageView objectPhoto;
        OnOfferListner onOfferListner;

        public MyViewHolder(View itemView, OnOfferListner onOfferListner) {
            super(itemView);
            title = itemView.findViewById(R.id.cardDescreptionSeeker);
            preis = itemView.findViewById(R.id.cardpreisSeeker);
            roomsNumber = itemView.findViewById(R.id.cardRoomsNumberSeeker);
            roll = itemView.findViewById(R.id.cardRollSeeker);
            faviort = itemView.findViewById(R.id.faviortSeeker);
            objectPhoto = itemView.findViewById(R.id.imageViewSeeker);
            this.onOfferListner = onOfferListner;
            itemView.setOnClickListener(this);

            faviort.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Buffer buffer = new Buffer();
                    if(Buffer.getOffers().get(getAdapterPosition()).isFaviort()){
                        Buffer.getOffers().get(getAdapterPosition()).setFaviort(false);
                        faviort.setImageResource(R.drawable.heart);
                    }else {
                        Buffer.getOffers().get(getAdapterPosition()).setFaviort(true);
                        faviort.setImageResource(R.drawable.heart_clicked);
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
        Offer offer = Buffer.getOffers().get(position);
        if(!Buffer.getOffers().get(position).isFaviort()){
            holder.faviort.setImageResource(R.drawable.heart);
        }else {
            holder.faviort.setImageResource(R.drawable.heart_clicked);
        }

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
        return Buffer.getOffers().size();
    }

    public interface OnOfferListner{
        void onOfferClick(int position);
    }
}
