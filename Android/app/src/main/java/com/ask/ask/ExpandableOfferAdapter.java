package com.ask.ask;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ask.ask.Utils.DownloadImageTask;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Created by alexander on 3/20/2018.
 */
public class ExpandableOfferAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listHeaders;
    private HashMap<String, List<String>> listHashMap;
    private static HashMap<String, User> userHashMap;

    public ExpandableOfferAdapter(Context context, List<String> listHeaders, HashMap<String, List<String>> listHashMap) {
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
            return 0;
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
        String[] headerInfoArr = headerStr.split("#");
        String requesterName = headerInfoArr[1];
        String statusStr = headerInfoArr[2];
        int temp = statusStr.indexOf(":");
        int status = Integer.parseInt(statusStr.substring(temp + 1).trim());
        final String requestId = headerInfoArr[3];
        int color = Integer.parseInt(headerInfoArr[4]);
        int imageIcon = Integer.parseInt(headerInfoArr[5]);
        String itemName = headerInfoArr[6];
        String requester_ppicture_url = headerInfoArr[7];


        if (status == LocalData.OFFER_PENDING_FOR_REQUEST) {

            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_offer_header_pending, null);

            TextView textViewItemName = (TextView) view.findViewById(R.id.textViewItemName);
            textViewItemName.setText(itemName);
            textViewItemName.setBackground(view.getResources().getDrawable(R.drawable.backgroundgradient));

            ImageView imageViewHeader = (ImageView) view.findViewById(R.id.imageViewItemImage);
            imageViewHeader.setImageResource(imageIcon);

            TextView textViewRequesterName = (TextView) view.findViewById(R.id.textViewRequesterName);
            textViewRequesterName.setText(context.getString(R.string.requestedBy) + " " + requesterName);

            TextView textViewStatus = (TextView) view.findViewById(R.id.textViewStatus);
            textViewStatus.setText(R.string.OFFER_PENDING_FOR_REQUEST);
            textViewStatus.setTextColor(view.getResources().getColor(R.color.offerPending));

        } else if (status == LocalData.OFFER_ACCEPTED_FOR_REQUEST) {

            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_offer_header_accepted, null);

            TextView textViewItemName = (TextView) view.findViewById(R.id.textViewItemName);
            textViewItemName.setText(itemName);
            textViewItemName.setBackground(view.getResources().getDrawable(R.drawable.backgroundgradient));

            ImageView imageViewHeader = (ImageView) view.findViewById(R.id.imageViewItemImage);
            imageViewHeader.setImageResource(imageIcon);

            TextView textViewRequesterName = (TextView) view.findViewById(R.id.textViewRequesterName);
            textViewRequesterName.setText(context.getString(R.string.requestedBy) + " " + requesterName);

            ImageView cardViewProfileImage = (ImageView) view.findViewById(R.id.cardViewProfileImage);
            new DownloadImageTask((ImageView) cardViewProfileImage).execute(requester_ppicture_url);
//            cardViewProfileImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    final Intent intent = new Intent(context, ProfileActivity.class);
//
//                    VolleyFetcher process = new VolleyFetcher("https://ask-capa.herokuapp.com/api/users", context);
//                    process.jsonReader(new VolleyCallback() {
//                        @Override
//                        public void onSuccess(JSONArray jsonArrayRequests) {
//                            // handle JSONOBJECT response
//                            userHashMap = JsonParser.JsonArrayUsersToHashMapUsers(jsonArrayRequests);
//                            User profile = userHashMap.get(re);
//                            Log.d("PROFILE#", profile.toString());
//                            intent.putExtra("profileUser", (Serializable) profile);
//                            context.startActivity(intent);
//                        }
//
//                        @Override
//                        public void onFailure() {
//                            // in case of failure
//                            Log.d("USER_GET_FAILURE", "Something went wrong");
//                        }
//                    });
//
//                }
//            });

            ImageView imageViewButtonMessage = (ImageView) view.findViewById(R.id.imageViewButtonMessage);
            imageViewButtonMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("OfferAdapter", "imageViewButtonMessage");
                    final Intent intent = new Intent(v.getContext(), MessagingActivity.class);
                    v.getContext().startActivity(intent);
                }
            });

        } else if (status == LocalData.OFFER_DENIED) {

            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_offer_header_denied, null);

            TextView textViewItemName = (TextView) view.findViewById(R.id.textViewItemName);
            textViewItemName.setText(itemName);
            textViewItemName.setBackground(view.getResources().getDrawable(R.drawable.backgroundgradient));

            ImageView imageViewHeader = (ImageView) view.findViewById(R.id.imageViewItemImage);
            imageViewHeader.setImageResource(imageIcon);

            TextView textViewUpdateMessage = (TextView) view.findViewById(R.id.textViewUpdateMessage);
            textViewUpdateMessage.setText("Your Offer for request " + requestId + " has been denied.");

        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}