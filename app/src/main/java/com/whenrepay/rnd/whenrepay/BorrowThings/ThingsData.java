package com.whenrepay.rnd.whenrepay.BorrowThings;

import com.whenrepay.rnd.whenrepay.TransactionData;

import java.io.Serializable;

/**
 * Created by RND on 2016-06-22.
 */
public class ThingsData implements Serializable, TransactionData {
    public static final long INVALID_ID = -1;
    public long _id = INVALID_ID;
    public String borrowerName;
    public String thingsName;
    public String memo;
    public String date;
    public String pictureUri;

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public int getPrice() {
        return 0;
    }
}