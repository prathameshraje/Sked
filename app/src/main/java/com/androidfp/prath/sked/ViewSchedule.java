package com.androidfp.prath.sked;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewSchedule extends AppCompatActivity {

    String EventID,skedTime;
    int UserID;
    SqlHelper sh = new SqlHelper(this);
    TextView textViewEventNameValue,textViewEventDetailsValue,textViewEventScheduleValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);
        Intent i = getIntent();
        EventID = i.getStringExtra("EventID");
        UserID = i.getIntExtra("UserID", 0);
        skedTime = i.getStringExtra("skedTime");
        Event event=new Event();
        event=sh.getEventDetails(EventID);
        textViewEventNameValue=(TextView) findViewById(R.id.textViewEventNameValue);
        textViewEventDetailsValue=(TextView) findViewById(R.id.textViewEventDetailsValue);
        textViewEventScheduleValue=(TextView) findViewById(R.id.textViewScheduleValue);
        textViewEventNameValue.setText(event.getEventName());
        textViewEventDetailsValue.setText(event.getEventDesc());
        textViewEventScheduleValue.setText(skedTime);

    }

}
