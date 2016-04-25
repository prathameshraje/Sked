package com.androidfp.prath.sked;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ThreeFragment extends Fragment {
    int userID;
    EditText Event_ID;

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View InputFragmentView3 = inflater.inflate(R.layout.fragment_three, container, false);
        final SqlHelper sh = new SqlHelper(getActivity());
        //To get the User Name of the logged in user
        userID = getActivity().getIntent().getExtras().getInt("UserID");
        Log.d("UserID",""+userID);

        Event_ID = (EditText) InputFragmentView3.findViewById(R.id.editTextEventID);
        Button joinEvent = (Button)InputFragmentView3.findViewById(R.id.buttonJoinEvent);
        joinEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.buttonJoinEvent) {
                    String strID = Event_ID.getText().toString();
                    Log.d("Event ID", strID);

                    if (strID.matches(""))
                        Event_ID.setError("Event ID required");
                     else {
                        //Insert into Database (Create new event record)
                        //Event event = new Event();
                        //event.setEventID(strID);
                        if(!sh.validEvent(strID)){
                            if (!sh.eventExists(strID, userID)) {
                                sh.addEventMember(strID, userID, "NO");
                                Toast eventAdded = Toast.makeText(getActivity(), "Event Added!", Toast.LENGTH_SHORT);
                                eventAdded.show();
                            } else {
                                Toast eventExists = Toast.makeText(getActivity(), "Event Already Exists!", Toast.LENGTH_SHORT);
                                eventExists.show();
                            }
                        }
                        else {
                            Toast eventError = Toast.makeText(getActivity(), "Not a Valid Event ID!", Toast.LENGTH_SHORT);
                            eventError.show();
                        }
                    }
                }
            }
        });
                    // Inflate the layout for this fragment
                    //return inflater.inflate(R.layout.fragment_three, container, false);
                    return InputFragmentView3;
                }

            }