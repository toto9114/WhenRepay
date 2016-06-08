package com.whenrepay.rnd.whenrepay;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.kakao.auth.KakaoSDK;
import com.kakao.util.helper.log.Logger;

/**
 * Created by RND on 2016-04-06.
 */
public class MyApplication extends Application {
    private static Context context;
    private static volatile Activity currentActivity = null;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        KakaoSDK.init(new KakaoSDKAdapter());
    }
    public static Activity getCurrentActivity() {
        Logger.d("++ currentActivity : " + (currentActivity != null ? currentActivity.getClass().getSimpleName() : ""));
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        MyApplication.currentActivity = currentActivity;
    }

    /**
     * 애플리케이션 종료시 singleton 어플리케이션 객체 초기화한다.
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        context = null;
    }
    public static Context getContext(){
        return context;
    }
}
