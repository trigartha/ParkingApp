package com.example.parking;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ParkingViewHolder> {
    private List<Parking> parkingList;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public ParkingAdapter() {
    }

    public void adaptParkingAdapter(List<Parking> parkingList) {
        this.parkingList = parkingList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ParkingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_parking, parent, false);
        return new ParkingViewHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingViewHolder holder, int position) {
        Parking currentItem = parkingList.get(position);
        holder.setData(currentItem);
    }

    @Override
    public int getItemCount() {
        if(parkingList!=null)
        {
            return parkingList.size();
        }
        return 0;
    }

    public static class ParkingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_parking_name)
        TextView parkingName;
        @BindView(R.id.textview_available)
        TextView availability;

        public ParkingViewHolder(@NonNull View itemView, OnItemClickListener itemListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemListener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            itemListener.onItemClick(view, position);
                        }
                    }
                }
            });
        }

        public void setData(Parking currentItem) {
            parkingName.setText(currentItem.getParkingName());
            int available = currentItem.getParkingStatus().getAvailableCapacity();
            int totalAvailability = currentItem.getParkingStatus().getTotalCapacity();
            availability.setText(""+ available);
            availability.setBackgroundResource(currentItem.getBackgroundResource());
            /*if(available >totalAvailability/4)
            {
                availability.setBackgroundResource(R.drawable.rounded_textview_green);
            }
           else if(available<totalAvailability/20)
            {
                availability.setBackgroundResource(R.drawable.rounded_textview_red);
            }
           else
            {
                availability.setBackgroundResource(R.drawable.rounded_textview_orange);
            }*/
        }
    }
}
