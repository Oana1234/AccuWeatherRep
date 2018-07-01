package com.example.ileana.accuweather;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.ileana.accuweather.api.ApiResponseCallback;
import com.example.ileana.accuweather.api.WeatherApi;
import com.example.ileana.accuweather.notifications.BootCompletedBroadcastReceiver;
import com.example.ileana.accuweather.notifications.WeatherNotificationsService;
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

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private MenuFragment menuFragment;
    private android.support.v7.app.ActionBar actionBar;
    private Intent myIntent;
    private static final int LOCATION_REQUEST_CODE = 1;
    public static final int REQ_WEATHER_SERVICE = 145;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        drawerLayout = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        //   actionBar.setIcon(R.drawable.share);

        viewPager.setAdapter(new WeatherPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        // actionBar.setTitle("Bucharest");

        if (savedInstanceState == null) {
            menuFragment = new MenuFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_menu, menuFragment).commit();
        } else {
            menuFragment = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.container_menu);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        checkLocalPermission();
        scheduleWeatherNotifications();
        LocalStorage localStorage = LocalStorage.getInstance(getApplicationContext());
        localStorage.putString("KEY", "Salut");

        if (savedInstanceState == null) {
            new WeatherApi(this).testNetwork(new ApiResponseCallback<String>() {
                @Override
                public void onSuccess(String result) {

                }

                @Override
                public void onError() {

                }
            });

        }
    }

    private void scheduleWeatherNotifications() {
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        Intent weatherNotificationsIntent = new Intent(this,WeatherNotificationsService.class);
        PendingIntent pendingIntent
                = PendingIntent.getService(this,
                REQ_WEATHER_SERVICE,
                weatherNotificationsIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
        long triggerTime = TimeUnit.MINUTES.toMillis(2);
        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                5000,
                triggerTime,
                pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            case R.id.action_find_me:
                myIntent = new Intent(MainActivity.this, FindMeActivity.class);
                MainActivity.this.startActivity(myIntent);
                return true;

            case R.id.action_change_f:
                myIntent = new Intent(MainActivity.this, ChangeToFActivity.class);
                MainActivity.this.startActivity(myIntent);
                return true;

            case R.id.action_disable_ads:
                myIntent = new Intent(MainActivity.this, DisableAdsActivity.class);
                MainActivity.this.startActivity(myIntent);
                return true;

            case R.id.action_rate_app:
                myIntent = new Intent(MainActivity.this, RateAppActivity.class);
                MainActivity.this.startActivity(myIntent);
                return true;

            case R.id.action_share_videos:
                myIntent = new Intent(MainActivity.this, ShareVideosActivity.class);
                MainActivity.this.startActivity(myIntent);
                return true;

            case R.id.action_settings:
                myIntent = new Intent(MainActivity.this, SettingsActivity.class);
                MainActivity.this.startActivity(myIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE && grantResults.length == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {

            }
        }
    }

    private void checkLocalPermission() {

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        } else {
            getCurrentLocation();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        FusedLocationProviderClient fusedLocationProviderClient
                = new FusedLocationProviderClient(getApplicationContext());
        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(10);
        locationRequest.setNumUpdates(1);

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback() {

            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Location location = locationResult.getLastLocation();
                if (location != null) {
                    Toast.makeText(getApplicationContext(), "Location" + location.getLatitude()
                            + " " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                }
            }
        }, null);
    }
}
