package com.androidfp.prath.sked;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

        listContent = (ListView) InputFragmentView.findViewById(R.id.list);
        events = new ArrayList<Event>();
        events = db.getAllEvents(userID);

        //get data from the table by the ListAdapter
        customAdapter = new ListAdapter(this.getActivity(), R.layout.itemlistrow, events);
        listContent.setAdapter(customAdapter);

        // Inflate the layout for this fragment
        return InputFragmentView;
    }

}