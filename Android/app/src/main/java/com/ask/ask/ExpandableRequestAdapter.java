package com.ask.ask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.baoyz.swipemenulistview.SwipeMenu;
//import com.baoyz.swipemenulistview.SwipeMenuCreator;
//import com.baoyz.swipemenulistview.SwipeMenuItem;
//import com.baoyz.swipemenulistview.SwipeMenuListView;

import com.ask.ask.Utils.DownloadImageTask;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by alexander on 3/20/2018.
 */
public class ExpandableRequestAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listHeaders;
    private HashMap<String, List<String>> listHashMap;
    private static HashMap<String, User> userHashMap;


    public ExpandableRequestAdapter(Context context, List<String> listHeaders, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.listHeaders = listHeaders;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listHeaders.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (listHashMap.get(listHeaders.get(groupPosition)) == null) {
            return 0; //this Request has no Offers
        } else {
            return listHashMap.get(listHeaders.get(groupPosition)).size();
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listHeaders.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listHeaders.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int position, boolean isExpanded, View view, ViewGroup parent) {
        String headerStr = (String) getGroup(position);
        String[] headerArr = headerStr.split("#");
        String numOffersForCurrentRequest = headerArr[1];
        final String requestId = headerArr[2];
        String status = headerArr[3];
        int temp = status.indexOf(":");
        int statusInt = Integer.parseInt(status.substring(temp + 1).trim());
        int color = Integer.parseInt(headerArr[4]);
        int imageIcon = Integer.parseInt(headerArr[5]);
        String itemName = headerArr[6];
        String providerName = headerArr[7];
        final String provider_id = headerArr[8];
        final String requester_id = headerArr[9];
        final String provider_ppicture_url = headerArr[10];

        if (statusInt == LocalData.REQUEST_WITH_PENDING_OFFERS) {

            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_request_header_pending, parent, false);

            ImageView imageViewRemoveRequest = (ImageView) view.findViewById(R.id.imageViewRemoveRequest);
            imageViewRemoveRequest.setBackgroundColor(view.getResources().getColor(R.color.colorPrimaryDark));
            imageViewRemoveRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("REQUESTADAPTER", "cancel request");
                    Toast.makeText(v.getContext(), "Requested Removed.", Toast.LENGTH_SHORT);
                }
            });

            TextView textViewItemName = (TextView) view.findViewById(R.id.textViewItemName);
            textViewItemName.setText(itemName);
            textViewItemName.setBackground(view.getResources().getDrawable(R.drawable.backgroundgradient));

            ImageView imageViewItemImage = (ImageView) view.findViewById(R.id.imageViewItemImage);
            imageViewItemImage.setImageResource(imageIcon);

            TextView textViewNumOffersForRequest = (TextView) view.findViewById(R.id.textViewNumOffersForRequest);
            textViewNumOffersForRequest.setText(numOffersForCurrentRequest);

            TextView textViewStatus = (TextView) view.findViewById(R.id.textViewStatus);
            textViewStatus.setText(R.string.REQUEST_WITH_PENDING_OFFERS);
            textViewStatus.setTextColor(view.getResources().getColor(R.color.requestWithOutOffer));

        } else if (statusInt == LocalData.REQUEST_WITH_OFFER_SELECTED) {

            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_request_header_accepted, parent, false);

            TextView textViewItemName = (TextView) view.findViewById(R.id.textViewItemName);
            textViewItemName.setText(itemName);
            textViewItemName.setBackground(view.getResources().getDrawable(R.drawable.backgroundgradient));

            // Handles when profilePic is clicked
            ImageView cardViewProfileImage = (ImageView) view.findViewById(R.id.cardViewProfileImage);
            new DownloadImageTask((ImageView) cardViewProfileImage).execute(provider_ppicture_url);
            cardViewProfileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Intent intent = new Intent(context, ProfileActivity.class);

                    VolleyFetcher process = new VolleyFetcher("https://ask-capa.herokuapp.com/api/users", context);
                    process.jsonReader(new VolleyCallback() {
                        @Override
                        public void onSuccess(JSONArray jsonArrayRequests) {
                            // handle JSONOBJECT response
                            userHashMap = JsonParser.JsonArrayUsersToHashMapUsers(jsonArrayRequests);
                            for (String each : userHashMap.keySet()) {
                                Log.d("USER KEY", each);
                            }
                            User profile = userHashMap.get(provider_id);
                            Log.d("PROFILE#", profile.toString());
                            intent.putExtra("profileUser", (Serializable) profile);
                            context.startActivity(intent);
                        }

                        @Override
                        public void onFailure() {
                            // in case of failure
                            Log.d("USER_GET_FAILURE", "Something went wrong");
                        }
                    });

                }
            });

            TextView textViewProviderName = (TextView) view.findViewById(R.id.textViewProviderName);
            textViewProviderName.setText(context.getString(R.string.providedBy) + " " + providerName);

            ImageView imageViewButtonMessage = (ImageView) view.findViewById(R.id.imageViewButtonMessage);
            imageViewButtonMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent intent = new Intent(v.getContext(), MessagingActivity.class);
                    v.getContext().startActivity(intent);
                }
            });

            ImageView imageViewItemImage = (ImageView) view.findViewById(R.id.imageViewItemImage);
            imageViewItemImage.setImageResource(imageIcon);

        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) { //only when statusInt == LocalData.REQUEST_WITH_OFFER_SELECTED
        String elementStr = (String) getChild(groupPosition, childPosition);
        String[] elementsArr = elementStr.split("#");
        final String provider_name = elementsArr[0];
        final String request_price = elementsArr[1];
//        int color = Integer.parseInt(elementsArr[2]);
        final String request_id = elementsArr[3];
        final String provider_id = elementsArr[4];
        final String requester_id = elementsArr[5]; //current logged in user
        final String provider_ppicture_url = elementsArr[6];
        final String requester_ppicture_url = elementsArr[7];
        final String requester_name = elementsArr[8];
        final String itemName = elementsArr[9];
        final String itemId = elementsArr[10];


        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.listview_request_item_offers, parent, false);

        TextView textViewProviderName = (TextView) view.findViewById(R.id.textViewProviderName);
        textViewProviderName.setText(provider_name);

        TextView textViewPrice = (TextView) view.findViewById(R.id.textViewPrice);
        textViewPrice.setText(request_price);

        ImageView cardViewProfileImage = (ImageView) view.findViewById(R.id.pA_profilePic);
        new DownloadImageTask((ImageView) cardViewProfileImage).execute(provider_ppicture_url);
        cardViewProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(context, ProfileActivity.class);

                VolleyFetcher process = new VolleyFetcher("https://ask-capa.herokuapp.com/api/users", context);
                process.jsonReader(new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONArray jsonArrayRequests) {
                        // handle JSONOBJECT response
                        userHashMap = JsonParser.JsonArrayUsersToHashMapUsers(jsonArrayRequests);
                        User profile = userHashMap.get(provider_id);
                        Log.d("PROFILE#", profile.toString());
                        intent.putExtra("profileUser", (Serializable) profile);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure() {
                        // in case of failure
                        Log.d("USER_GET_FAILURE", "Something went wrong");
                    }
                });

            }
        });

        Button buttonAcceptOffer = (Button) view.findViewById(R.id.buttonAcceptOffer);
        buttonAcceptOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url = "https://ask-capa.herokuapp.com/api/offers/accept/" + requester_id;
                final View vi = v;

                POSTData postData = new POSTData();
                postData.postAcceptOffer(url, request_id, provider_id, "Accepting Offer from user id " + provider_id + ".", v.getContext(), new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONArray jsonArray) {
                        Toast.makeText(vi.getContext(), "Offer Accepted.", Toast.LENGTH_SHORT).show();

                        final Intent intent = new Intent(vi.getContext(), OfferAcceptedActivity.class);
                        intent.putExtra("itemName", itemName);
                        intent.putExtra("itemImage", LocalData.getHashMapItemsById().get(itemId).getIcon());
                        intent.putExtra("requesterName", requester_name);
                        intent.putExtra("requesterImage", requester_ppicture_url);
                        intent.putExtra("providerName", provider_name);
                        intent.putExtra("providerImage", provider_ppicture_url);
                        vi.getContext().startActivity(intent);
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(vi.getContext(), "Error Accepting Offer. Returning to Home Screen.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(vi.getContext(), MainActivity.class);
                        vi.getContext().startActivity(intent);
                    }
                });

            }
        });

        Button buttonDenyOffer = (Button) view.findViewById(R.id.buttonDenyOffer);
        buttonDenyOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //TODO: send Offer accept status to database and update Request, Offer
                //TODO: send OFFER_DENIED to database with the offer_id
                Log.d("RequestAdapter", "buttonDenyOffer");


                Toast.makeText(v.getContext(), "Offer Denied.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}