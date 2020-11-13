package com.example.parking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ParkingApi {
    @GET("mobiliteit/bezettingparkingsrealtime.json")
    Call<List<Parking>> loadParkingList();
}
