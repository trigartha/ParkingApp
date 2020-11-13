package com.example.parking;

import com.google.gson.annotations.SerializedName;

public class ParkingStatus {
    @SerializedName("availableCapacity")
    int availableCapacity;
    @SerializedName("totalCapacity")
    int totalCapacity;

    public int getAvailableCapacity(){
        return availableCapacity;
    }
    public void setAvailableCapacity(Integer availableCapacity){
        this.availableCapacity = availableCapacity;
    }
    public int getTotalCapacity(){
        return totalCapacity;
    }
    public void setTotalCapacity (int totalCapacity){
        this.totalCapacity = totalCapacity;
    }
}
