package com.whenrepay.rnd.whenrepay;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by RND on 2016-04-06.
 */
public class PropertyManager {
    private static PropertyManager instance;
    public static PropertyManager getInstance(){
        if(instance == null){
            instance = new PropertyManager();
        }
        return instance;
    }

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    private PropertyManager(){
        mPrefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        mEditor = mPrefs.edit();
    }

}
