package com.whenrepay.rnd.whenrepay.Contact;

import android.content.Context;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.Group.PersonData;
import com.whenrepay.rnd.whenrepay.R;

/**
 * Created by RND on 2016-07-14.
 */
public class SingleContactView extends FrameLayout {
    TextView nameView, phoneView;
    public SingleContactView(Context context) {
        super(context);
        inflate(getContext(), R.layout.view_single_contact, this);
        nameView = (TextView)findViewById(R.id.text_name);
        phoneView = (TextView)findViewById(R.id.text_phone);

    }
    PersonData data;
    public void setData(PersonData data){
        this.data = data;
        nameView.setText(data.getName());
        if(!TextUtils.isEmpty(data.getPhone())) {
            phoneView.setText(data.getPhone());
        }
    }
}
