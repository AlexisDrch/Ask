package com.ask.ask;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    private List<String> listItemNames;
    private List<String> listElements;
    private HashMap<String, List<String>> hashMapRequestData;


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

        //https://ask-capa.herokuapp.com/api/requests/by/1
        listRequests = ; //TODO: use Pulak's FetchRequests method
        listItemNames = new ArrayList<>(listRequests.size());
        listElements = new ArrayList<>();
        hashMapRequestData = new HashMap<>(listRequests.size());

        for (int i = 0; i < listRequests.size(); i++) {
            listItemNames.add(listRequests.get(i).getItem().getName());

            listElements.clear();
            listElements.add(listRequests.get(i).getBeginDate() + " - " + listRequests.get(i).getEndDate());
            listElements.add("$" + listRequests.get(i).getItem().getPrice());
            listElements.add(listRequests.get(i).getDescription());

            hashMapRequestData.put(listItemNames.get(i), listElements);
        }

        expandableListViewRequests = (ExpandableListView) rootView.findViewById(R.id.expandableListViewRequests);
        expandableListViewAdapter = new ExpandableRequestAdapter(getContext(), listItemNames, hashMapRequestData);
        expandableListViewRequests.setAdapter(expandableListViewAdapter);

        return rootView;
    }

    //not necessary anymore
//    //----------
//    public List<Request> getUsersRequests() {
//        List<Request> parsedRequests = new ArrayList<>();
//
//        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//
//        final String url = "https://ask-capa.herokuapp.com/api/requests/by/1";
//
//        JsonObjectRequest getRequest = new JsonObjectRequest(com.android.volley.Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d("RESPONSE", response.toString());
////                        parsedRequests = parseJSONObject(response.toString());
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //TODO: trycatch for error types
//                        Log.d("Error.Response", error.getMessage());
//                    }
//                }
//            );
//
//        requestQueue.add(getRequest);
//
//        return parsedRequests;
//    }
//    //----------

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