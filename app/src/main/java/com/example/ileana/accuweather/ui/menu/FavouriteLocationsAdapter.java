package com.example.ileana.accuweather.ui.menu;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ileana.accuweather.R;
import com.example.ileana.accuweather.models.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oana-Maria on 08/03/2018.
 */

public class FavouriteLocationsAdapter extends RecyclerView.Adapter<FavouriteLocationsAdapter.LocationsListViewHolder> {


    private final List<Location> locationsList = new ArrayList<>();
    public FavouriteLocationsAdapter.OnLocationClickedListener onLocationClickedListener;


    public  interface Listener{

        void onFavouriteLocationSelected(Location location);
        void onFavouriteLocationLongPressed();
    }


    private Listener listener;



    public void setLocation(List<Location> location) {
        this.locationsList.clear();
        this.locationsList.addAll(location);
        notifyDataSetChanged();

    }


    public FavouriteLocationsAdapter(FavouriteLocationsAdapter.OnLocationClickedListener onLocationClickedListener) {
        this.onLocationClickedListener = onLocationClickedListener;
    }

    @Override
    public LocationsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_view_drawer, parent, false);
        return new FavouriteLocationsAdapter.LocationsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationsListViewHolder holder, int position) {

        holder.bind(locationsList.get(position));
    }

    @Override
    public int getItemCount() {
        return locationsList.size();
    }

    public interface OnLocationClickedListener {
        void onLocationClicked(Location location);
    }

    class LocationsListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        Location location;
        CardView locCardView;
        TextView locCity;
        TextView locCountry;


        LocationsListViewHolder(View itemView) {
            super(itemView);

            locCardView = itemView.findViewById(R.id.card_view_drawer);
            locCity = itemView.findViewById(R.id.city);
            locCountry = itemView.findViewById(R.id.country);
            itemView.setOnClickListener(this);
        }

        void bind(Location location) {

            this.location = location;
            locCountry.setText(String.valueOf(location.getLongitude()));
            locCity.setText(String.valueOf(location.getLatitude()));
        }

        @Override
        public void onClick(View v) {
            if (onLocationClickedListener != null) {
                onLocationClickedListener.onLocationClicked(location);

            }
        }


        @Override
        public boolean onLongClick(View v) {
            return true;
        }
    }
}
