package com.ask.ask;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RequestsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RequestsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private ExpandableListView expandableListViewRequests;
    private ExpandableListAdapter expandableListViewAdapter;
    private List<Request> listRequests;
    private List<Integer> listItemHeaders;
    private List<String> listElements;
    private HashMap<Integer, List<String>> hashMapRequestData;


    public RequestsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RequestsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RequestsFragment newInstance(String param1, String param2) {
        RequestsFragment fragment = new RequestsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_requests, container, false);

//        //https://ask-capa.herokuapp.com/api/requests/by/1
//        HashMap<String, Request> hashMapOfUserRequests = FetchRequest("https://ask-capa.herokuapp.com/api/requests/by/" + "1", getContext());
//        listRequests = new ArrayList<Request>(hashMapOfUserRequests.values()); //TODO: get Requests //these are the Requests
//        listItemHeaders = new ArrayList<>(listRequests.size());
//        listElements = new ArrayList<>();
//        hashMapRequestData = new HashMap<>(listRequests.size());
//
//        for (int i = 0; i < listRequests.size(); i++) {
////            listItemHeaders.add(listRequests.get(i).getItem().getIcon());
//            listItemHeaders.add(R.drawable.tent); //0000000000 TODO: set item ids locally
//
//            listElements.clear();
//            listElements.add("Item: " + listRequests.get(i).getItem().getName());
//            listElements.add("Date: " + listRequests.get(i).getBeginDate() + " - " + listRequests.get(i).getEndDate());
//            listElements.add("Price: $" + listRequests.get(i).getItem().getPrice());
//            listElements.add("Description: " + listRequests.get(i).getDescription());
//            listElements.add("Status: " + listRequests.get(i).getStatus());
//
//            hashMapRequestData.put(listItemHeaders.get(i), listElements);
//        }
//
//        expandableListViewRequests = (ExpandableListView) rootView.findViewById(R.id.expandableListViewRequests);
//        expandableListViewAdapter = new ExpandableRequestAdapter(getContext(), listItemHeaders, hashMapRequestData);
//        expandableListViewRequests.setAdapter(expandableListViewAdapter);
//        int[] color = {Color.BLACK, Color.BLACK};
//        expandableListViewRequests.setDivider(new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, color));
//        expandableListViewRequests.setDividerHeight(4);
//
//        Display display = getActivity().getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        int width = size.x;
//
//        expandableListViewRequests.setIndicatorBounds(width - 100, width);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}