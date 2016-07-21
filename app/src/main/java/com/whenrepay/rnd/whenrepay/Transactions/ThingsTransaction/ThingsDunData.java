package com.whenrepay.rnd.whenrepay.Transactions.ThingsTransaction;

import io.realm.RealmObject;

/**
 * Created by RND on 2016-07-21.
 */
public class ThingsDunData extends RealmObject{
    private long _id;
    private long date;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
