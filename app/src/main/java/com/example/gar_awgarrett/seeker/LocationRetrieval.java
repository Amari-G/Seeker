package com.example.gar_awgarrett.seeker;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Property;

/**
 * Created by Amy on 2/3/2018.
 */

//base class to hold ArrayList of Location info
public class LocationRetrieval implements Parcelable {

    String name;
    Double latitude;
    Double longitude;
    Boolean collected;
    String tags;

    //constructor
    public LocationRetrieval(String name, Double latitude, Double longitude, Boolean collected, String tags) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.collected = collected;
        this.name = name;
        this.tags = tags;
    }

    //getters
    public String getName(){
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }
    public Double getLongitude() {

        return longitude;
    }
    public Boolean isCollected() {
        return collected;
    }
    public String getTags() {
        return tags;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setCollected(Boolean collected) {
        this.collected = collected;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    /*public void setIntent(Intent intent) {
        this.intent = intent;
    }
    */

    //write object values to parcel for storage
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(name);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeValue(collected);
        dest.writeString(tags);
    }

    //constructor used for parcel
    protected LocationRetrieval(Parcel parcel){
        name = parcel.readString();
        latitude = parcel.readDouble();
        longitude = parcel.readDouble();
        collected = (Boolean) parcel.readValue(null);
        tags = parcel.readString();
    }

    //creator - used when un-parceling our parcel (creating the object)
    public static final Parcelable.Creator<LocationRetrieval> CREATOR = new Parcelable.Creator<LocationRetrieval>(){

        @Override
        public LocationRetrieval createFromParcel(Parcel parcel) {
            return new LocationRetrieval(parcel);
        }

        @Override
        public LocationRetrieval[] newArray(int size) {
            return new LocationRetrieval[size];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }


    /*Intent intent = new Intent(LocationRetrieval.this, MapPage.class);
    intent.putParcelableExtra("Location", location);
    startActivity(intent);
    */

}
