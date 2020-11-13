package com.example.parking;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Parking {
    @SerializedName("name")
    String parkingName;
    @SerializedName("description")
    String description;
    @SerializedName("address")
    String address;
    @SerializedName("contactInfo")
    String contactInfo;
    @SerializedName("parkingStatus")
    ParkingStatus parkingStatus;
    @SerializedName("latitude")
    double latitude;
    @SerializedName("longitude")
    double longitude;

    public String getParkingName(){
        return parkingName;
    }
    public void setParkingName(String parkingName){
        this.parkingName = parkingName;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
                this.address = address;
    }
    public String getContactInfo(){
        return contactInfo;
    }
    public void setContactInfo(String contactInfo){
        this.contactInfo = contactInfo;
    }
    public ParkingStatus getParkingStatus(){
        return parkingStatus;
    }
    public void setParkingStatus(ParkingStatus parkingStatus){ this.parkingStatus = parkingStatus;}
    public double getLatitude(){
        return latitude;
    }
    public void setLatitude(double latitude){ this.latitude = latitude;}
    public double getLongitude(){ return longitude; }
    public void setLongitude(double longitude){ this.longitude = longitude;}

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Parking fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Parking.class);
    }
    public int getBackgroundResource()
    {
        int resource = 0;
        int available = this.getParkingStatus().getAvailableCapacity();
        int totalAvailability = this.getParkingStatus().getTotalCapacity();
        if(available >totalAvailability/4)
        {
           resource = R.drawable.rounded_textview_green;
        }
        else if(available<totalAvailability/20)
        {
            resource = R.drawable.rounded_textview_red;
        }
        else
        {
            resource = R.drawable.rounded_textview_orange;
        }
        return resource;
    }
}
