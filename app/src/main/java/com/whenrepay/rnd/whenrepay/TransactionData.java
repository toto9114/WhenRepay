package com.whenrepay.rnd.whenrepay;

/**
 * Created by RND on 2016-06-23.
 */
public interface TransactionData {
    public long getId();
    public String getName();
    public long getDate();
    public int getPrice();
    public String getRepayDate();
}
