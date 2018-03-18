package com.ask.ask;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by alexander on 3/18/2018.
 *
 * This fragment is used to display a popup calendar where the user can select their request dates.
 */

//TODO: some how display the message of which date to select in title bar or something not through mistimed toasts
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private TextView textViewDateTemp;

    public DatePickerFragment() {};

    @SuppressLint("ValidFragment")
    public DatePickerFragment(TextView textViewDate) {
        super();
        this.textViewDateTemp = textViewDate;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        datePickerDialog.setTitle("Select Begin and End Dates.");

        return datePickerDialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Log.d("onDateSet", "year: " + year + ", month: " + (month + 1) + ", day: " + day); //month starts 1t 0
        String date = (month + 1) + "/" + day + "/" + year;
        textViewDateTemp.setText(date);
    }

//    public void onCancel(DialogInterface dialog){
//        Toast.makeText(getActivity(),"Date Picker Canceled.", Toast.LENGTH_SHORT).show();
//    }

}