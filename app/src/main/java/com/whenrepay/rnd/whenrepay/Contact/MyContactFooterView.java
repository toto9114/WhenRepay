package com.whenrepay.rnd.whenrepay.Contact;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.MyProfile;
import com.whenrepay.rnd.whenrepay.R;

import io.realm.Realm;

/**
 * Created by RND on 2016-07-13.
 */
public class MyContactFooterView extends FrameLayout {
    TextView nameView, bankAccountView, phoneView;
    Realm mRealm;
    public MyContactFooterView(Context context,AttributeSet attrs) {
        super(context,attrs);
        inflate(getContext(),R.layout.view_mycontact_footer,this);
        nameView = (TextView) findViewById(R.id.text_name);
        bankAccountView = (TextView) findViewById(R.id.text_bank_account);
        phoneView = (TextView) findViewById(R.id.text_phone);

        mRealm = Realm.getInstance(getContext());
        nameView.setText(mRealm.where(MyProfile.class).findFirst().getName());
        bankAccountView.setText(mRealm.where(MyProfile.class).findFirst().getBank() + "/"+mRealm.where(MyProfile.class).findFirst().getAccount());
    }
}


