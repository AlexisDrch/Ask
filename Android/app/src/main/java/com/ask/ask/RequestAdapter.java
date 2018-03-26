package com.ask.ask;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by pulakazad on 3/3/18.
 */

public class RequestAdapter extends ArrayAdapter<Request> {

    Context context;
    int layoutResourceId;
    Request data[] = null;

    /**
     * adapter for requests that creates the view for each row in the main activity
     * @param context the context
     * @param layoutResourceId the layout that is being used
     * @param data an array of requests
     */
    public RequestAdapter(Context context, int layoutResourceId, Request[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RequestHolder holder;

        if(row == null)
        {


            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RequestHolder();
            holder.profile = (ImageView)row.findViewById(R.id.profileIcon);
            holder.itmPic = (ImageView)row.findViewById(R.id.itmIcon);
            holder.itmTitle = (TextView)row.findViewById(R.id.itmTitle);
            holder.itmPrice = (TextView)row.findViewById(R.id.itmPrice);
            holder.itmDate = (TextView)row.findViewById(R.id.itmDate);

            row.setTag(holder);
        }
        else
        {
            holder = (RequestHolder) row.getTag();
        }

        Request request = data[position];
        holder.itmTitle.setText(request.getItem().getName());
        holder.profile.setImageResource(request.getRequester().getProfileImage());
        holder.itmPic.setImageResource(request.getItem().getIcon());
        String price = "$" + String.valueOf(request.getItem().getPrice()) + "0";
        holder.itmPrice.setText(String.valueOf(price));
        String date = request.getBeginDate() + " to " + request.getEndDate();
        holder.itmDate.setText(date);

        return row;
    }

    /**
     * initializes the image views and text views needed for each row
     */

    static class RequestHolder
    {
        ImageView profile;
        ImageView itmPic;
        TextView itmTitle;
        TextView itmPrice;
        TextView itmDate;
    }
}


