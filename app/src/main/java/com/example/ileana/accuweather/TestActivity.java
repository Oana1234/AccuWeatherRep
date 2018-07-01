package com.example.ileana.accuweather;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ileana.accuweather.api.WeatherApi;
import com.example.ileana.accuweather.ui.settings.ChangeToFActivity;
import com.example.ileana.accuweather.ui.settings.DisableAdsActivity;
import com.example.ileana.accuweather.ui.settings.FindMeActivity;
import com.example.ileana.accuweather.ui.settings.RateAppActivity;
import com.example.ileana.accuweather.ui.settings.SettingsActivity;
import com.example.ileana.accuweather.ui.settings.ShareVideosActivity;
import com.example.ileana.accuweather.managers.LocalStorage;
import com.example.ileana.accuweather.ui.menu.MenuFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;

public class TestActivity extends AppCompatActivity {

    private TextView tvValue;
    private Button btnIncrement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tvValue = findViewById(R.id.tv_value);
        btnIncrement = findViewById(R.id.btn_increment);

        TestViewModel viewModel= ViewModelProviders.of(this).get(TestViewModel.class);

        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                counter++;
                viewModel.increment();
                tvValue.setText("Value is " + viewModel.getCounter());
            }
        });
    }
}
