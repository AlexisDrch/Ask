package com.ask.ask;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

/**
 * Created by alexander on 3/20/2018.
 */
public class ExpandableOfferAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Integer> listHeaders;
    private HashMap<Integer, List<String>> listHashMap;

    public ExpandableOfferAdapter(Context context, List<Integer> listHeaders, HashMap<Integer, List<String>> listHashMap) {
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
        int groupText = (Integer) getGroup(position);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_offer_header, null);
        }

        ImageView imageViewHeader = (ImageView) view.findViewById(R.id.imageViewItemImage);
        imageViewHeader.setImageResource(groupText);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        String childText = (String) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_offer_item, null);
        }

        TextView textViewItem = (TextView) view.findViewById(R.id.textViewItem);
        textViewItem.setText(childText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}