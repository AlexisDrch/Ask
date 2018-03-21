package com.ask.ask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

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
        String groupText = (String) getGroup(position);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_request_header, null);
        }

        TextView textViewHeader = (TextView) view.findViewById(R.id.textViewHeader);
        textViewHeader.setText(groupText);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        String childText = (String) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_request_item, null);
        }

        TextView textViewItem = (TextView) view.findViewById(R.id.textViewItem);
        textViewItem.setText(childText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

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
//        requestHolder.requestDate.setText(request.getBeginDate() + " - " + request.getEndDate());
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

}