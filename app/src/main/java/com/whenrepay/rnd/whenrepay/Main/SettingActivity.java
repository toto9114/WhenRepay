package com.whenrepay.rnd.whenrepay.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
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

    public static final String RESULT_INFO = "info";
    private static final int REQUEST_NAME = 100;
    private static final int REQUEST_ACCOUNT = 200;
    private static final int REQUEST_BANK = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setTitle("프로필 수정");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out_background);

        nameView = (TextView) findViewById(R.id.text_name);
        accountView = (TextView) findViewById(R.id.text_account);
        bankView = (TextView) findViewById(R.id.text_bank);
        mRealm = Realm.getInstance(this);
        nameView.setText(mRealm.where(MyProfile.class).findFirst().getName());
        bankView.setText(mRealm.where(MyProfile.class).findFirst().getBank());
        accountView.setText(mRealm.where(MyProfile.class).findFirst().getAccount());

        nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, EditInfoActivity.class);
                intent.putExtra(EditInfoActivity.EXTRA_TYPE, EditInfoActivity.TYPE_NAME);
                startActivityForResult(intent, REQUEST_NAME);
            }
        });

        accountView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, EditInfoActivity.class);
                intent.putExtra(EditInfoActivity.EXTRA_TYPE, EditInfoActivity.TYPE_ACCOUNT);
                startActivityForResult(intent, REQUEST_ACCOUNT);
            }
        });

        bankView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BankListDialog dialog = new BankListDialog();
                dialog.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void OnItemClick(String bank) {
                        bankView.setText(bank);
                    }
                });
                dialog.show(SettingActivity.this.getSupportFragmentManager(), "dialog");
//                startActivityForResult(new Intent(SettingActivity.this,BankListActivity.class),REQUEST_BANK);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == REQUEST_NAME && resultCode != RESULT_CANCELED) {
                nameView.setText(data.getStringExtra(RESULT_INFO));
            } else if (requestCode == REQUEST_ACCOUNT && resultCode != RESULT_CANCELED) {
                accountView.setText(data.getStringExtra(RESULT_INFO));
            } else {
                bankView.setText(data.getStringExtra(RESULT_INFO));
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in_background,R.anim.slide_right_out);
    }
}
