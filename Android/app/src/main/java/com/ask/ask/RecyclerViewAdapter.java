package com.ask.ask;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by pulakazad on 3/11/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private HashMap<String, Request> requestsHashMap;
    private Context myContext;

    public RecyclerViewAdapter (Context myContext, HashMap<String, Request> requestsHashMap){
        this.requestsHashMap = requestsHashMap;
        this.myContext = myContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //create a new card using request data
        Request currentRequest = (Request) requestsHashMap.values().toArray()[position];


        holder.itemName.setText(currentRequest.getDescription());
        Log.d("OOKOKOK", currentRequest.toString());


        // fills the Adapter with a request object
        //holder.itemName.setText(myValues[position].getItem().getName());


//       holder.profileName.setText(myValues[position].getRequester().getName());
//        holder.profileIcon.setImageResource(myValues[position].getRequester().getProfileImage());
//        holder.itemIcon.setImageResource(myValues[position].getItem().getIcon())
//
//        String date = myValues[position].getBegin_date() + " to " + myValues[position].getEnd_date();
//        holder.itemDate.setText(date);
//
//        String price = "$" + String.valueOf(myValues[position].getItem().getPrice()) + "0";
//        holder.itemPrice.setText(price);
//
//        holder.matchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), MatchConfirmationActivity.class);
//                myContext.startActivity(intent);
//            }
//        });



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
        if (requestsHashMap == null) {
            return 0;
        }
        return requestsHashMap.size();
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
            itemDescription = (TextView) itemView.findViewById(R.id.itemDescription);
            profileName = (TextView) itemView.findViewById(R.id.profileName);
            itemPrice = (TextView) itemView.findViewById(R.id.itemPrice);
            itemPrice.setTextColor(Color.parseColor("#85bb65"));
            matchButton = (Button) itemView.findViewById(R.id.match_button);
            cardView = (CardView) itemView.findViewById(R.id.requestCard);

        }
    }
}