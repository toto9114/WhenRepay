package com.whenrepay.rnd.whenrepay.Transactions.ThingsTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.whenrepay.rnd.whenrepay.BorrowThings.ThingsData;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailThingsTransactionActivity extends AppCompatActivity {

    private static final String TYPE_NOTIFY = "notify";
    private static final String TYPE_PICTURE = "picture";

    public static final String EXTRA_THINGS_DATA = "things";
    ThingsData thingsData;

    ImageView pictureVIew;
    ViewSwitcher returnSwitcher;

    TextView thingsNameView, categoryView, dateView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_things_transaction);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out_background);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        thingsData = (ThingsData) i.getSerializableExtra(EXTRA_THINGS_DATA);
        pictureVIew = (ImageView) findViewById(R.id.image_picture);
        returnSwitcher = (ViewSwitcher) findViewById(R.id.switcher_return);
        thingsNameView = (TextView) findViewById(R.id.text_things_name);
        categoryView = (TextView) findViewById(R.id.text_category);
        dateView = (TextView) findViewById(R.id.text_date);

        pictureVIew.setImageBitmap(byteArrayToBitmap(thingsData.picture));

        if (thingsData.isCompleted) {
            returnSwitcher.showNext();
        }
        Button btn = (Button) findViewById(R.id.btn_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn = (Button) findViewById(R.id.btn_return);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thingsData.isCompleted = true;
                DataManager.getInstance().updateContractThings(thingsData);
                returnSwitcher.showNext();
                if (getSupportFragmentManager().findFragmentByTag(TYPE_NOTIFY).isVisible()) {
                    changeNotify();
                }
            }
        });
        btn = (Button) findViewById(R.id.btn_notify);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNotify();
            }
        });
        btn = (Button) findViewById(R.id.btn_picture);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePicture();
            }
        });
        if (savedInstanceState == null) {
            ThingsNotifyFragment f = new ThingsNotifyFragment();
            Bundle args = new Bundle();
            args.putSerializable(ThingsNotifyFragment.EXTRA_THINGS_DATA, thingsData);
            f.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, f, TYPE_NOTIFY)
                    .commit();
        }
        init();
    }

    private void init(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일");
        thingsNameView.setText(thingsData.thingsName);
        categoryView.setText("물건 빌려주기");
        dateView.setText(sdf.format(new Date(thingsData.date)));
    }
    public void changeNotify() {
        ThingsNotifyFragment f = new ThingsNotifyFragment();
        Bundle args = new Bundle();
        args.putSerializable(ThingsNotifyFragment.EXTRA_THINGS_DATA, thingsData);
        f.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, f, TYPE_NOTIFY)
                .commit();
    }

    public void changePicture() {
        ThingsPictureFragment f = new ThingsPictureFragment();
        Bundle args = new Bundle();
        args.putSerializable(ThingsPictureFragment.EXTRA_THINGS_DATA, thingsData);
        f.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, f, TYPE_PICTURE)
                .commit();
    }

    public Bitmap byteArrayToBitmap(byte[] $byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray($byteArray, 0, $byteArray.length);
        return bitmap;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_send_iou,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        if(id == R.id.send_iou){
//            Intent i = new Intent(this, IOUActivity.class);
//            i.putExtra(IOUActivity.EXTRA_ACCOUNT_DATA, thingsData);
//            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in_background, R.anim.slide_right_out);
    }
}
