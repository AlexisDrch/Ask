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
import android.view.View;
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

    private DatePickerDialog datePickerDialog;

    private TextView textViewBegin;
    private TextView textViewEnd;
    private View v;
    private int dateTime; //0 = begin, 1 = end

    public DatePickerFragment() {};

    @SuppressLint("ValidFragment")
    public DatePickerFragment(TextView textViewBegin, TextView textViewEnd, View v, int dateTime) {
        super();
        this.textViewBegin = textViewBegin;
        this.textViewEnd = textViewEnd;
        this.v = v;
        this.dateTime = dateTime;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int year;
        int month;
        int day;

        if (dateTime == 0) {
            Calendar calendar = Calendar.getInstance(); //get today's date
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        } else { //TODO: use begin's dates, errors when invalid date selectrd for begin because nothing is filled out
            String[] date = textViewBegin.getText().toString().split("/");
            year = Integer.parseInt(date[2]);
            month = Integer.parseInt(date[0]) - 1;
            day = Integer.parseInt(date[1]);
        }

        datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
//        datePickerDialog.setTitle("Select Begin and End Dates.");

        return datePickerDialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Log.d("onDateSet " + dateTime, "year: " + year + ", month: " + (month + 1) + ", day: " + day); //month starts 1t 0
        String date = (month + 1) + "/" + day + "/" + year;

        if (dateTime == 0) { //begin date
            Calendar calendar = Calendar.getInstance();
            int yearToday = calendar.get(Calendar.YEAR);
            int monthToday = calendar.get(Calendar.MONTH);
            int dayToday = calendar.get(Calendar.DAY_OF_MONTH);

            if (isDateValid(year, month, day, yearToday, monthToday, dayToday)) {
                textViewBegin.setText(date);
            } else {
                //TODO: add begin date verification
            }

            dateTime = 1;
            showDateEndPickerDialog(v, "Select End Date.");
        } else {
            String[] beginDate = textViewBegin.getText().toString().split("/");
            int yearBegin = Integer.parseInt(beginDate[2]);
            int monthBegin = Integer.parseInt(beginDate[0]) - 1;
            int dayBegin = Integer.parseInt(beginDate[1]);
            if (isDateValid(year, month, day, yearBegin, monthBegin, dayBegin)) { //date validation
                textViewEnd.setText(date);
            } else {
                showDateEndPickerDialog(v, "Invalid date selected. Select End Date.");
            }
        }
    }

    public void onCancel(DialogInterface dialog){
        if (dateTime == 0) {
            dateTime = 1;
            showDateEndPickerDialog(v, "Select End Date.");
        }
    }

    public void showDateEndPickerDialog(View v, String message) {
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        DialogFragment datePickerEndFragment = new DatePickerFragment(textViewBegin, textViewEnd, v, 1);
        datePickerEndFragment.show(getFragmentManager(), "dateEndPicker");
    }

    public boolean isDateValid(int year, int month, int day, int yearBegin, int monthBegin, int dayBegin) {
        if (year < yearBegin || month < monthBegin || day <= dayBegin) { //can't choose same day, or can you...?
            return false;
        } else {
            return true;
        }
    }

    //TODO: for later
//    datePickerDialog.setOnDismissListener(mOnDismissListener);
//   datePickerDialog.show();
//    datePickerDialog_visible=true;  //indicate dialog is up
//} // [END showDatePickerDialog]
//
//    //onDismiss handler
//    private DialogInterface.OnDismissListener mOnDismissListener =
//            new DialogInterface.OnDismissListener() {
//                public void onDismiss(DialogInterface dialog) {
//                    datePickerDialog_visible=false;  //indicate dialog is cancelled/gone
//                    if (isDataSet) {  // [IF date was picked
//                        //do something, date now selected
//                    } else {
//                        //do someething else, dialog cancelled or exited
//                    }
//                }
//            };
}