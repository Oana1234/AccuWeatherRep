package com.example.ileana.accuweather.tabs.current;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ileana.accuweather.R;
import com.example.ileana.accuweather.models.WeatherCondition;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ILEANA on 2/22/2018.
 */

public class CurrentWeatherFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, CurrentWeatherAdapter.OnWeatherClickedListener {


    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerViewCurrentTime;
    private CurrentWeatherAdapter mWheatherListAdapter;
    public List<WeatherCondition> weatherConditionList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_current_time, container, false);
        bindViews(fragmentView);
        return fragmentView;
    }

    private void bindViews(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.refresh_layout_current_time);
        mRecyclerViewCurrentTime = view.findViewById(R.id.recycler_view_current_time);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mWheatherListAdapter = new CurrentWeatherAdapter(this);

        weatherConditionList = generateWeather();

        mWheatherListAdapter.setWeather(weatherConditionList);

        mRecyclerViewCurrentTime.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewCurrentTime.setAdapter(mWheatherListAdapter);

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.refresh_layout_current_time);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                weatherConditionList = generateWeather();
                mWheatherListAdapter.setWeather(weatherConditionList);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void updateCurrentWeather(WeatherCondition weatherCondition) {
        weatherConditionList = generateWeather();
        mWheatherListAdapter.setWeather(weatherConditionList);
    }


    @Override
    public void onRefresh() {


        if (generateWeather().size() > 0) {
            mWheatherListAdapter.setWeather(generateWeather());
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(false);

                }
            });
        } else {
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(true);

                }
            });
        }
        mWheatherListAdapter.notifyDataSetChanged();
    }

    private List<WeatherCondition> generateWeather() {
        weatherConditionList = new ArrayList<>();

        Date currentTime = Calendar.getInstance().getTime();

        for (int i = 0; i < 5; i++) {
            WeatherCondition weatherCondition = new WeatherCondition("Prague",currentTime,i,21232,12324);
            weatherConditionList.add(weatherCondition);
        }

        return weatherConditionList;
    }

    @Override
    public void onWeatherClicked(WeatherCondition weatherCondition) {
        if (weatherCondition != null) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Action info")
                    .setMessage("Clicked on weatherCondition with id " + weatherCondition.getTemp())
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }
}
