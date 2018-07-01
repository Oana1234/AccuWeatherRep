package com.example.ileana.accuweather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ileana.accuweather.tabs.current.CurrentWeatherFragment;
import com.example.ileana.accuweather.tabs.forecast.DailyFragment;
import com.example.ileana.accuweather.tabs.forecast.HourlyFragment;
import com.example.ileana.accuweather.tabs.MapsFragment;
import com.example.ileana.accuweather.tabs.NewsFragment;

/**
 * Created by ILEANA on 2/22/2018.
 */

public class WeatherPagerAdapter extends FragmentPagerAdapter {

    public WeatherPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new CurrentWeatherFragment();
            case 1: return new HourlyFragment();
            case 2: return new DailyFragment();
            case 3: return new MapsFragment();
            case 4: return new NewsFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "NOW";
            case 1: return "HOULRY";
            case 2: return "DAILY";
            case 3: return "HMAPS";
            case 4: return "NEWS";
            default: return "";
        }
    }
}
