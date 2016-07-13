package com.whenrepay.rnd.whenrepay.Main;

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

public class EditNameActivity extends AppCompatActivity {

    public static final String EXTRA_TYPE = "type";
    public static final String TYPE_NAME = "name";
    public static final String TYPE_ACCOUNT = "account";

    TextView titleVIew;
    EditText nameView;

    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRealm = Realm.getInstance(this);
        MyProfile profile = mRealm.where(MyProfile.class).findFirst();

        titleVIew = (TextView) findViewById(R.id.text_message);
        nameView = (EditText) findViewById(R.id.edit_info);

        titleVIew.setText("본인 이름을 입력해주세요.");
        nameView.setText(profile.getName());


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
            if (!TextUtils.isEmpty(nameView.getText().toString())) {
//                Intent i = new Intent();
//                String info = nameView.getText().toString();
//                i.putExtra(SettingActivity.RESULT_INFO, info);
//                setResult(RESULT_OK, i);
//                finish();

                mRealm.beginTransaction();
                mRealm.where(MyProfile.class).findFirst().setName(nameView.getText().toString());
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
