package com.ask.ask;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Button buttonEditSave;
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private boolean currentlyEditing;

    //    private RatingBar ratingBarUserRating;


    public MyProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyProfileFragment newInstance(String param1, String param2) {
        MyProfileFragment fragment = new MyProfileFragment();
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

        //text views, edit texts, buttons, user info to add get R.id.
        //when edit button is pressed, all fields should be set to editable, when save is pressed all fields should be set to view only

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        currentlyEditing = false;

        View rootView = inflater.inflate(R.layout.fragment_myprofile, container, false);

        editTextName = (EditText) rootView.findViewById(R.id.editTextName);
//        ratingBarUserRating = (RatingBar) rootView.findViewById(R.id.ratingBarUserRating);
        editTextEmail = (EditText) rootView.findViewById(R.id.editTextEmail);
        editTextPhone = (EditText) rootView.findViewById(R.id.editTextPhone);
        buttonEditSave = (Button) rootView.findViewById(R.id.buttonEditSave);

        editTextName.setEnabled(false);
//        ratingBarUserRating.setEnabled(false);
        editTextEmail.setEnabled(false);
        editTextPhone.setEnabled(false);

//        ratingBarUserRating.setRating(4.3f);

        buttonEditSave.setText("Edit");
        buttonEditSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentlyEditing) { //save user's edit
                    editTextName.setEnabled(false);
                    editTextEmail.setEnabled(false);
                    editTextPhone.setEnabled(false);
                    buttonEditSave.setText("Edit");
                    currentlyEditing = false;

                    Toast.makeText(getContext(), "Changes Saved!", Toast.LENGTH_SHORT).show();
                } else { //allow user to edit
                    editTextName.setEnabled(true);
                    editTextEmail.setEnabled(true);
                    editTextPhone.setEnabled(true);
                    buttonEditSave.setText("Save");
                    currentlyEditing = true;
                }

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