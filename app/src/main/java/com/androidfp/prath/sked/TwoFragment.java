package com.androidfp.prath.sked;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class TwoFragment extends Fragment {
    int userID;
    ListView listContent;
    ListAdapter customAdapter;
    List<Event> events;
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

        SqlHelper db = new SqlHelper(getActivity());
        View InputFragmentView = inflater.inflate(R.layout.fragment_two, container, false);
        userID = getActivity().getIntent().getExtras().getInt("UserID");
        Log.d("UserID", "" + userID);
        //SqlHelper db = new SqlHelper(getActivity());

        // get all books
        //List<Event> list = db.getAllEvents(userID);

        // get all books
        //db.getAllEvents(userID);

        //Initial loding of List view with data from database
        listContent = (ListView) InputFragmentView.findViewById(R.id.list);
        events = new ArrayList<Event>();
        events = db.getAllEvents(userID);

        //get data from the table by the ListAdapter
        customAdapter = new ListAdapter(this.getActivity(), R.layout.itemlistrow, events);
        listContent.setAdapter(customAdapter);

        //Swipe refresh logic
        swipeContainer = (SwipeRefreshLayout) InputFragmentView.findViewById(R.id.swipeContainer);
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
        return InputFragmentView;
    }


}