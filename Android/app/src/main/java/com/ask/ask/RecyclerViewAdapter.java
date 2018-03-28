package com.ask.ask;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ask.ask.Utils.DownloadImageTask;

import org.json.JSONArray;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by pulakazad on 3/11/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private HashMap<String, Request> requestsHashMap;
    private Context myContext;
    private int previousPosition = 0;

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
        final Request currentRequest = (Request) requestsHashMap.values().toArray()[position];

        // Request data
        String date = currentRequest.getBegin_date() + " to " + currentRequest.getEnd_date();
        //holder.itemDate.setText(date);

        // Requester data
        holder.profileName.setText(currentRequest.getRequester_name());
        new DownloadImageTask((ImageView) holder.profileIcon)
                .execute(currentRequest.getRequester_ppicture_url());

        // Items data
        final Item currentItem = LocalData.getHashMapItemsById().get(currentRequest.getItem_id());
        holder.itemName.setText(currentItem.getName());
        holder.itemIcon.setImageResource(currentItem.getIcon());
        String price = "$" + String.valueOf(currentItem.getPrice()) + "0";
        holder.itemPrice.setText(price);

        // Match button
        holder.matchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User currentUser = LocalData.getCurrentUserInstance(); //this is the logged in provider
                String[] nameArr = currentUser.getName().split(" ");
                String provider_name = nameArr[0];
                String provider_surname = nameArr[1];

                final Offer newOffer = new Offer("" + currentUser.getUser_id(), currentRequest.getRequest_id(), currentRequest.getItem_id(),
                        currentRequest.getBegin_date(), currentRequest.getEnd_date(),
                        currentRequest.getDescription(), "MESSAGE", provider_name, provider_surname, "-1", "-1");

                Log.d("POSTING OFFER", newOffer.toString());
                //post new offer json object
                final String url = "https://ask-capa.herokuapp.com/api/offers";
                POSTData postData = new POSTData();
                postData.postOffer(url, newOffer, myContext, new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONArray jsonArray) {
                        //pass request object for RequestConfirmationActivity display
                        Intent intent = new Intent(myContext, MatchConfirmationActivity.class);
                        intent.putExtra("Offer", (Serializable) newOffer);
                        myContext.startActivity(intent);
                    }

                    @Override
                    public void onFailure() {
                        Log.d("RecyclerViewAdapter", "failure posting offer!");
                        Toast.makeText(myContext, "Unable to send Offer.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        // Card view
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainMenuFragment mainFrag = new MainMenuFragment();

                FragmentTransaction ft = ((FragmentActivity)myContext).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.expanded_container, mainFrag);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        if (position > previousPosition) {
            // we are scrolling right
            AnimationUtil.animate(holder, true);
        } else {
            //we are scrolling left
            AnimationUtil.animate(holder, false);

        }
        previousPosition = position;
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