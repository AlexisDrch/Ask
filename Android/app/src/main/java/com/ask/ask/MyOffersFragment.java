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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyOffersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyOffersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyOffersFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private TextView textViewNoOffers;

    private ExpandableListView expandableListViewOffers;
    private ExpandableListAdapter expandableListViewAdapter;
    public static HashMap<String, Offer> offerHashMap;
    private List<String> listItemImages;
    private List<String> listElements;
    private HashMap<String, List<String>> hashMapOfferData;

    private static int offerCount = 0;
    private int offerColor;

    public MyOffersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyOffersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyOffersFragment newInstance(String param1, String param2) {
        MyOffersFragment fragment = new MyOffersFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_myoffers, container, false);
        refreshOffersFragment(rootView);
        return rootView;
    }

    public void refreshOffersFragment(final View rootView) {
        User currentUser = LocalData.getCurrentUserInstance();

        VolleyFetcher fetcher = new VolleyFetcher("https://ask-capa.herokuapp.com/api/offers/by/" + currentUser.getUser_id(), getContext());
        fetcher.jsonReader(new VolleyCallback() {
            @Override
            public void onSuccess(JSONArray jsonArrayOffers) {
                offerHashMap = JsonParser.JsonArrayOffersToHashMapOffers(jsonArrayOffers);

                hashMapOfferData = new HashMap<>();
                listItemImages = new ArrayList<>();
                listElements = null;

                offerCount = 0;
                offerColor = R.color.offerPending; //defaulted

                Log.d("OFFERS FRAGMENT", "offerHashMap size = " + offerHashMap.size());
                if (offerHashMap.size() == 0) { //no current Offers made by User
                    textViewNoOffers = (TextView) rootView.findViewById(R.id.textViewNoOffers);
                    textViewNoOffers.setText(R.string.noOffersMade);
                } else {

                    for (Offer currentOffer : offerHashMap.values()) {
                        Log.d("OFFERS FRAGMENT", "CURRENT OFFER: ID: " + currentOffer.getRequest_id() + " STATUS: " + currentOffer.getStatus());

                        final Item currentItem = LocalData.getHashMapItemsById().get(currentOffer.getBelonging_id());

                        if (currentItem != null) {
                            String currentOfferInfoStr = "";

                            if (currentOffer.getStatus() == LocalData.OFFER_PENDING_FOR_REQUEST) { //PENDING
                                offerColor = R.color.offerPending;
                                Log.d("OFFERS FRAGMENT", "OFFER_PENDING_FOR_REQUEST");
                            } else if (currentOffer.getStatus() == LocalData.OFFER_ACCEPTED_FOR_REQUEST) { //ACCEPTED
                                Log.d("OFFERS FRAGMENT", "OFFER_ACCEPTED_FOR_REQUEST");
                            } else if (currentOffer.getStatus() == LocalData.OFFER_DENIED){ //DENIED
                                offerColor = R.color.offerDenied;
                                Log.d("OFFERS FRAGMENT", "LocalData");
                            }
                            String name = currentOffer.getRequester_name() + " " + currentOffer.getRequester_surname();

                            currentOfferInfoStr = offerCount + "#" + name +  "#Status: " + currentOffer.getStatus()
                                    + "#Request Id: " + currentOffer.getRequest_id()
                                    + "#" + offerColor + "#" + currentItem.getIcon() + "#" + currentItem.getName()
                                    + "#" + currentOffer.getRequester_ppicture_url(); // + "#" + currentOffer.getRequester_id(); //don't have access to this

                            listItemImages.add(currentOfferInfoStr);
                            hashMapOfferData.put(currentOfferInfoStr, listElements);

                            offerCount++;
                        }

                    } //end for loop

                    expandableListViewOffers = (ExpandableListView) rootView.findViewById(R.id.expandableListViewOffers); //defined in fragment_myoffersrs.xml
                    expandableListViewAdapter = new ExpandableOfferAdapter(getContext(), listItemImages, hashMapOfferData);
                    expandableListViewOffers.setAdapter(expandableListViewAdapter);
                    int[] color = {Color.BLACK, Color.BLACK};
                    expandableListViewOffers.setDivider(new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, color));
                    expandableListViewOffers.setDividerHeight(4);

                    Display display = getActivity().getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);
                    int width = size.x;

                    expandableListViewOffers.setIndicatorBounds(width + 100, width);

                }

            }

            @Override
            public void onFailure() {
                Toast.makeText(getContext(), "Failure to receive your Offers.", Toast.LENGTH_SHORT).show();
            }
        });

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