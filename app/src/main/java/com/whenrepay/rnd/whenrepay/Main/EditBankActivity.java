package com.whenrepay.rnd.whenrepay.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.Intro.OnItemClickListener;
import com.whenrepay.rnd.whenrepay.MyProfile;
import com.whenrepay.rnd.whenrepay.R;

import io.realm.Realm;

public class EditBankActivity extends AppCompatActivity {

    TextView bankView;
    EditText accountView;
    Realm mRealm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bank);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bankView = (TextView)findViewById(R.id.text_bank);
        accountView = (EditText)findViewById(R.id.edit_account);

        initData();
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
                dialog.show(EditBankActivity.this.getSupportFragmentManager(), "dialog");
            }
        });
    }
    private void initData(){
        mRealm = Realm.getInstance(this);

        bankView.setText(mRealm.where(MyProfile.class).findFirst().getBank());
        accountView.setText(mRealm.where(MyProfile.class).findFirst().getAccount());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ok, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.done) {
            if (!TextUtils.isEmpty(accountView.getText().toString()) && !TextUtils.isEmpty(bankView.getText().toString())) {
//                    Intent i = new Intent();
//                    String account = accountView.getText().toString();
//                    i.putExtra(SettingActivity.RESULT_INFO, account);
//                    setResult(RESULT_OK, i);
//                    finish();
                mRealm.beginTransaction();
                mRealm.where(MyProfile.class).findFirst().setBank(bankView.getText().toString());
                mRealm.where(MyProfile.class).findFirst().setAccount(accountView.getText().toString());
                mRealm.commitTransaction();
                finish();
            }
        }
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out_background);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in_background, R.anim.slide_right_out);
    }
}
