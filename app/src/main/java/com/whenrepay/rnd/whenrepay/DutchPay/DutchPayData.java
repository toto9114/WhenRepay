package com.whenrepay.rnd.whenrepay.DutchPay;

import com.whenrepay.rnd.whenrepay.Group.PersonData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RND on 2016-06-24.
 */
public class DutchPayData implements Serializable{
    public static final long INVALID_ID = -1;
    public long _id = INVALID_ID;
    int totalPrice;
    String date;
    List<PersonData> personList;
    List<EventData> eventList;
}
