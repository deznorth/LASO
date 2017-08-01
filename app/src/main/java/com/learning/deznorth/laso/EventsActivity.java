package com.learning.deznorth.laso;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EventsActivity extends Activity {


    private ListView theListView;
    String JSON_STRING = "";
    boolean mEmpty = false;
    String mInfo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        //ArrayList<Event> events = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        new eventsTask().execute();
    }

    private class eventsTask extends AsyncTask<Void, Void, Void> {

        String JSON_URL;

        @Override
        protected void onPreExecute() {
            JSON_URL = Constants.EVENTS_URL;
            Log.i("logging url", JSON_URL);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                //Set up the connection first
                URL url = new URL(JSON_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                //create the string builder
                StringBuilder stringBuilder = new StringBuilder();
                String info = "";

                while((JSON_STRING = bufferedReader.readLine())!=null){
                    //start building the string
                    stringBuilder.append(JSON_STRING);
                    Log.d("da string", JSON_STRING);
                    info = stringBuilder.toString().trim();
                }
                if(info.equals("[]")){
                    mEmpty = true;
                }
                mInfo = info;
                //parseJson(info);


                //close the connections
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            if(mEmpty){
                Toast.makeText(EventsActivity.this,"NO EVENTS TO SHOW",Toast.LENGTH_LONG).show();
            }
            parseJson(mInfo);
        }
    }

    //PARSE JSON WORKS ALREADY, FIXING GETTING THE STRING
    private void parseJson(String json){ //PARSE JSON WORKS ALREADY, FIXING GETTING THE STRING

        theListView = findViewById(R.id.list_view);
        EventAdapter eventAdapter = new EventAdapter(EventsActivity.this, R.layout.list_item);
        theListView.setAdapter(eventAdapter);
        int c = 0;

//        try {
//            JSONObject jsonObject = new JSONObject(json);
//
//            String date,location,title,description;
//
//            for(int i = 1; i<31; i++){
//                String j = ""+i;
//                JSONObject jo;
//
//
//                if(c>5){
//                        break;
//                }else {
//                        jo = jsonObject.getJSONObject(j);
//
//
//                        date = jo.getString("Date");
//                        location = jo.getString("Location");
//                        title = jo.getString("Title");
//                        description = jo.getString("Description");
//
//                        Event event = new Event(date, location, title, description);
//                        eventAdapter.add(event);
//                }
//
//            }
//        } catch (JSONException e) {
//            c++;
//            //e.printStackTrace();
//        }
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(json);
            for (int i = 1; i < 31; i++) {
                String j = "" + i;
                JSONObject jo;
                try {
                    String date, location, title, description;


                    if (c > 5) {
                        break;
                    } else {
                        jo = jsonObject.getJSONObject(j);


                        date = jo.getString("Date");
                        location = jo.getString("Location");
                        title = jo.getString("Title");
                        description = jo.getString("Description");

                        Event event = new Event(date, location, title, description);
                        eventAdapter.add(event);
                    }

                } catch(JSONException e) {
                    c++;
                    //e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
