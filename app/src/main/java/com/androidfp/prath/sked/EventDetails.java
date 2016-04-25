package com.androidfp.prath.sked;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class EventDetails extends AppCompatActivity {
    private String EventID,EventName, eventDayOne, eventDayTwo, eventDayThree;
    EditText eventName;
    TextView textViewEventDayOne, textViewEventDayTwo, textViewEventDayThree,
            textViewEventDayOneLabel, textViewEventDayTwoLabel,textViewEventDayThreeLabel,
            textViewTSA1,textViewTSA2,textViewTSA3,textViewTSA4,textViewTSA5,
            textViewTSB1,textViewTSB2,textViewTSB3,textViewTSB4,textViewTSB5,
            textViewTSC1,textViewTSC2,textViewTSC3,textViewTSC4,textViewTSC5;
    Spinner spinner1,spinner2,spinner3,spinner4,spinner5,
            spinner6,spinner7,spinner8,spinner9,spinner10,
            spinner11,spinner12,spinner13,spinner14,spinner15;
    Button buttonSaveAvailability, buttonSked, buttonViewSked;
    String ts1,ts2,ts3,ts4,ts5,ts6,ts7,ts8,ts9,ts10,ts11,ts12,ts13,ts14,ts15;
    String skedTime,skedTime2,skedTime3;
    int timeFrame,UserID;
    int a=0; //arrAvailability position
    String[] arrAvailability;
    SqlHelper sh = new SqlHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Intent i = getIntent();
        EventID = i.getStringExtra("event_id");
        EventName = i.getStringExtra("event_name");
        UserID = i.getIntExtra("user_id", 0);
        Log.d("Got EventID", EventID);
        Log.d("Got EventName here", EventName);
        Log.d("UserID EventDetails", Integer.toString(UserID));

/*
        for(int x=0;x<arrAvailability.length;x++)
        {
            arrAvailability[x] = null;
        }
*/
        eventName = (EditText) findViewById(R.id.editTextEventName);
        eventName.setText(EventName);
        textViewEventDayOne = (TextView) findViewById(R.id.textViewDayOneDate);
        textViewEventDayTwo = (TextView) findViewById(R.id.textViewDayTwoDate);
        textViewEventDayThree = (TextView) findViewById(R.id.textViewDayThreeDate);
        textViewEventDayTwoLabel = (TextView) findViewById(R.id.textViewDayTwoLabel);
        textViewEventDayThreeLabel = (TextView) findViewById(R.id.textViewDayThreeLabel);
        textViewTSB1 = (TextView) findViewById(R.id.textViewTSB1);
        textViewTSB2 = (TextView) findViewById(R.id.textViewTSB2);
        textViewTSB3 = (TextView) findViewById(R.id.textViewTSB3);
        textViewTSB4 = (TextView) findViewById(R.id.textViewTSB4);
        textViewTSB5 = (TextView) findViewById(R.id.textViewTSB5);
        textViewTSC1 = (TextView) findViewById(R.id.textViewTSC1);
        textViewTSC2 = (TextView) findViewById(R.id.textViewTSC2);
        textViewTSC3 = (TextView) findViewById(R.id.textViewTSC3);
        textViewTSC4 = (TextView) findViewById(R.id.textViewTSC4);
        textViewTSC5 = (TextView) findViewById(R.id.textViewTSC5);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner4 = (Spinner) findViewById(R.id.spinner4);
        spinner5 = (Spinner) findViewById(R.id.spinner5);
        spinner6 = (Spinner) findViewById(R.id.spinner6);
        spinner7 = (Spinner) findViewById(R.id.spinner7);
        spinner8 = (Spinner) findViewById(R.id.spinner8);
        spinner9 = (Spinner) findViewById(R.id.spinner9);
        spinner10 = (Spinner) findViewById(R.id.spinner10);
        spinner11 = (Spinner) findViewById(R.id.spinner11);
        spinner12 = (Spinner) findViewById(R.id.spinner12);
        spinner13 = (Spinner) findViewById(R.id.spinner13);
        spinner14 = (Spinner) findViewById(R.id.spinner14);
        spinner15 = (Spinner) findViewById(R.id.spinner15);

        buttonSaveAvailability = (Button) findViewById(R.id.buttonSaveAvailability);
        buttonSked = (Button) findViewById(R.id.buttonSked);
        buttonViewSked = (Button) findViewById(R.id.buttonViewSked);

        Event event = new Event();
        event = sh.getEventDetails(EventID);
        //timeFrame = sh.getTimeframe(EventID);
        timeFrame = event.getEventTimeFrame();

        Log.d("TimeFrame", Integer.toString(timeFrame));

        switch (timeFrame) {

            case 1:
                eventDayOne = event.getEventDayOne();
                textViewEventDayOne.setText(eventDayOne);
                setTextViewDisabled(textViewEventDayTwoLabel);
                setTextViewDisabled(textViewEventDayTwo);
                setTextViewDisabled(textViewEventDayThreeLabel);
                setTextViewDisabled(textViewEventDayThree);
                setTextViewDisabled(textViewTSB1);
                setTextViewDisabled(textViewTSB2);
                setTextViewDisabled(textViewTSB3);
                setTextViewDisabled(textViewTSB4);
                setTextViewDisabled(textViewTSB5);
                setTextViewDisabled(textViewTSC1);
                setTextViewDisabled(textViewTSC2);
                setTextViewDisabled(textViewTSC3);
                setTextViewDisabled(textViewTSC4);
                setTextViewDisabled(textViewTSC5);

                setSpinnerDisabled(spinner6);
                setSpinnerDisabled(spinner7);
                setSpinnerDisabled(spinner8);
                setSpinnerDisabled(spinner9);
                setSpinnerDisabled(spinner10);
                setSpinnerDisabled(spinner11);
                setSpinnerDisabled(spinner12);
                setSpinnerDisabled(spinner13);
                setSpinnerDisabled(spinner14);
                setSpinnerDisabled(spinner15);
                //To bring the Save button up and below spinner 5
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) buttonSaveAvailability.getLayoutParams();
                //params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params.addRule(RelativeLayout.BELOW, R.id.spinner5);
                buttonSaveAvailability.setLayoutParams(params); //causes layout update

                buttonSaveAvailability.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (view.getId() == R.id.buttonSaveAvailability) {
                            arrAvailability = new String[5];
                            ts1 = spinner1.getSelectedItem().toString();
                            if (ts1.equals("SELECT TIME")) {
                                ts1 = null;
                            }
                            ts2 = spinner2.getSelectedItem().toString();
                            if (ts2.equals("SELECT TIME")) {
                                ts2 = null;
                            }
                            ts3 = spinner3.getSelectedItem().toString();
                            if (ts3.equals("SELECT TIME")) {
                                ts3 = null;
                            }
                            ts4 = spinner4.getSelectedItem().toString();
                            if (ts4.equals("SELECT TIME")) {
                                ts4 = null;
                            }
                            ts5 = spinner5.getSelectedItem().toString();
                            if (ts5.equals("SELECT TIME")) {
                                ts5 = null;
                            }


                            arrAvailability[0] = ts1;
                            arrAvailability[1] = ts2;
                            arrAvailability[2] = ts3;
                            arrAvailability[3] = ts4;
                            arrAvailability[4] = ts5;
                            sh.addAvailability(EventID, UserID, arrAvailability, eventDayOne, null, null);
                            Toast dataSaved = Toast.makeText(getBaseContext(), "Availability Details Saved!", Toast.LENGTH_SHORT);
                            dataSaved.show();


                        }
                    }
                });
                buttonViewSked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (view.getId() == R.id.buttonViewSked) {
                            if(!sh.isScheduled(EventID)){
                                Toast initiatorError = Toast.makeText(getBaseContext(), "The Event is not Scheduled yet by the Organizer!", Toast.LENGTH_LONG);
                                initiatorError.show();
                            }
                            else {
                                skedTime = sh.getSkedOne(EventID, timeFrame, eventDayOne, null, null);
                                Log.d("Sked Time", "" + skedTime);
                                Intent i = new Intent(EventDetails.this, ViewSchedule.class);
                                i.putExtra("UserID", UserID);
                                i.putExtra("EventID",EventID);
                                i.putExtra("skedTime",skedTime);
                                startActivity(i);
                                /*Toast sked = Toast.makeText(getBaseContext(), "Your Sked: " + skedTime, Toast.LENGTH_SHORT);
                                sked.show(); */

                            }
                        }
                    }
                });
                buttonSked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (view.getId() == R.id.buttonSked) {
                            if(sh.checkAuthority(EventID,UserID)) {
                                sh.setScheduled(EventID);
                                Toast setSked = Toast.makeText(getBaseContext(), "Your Event is now Scheduled!", Toast.LENGTH_LONG);
                                setSked.show();
                            }
                            else
                            {
                                Toast setSked = Toast.makeText(getBaseContext(), "You are not authorized for this action. Only Event Organizer can Sked It!", Toast.LENGTH_LONG);
                                setSked.show();
                            }
                        }
                    }
                });

                break;

            case 2:
                eventDayOne = event.getEventDayOne();
                textViewEventDayOne.setText(eventDayOne);
                eventDayTwo = event.getEventDayTwo();
                textViewEventDayTwo.setText(eventDayTwo);
                setTextViewDisabled(textViewEventDayThreeLabel);
                setTextViewDisabled(textViewEventDayThree);
                setTextViewDisabled(textViewTSC1);
                setTextViewDisabled(textViewTSC2);
                setTextViewDisabled(textViewTSC3);
                setTextViewDisabled(textViewTSC4);
                setTextViewDisabled(textViewTSC5);

                setSpinnerDisabled(spinner11);
                setSpinnerDisabled(spinner12);
                setSpinnerDisabled(spinner13);
                setSpinnerDisabled(spinner14);
                setSpinnerDisabled(spinner15);
                //To bring the Save button up and below spinner 10
                RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) buttonSaveAvailability.getLayoutParams();
                //params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                params2.addRule(RelativeLayout.BELOW, R.id.spinner10);
                buttonSaveAvailability.setLayoutParams(params2); //causes layout update

                buttonSaveAvailability.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        arrAvailability = new String[10];
                        if (view.getId() == R.id.buttonSaveAvailability) {

                            ts1 = spinner1.getSelectedItem().toString();
                            if (ts1.equals("SELECT TIME")) {
                                ts1 = null;
                            }
                            ts2 = spinner2.getSelectedItem().toString();
                            if (ts2.equals("SELECT TIME")) {
                                ts2 = null;
                            }
                            ts3 = spinner3.getSelectedItem().toString();
                            if (ts3.equals("SELECT TIME")) {
                                ts3 = null;
                            }
                            ts4 = spinner4.getSelectedItem().toString();
                            if (ts4.equals("SELECT TIME")) {
                                ts4 = null;
                            }
                            ts5 = spinner5.getSelectedItem().toString();
                            if (ts5.equals("SELECT TIME")) {
                                ts5 = null;
                            }
                            ts6 = spinner6.getSelectedItem().toString();
                            if (ts6.equals("SELECT TIME")) {
                                ts6 = null;
                            }
                            ts7 = spinner7.getSelectedItem().toString();
                            if (ts7.equals("SELECT TIME")) {
                                ts7 = null;
                            }
                            ts8 = spinner8.getSelectedItem().toString();
                            if (ts8.equals("SELECT TIME")) {
                                ts8 = null;
                            }
                            ts9 = spinner9.getSelectedItem().toString();
                            if (ts9.equals("SELECT TIME")) {
                                ts9 = null;
                            }
                            ts10 = spinner10.getSelectedItem().toString();
                            if (ts10.equals("SELECT TIME")) {
                                ts10 = null;
                            }


                            arrAvailability[0] = ts1;
                            arrAvailability[1] = ts2;
                            arrAvailability[2] = ts3;
                            arrAvailability[3] = ts4;
                            arrAvailability[4] = ts5;
                            arrAvailability[5] = ts6;
                            arrAvailability[6] = ts7;
                            arrAvailability[7] = ts8;
                            arrAvailability[8] = ts9;
                            arrAvailability[9] = ts10;
                            sh.addAvailability(EventID, UserID, arrAvailability, eventDayOne, eventDayTwo, null);
                            Toast dataSaved = Toast.makeText(getBaseContext(), "Availability Details Saved!", Toast.LENGTH_SHORT);
                            dataSaved.show();
                        }
                    }
                });
                buttonViewSked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (view.getId() == R.id.buttonViewSked) {
                            if(!sh.isScheduled(EventID)){
                                Toast initiatorError = Toast.makeText(getBaseContext(), "The Event is not Scheduled yet by the Organizer!", Toast.LENGTH_LONG);
                                initiatorError.show();
                            }
                            else {
                                skedTime = sh.getSkedOne(EventID, timeFrame, eventDayOne, eventDayTwo, null);
                                Log.d("Sked Time", "" + skedTime);
                                Intent i = new Intent(EventDetails.this, ViewSchedule.class);
                                i.putExtra("UserID", UserID);
                                i.putExtra("EventID",EventID);
                                i.putExtra("skedTime",skedTime);
                                startActivity(i);
                                /*Toast sked = Toast.makeText(getBaseContext(), "Your Sked: " + skedTime, Toast.LENGTH_SHORT);
                                sked.show();*/
                            }
                        }
                    }
                });
                buttonSked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (view.getId() == R.id.buttonSked) {
                            sh.setScheduled(EventID);
                            Toast setSked = Toast.makeText(getBaseContext(), "Your Event is now Scheduled!", Toast.LENGTH_LONG);
                            setSked.show();
                        }
                    }
                });
                break;

            case 3:
                eventDayOne = event.getEventDayOne();
                textViewEventDayOne.setText(eventDayOne);
                eventDayTwo = event.getEventDayTwo();
                textViewEventDayTwo.setText(eventDayTwo);
                eventDayThree = event.getEventDayThree();
                textViewEventDayThree.setText(eventDayThree);

                buttonSaveAvailability.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (view.getId() == R.id.buttonSaveAvailability) {
                            arrAvailability = new String[15];
                            ts1 = spinner1.getSelectedItem().toString();
                            if (ts1.equals("SELECT TIME")) {
                                ts1 = null;
                            }
                            ts2 = spinner2.getSelectedItem().toString();
                            if (ts2.equals("SELECT TIME")) {
                                ts2 = null;
                            }
                            ts3 = spinner3.getSelectedItem().toString();
                            if (ts3.equals("SELECT TIME")) {
                                ts3 = null;
                            }
                            ts4 = spinner4.getSelectedItem().toString();
                            if (ts4.equals("SELECT TIME")) {
                                ts4 = null;
                            }
                            ts5 = spinner5.getSelectedItem().toString();
                            if (ts5.equals("SELECT TIME")) {
                                ts5 = null;
                            }
                            ts6 = spinner6.getSelectedItem().toString();
                            if (ts6.equals("SELECT TIME")) {
                                ts6 = null;
                            }
                            ts7 = spinner7.getSelectedItem().toString();
                            if (ts7.equals("SELECT TIME")) {
                                ts7 = null;
                            }
                            ts8 = spinner8.getSelectedItem().toString();
                            if (ts8.equals("SELECT TIME")) {
                                ts8 = null;
                            }
                            ts9 = spinner9.getSelectedItem().toString();
                            if (ts9.equals("SELECT TIME")) {
                                ts9 = null;
                            }
                            ts10 = spinner10.getSelectedItem().toString();
                            if (ts10.equals("SELECT TIME")) {
                                ts10 = null;
                            }
                            ts11 = spinner11.getSelectedItem().toString();
                            if (ts11.equals("SELECT TIME")) {
                                ts11 = null;
                            }
                            ts12 = spinner12.getSelectedItem().toString();
                            if (ts12.equals("SELECT TIME")) {
                                ts12 = null;
                            }
                            ts13 = spinner13.getSelectedItem().toString();
                            if (ts13.equals("SELECT TIME")) {
                                ts13 = null;
                            }
                            ts14 = spinner14.getSelectedItem().toString();
                            if (ts14.equals("SELECT TIME")) {
                                ts14 = null;
                            }
                            ts15 = spinner15.getSelectedItem().toString();
                            if (ts15.equals("SELECT TIME")) {
                                ts15 = null;
                            }

                            arrAvailability[0] = ts1;
                            arrAvailability[1] = ts2;
                            arrAvailability[2] = ts3;
                            arrAvailability[3] = ts4;
                            arrAvailability[4] = ts5;
                            arrAvailability[5] = ts6;
                            arrAvailability[6] = ts7;
                            arrAvailability[7] = ts8;
                            arrAvailability[8] = ts9;
                            arrAvailability[9] = ts10;
                            arrAvailability[10] = ts11;
                            arrAvailability[11] = ts12;
                            arrAvailability[12] = ts13;
                            arrAvailability[13] = ts14;
                            arrAvailability[14] = ts15;
                            sh.addAvailability(EventID, UserID, arrAvailability, eventDayOne, eventDayTwo, eventDayThree);
                            Toast dataSaved = Toast.makeText(getBaseContext(), "Availability Details Saved!", Toast.LENGTH_SHORT);
                            dataSaved.show();
                        }
                    }
                });
                buttonViewSked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (view.getId() == R.id.buttonViewSked) {
                            if(!sh.isScheduled(EventID)){
                                Toast initiatorError = Toast.makeText(getBaseContext(), "The Event is not Scheduled yet by the Organizer!", Toast.LENGTH_LONG);
                                initiatorError.show();
                            }
                            else {
                                skedTime = sh.getSkedOne(EventID, timeFrame, eventDayOne, eventDayTwo, eventDayThree);
                                Log.d("Sked Time", "" + skedTime);
                                Intent i = new Intent(EventDetails.this, ViewSchedule.class);
                                i.putExtra("UserID", UserID);
                                i.putExtra("EventID",EventID);
                                i.putExtra("skedTime",skedTime);
                                startActivity(i);
                                /*Toast sked = Toast.makeText(getBaseContext(), "Your Sked: " + skedTime, Toast.LENGTH_SHORT);
                                sked.show();*/
                            }
                        }
                    }
                });
                buttonSked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (view.getId() == R.id.buttonSked) {
                            sh.setScheduled(EventID);
                            Toast setSked = Toast.makeText(getBaseContext(), "Your Event is now Scheduled!", Toast.LENGTH_LONG);
                            setSked.show();
                        }
                    }
                });
                break;
        }

        //spinner2.setAlpha(true ? 1.0f : 0.4f);
        //((Spinner) spinner2).getSelectedView().setEnabled(false);
        //((Spinner) spinner2).setEnabled(false);

    }

    private void setSpinnerDisabled(Spinner spinner) {
        spinner.setEnabled(false);
        spinner.setVisibility(View.INVISIBLE);
        //spinner.setAlpha(enabled ? 1.0f : 0.4f);
    }
    private void setTextViewDisabled(TextView textview) {
        textview.setEnabled(false);
        textview.setVisibility(View.INVISIBLE);
        //textview.setAlpha(enabled ? 1.0f : 0.4f);
    }
}
