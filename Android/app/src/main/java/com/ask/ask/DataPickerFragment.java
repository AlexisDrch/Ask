package com.ask.ask;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by alexisdurocher on 03/04/2018.
 */

public class DataPickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DatePickerDialog datePickerDialog;

    private TextView textViewBegin;
    private TextView textViewEnd;
    private View view;
    private int dateTime; //-1 = complete, 0 = begin, 1 = end

    private String BEGIN_DATE = "Select Begin Date.";
    private String END_DATE = "Select End Date.";
    private String INVALID_DATE = "Invalid Date Selected.";
    private String NO_DATE = "No date selected.";


    public DataPickerFragment() {};

    @SuppressLint("ValidFragment")
    public DataPickerFragment(TextView textViewBegin, TextView textViewEnd, View view, int dateTime) {
        super();
        this.textViewBegin = textViewBegin;
        this.textViewEnd = textViewEnd;
        this.view = view;
        this.dateTime = dateTime;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int year;
        int month;
        int day;

        Calendar calendar = Calendar.getInstance();

        if (dateTime == 0) {
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            String[] date = textViewBegin.getText().toString().split("/"); //TODO: settings thing to add different formatting (month fist or second)
            year = Integer.parseInt(date[2]);
            month = Integer.parseInt(date[0]) - 1;
            day = Integer.parseInt(date[1]);

            calendar.set(year, month, day);
        }

        datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis() - 1000);

        return datePickerDialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Log.d("onDateSet " + dateTime, "year: " + year + ", month: " + (month + 1) + ", day: " + day); //month begins at 0
        String date = (month + 1) + "/" + day + "/" + year;

        if (dateTime == 0) { //begin date
            Calendar calendar = Calendar.getInstance();
            int yearToday = calendar.get(Calendar.YEAR);
            int monthToday = calendar.get(Calendar.MONTH);
            int dayToday = calendar.get(Calendar.DAY_OF_MONTH);

            if (isDateValid(year, month, day, yearToday, monthToday, dayToday)) {
                textViewBegin.setText(date);
                dateTime = 1;
                showDateEndPickerDialog(view, END_DATE);
            } else {
                showDateBeginPickerDialog(view, INVALID_DATE + " " + BEGIN_DATE);
            }
        } else { //end date
            String[] beginDate = textViewBegin.getText().toString().split("/");
            int yearBegin = Integer.parseInt(beginDate[2]);
            int monthBegin = Integer.parseInt(beginDate[0]) - 1;
            int dayBegin = Integer.parseInt(beginDate[1]);
            if (isDateValid(year, month, day, yearBegin, monthBegin, dayBegin)) {
                textViewEnd.setText(date);
                dateTime = -1;
            } else {
                showDateEndPickerDialog(view, INVALID_DATE + " " + END_DATE);
            }
        }

    }

    public void onCancel(DialogInterface dialog){
        if (dateTime == 0) {
            if (TextUtils.isEmpty(textViewBegin.getText().toString())) { //invalid selection
                showDateBeginPickerDialog(view, NO_DATE + " " + BEGIN_DATE);
            } else { //valid selection
                dateTime = 1;
                showDateEndPickerDialog(view, END_DATE);
            }
        } else if (dateTime == 1) {
            if (TextUtils.isEmpty(textViewEnd.getText().toString())) { //invalid selection
                showDateEndPickerDialog(view, NO_DATE + " " + END_DATE);
            }
            //valid selection
        }

    }

    public void showDateBeginPickerDialog(View view, String message) {
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        DialogFragment datePickerBeginFragment = new DataPickerFragment(textViewBegin, textViewEnd, view, 0);
        datePickerBeginFragment.show(getFragmentManager(), "dateBeginPicker");
    }

    public void showDateEndPickerDialog(View v, String message) {
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        DialogFragment datePickerEndFragment = new DataPickerFragment(textViewBegin, textViewEnd, v, 1);
        datePickerEndFragment.show(getFragmentManager(), "dateEndPicker");
    }

    /*
    * Check if date selected is valid.
    * Begin and End dates may be the same.
     */
    public boolean isDateValid(int year, int month, int day, int yearBegin, int monthBegin, int dayBegin) {
        if (year < yearBegin || month < monthBegin || (month == monthBegin && day < dayBegin)) {
            return false;
        } else {
            return true;
        }
    }

}