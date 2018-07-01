package com.example.ileana.accuweather.ui.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ileana.accuweather.MainActivity;
import com.example.ileana.accuweather.R;
import com.example.ileana.accuweather.api.ApiResponseCallback;
import com.example.ileana.accuweather.api.SearchLocation;
import com.example.ileana.accuweather.managers.LocationManager;
import com.example.ileana.accuweather.models.Location;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ILEANA on 3/15/2018.
 */

public class SearchLocationActivity extends AppCompatActivity implements SearchLocationAdapter.Listener, SearchActivityContext {

    private LocationManager locationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);

        locationManager = LocationManager.getInstance(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setupActionBar(toolbar);

        final SearchLocationAdapter adapter = new SearchLocationAdapter();
        adapter.setListener(this);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);

        EditText editText = findViewById(R.id.et_location);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /*List<LocationWeather> locations = locationService.getLocations(charSequence.toString());
                adapter.setSearchWeatherLocations(locations);*/

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String query = editable.toString();
                if (query.length() >= 3) {
                    locationManager.searchLocation(query, new ApiResponseCallback<List<SearchLocation>>() {
                        @Override
                        public void onSuccess(final List<SearchLocation> result) {
                            Handler handler = new Handler(getMainLooper());
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.setLocations(result);

                                }
                            });
                        }

                        @Override
                        public void onError() {
                            Toast.makeText(SearchLocationActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public void onLocationSelected(SearchLocation searchLocation) {
        Location location = new Location(
                searchLocation.getId(),
                searchLocation.getName(),
//                searchLocation.getCountry(),
                true,
                searchLocation.getCoord().getLatitude(),
                searchLocation.getCoord().getLongitude()
        );
        location.setActive(true);
        locationManager.addFavoriteLocation(location);
        locationManager.setActiveLocation(location);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
