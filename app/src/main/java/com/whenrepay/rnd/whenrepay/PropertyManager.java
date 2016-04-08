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

    private static final String REG_LOG = "log";

    public void setLog(boolean isLog){
        mEditor.putBoolean(REG_LOG,true);
        mEditor.commit();
    }
    public boolean getLog(){
        return mPrefs.getBoolean(REG_LOG,false);
    }

    private static final String REG_NAME = "name";
    public void setName(String name){
        mEditor.putString(REG_NAME,name);
        mEditor.commit();
    }
    public String getName(){
        return mPrefs.getString(REG_NAME,"");
    }

    private static final String REG_PHONE = "phone";
    public void setPhone(String phone){
        mEditor.putString(REG_PHONE,phone);
        mEditor.commit();
    }
    public String getPhone(){
        return mPrefs.getString(REG_PHONE,"");
    }
}
