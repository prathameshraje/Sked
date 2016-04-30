package com.androidfp.prath.sked;

/**
 * Created by prath on 4/16/2016.
 */
public class Event {
    String eventID, eventName, eventDesc, eventDayOne, eventDayTwo, eventDayThree,Address;
    int eventTimeFrame;
    Double lat;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    Double lng;

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventDayOne() {
        return eventDayOne;
    }

    public void setEventDayOne(String eventDayOne) {
        this.eventDayOne = eventDayOne;
    }

    public String getEventDayTwo() {
        return eventDayTwo;
    }

    public void setEventDayTwo(String eventDayTwo) {
        this.eventDayTwo = eventDayTwo;
    }

    public String getEventDayThree() {
        return eventDayThree;
    }

    public void setEventDayThree(String eventDayThree) {
        this.eventDayThree = eventDayThree;
    }

    public int getEventTimeFrame() {
        return eventTimeFrame;
    }

    public void setEventTimeFrame(int eventTimeFrame) {
        this.eventTimeFrame = eventTimeFrame;
    }
}
