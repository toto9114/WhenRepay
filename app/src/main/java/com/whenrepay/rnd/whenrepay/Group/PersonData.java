package com.whenrepay.rnd.whenrepay.Group;

import java.io.Serializable;

/**
 * Created by RND on 2016-06-23.
 */
public class PersonData implements Serializable{
    public String name;
    public String phone;

    public boolean isPay;
    @Override
    public String toString() {
        return name;
    }
}
