package com.ask.ask;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("onCreateView()", "RequestsFragment");
        View rootView = inflater.inflate(R.layout.fragment_requests, container, false);

        User user_data[] = new User[]
                {
                        new User(null, "Bob", 25, R.mipmap.bob_profile,
                                "678-456-9831", "711-2880 Nulla St." +
                                "Mankato, Mississippi 96522"),

                        new User(null, "Jim", 29, R.mipmap.jim_profile,
                                "770-783-2923", "606-3727 Ullamcorper. Street " +
                                "Roseville, NH 11523"),

                        new User(null, "Nancy", 26, R.mipmap.nancy_profile,
                                "404-719-3817", "Ap #867-859 Sit Rd. Azusa, New York 39531"),

                        new User(null, "Karen", 29, R.mipmap.karen_profile,
                                "678-820-8638", "7292 Dictum Av. San Antonio, MI 47096"),

                        new User(null, "John", 31, R.mipmap.john_profile,
                                "770-293-3621", "191-103 Integer Rd. " +
                                "Corona, New Mexico 08219")
                };

        //Creating an array of items

        Item item_data[] = new Item[]
                {
                        new Item(null, null,"Golf Club", null,
                                5.00, null, R.mipmap.item_golfclub, user_data[0]),
                        new Item(null, null,"Pot", null,
                                10.00, null, R.mipmap.item_pot, user_data[1]),
                        new Item(null, null,"Sleeping Bag", null,
                                7.00, null, R.mipmap.item_sleepingbag, user_data[2]),
                        new Item(null, null,"Surfboard", null,
                                12.00, null, R.mipmap.item_surfboard, user_data[3]),
                        new Item(null,null, "Tent", null,
                                5.00, null, R.mipmap.item_tent, user_data[4])
                };

        //Creating an array of Requests

        Request request_data[] = new Request[]
                {
                        new Request(user_data[0], item_data[0], "6/10/18", "6/12/18",
                                "I want a golf club for one weekend," +
                                        " price can be negotiable" ),
                        new Request(user_data[1], item_data[1], "7/05/18", "7/06/18",
                                "I am traveling for a day and " +
                                        "need a pot to cook some food" ),
                        new Request(user_data[2], item_data[2], "7/15/18", "7/18/18",
                                "I am camping for a couple of days and need" +
                                        " a sleeping bag"),
                        new Request(user_data[3], item_data[3], "6/12/18", "6/13/18",
                                "I want to go surfing for the day"),
                        new Request(user_data[4], item_data[4], "7/24/18", "7/26/18",
                                "I need a tent for the weekend, preferably" +
                                        " for one person")
                };

//        ExpandableRequestAdapter adapter = new ExpandableRequestAdapter(getContext(), R.layout.listview_request_row, request_data);
//        expandableListViewRequests = (ExpandableListView) rootView.findViewById(R.id.expandableListViewRequests);
//        expandableListViewRequests.setAdapter(adapter);

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