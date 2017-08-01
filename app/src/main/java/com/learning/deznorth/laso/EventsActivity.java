package com.learning.deznorth.laso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class EventsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        /**
         * Figure out a way to parse the JSON from the database and add the events
         * */

//        LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);
//
//        for(int index = 0;index<words.size(); index++){
//            TextView wordView = new TextView(this);
//            wordView.setText(words.get(index));
//            rootView.addView(wordView);
//        }

        ListView listView = (ListView) findViewById(R.id.list);

        //listView.setAdapter(/**ADD IN HERE*/);
    }


}
