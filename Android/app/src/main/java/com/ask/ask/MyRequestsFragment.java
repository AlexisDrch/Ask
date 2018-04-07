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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyRequestsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyRequestsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyRequestsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private TextView textViewNoRequests;

    private ExpandableListView expandableListViewRequests;
    private ExpandableListAdapter expandableListViewAdapter;
    private static HashMap<String, Request> requestHashMap = null;//
    private static HashMap<String, User> userHashMap;

    private HashMap<String, Offer> offersForRequestHashMap = null; //offer id, Offer object
    private List<String> listItemImages;
    private List<String> listElements;
    private HashMap<String, List<String>> hashMapRequestData;

    private static int requestCount = 0;
    private int requestColor = R.color.requestWithOutOffer;
    private int numOffersForCurrentRequest;
    private String providerName;
    private String providerIdForCurrentRequest;
    private String providerProfileImageForCurrentRequest;
    private User provider;


    public MyRequestsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyRequestsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyRequestsFragment newInstance(String param1, String param2) {
        MyRequestsFragment fragment = new MyRequestsFragment();
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
        refreshRequestsFragment(rootView);
        return rootView;
    }

    public void refreshRequestsFragment(final View rootView) {
        final User currentUser = LocalData.getCurrentUserInstance(); //current user who is logged in

        VolleyFetcher fetcher = new VolleyFetcher("https://ask-capa.herokuapp.com/api/requests/by/" + currentUser.getUser_id(), getContext());
        fetcher.jsonReader(new VolleyCallback() {

            @Override
            public void onSuccess(JSONArray jsonArrayRequests) {
                requestHashMap = JsonParser.JsonArrayRequestsToHashMapRequests(jsonArrayRequests);

                hashMapRequestData = new HashMap<>();
                listItemImages = new ArrayList<>();
                listElements = null;
                provider = null;

                requestCount = 0;

                if (requestHashMap.size() == 0) { //no current Requests
                    textViewNoRequests = (TextView) rootView.findViewById(R.id.textViewNoRequests);
                    textViewNoRequests.setText(R.string.noRequestsMade);
                }

                for (final Request currentRequest : requestHashMap.values()) {
                    final Item currentItem = LocalData.getHashMapItemsById().get(currentRequest.getItem_id());

                    numOffersForCurrentRequest = 0; //defaulted
                    requestColor = R.color.requestWithOutOffer; //defaulted

                    VolleyFetcher offersForRequestFetcher = new VolleyFetcher("https://ask-capa.herokuapp.com/api/offers/for/" + currentRequest.getRequest_id(), getContext());
                    offersForRequestFetcher.jsonReader(new VolleyCallback() {
                        @Override
                        public void onSuccess(JSONArray jsonArrayOffersForRequest) {
                            offersForRequestHashMap = JsonParser.JsonArrayOffersToHashMapOffers(jsonArrayOffersForRequest);

                            listElements = new ArrayList<>();
                            providerName = "XXXX"; //to make string split happy
                            providerIdForCurrentRequest = "XXXX";
                            providerProfileImageForCurrentRequest = "XXXX";

                            Log.d("CURRENT REQUEST", "ID: " + currentRequest.getRequest_id() + " STATUS: " + currentRequest.getStatus());
                            if (Integer.parseInt(currentRequest.getStatus()) == LocalData.REQUEST_WITH_PENDING_OFFERS) { //With or without pending Offers

                                if (offersForRequestHashMap != null && offersForRequestHashMap.size() > 0) { //add if no offer accepted
                                    Log.d("OFFER HASH MAP", "SIZE: " + offersForRequestHashMap.size());

                                    numOffersForCurrentRequest = offersForRequestHashMap.size();

                                    //loop through Offers for current Request and create String description to add to listElements
                                    for (final Offer currentOfferForCurrentRequest : offersForRequestHashMap.values()) {
                                        String currentOfferInfoStr = (currentOfferForCurrentRequest.getProvider_name() + " "
                                                + currentOfferForCurrentRequest.getProvider_surname()) + "#$" + currentItem.getPrice()
                                                + "0#" + requestColor + "#" + currentRequest.getRequest_id()
                                                + "#" + currentOfferForCurrentRequest.getProvider_id() + "#" + currentRequest.getRequester_id()
                                                + "#" + currentOfferForCurrentRequest.getProvider_ppicture_url();

                                        listElements.add(currentOfferInfoStr);
                                    }
                                } else {
                                    numOffersForCurrentRequest = 0;
                                    Log.d("OFFER HASH MAP", "NO OFFERS");
                                }

                            } else if (Integer.parseInt(currentRequest.getStatus()) == LocalData.REQUEST_WITH_OFFER_SELECTED){ //Requester accepted an Offer
                                for (final Offer currentOfferForCurrentRequest : offersForRequestHashMap.values()) {
                                    if (currentOfferForCurrentRequest.getStatus() == LocalData.OFFER_ACCEPTED_FOR_REQUEST) {


                                        providerName = currentOfferForCurrentRequest.getProvider_name() + " " + currentOfferForCurrentRequest.getProvider_surname();
                                        providerIdForCurrentRequest = currentOfferForCurrentRequest.getProvider_id();
                                        providerProfileImageForCurrentRequest = currentOfferForCurrentRequest.getProvider_ppicture_url();
                                        break;
                                    }
                                }

                                numOffersForCurrentRequest = -1;
                                Log.d("OFFER HASH MAP", "OFFER ACCEPTED");
                            }

                            //current Request information
                            String imageHeaderStr = requestCount + "#Your offers: " + numOffersForCurrentRequest + "#Request Id: " + currentRequest.getRequest_id()
                                    + "#Status: " + currentRequest.getStatus() + "#" + requestColor + "#" + currentItem.getIcon() + "#" + currentItem.getName()
                                    + "#" + providerName + "#" + providerIdForCurrentRequest + "#" + currentUser.getUser_id() + "#" + providerProfileImageForCurrentRequest;
                            listItemImages.add(imageHeaderStr);

                            hashMapRequestData.put(imageHeaderStr, listElements);

                            requestCount++;
                            if (requestCount == requestHashMap.size()) { //gone through all Requests and their Offers
                                assignToExpandableListView(rootView);
                            }

                        }

                        @Override
                        public void onFailure() {
                            Toast.makeText(getContext(), "Failure to receive Offers for Request " + currentRequest.getRequest_id() + ".", Toast.LENGTH_SHORT).show();

                            requestCount++;
                            if (requestCount == requestHashMap.size()) { //gone through all Requests and their Offers
                                assignToExpandableListView(rootView);
                            }
                        }

                    });

                }

            }

            @Override
            public void onFailure() {
                Toast.makeText(getContext(), "Failure to receive your Requests.", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void assignToExpandableListView(View rootView) {
//        Log.d("hashMapRequestData", hashMapRequestData.toString());

        expandableListViewRequests = (ExpandableListView) rootView.findViewById(R.id.expandableListViewRequests); //defined in fragment_requests.xml
        expandableListViewAdapter = new ExpandableRequestAdapter(getContext(), listItemImages, hashMapRequestData);
        expandableListViewRequests.setAdapter(expandableListViewAdapter);
        int[] color = {Color.BLACK, Color.BLACK};
        expandableListViewRequests.setDivider(new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, color));
        expandableListViewRequests.setDividerHeight(4);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        expandableListViewRequests.setIndicatorBounds(width - 100, width);
    }

    //0000000000 v leave below as defaulted

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