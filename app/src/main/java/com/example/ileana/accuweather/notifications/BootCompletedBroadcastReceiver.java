package com.example.ileana.accuweather.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.concurrent.TimeUnit;

/**
 * Created by ILEANA on 5/3/2018.
 */

public class BootCompletedBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            Intent weatherNotificationsIntent = new Intent(context,WeatherNotificationsService.class);
            PendingIntent pendingIntent
                    = PendingIntent.getService(context,
                    1,
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
    }
}
