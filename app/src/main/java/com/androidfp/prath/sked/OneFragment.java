package com.androidfp.prath.sked;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


public class OneFragment extends Fragment{
    static EditText DateEdit,DateEdit2,DateEdit3;
    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //showDatePickerDialog(new EditText(getActivity()));
       /* public void onClick(View v) {
            if(v.getId() == R.id.editTextDatePicker){
                showDatePickerDialog(v);
            }}*/}

            public void onClick(View v) {
                showDatePickerDialog(v);
            }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
/*
        String userName = getActivity().getIntent().getExtras().getString("UserName");
        Log.d("setUSerNAme",userName);
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_one, container, false); //pass the correct layout name for the fragment

        TextView textViewUserName = (TextView) view.findViewById(R.id.textViewUserName);
        textViewUserName.setText(userName);
*/

        View InputFragmentView = inflater.inflate(R.layout.fragment_one, container, false);

        DateEdit = (EditText) InputFragmentView.findViewById(R.id.editTextDatePicker);
        DateEdit2 = (EditText) InputFragmentView.findViewById(R.id.editTextDatePickerTwo);
        DateEdit3 = (EditText) InputFragmentView.findViewById(R.id.editTextDatePickerThree);
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view =  lf.inflate(R.layout.fragment_one, container, false); //pass the correct layout name for the fragment
        //DateEdit = (EditText) view.findViewById(R.id.editTextDatePicker);
        DateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDatePickerDialog(v);
                //DateEdit.setInputType(InputType.TYPE_NULL);
                showDatePickerDialog(new EditText(getActivity()));
            }
        });
        DateEdit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog2(new EditText(getActivity()));
            }
        });
        DateEdit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog3(new EditText(getActivity()));
            }
        });

        // Inflate the layout for this fragment
        return InputFragmentView;
    }


    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
    public void showDatePickerDialog2(View v) {
        DatePickerFragment2 newFragment = new DatePickerFragment2();
        newFragment.show(getFragmentManager(), "datePicker");
    }
    public void showDatePickerDialog3(View v) {
        DatePickerFragment3 newFragment = new DatePickerFragment3();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    @SuppressLint("ValidFragment")
    public  class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            DateEdit.setText(day + "/" + (month + 1) + "/" + year);
        }
    }

    @SuppressLint("ValidFragment")
    public  class DatePickerFragment2 extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            DateEdit2.setText(day + "/" + (month + 1) + "/" + year);
        }
    }

    @SuppressLint("ValidFragment")
    public  class DatePickerFragment3 extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            DateEdit3.setText(day + "/" + (month + 1) + "/" + year);
        }
    }
}