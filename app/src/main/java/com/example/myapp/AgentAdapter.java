package com.example.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class AgentAdapter extends RecyclerView.Adapter<AgentAdapter.MyViewHolder> {

    OnOfferListner onOfferListner;

    public AgentAdapter(OnOfferListner onOfferListner) {
        this.onOfferListner = onOfferListner;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
        public TextView preis;
        public TextView roomsNumber;
        public TextView roll;
        public ImageView delete;
        public ImageView objectPhoto;
        OnOfferListner onOfferListner;

        public MyViewHolder(View itemView, OnOfferListner onOfferListner) {
            super(itemView);
            title = itemView.findViewById(R.id.cardDescreption);
            preis = itemView.findViewById(R.id.cardpreis);
            roomsNumber = itemView.findViewById(R.id.cardRoomsNumber);
            roll = itemView.findViewById(R.id.cardRoll);
            delete = itemView.findViewById(R.id.image_delete);
            objectPhoto = itemView.findViewById(R.id.imageViewMakler);
            this.onOfferListner = onOfferListner;
            itemView.setOnClickListener(this);


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Buffer buffer = new Buffer();
                    Buffer.getOffers().remove(getAdapterPosition());
                    AgentAdapter.super.notifyItemRemoved(getAdapterPosition());
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.agent_offers, parent, false);
        MyViewHolder evh = new MyViewHolder(v,onOfferListner);
        return evh;
    }

    @Override
    public void onBindViewHolder(AgentAdapter.MyViewHolder holder, int position)  {
            Offer offer = Buffer.getOffers().get(position);
            holder.objectPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.objectPhoto.setImageDrawable(offer.getImage().getDrawable());
            holder.title.setText(offer.getTitle());
            holder.preis.setText(String.valueOf(offer.getPrice()));
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
