package com.androidfp.prath.sked;

/**
 * Created by prath on 4/13/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Event> {

    private List<Event> items;

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }
    public ListAdapter(Context context, int resource, List<Event> items) {
        super(context, resource, items);

        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlistrow, null);
        }

        Event e = getItem(position);

        if (e != null) {

            ImageView icon=(ImageView)v.findViewById(R.id.imageView);
            TextView tt1 = (TextView) v.findViewById(R.id.textViewEventTitle);
            TextView tt3 = (TextView) v.findViewById(R.id.textViewDesc);

            /*if (p.getImgTitle().equals("profand4")){
                icon.setImageResource(R.drawable.book1);
            }*/
            icon.setImageResource(R.drawable.eventicon);

            if (tt1 != null) {
                tt1.setText(e.getEventName());
            }
            if (tt3 != null) {
                tt3.setText(e.getEventDesc());
            }
        }

        return v;
    }
}
