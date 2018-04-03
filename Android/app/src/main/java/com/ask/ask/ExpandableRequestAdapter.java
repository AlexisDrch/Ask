package com.ask.ask;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by alexander on 3/20/2018.
 */
public class ExpandableRequestAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listHeaders;
    private HashMap<String, List<String>> listHashMap;

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
        String requestId = headerArr[2];
        String status = headerArr[3];
        int temp = status.indexOf(":");
        int statusInt = Integer.parseInt(status.substring(temp + 1).trim());
        int color = Integer.parseInt(headerArr[4]);
        int imageIcon = Integer.parseInt(headerArr[5]);

        view.setBackgroundColor(view.getResources().getColor(color));

        if (statusInt == LocalData.REQUEST_WITH_PENDING_OFFERS) {

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.listview_request_header_pending, null);
            }

            ImageView imageViewHeader = (ImageView) view.findViewById(R.id.imageViewItemImage);
            imageViewHeader.setImageResource(imageIcon);

            TextView textViewNumOffersForRequest = (TextView) view.findViewById(R.id.textViewNumOffersForRequest);
            textViewNumOffersForRequest.setText(numOffersForCurrentRequest);

            TextView textViewStatus = (TextView) view.findViewById(R.id.textViewStatus);
            textViewStatus.setText(status);

            TextView textViewRequestId = (TextView) view.findViewById(R.id.textViewRequestId);
            textViewRequestId.setText(requestId);

        } else if (statusInt == LocalData.REQUEST_WITH_OFFER_SELECTED) {

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.listview_request_header_accepted, null);
            }

            ImageView imageViewHeader = (ImageView) view.findViewById(R.id.imageViewItemImage);
            imageViewHeader.setImageResource(imageIcon);

            TextView textViewStatus = (TextView) view.findViewById(R.id.textViewStatus);
            textViewStatus.setText(status);

            TextView textViewProviderName = (TextView) view.findViewById(R.id.textViewProviderName);
            textViewProviderName.setText("Provider Name"); //TODO: need to get provider name and put here

            Button buttonMessage = (Button) view.findViewById(R.id.buttonMessage);
            buttonMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: switch to messaging screen



                    Toast.makeText(v.getContext(), "Go to Message Screen.", Toast.LENGTH_SHORT).show();
                }
            });

        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_request_item_offers, null);
        }

        String elementStr = (String) getChild(groupPosition, childPosition);
        String[] elementsArr = elementStr.split("#");
        String name = elementsArr[0];
        String price = elementsArr[1];
        int color = Integer.parseInt(elementsArr[2]);
        final String request_id = elementsArr[3];
        final String provider_id = elementsArr[4];

        view.setBackgroundColor(view.getResources().getColor(color));

        TextView textViewProviderName = (TextView) view.findViewById(R.id.textViewProviderName);
        textViewProviderName.setText(name);

        TextView textViewPrice = (TextView) view.findViewById(R.id.textViewPrice);
        textViewPrice.setText(price);

        Button buttonAcceptOffer = (Button) view.findViewById(R.id.buttonAcceptOffer);
        buttonAcceptOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AcceptOfferConfirmationActivity.class);
                intent.putExtra("request_id", request_id);
                intent.putExtra("provider_id", provider_id);

                //TODO: send OFFER_ACCEPTED_FOR_REQUEST to database with the offer_id


                v.getContext().startActivity(intent);
            }
        });

        Button buttonDenyOffer = (Button) view.findViewById(R.id.buttonDenyOffer);
        buttonDenyOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: send update to database


                //TODO: send OFFER_DENIED to database with the offer_id


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