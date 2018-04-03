package com.ask.ask;

/**
 * Created by alexisdurocher on 27/03/2018.
 */


        import android.content.Context;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import org.json.JSONArray;

        import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    protected Context fragmentContext;

    // requests
    public static HashMap<String, Request> requestHashMap;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentContext = this.getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        refreshRecyclerViewAdapter(rootView);
        return rootView;
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

    /*
        * Refresh the requests card with new data from POST request
     */
    public void refreshRecyclerViewAdapter(final View rootView) {
        VolleyFetcher fetcher = new VolleyFetcher("https://ask-capa.herokuapp.com/api/requests", fragmentContext);
        fetcher.jsonReader(new VolleyCallback() {
            @Override
            public void onSuccess(JSONArray jsonArrayRequests) {
                // handle JSONOBJECT response
                requestHashMap = JsonParser.JsonArrayRequestsToHashMapRequests(jsonArrayRequests);
                for (String each : requestHashMap.keySet()) {
                    Log.d("ITEM KEY", each);
                    // display adapater
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(fragmentContext, requestHashMap);
                    RecyclerView myView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
                    myView.setHasFixedSize(true);
                    myView.setAdapter(adapter);

                    LinearLayoutManager llm = new LinearLayoutManager(fragmentContext);
                    llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                    myView.setLayoutManager(llm);

                }
            }
            @Override
            public void onFailure() {
                // handle failure ?
            }
        });
    }

}