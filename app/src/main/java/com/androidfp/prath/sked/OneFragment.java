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

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;
import java.util.Date;


public class OneFragment extends Fragment {
    static EditText DateEdit,DateEdit2,DateEdit3;
    int timeFrameCount=3,userID,count;
    String placeDetailsStr=null;
    String placeLatLng;
    Double[] latlong = new Double[2];
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
            }}*/


    }

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

        final PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getActivity().getFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                // Log.i(TAG, "Place: " + place.getName());

                placeDetailsStr = "" + place.getAddress();
                //place.getName() + "\n"
                //+ place.getId() + "\n"
                //+ place.getLatLng().toString() + "\n"
                // +
                //+ "\n"
                //+ place.getAttributions();
                placeLatLng = "" + place.getLatLng().toString();
                Log.d("Address", "" + place.getAddress());
                Log.d("ID", place.getId());
                Log.d("Attr", "" + place.getAttributions());
                Log.d("LatLng", placeLatLng);
                //etSearch.setText(placeDetailsStr);
            }
            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                // Log.i(TAG, "An error occurred: " + status);
            }
        });



        DateEdit = (EditText) InputFragmentView.findViewById(R.id.editTextDatePicker);
        DateEdit2 = (EditText) InputFragmentView.findViewById(R.id.editTextDatePickerTwo);
        DateEdit3 = (EditText) InputFragmentView.findViewById(R.id.editTextDatePickerThree);
        LayoutInflater lf = getActivity().getLayoutInflater();
       // View view =  lf.inflate(R.layout.fragment_one, container, false); //pass the correct layout name for the fragment
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
                        if(placeDetailsStr!=null) {
                            count = 0;
                            placeLatLng = placeLatLng.substring(10, placeLatLng.length() - 1);
                            Log.d("New LATLNG", placeLatLng);
                            for (String val : placeLatLng.split(",")) {
                                latlong[count] = Double.parseDouble(val);
                                //Log.d("Round",Double.toString(latlong[count]));
                                count++;
                            }
                        }
                        else{
                            placeDetailsStr=null;
                            latlong[0]=0.0;
                            latlong[1]=0.0;
                        }
                        //placeDetailsStr;

                        //Insert into Database (Create new event record)
                        Event event = new Event();
                        event.setEventID(strID);
                        event.setEventName(strName);
                        event.setEventDesc(strDesc);
                        event.setEventTimeFrame(timeFrameCount);
                        event.setEventDayOne(strDayOne);
                        event.setEventDayTwo(strDayTwo);
                        event.setEventDayThree(strDayThree);
                        event.setAddress(placeDetailsStr);
                        event.setLat(latlong[0]);
                        event.setLng(latlong[1]);

                        sh.addEvent(event);
                        sh.addEventMember(strID,userID,"YES");
                        placeDetailsStr=null;
                        latlong[0]=null;
                        latlong[1]=null;
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