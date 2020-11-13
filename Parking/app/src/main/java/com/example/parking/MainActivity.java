package com.example.parking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ParkingAdapter adapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

    private static final String  LOG_TAG =  "Trigi_" + MainActivity.class.getSimpleName();

    private static final String BASE_URL = "https://datatank.stad.gent/4/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);
        adapter = new ParkingAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        build();
    }
    @Override
    protected void onRestart() {

        super.onRestart();
        build();
    }
    private void build()
    {
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ParkingApi apiService = retrofit.create(ParkingApi.class);
        Call<List<Parking>> call = apiService.loadParkingList();
        Callback<List<Parking>> responseCall;
        call.enqueue(responseCall = new Callback<List<Parking>>() {
            @Override
            public void onResponse(Call<List<Parking>> call, Response<List<Parking>> response) {
                if (response.isSuccessful()) {

                    adapter.adaptParkingAdapter(response.body());
                    adapter.setOnItemClickListener(new ParkingAdapter.OnItemClickListener(){
                        @Override
                        public void onItemClick(View view, int position) {
                            String toJson = response.body().get(position).toJson();
                            Intent intent = new Intent(MainActivity.this, ParkingFragment.class);
                            intent.putExtra("parking", toJson);
                            startActivity(intent);
                        }
                    });

                }
            }
            @Override
            public void onFailure(Call<List<Parking>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
