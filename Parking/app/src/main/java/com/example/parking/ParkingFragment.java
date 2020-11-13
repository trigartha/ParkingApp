package com.example.parking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParkingFragment extends AppCompatActivity {
    @BindView(R.id.textview_parking_name)
    TextView parkingNameTextView;
    @BindView(R.id.textview_available)
    TextView availabilityTextView;
    @BindView(R.id.textview_name)
    TextView nameTextView;
    @BindView(R.id.textview_adres)
    TextView adresTextView;
    @BindView(R.id.textview_contact)
    TextView contactTextView;
    @BindView(R.id.textview_totale_cap)
    TextView totalCapTextView;

    private Parking parking;
    ParkingAdapter.OnItemClickListener onItemClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_parking);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("parking");
        parking = Parking.fromJson(message);
        adresTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geo = "geo:" + "" + Double.toString(parking.getLatitude())+","+ "" + Double.toString(parking.getLongitude())+"?z=" + "" + 20;
                //+"?q=" + parking.getDescription() ;
                Uri googleMapIntentUri = Uri.parse(geo);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, googleMapIntentUri);
                startActivity(mapIntent);
            }
        });
        setData(parking);
    }
    private void setData(Parking currentItem){
        parkingNameTextView.setText(currentItem.getParkingName().toUpperCase());
        availabilityTextView.setText(""+currentItem.getParkingStatus().getAvailableCapacity());
        availabilityTextView.setBackgroundResource(currentItem.getBackgroundResource());
        nameTextView.setText(currentItem.getDescription());
        adresTextView.setText(currentItem.getAddress());
        contactTextView.setText(currentItem.getContactInfo());
        totalCapTextView.setText("" + currentItem.getParkingStatus().getTotalCapacity());
    }
}
