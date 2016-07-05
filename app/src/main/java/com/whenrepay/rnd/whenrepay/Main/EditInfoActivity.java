package com.whenrepay.rnd.whenrepay.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.MyProfile;
import com.whenrepay.rnd.whenrepay.R;

import io.realm.Realm;

public class EditInfoActivity extends AppCompatActivity {

    public static final String EXTRA_TYPE = "type";
    public static final String TYPE_NAME = "name";
    public static final String TYPE_ACCOUNT = "account";

    TextView titleVIew;
    EditText editView;

    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRealm = Realm.getInstance(this);
        MyProfile profile = mRealm.where(MyProfile.class).findFirst();

        titleVIew = (TextView) findViewById(R.id.text_message);
        editView = (EditText) findViewById(R.id.edit_info);

        if (getIntent().getStringExtra(EXTRA_TYPE).equals(TYPE_NAME)) {
            titleVIew.setText("본인 이름을 입력해주세요.");
            editView.setText(profile.getName());
        } else {
            titleVIew.setText("본인 계좌번호를 입력해주세요");
            editView.setText(profile.getAccount());
        }


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
            if (!TextUtils.isEmpty(editView.getText().toString())) {
                Intent i = new Intent();
                String info = editView.getText().toString();
                i.putExtra(SettingActivity.RESULT_INFO, info);
                setResult(RESULT_OK, i);
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
