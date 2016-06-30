package com.whenrepay.rnd.whenrepay.DutchPay;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RND on 2016-06-29.
 */
public class EventData implements Serializable{
    int money;
    List<DutchPersonData> people;
}
