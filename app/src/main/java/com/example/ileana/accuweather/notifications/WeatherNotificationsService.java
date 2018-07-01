package com.example.ileana.accuweather.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.example.ileana.accuweather.MainActivity;
import com.example.ileana.accuweather.R;
import com.example.ileana.accuweather.managers.LocationManager;
import com.example.ileana.accuweather.managers.WeatherService;
import com.example.ileana.accuweather.models.WeatherCondition;

/**
 * Created by ILEANA on 5/3/2018.
 */

public class WeatherNotificationsService extends Service {

    private WeatherService weatherService;
    private NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        weatherService = WeatherService.getInstance(this);
        loadCurrentWeather();
    }

    private void loadCurrentWeather() {
        WeatherCondition weatherCondition = WeatherService.getInstance(this).getCurrentWeather(44.4469943,26.0907671);

        checkNotificationChanel();

        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                1, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, "weather")
                .setContentTitle("Weather")
                .setContentText("Weather" + weatherCondition.getCity() + " " + weatherCondition.getTemp() + "C")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_menu)
                .build();
        notificationManager.notify(1, notification);
    }

    private void checkNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel
                    = new NotificationChannel(
                    "weather",
                    "weather",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
