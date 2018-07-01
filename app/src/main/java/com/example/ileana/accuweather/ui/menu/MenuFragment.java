package com.example.ileana.accuweather.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ileana.accuweather.R;
import com.example.ileana.accuweather.database.FavoriteLocationDatabase;
import com.example.ileana.accuweather.managers.LocationManager;
import com.example.ileana.accuweather.models.Location;
import com.example.ileana.accuweather.ui.search.SearchLocationActivity;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ILEANA on 3/1/2018.
 */

public class MenuFragment extends Fragment implements FavouriteLocationsAdapter.OnLocationClickedListener, LocationManager.Listener {


    private RecyclerView recyclerViewLocation;
    private FavouriteLocationsAdapter locationsListAdapter;
    private ImageButton imageButton;
    public TextView textActiveCity;
    public TextView textCity;
    public TextView textCountry;
    public TextView textTemperature;
    public static List<Location> locations = new ArrayList<>();
    public Location location;
    public String country;
    public LocationManager locationManager;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), SearchLocationActivity.class);
                startActivity(myIntent);
            }
        });
        locationManager = LocationManager.getInstance(getContext());
        locationManager.addListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_menu, container, false);
        bindViews(fragmentView);

        textActiveCity = fragmentView.findViewById(R.id.city);
        textCity = fragmentView.findViewById(R.id.tv_location_city);
        textCountry = fragmentView.findViewById(R.id.tv_location_country);
        textTemperature = fragmentView.findViewById(R.id.tv_location_temperature);


        country = getActivity().getIntent().getStringExtra("Country");
        locationsListAdapter = new FavouriteLocationsAdapter(this);
        locationsListAdapter.setLocation(locations);
        recyclerViewLocation.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewLocation.setAdapter(locationsListAdapter);
        imageButton = fragmentView.findViewById(R.id.map_add_button);
        return fragmentView;

    }

    private void bindViews(View view) {

        recyclerViewLocation = view.findViewById(R.id.recycler_view_menu);
    }


    @Override
    public void onLocationClicked(Location location) {
        updateData(location);

    }

    private void updateData(Location location) {

    }



    @Override
    public void onFavouriteLocationAdded(Location location) {
        Log.d("Menu Fragment", "SUCCES");
        textActiveCity.setText(location.getName());
        textCity.setText(location.getName());
        textCountry.setText(location.getCountry());
//        textTemperature.setText(location.get);
        locations.add(location);
        locationsListAdapter.setLocation(locations);
        recyclerViewLocation.setAdapter(locationsListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        locationManager.deleteListener(this);
    }

    @Override
    public void onFavouriteLocationRemoved(Location location) {

    }

    @Override
    public void onFavouriteLocationChanged(boolean removed) {

    }

    @Override
    public void onActiveLocationChanged(Location location) {


    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
