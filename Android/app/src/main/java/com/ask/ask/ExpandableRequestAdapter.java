//package com.ask.ask;
//
//
//import android.app.Activity;
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.ExpandableListAdapter;
//import android.widget.ImageView;
//import android.widget.SimpleExpandableListAdapter;
//import android.widget.TextView;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by alexander on 3/20/2018.
// */
//public class ExpandableRequestAdapter extends SimpleExpandableListAdapter {
//
//    private Context context;
//    private int layoutResourceId;
//    private Request[] requestsArr;
//
//    public ExpandableRequestAdapter(Context context, List<? extends Map<String, ?>> groupData, int groupLayout, String[] groupFrom, int[] groupTo, List<? extends List<? extends Map<String, ?>>> childData, int childLayout, String[] childFrom, int[] childTo) {
//        super();
//        Log.d("constructor", "expandable");
//        this.context = context;
//        this.layoutResourceId = groupLayout;
//        this.requestsArr = requestsArr;
//    }
//
//    @Override
//    public View getGroupView(int position, boolean isExpanded, View convertView, ViewGroup parent) {
//        Log.d("getView", "in");
//        View row = convertView;
//        RequestHolder requestHolder;
//
//        if (row == null) {
//            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
//            row = inflater.inflate(layoutResourceId, parent, false);
//
//            requestHolder = new RequestHolder();
//            requestHolder.requestImage = (ImageView) row.findViewById(R.id.imageViewItemImage);
//            requestHolder.requestItemName = (TextView) row.findViewById(R.id.textViewItemName);
//            requestHolder.requestDate = (TextView) row.findViewById(R.id.textViewDate);
//            requestHolder.requestPrice = (TextView) row.findViewById(R.id.textViewPrice);
//            requestHolder.requestDescription = (TextView) row.findViewById(R.id.textViewDescription);
//
//            row.setTag(requestHolder);
//        } else {
//            requestHolder = (RequestHolder) row.getTag();
//        }
//
//        Request request = requestsArr[position];
//        requestHolder.requestImage.setImageResource(request.getItem().getIcon());
//        requestHolder.requestItemName.setText(request.getItem().getName());
//        requestHolder.requestDate.setText(request.getBegin_date() + " - " + request.getEnd_date());
//        requestHolder.requestPrice.setText("$" + String.valueOf(request.getItem().getPrice()) + "0");
//        requestHolder.requestDescription.setText(request.getDescription());
//
//        return row;
//    }
//
//    static class RequestHolder {
//        ImageView requestImage;
//        TextView requestItemName;
//        TextView requestDate;
//        TextView requestPrice;
//        TextView requestDescription;
//    }
//
//    @Override
//    public int getGroupCount() {
//        return 0;
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        return 0;
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        return null;
//    }
//
//    @Override
//    public Object getChild(int groupPosition, int childPosition) {
//        return null;
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return 0;
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return 0;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//
//    @Override
//    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        return null;
//    }
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return false;
//    }
//
//}