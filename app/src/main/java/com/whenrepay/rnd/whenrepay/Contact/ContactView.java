package com.whenrepay.rnd.whenrepay.Contact;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import com.whenrepay.rnd.whenrepay.R;

/**
 * Created by RND on 2016-07-04.
 */
public class ContactView extends FrameLayout {

    CheckBox nameView;
    public ContactView(Context context) {
        super(context);
        inflate(getContext(), R.layout.view_contact, this);
//        nameView = (CheckBox)findViewById(R.id.checkBox_name);
//        nameView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(itemCheckedListener != null){
//                    itemCheckedListener.OnItemChecked(buttonView, isChecked);
//                }
//            }
//        });
    }

    public void setData(String name){
        nameView.setText(name);
    }
}
