package com.ask.ask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by pulakazad on 3/11/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    public Request[] myValues;
    private Context myContext;

    public RecyclerViewAdapter (Context myContext, Request[] myValues){
        this.myValues= myValues;
        this.myContext = myContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.itemName.setText(myValues[position].getItem().getName());
        holder.profileName.setText(myValues[position].getRequester().getName());
        holder.profileIcon.setImageResource(myValues[position].getRequester().getProfileImage());
        holder.itemIcon.setImageResource(myValues[position].getItem().getIcon());
//        holder.itemDescription.setText(myValues[position].getDescription());

        String date = myValues[position].getBeginDate() + " to " + myValues[position].getEndDate();
        holder.itemDate.setText(date);

        String price = "$" + String.valueOf(myValues[position].getItem().getPrice()) + "0";
        holder.itemPrice.setText(price);

        holder.matchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MatchConfirmationActivity.class);
                myContext.startActivity(intent);
            }
        });



//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                MainMenuFragment mainFrag = new MainMenuFragment();
//
//                FragmentTransaction ft = ((FragmentActivity)myContext).getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.expanded_container, mainFrag);
//                ft.addToBackStack(null);
//                ft.commit();            }
//        });

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
        private Button matchButton;

        private TextView itemDescription;

        private CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            profileIcon = (ImageView) itemView.findViewById(R.id.profilePic);
            itemIcon = (ImageView) itemView.findViewById(R.id.itemPic);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            itemDate = (TextView) itemView.findViewById(R.id.itemDate);
//            itemDescription = (TextView) itemView.findViewById(R.id.itemDescription);
            profileName = (TextView) itemView.findViewById(R.id.profileName);
            itemPrice = (TextView) itemView.findViewById(R.id.itemPrice);
            itemPrice.setTextColor(Color.parseColor("#85bb65"));
            matchButton = (Button) itemView.findViewById(R.id.match_button);

            cardView = (CardView) itemView.findViewById(R.id.requestCard);

        }
    }

}