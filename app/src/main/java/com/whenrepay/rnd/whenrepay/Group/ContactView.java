package com.whenrepay.rnd.whenrepay.Group;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import com.whenrepay.rnd.whenrepay.R;

/**
 * Created by RND on 2016-07-04.
 */
public class ContactView extends FrameLayout {

    OnItemCheckedListener itemCheckedListener;
    public void setOnItemCheckedListener(OnItemCheckedListener listener){
        itemCheckedListener = listener;
    }

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

    PersonData data;
    public void setData(PersonData data){
        this.data = data;
        nameView.setText(data.name);
    }
}
