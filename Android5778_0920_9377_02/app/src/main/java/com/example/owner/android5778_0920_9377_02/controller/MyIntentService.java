package com.example.owner.android5778_0920_9377_02.controller;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import com.example.owner.android5778_0920_9377_02.R;
import com.example.owner.android5778_0920_9377_02.model.backend.DB_ManagerFactory;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Notification.Builder nBuilder = new Notification.Builder(getBaseContext());
        nBuilder.setContentTitle("Foreground Service");
        nBuilder.setContentText("Service Is Running");
        nBuilder.setSmallIcon(R.drawable.car_black_24dp);
        Notification notification=nBuilder.build();
        startForeground(1234,notification);
    }

    /**
     * send broadcast for each 10 seconds
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        while (true) {
            try {
                Thread.sleep(10000);
                DB_ManagerFactory.getManager().getAllAvailableCars();
                sendBroadcast(new Intent(this, MyReceiver.class));
            } catch (Exception e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
}
