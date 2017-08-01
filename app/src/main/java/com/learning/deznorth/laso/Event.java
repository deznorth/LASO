package com.learning.deznorth.laso;

/**
 * Created by Deznorth on 7/31/2017.
 */

public class Event {
    private String mDate, mLocation, mTitle, mDescription;

    public Event(String date, String location, String title, String description){
        mDate = date;
        mLocation = location;
        mTitle = title;
        mDescription = description;
    }


    public String getDate(){return mDate;}

    public String getLocation(){return mLocation;}

    public String getTitle(){return mTitle;}

    public String getDescription(){return mDescription;}
}
