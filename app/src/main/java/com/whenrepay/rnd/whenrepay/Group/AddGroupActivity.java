package com.whenrepay.rnd.whenrepay.Group;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.whenrepay.rnd.whenrepay.BorrowMoney.SuccessFragment;
import com.whenrepay.rnd.whenrepay.R;

public class AddGroupActivity extends AppCompatActivity {



    GroupData groupData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("회비등록");

        groupData = new GroupData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out)
                    .add(R.id.container, new RegistGroupInfoFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
    public void changePayment(GroupData data){
//        groupData.personList = data.personList;
//        groupData.groupName = data.groupName;
        groupData.setPersonList(data.getPersonList());
        groupData.setGroupName(data.getGroupName());
        PaymentSettingFragment f = new PaymentSettingFragment();
        Bundle args = new Bundle();
        args.putSerializable(PaymentSettingFragment.EXTRA_GROUP_DATA, groupData);
        f.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out)
                .replace(R.id.container, f)
                .addToBackStack(null)
                .commit();
    }

    public void changeSend(GroupData data){
//        groupData.moneyPerPerson = data.moneyPerPerson;
//        groupData.paymentDate = data.paymentDate;
//        groupData.account = data.account;

        groupData.setMoneyPerPerson(data.getMoneyPerPerson());
        groupData.setPaymentDate(data.getPaymentDate());
        groupData.setAccount(data.getAccount());
        SendGroupFragment f = new SendGroupFragment();
        Bundle args = new Bundle();
        args.putSerializable(SendGroupFragment.EXTRA_GROUP_DATA,groupData);
        f.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out)
                .replace(R.id.container, f)
                .addToBackStack(null)
                .commit();
    }

    public void changeSuccess(){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out)
                .replace(R.id.container, new SuccessFragment())
                .addToBackStack(null)
                .commit();
    }


}
