package com.ask.ask;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by pulakazad on 3/11/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    public Request[] myValues;
    public RecyclerViewAdapter (Request[] myValues){
        this.myValues= myValues;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.itemName.setText(myValues[position].getItem().getName());
        holder.profileName.setText(myValues[position].getRequester().getName());
        holder.profileIcon.setImageResource(myValues[position].getRequester().getProfileImage());
        holder.itemIcon.setImageResource(myValues[position].getItem().getIcon());

        String date = myValues[position].getBeginDate() + " to " + myValues[position].getEndDate();
        holder.itemDate.setText(date);

        String price = "$" + String.valueOf(myValues[position].getItem().getPrice()) + "0";
        holder.itemPrice.setText(price);

    }


    @Override
    public int getItemCount() {
        return myValues.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView profileIcon;
        private ImageView itemIcon;
        private TextView itemName;
        private TextView itemDate;
        private TextView profileName;
        private TextView itemPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            profileIcon = (ImageView) itemView.findViewById(R.id.profilePic);
            itemIcon = (ImageView) itemView.findViewById(R.id.itemPic);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            itemDate = (TextView) itemView.findViewById(R.id.itemDate);
            profileName = (TextView) itemView.findViewById(R.id.profileName);
            itemPrice = (TextView) itemView.findViewById(R.id.itemPrice);
            itemPrice.setTextColor(Color.parseColor("#85bb65"));

        }
    }
}