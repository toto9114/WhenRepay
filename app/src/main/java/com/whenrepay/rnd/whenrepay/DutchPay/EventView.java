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
import java.util.Random;

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
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                final CheckBox checkBox = new CheckBox(getContext());
                checkBox.setText(data.get(i).getName());
                checkBox.setId(i);
                checkBox.setChecked(true);
                DutchPersonData person = new DutchPersonData();
                person.name = data.get(i).getName();
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
    }

    public EventData getData() {
        EventData data = new EventData();
        int count = 0;
        for (DutchPersonData personData : personList) {
            if (personData.attended) {
                count++;
            }
        }
        List<Integer> index = new ArrayList<>();
        int money =0;
        int lessMoney = 0 ;
        if (!TextUtils.isEmpty(editMoney.getText().toString())) {
            data.money = Integer.parseInt(editMoney.getText().toString());
            money = data.money/count;
            if(money %100 !=0){
                money = (money - (money%100))+100;
            }
            for (int i = 0; i < personList.size(); i++) {
                if (personList.get(i).attended) {
                    index.add(i);
                    personList.get(i).dutchMoney = money;
                } else {
                    personList.get(i).dutchMoney = 0;
                }
            }
            lessMoney = data.money - ((index.size()-1)*money);
            if(data.money/count %100 !=0) {
                Random r = new Random();
                personList.get(r.nextInt(index.size() - 1)).dutchMoney = lessMoney;
            }
        } else {
            data.money = 0;
        }
        data.people = personList;
        return data;
    }
}
