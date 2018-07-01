package com.example.ileana.accuweather.managers;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.ileana.accuweather.api.ApiResponseCallback;
import com.example.ileana.accuweather.api.SearchLocation;
import com.example.ileana.accuweather.api.WeatherApi;
import com.example.ileana.accuweather.database.FavoriteLocationDatabase;
import com.example.ileana.accuweather.models.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oana-Maria on 20/03/2018.
 */

public class LocationManager {

    private List<Location> availableLocations;
    private Location activeLocation;
    Context context;
    private FavoriteLocationDatabase favoriteLocationDatabase;
    ArrayList<Listener> listeners = new ArrayList<>();
    WeatherApi weatherApi;

    public interface Listener {

        void onFavouriteLocationAdded(Location location);

        void onFavouriteLocationRemoved(Location location);

        void onFavouriteLocationChanged(boolean removed);

        void onActiveLocationChanged(Location location);

    }


    public List<Location> getAvailableLocations() {
        return availableLocations;
    }

    private static LocationManager locationManager = null;

    private LocationManager(Context context) {
        this.context = context;
        favoriteLocationDatabase = Room.databaseBuilder(context, FavoriteLocationDatabase.class, FavoriteLocationDatabase.NAME)
                .allowMainThreadQueries()
                .build();
        activeLocation = favoriteLocationDatabase.locationDao().getActiveLocation();
        notifyActiveLocationChanged(activeLocation);

        weatherApi = new WeatherApi(context);

    }

    private void notifyActiveLocationChanged(Location activeLocation) {
        for (Listener listener : listeners) {
            listener.onActiveLocationChanged(activeLocation);
        }
    }

    public static LocationManager getInstance(Context context) {
        if (locationManager == null) {
            locationManager = new LocationManager(context);
        }
        return locationManager;
    }

    List<Location> getFavoriteLocations() {
        return favoriteLocationDatabase.locationDao().getLocations();
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void deleteListener(Listener listener) {
        listeners.remove(listener);
    }

    public void addFavoriteLocation(Location location) {
        favoriteLocationDatabase.locationDao().insertLocation(location);

        for (Listener listener : listeners) {
            listener.onFavouriteLocationAdded(location);
        }
    }

    public void setActiveLocation(Location location) {

        activeLocation = location;
        for (Listener listener : listeners) {

            listener.onActiveLocationChanged(location);
        }
    }

    void removeFavoriteLocation(Location location) {

        favoriteLocationDatabase.locationDao().deleteLocaions(location);

        for (Listener listener : listeners) {

            listener.onFavouriteLocationRemoved(location);
        }

        if (location.isActive()) {

            List<Location> favoriteLocations = getFavoriteLocations();
            if (!favoriteLocations.isEmpty()) {
                setActiveLocation(favoriteLocations.get(0));
            } else {
                // TODO
            }

        }

    }

    public void searchLocation(String query, ApiResponseCallback<List<SearchLocation>> callback) {
        weatherApi.searchLocation(query, callback);
    }

}
