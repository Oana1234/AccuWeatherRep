package com.example.ileana.accuweather.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.RoomDatabase;

import com.example.ileana.accuweather.models.Location;

/**
 * Created by Oana-Maria on 22/03/2018.
 */

@Database(version= 1, entities = {Location.class})
public abstract  class FavoriteLocationDatabase extends RoomDatabase {

    public  static  final String NAME = "favourite_locations";
    public  abstract LocationDao  locationDao();

}
