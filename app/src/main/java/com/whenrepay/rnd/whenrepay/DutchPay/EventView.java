package com.whenrepay.rnd.whenrepay.DutchPay;

import android.content.Context;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.whenrepay.rnd.whenrepay.Group.PersonData;
import com.whenrepay.rnd.whenrepay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RND on 2016-06-30.
 */
public class EventView extends FrameLayout {
    EditText editMoney;
    LinearLayout group;
    List<DutchPersonData> personList = new ArrayList<>();


    public EventView(Context context, List<PersonData> data) {
        super(context);
        inflate(getContext(), R.layout.view_edit_event, this);
        editMoney = (EditText) findViewById(R.id.edit_money);
        group = (LinearLayout) findViewById(R.id.check_group);

        for (int i = 0; i < data.size(); i++) {
            final CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(data.get(i).name);
            checkBox.setId(i);
            checkBox.setChecked(true);
            DutchPersonData person = new DutchPersonData();
            person.name = data.get(i).name;
            person.attended = true;

            personList.add(person);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        DutchPersonData data = personList.get(buttonView.getId());
                        data.attended = true;
                        personList.set(buttonView.getId(), data);
                    } else {
                        DutchPersonData data = personList.get(buttonView.getId());
                        data.attended = false;
                        personList.set(buttonView.getId(), data);
                    }
                }
            });
            group.addView(checkBox);
        }
    }

    public EventData getData() {
        EventData data = new EventData();
        if (!TextUtils.isEmpty(editMoney.getText().toString())) {
            data.money = Integer.parseInt(editMoney.getText().toString());
        } else {
            data.money = 0;
        }
        data.people = personList;
        return data;
    }
}
