package com.learning.deznorth.laso;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deznorth on 7/31/2017.
 */

public class EventAdapter extends ArrayAdapter {

    private List theList = new ArrayList();


    public EventAdapter( Context context, int resource) {
        super(context, resource);
    }


    public void add(Event object) {
        //super.add(object);
        theList.add(object);
    }

    @Override
    public int getCount() {
        return theList.size();
    }

    @Override
    public Object getItem(int position) {
        return theList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View row;
        row = convertView;
        EventHolder eventHolder;

        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.list_item, parent, false);
            eventHolder = new EventHolder();
            eventHolder.tx_date = row.findViewById(R.id.dateText);
            eventHolder.tx_location = row.findViewById(R.id.locationText);
            eventHolder.tx_title = row.findViewById(R.id.titleText);
            eventHolder.tx_description = row.findViewById(R.id.descriptionText);

            row.setTag(eventHolder);

        } else {
            eventHolder = (EventHolder) row.getTag();
        }

        Event event = (Event) this.getItem(position);
        eventHolder.tx_date.setText(event.getDate());
        eventHolder.tx_location.setText(event.getLocation());
        eventHolder.tx_title.setText(event.getTitle());
        eventHolder.tx_description.setText(event.getDescription());

        return row;
    }

    private static class EventHolder{

        TextView tx_date, tx_location, tx_title, tx_description;

    }
}
