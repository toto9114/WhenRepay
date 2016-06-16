package com.whenrepay.rnd.whenrepay;

import io.realm.RealmObject;

/**
 * Created by RND on 2016-06-16.
 */
public class MyProfile extends RealmObject {
    private byte[] signature;

    public void setSignature(byte[] byteArray){
        signature = byteArray;
    }
    public byte[] getSignature(){
        return signature;
    }
}
