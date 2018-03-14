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
 * Created by alexander on 3/14/2018.
 */

public class ProfileAdapter extends ArrayAdapter<String> {

    private Context context;
    private int layoutResourceId;
    private String[] data = null;

    /**
     * adapter for requests that creates the view for each row in the fragment_about
     *
     * @param context the context
     * @param layoutResourceId the layout that is being used
     * @param data an array of profile data
     */
    public ProfileAdapter(Context context, int layoutResourceId, String[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ProfileHolder holder;

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ProfileHolder();
            holder.imageViewProfileImage = (ImageView) row.findViewById(R.id.imageViewProfileImage);
            holder.textViewName = (TextView)row.findViewById(R.id.textViewName);
            holder.textViewBio = (TextView)row.findViewById(R.id.textViewBio);

            row.setTag(holder);
        } else {
            holder = (ProfileHolder) row.getTag();
        }

        String[] currData = data[position].split("#");
        //parse string
        String name = currData[0];
        String bio = currData[1];
        int profileImage = Integer.parseInt(currData[2]);

        holder.imageViewProfileImage.setImageResource(profileImage);
        holder.textViewName.setText(name);
        holder.textViewBio.setText(bio);

        return row;
    }

    static class ProfileHolder {
        ImageView imageViewProfileImage;
        TextView textViewName;
        TextView textViewBio;
    }

}