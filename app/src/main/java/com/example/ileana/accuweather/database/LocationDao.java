package com.example.ileana.accuweather.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.ileana.accuweather.models.Location;

import java.util.List;

/**
 * Created by Oana-Maria on 22/03/2018.
 */

@Dao
public interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLocation(Location location);

    @Update
    void updateLocation(Location...location);

    @Delete
    void deleteLocaions(Location...location);

    @Query("select * from locations")
    List<Location> getLocations();

    @Query("select * from locations where active=1")
    Location  getActiveLocation();
}
