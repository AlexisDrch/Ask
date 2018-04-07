package com.ask.ask;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ask.ask.Utils.DownloadImageTask;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "Item";
    private static final String ARG_PARAM2 = "Request";

    // TODO: Rename and change types of parameters
    private Item mParam1;
    private Request mParam2;

    private MainMenuFragment.OnFragmentInteractionListener mListener;
    private static HashMap<String, User> userHashMap;


    private ImageView profileIcon, itemIcon;
    private TextView itemName, itemDate, profileName, itemPrice, itemDescription;
    private Button matchButton;
    private CardView expandedCard;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMenuFragment.
     */
    // Pulls in an Item and Request from recyclerView via bundle
    public static MainMenuFragment newInstance(String param1, String param2) {
        MainMenuFragment fragment = new MainMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { //creates the item and request
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Item) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = (Request) getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //assigns each piece of the layout
        View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);
        profileIcon = (ImageView) rootView.findViewById(R.id.pA_profilePic);
        profileName = (TextView) rootView.findViewById(R.id.profileName);
        itemIcon = (ImageView) rootView.findViewById(R.id.itemPic);
        itemName = (TextView) rootView.findViewById(R.id.itemName);
        itemDate = (TextView) rootView.findViewById(R.id.itemDate);
        itemPrice = (TextView) rootView.findViewById(R.id.itemPrice);
        matchButton = (Button) rootView.findViewById(R.id.match_button);
        itemDescription = (TextView) rootView.findViewById(R.id.itemDescription);
        expandedCard = (CardView) rootView.findViewById(R.id.expanded_Card);



        final Request currentRequest = mParam2;
        final Item currentItem = mParam1;

        //profile data
        profileName.setText(currentRequest.getRequester_name());
        new DownloadImageTask((ImageView) profileIcon)
                .execute(currentRequest.getRequester_ppicture_url());

        // Items data
        itemName.setText(currentItem.getName());
        itemIcon.setImageResource(currentItem.getIcon());
        final String price = String.valueOf(currentItem.getPrice());
        itemPrice.setText(price);
        itemPrice.setTextColor(Color.parseColor("#85bb65"));
        itemDescription.setText(currentRequest.getDescription());




        // Match button
        matchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User currentUser = LocalData.getCurrentUserInstance(); //this is the logged in provider
                String provider_name = currentUser.getName();
                String provider_surname = currentUser.getSurname();

                final Offer newOffer = new Offer(
                        ""+currentItem.getItem_id(),
                        ""+currentRequest.getRequest_id(),
                        "" + currentUser.getUser_id(),
                        ""+currentRequest.getBegin_date(),
                        ""+currentRequest.getEnd_date(),
                        ""+currentRequest.getLon(),
                        ""+currentRequest.getLat(),
                        ""+price,
                        ""+currentRequest.getDescription(),
                        ""+currentRequest.getEnd_date(),
                        "" + currentUser.getPpicture_url(),
                        ""+currentRequest.getRequester_name(),
                        ""+currentRequest.getRequester_surname(),
                        ""+currentRequest.getRequester_ppicture_url());

                Log.d("POSTING OFFER", newOffer.toString());
                //post new offer json object
                final String url = "https://ask-capa.herokuapp.com/api/offers";
                POSTData postData = new POSTData();
                postData.postOffer(url, newOffer, getContext(), new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONArray jsonArray) {
                        //pass request object for RequestConfirmationActivity display
                        Intent intent = new Intent(getContext(), MatchConfirmationActivity.class);
                        intent.putExtra("Offer", (Serializable) newOffer);
                        getContext().startActivity(intent);
                    }

                    @Override
                    public void onFailure() {
                        Log.d("RecyclerViewAdapter", "failure posting offer");
                        Toast.makeText(getContext(), "Unable to send Offer.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        //onClick for expanded card so that it returns to original page
        final Fragment myFrag = this;
        expandedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().beginTransaction().remove(myFrag).commit();
            }
        });

        // Handles when the profile icon is clicked
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(getContext(), ProfileActivity.class);

                VolleyFetcher process = new VolleyFetcher("https://ask-capa.herokuapp.com/api/users", getContext());
                process.jsonReader(new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONArray jsonArrayRequests) {
                        // handle JSONOBJECT response
                        userHashMap = JsonParser.JsonArrayUsersToHashMapUsers(jsonArrayRequests);
                        for (String each : userHashMap.keySet()) {
                            Log.d("USER KEY", each);
                        }
                        User profile = userHashMap.get(currentRequest.getRequester_id());
                        Log.d("PROFILE#", profile.toString());
                        intent.putExtra("profileUser", (Serializable) profile);
                        getContext().startActivity(intent);
                    }

                    @Override
                    public void onFailure() {
                        // in case of failure
                        Log.d("USER_GET_FAILURE", "Something went wrong");
                    }
                });

            }
        });



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

