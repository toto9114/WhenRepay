package com.whenrepay.rnd.whenrepay;

import android.app.AlarmManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;

public class MyService extends Service {
    AlarmManager mAM;
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAM = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static final Uri ALARM_URI = Uri.parse("myscheme://com.whenrepay.rnd.whenrepay/alarm");

    private void updateAlarmData(){

    }

}