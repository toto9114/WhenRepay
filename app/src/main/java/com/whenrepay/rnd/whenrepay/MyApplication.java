package com.whenrepay.rnd.whenrepay;

import android.app.Application;
import android.content.Context;

/**
 * Created by RND on 2016-04-06.
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext(){
        return context;
    }
}
