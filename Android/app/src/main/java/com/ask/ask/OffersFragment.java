package com.ask.ask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OffersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OffersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OffersFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ExpandableListView expandableListViewOffers;
    private ExpandableListAdapter expandableListViewAdapter;
    public static HashMap<String, Offer> offerHashMap;
    private List<String> listItemImages;
    private List<String> listElements;
    private HashMap<String, List<String>> hashMapOfferData;

    public OffersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OffersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OffersFragment newInstance(String param1, String param2) {
        OffersFragment fragment = new OffersFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_offers, container, false);
        refreshOffersFragment(rootView);

//        //dummy data
//        listItemHeaders = new ArrayList<>(3);
//        ArrayList<String> listElements1 = new ArrayList<>();
//        ArrayList<String> listElements2 = new ArrayList<>();
//        ArrayList<String> listElements3 = new ArrayList<>();
//        hashMapOfferData = new HashMap<>(3);
//
//        listItemHeaders.add(R.drawable.tent);
//        listElements1.add("Item: Tent");
//        listElements1.add("Requester: Alexis");
//        listElements1.add("Date: 4/1/18 - 4/3/18");
//        listElements1.add("Price: $15 per day");
//        listElements1.add("Description: to go camping in Yosemite");
//        listElements1.add("Status: Pending");
//        hashMapOfferData.put(listItemHeaders.get(0), listElements1);
//
//        listItemHeaders.add(R.drawable.stove);
//        listElements2.add("Item: Surfboard");
//        listElements2.add("Requester: Carolyn");
//        listElements2.add("Date: 4/4/18 - 4/7/18");
//        listElements2.add("Price: $5 per day");
//        listElements2.add("Description: for beach weekend");
//        listElements2.add("Status: Pending");
//        hashMapOfferData.put(listItemHeaders.get(1), listElements2);
//
//        listItemHeaders.add(R.drawable.sleepingbag);
//        listElements3.add("Item: Sleeping Bag");
//        listElements3.add("Requester: Pulak");
//        listElements3.add("Date: 4/2/18 - 4/5/18");
//        listElements3.add("Price: $10 per day");
//        listElements3.add("Description: for hiking trip");
//        listElements3.add("Status: Pending");
//        hashMapOfferData.put(listItemHeaders.get(2), listElements3);
//        //dummy data

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

                int imageCount = 0;
                for (Offer currentOffer : offerHashMap.values()) {
                    Log.d("CURRENT OFFER", currentOffer.toString());

//                    final Item currentItem = LocalData.getHashMapItemsById().get(currentOffer.getBelonging_id());

                    listItemImages.add(imageCount + "#" + R.drawable.tent); //need to make this
                    listElements = new ArrayList<>();

//                    listElements.add("Item: " + currentOffer);
                    listElements.add("Requester: " + "Requester's Name"); //TODO:
                    listElements.add("Date: " + currentOffer.getBegin_date() + " - " + currentOffer.getEnd_date());
//                    listElements.add("Price: " + currentOffer.get);
                    listElements.add("Description: " + currentOffer.getDescription());
                    listElements.add("Status: " + currentOffer.getStatus());

                    hashMapOfferData.put(imageCount + "#" + R.drawable.tent, listElements);
                    imageCount++;
                }

                expandableListViewOffers = (ExpandableListView) rootView.findViewById(R.id.expandableListViewOffers);
                expandableListViewAdapter = new ExpandableOfferAdapter(getContext(), listItemImages, hashMapOfferData);
                expandableListViewOffers.setAdapter(expandableListViewAdapter);
                int[] color = {Color.BLACK, Color.BLACK};
                expandableListViewOffers.setDivider(new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, color));
                expandableListViewOffers.setDividerHeight(4);

                Display display = getActivity().getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;

                expandableListViewOffers.setIndicatorBounds(width - 100, width);

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