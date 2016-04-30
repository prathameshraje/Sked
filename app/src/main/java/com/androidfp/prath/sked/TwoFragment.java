package com.androidfp.prath.sked;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class TwoFragment extends Fragment {
    int userID;
    ListView listContent;
    ListAdapter customAdapter;
    List<Event> events;
    String Event_ID,Event_Name;
    String[] eventIDList;
    Event event;
    private SwipeRefreshLayout swipeContainer;

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final SqlHelper db = new SqlHelper(getActivity());
        final View InputFragmentView2 = inflater.inflate(R.layout.fragment_two, container, false);
        userID = getActivity().getIntent().getExtras().getInt("UserID");
        Log.d("UserID", "" + userID);

        //SqlHelper db = new SqlHelper(getActivity());

        // get all books
        //List<Event> list = db.getAllEvents(userID);

        // get all books
        //db.getAllEvents(userID);

        //Initial loading of List view with data from database
        listContent = (ListView) InputFragmentView2.findViewById(R.id.list);
        events = new ArrayList<Event>();
        events = db.getAllEvents(userID);

        //get data from the table by the ListAdapter
        customAdapter = new ListAdapter(this.getActivity(), R.layout.itemlistrow, events);
        listContent.setAdapter(customAdapter);

        //Load the Event Detailed Activity on clicking an event from list
        listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                //Toast test = Toast.makeText(getActivity(), "click works!", Toast.LENGTH_SHORT);
                //test.show();
                Intent i = new Intent(getActivity(), EventDetails.class);
                events=db.getAllEvents(userID);
                Event_ID=events.get(position).getEventID();
                Event_Name=events.get(position).getEventName();
                Log.d("Event ID",Event_ID);
                i.putExtra("event_id", Event_ID);
                i.putExtra("event_name",Event_Name);
                i.putExtra("user_id",userID);
                startActivity(i);
            }
        });

        //Notification Bar to show if event is scheduled
        eventIDList= db.isScheduledNotification(userID);
        if (eventIDList.length>0) {
            // Prepare intent which is triggered if the
            // notification is selected
            Intent intent = new Intent(getActivity(), TwoFragment.class);
            PendingIntent pIntent = PendingIntent.getActivity(getActivity(), (int) System.currentTimeMillis(), intent, 0);
            Context context;
            // Build notification
            // Actions are just fake
            Notification[] not = new Notification[eventIDList.length];
            for (int i = 0; i < eventIDList.length; i++){
                event=db.getEventDetails(eventIDList[i]);
                not[i] = new Notification.Builder(getActivity())
                        .setContentTitle(event.getEventName() + " is Scheduled!")
                        .setContentText("Subject").setSmallIcon(R.drawable.appicon)
                        .setContentText(event.getEventDesc())
                        .setContentIntent(pIntent)
                        .addAction(R.drawable.appicon, "Your Event has been scheduled", pIntent).build();
                //set all default sound, vibration etc on notification
                not[i].defaults = Notification.DEFAULT_ALL;
                NotificationManager notificationManager
                        = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                // hide the notification after its selected
                not[i].flags |= Notification.FLAG_AUTO_CANCEL;
                //not[i].flags |= Notification.FLAG_AUTO_CANCEL;
                notificationManager.notify(i, not[i]);
                ;
            }
        }

        //Swipe refresh logic
        swipeContainer = (SwipeRefreshLayout) InputFragmentView2.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //delay handler to add a delay of 1.5 seconds to show refresh rotator color
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        SqlHelper db = new SqlHelper(getActivity());
                        listContent = (ListView) getActivity().findViewById(R.id.list);
                        events = new ArrayList<Event>();
                        events = db.getAllEvents(userID);

                        //get data from the table by the ListAdapter
                        customAdapter = new ListAdapter(getActivity(), R.layout.itemlistrow, events);
                        listContent.setAdapter(customAdapter);
                        swipeContainer.setRefreshing(false);
                    }
                }, 1500);

            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);



        // Inflate the layout for this fragment
        return InputFragmentView2;
    }


}