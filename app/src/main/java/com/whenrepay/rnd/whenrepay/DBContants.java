package com.whenrepay.rnd.whenrepay;

import android.provider.BaseColumns;

/**
 * Created by dongja94 on 2016-02-16.
 */
public class DBContants {
    public interface AddressBook extends BaseColumns {
        public static final String TABLE_NAME = "addressBook";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_HOME = "home";
        public static final String COLUMN_OFFICE = "office";
        public static final String COLUMN_LAST_MESSAGE_ID = "lastMessageId";
    }
}
