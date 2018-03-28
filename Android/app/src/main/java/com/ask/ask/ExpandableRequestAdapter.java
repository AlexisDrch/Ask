package com.ask.ask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        return listHashMap.get(listHeaders.get(groupPosition)).size();
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
        String imageStr = (String) getGroup(position);
        int index = imageStr.indexOf('#');

        imageStr = imageStr.substring(index + 1);
        index = imageStr.indexOf('#');
        String numOffersForCurrentRequest = imageStr.substring(0, index);

        imageStr = imageStr.substring(index + 1);
        index = imageStr.indexOf('#');
        String date =imageStr.substring(0, index);

        imageStr = imageStr.substring(index + 1);
        index = imageStr.indexOf('#');
        int color = Integer.parseInt(imageStr.substring(0, index));

        imageStr = imageStr.substring(index + 1);
        int groupText = Integer.parseInt(imageStr);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_request_header, null);
        }

        TextView textViewNumOffersForRequest = (TextView) view.findViewById(R.id.textViewNumOffersForRequest);
        textViewNumOffersForRequest.setText(numOffersForCurrentRequest);
        textViewNumOffersForRequest.setBackgroundResource(color);

        TextView textViewDate = (TextView) view.findViewById(R.id.textViewDate);
        textViewDate.setText(date);
        textViewDate.setBackgroundResource(color);

        ImageView imageViewHeader = (ImageView) view.findViewById(R.id.imageViewItemImage);
        imageViewHeader.setImageResource(groupText);
        imageViewHeader.setBackgroundResource(color);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        String childText = (String) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_request_item_offers, null);
        }

        TextView textViewItem = (TextView) view.findViewById(R.id.textViewItem);
        textViewItem.setText(childText);

        Button buttonAcceptOffer = (Button) view.findViewById(R.id.buttonAcceptOffer);
        buttonAcceptOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: send update to database






                Toast.makeText(v.getContext(), "Offer Accepted.", Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonDenyOffer = (Button) view.findViewById(R.id.buttonDenyOffer);
        buttonDenyOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: send update to database




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