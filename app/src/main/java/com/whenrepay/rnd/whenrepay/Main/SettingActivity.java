package com.whenrepay.rnd.whenrepay.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.Intro.OnItemClickListener;
import com.whenrepay.rnd.whenrepay.MyProfile;
import com.whenrepay.rnd.whenrepay.R;

import io.realm.Realm;

public class SettingActivity extends AppCompatActivity {

    TextView nameView, accountView;
    TextView bankView;
    Realm mRealm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        nameView = (TextView)findViewById(R.id.text_name);
        accountView = (TextView)findViewById(R.id.text_account);
        bankView = (TextView)findViewById(R.id.text_bank);
        mRealm = Realm.getInstance(this);
        nameView.setText(mRealm.where(MyProfile.class).findFirst().getName());
        bankView.setText(mRealm.where(MyProfile.class).findFirst().getBank());
        accountView.setText(mRealm.where(MyProfile.class).findFirst().getAccount());

        bankView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankListDialog dialog= new BankListDialog();
                dialog.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void OnItemClick(String bank) {
                        bankView.setText(bank);
                    }
                });
                dialog.show(SettingActivity.this.getSupportFragmentManager(),"dialog");
            }
        });

    }
}
