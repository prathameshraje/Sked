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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import java.util.Calendar;
import java.util.Date;


public class OneFragment extends Fragment {
    static EditText DateEdit,DateEdit2,DateEdit3;
    int timeFrameCount=3,userID;

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

           /* public void onClick(View v) {
                showDatePickerDialog(v);
            } */


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
        final SqlHelper sh = new SqlHelper(getActivity());
        final View InputFragmentView = inflater.inflate(R.layout.fragment_one, container, false);

        //To get the User Name of the logged in user
        userID = getActivity().getIntent().getExtras().getInt("UserID");
        Log.d("UserID",""+userID);

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

        RadioGroup rg1 = (RadioGroup) InputFragmentView.findViewById(R.id.radioGroupTimeframe);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButtonOne:
                        DateEdit.setEnabled(true);
                        DateEdit2.setEnabled(false);
                        DateEdit2.setText(null);
                        DateEdit3.setEnabled(false);
                        DateEdit3.setText(null);
                        timeFrameCount = 1;
                        break;

                    case R.id.radioButtonTwo:
                        DateEdit.setEnabled(true);
                        DateEdit2.setEnabled(true);
                        DateEdit3.setEnabled(false);
                        DateEdit3.setText(null);
                        timeFrameCount = 2;
                        break;

                    case R.id.radioButtonThree:
                        DateEdit.setEnabled(true);
                        DateEdit2.setEnabled(true);
                        DateEdit3.setEnabled(true);
                        timeFrameCount = 3;
                        break;
                }

            }
        });

        //On creating event

        Button createEvent = (Button)InputFragmentView.findViewById(R.id.buttonCreateEvent);

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.buttonCreateEvent) {
                    EditText eventID = (EditText) InputFragmentView.findViewById(R.id.editTextEventID);
                    EditText eventName = (EditText) InputFragmentView.findViewById(R.id.editTextEventName);
                    EditText eventDesc = (EditText) InputFragmentView.findViewById(R.id.editTextEventDescription);
                    EditText eventDayOne = (EditText) InputFragmentView.findViewById(R.id.editTextDatePicker);
                    EditText eventDayTwo = (EditText) InputFragmentView.findViewById(R.id.editTextDatePickerTwo);
                    EditText eventDayThree = (EditText) InputFragmentView.findViewById(R.id.editTextDatePickerThree);

                    String strID = eventID.getText().toString();
                    String strName = eventName.getText().toString();
                    String strDesc = eventDesc.getText().toString();
                    String strDayOne = eventDayOne.getText().toString();
                    String strDayTwo = eventDayTwo.getText().toString();
                    String strDayThree = eventDayThree.getText().toString();
                    Log.d("Event Name", strName);

                    if (strID.matches("") || strName.matches("") || strDesc.matches("")) {
                        Toast nullValueError = Toast.makeText(getActivity(), "Kindly fill all the field", Toast.LENGTH_SHORT);
                        nullValueError.show();

                        if (strName.matches("")) {
                            eventName.setError("Name required");
                        }
                        if (strDesc.matches("")) {
                            eventDesc.setError("Description required");
                        }
                    }
                    else if (!isDateSelected(timeFrameCount)) {

                        Toast dateError = Toast.makeText(getActivity(), "Kindly select date!", Toast.LENGTH_SHORT);
                        dateError.show();
                    }
                    else {
                        //Insert into Database (Create new event record)
                        Event event = new Event();
                        event.setEventID(strID);
                        event.setEventName(strName);
                        event.setEventDesc(strDesc);
                        event.setEventTimeFrame(timeFrameCount);
                        event.setEventDayOne(strDayOne);
                        event.setEventDayTwo(strDayTwo);
                        event.setEventDayThree(strDayThree);

                        sh.addEvent(event);
                        sh.addEventMember(strID,userID,"YES");
                        Toast accountCreated = Toast.makeText(getActivity(), "Event Created!", Toast.LENGTH_SHORT);
                        accountCreated.show();
                    }
                }
            }
        });


        // Inflate the layout for this fragment
        return InputFragmentView;
    }

    // validating email id
    private boolean isDateSelected(int timeframeDays){
        if(timeframeDays==1)
            if(DateEdit.getText().toString().equals("")) {
                DateEdit.setError("Enter a Date!");
                return false;
            }
            else return true;
        else if(timeframeDays==2)
            if(DateEdit.getText().toString().equals("") || DateEdit2.getText().toString().equals("")) {
                DateEdit.setError("Enter a Date!");
                DateEdit2.setError("Enter a Date!");
                return false;
            }
            else return true;
        else if(timeframeDays==3)
                if (DateEdit.getText().toString().equals("") || DateEdit2.getText().toString().equals("") || DateEdit3.getText().toString().equals("")){
                    DateEdit.setError("Enter a Date!");
                DateEdit2.setError("Enter a Date!");
                DateEdit3.setError("Enter a Date!");
                    return false;
                }
                else return true;
        else return false;
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