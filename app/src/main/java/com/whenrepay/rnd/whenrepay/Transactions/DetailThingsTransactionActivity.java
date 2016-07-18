package com.whenrepay.rnd.whenrepay.Transactions;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.whenrepay.rnd.whenrepay.BorrowThings.ThingsData;
import com.whenrepay.rnd.whenrepay.R;

public class DetailThingsTransactionActivity extends AppCompatActivity {

    public static final String EXTRA_THINGS_DATA = "things";
    ThingsData thingsData;

    ImageView pictureVIew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_things_transaction);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out_background);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        thingsData = (ThingsData)i.getSerializableExtra(EXTRA_THINGS_DATA);
        pictureVIew = (ImageView)findViewById(R.id.image_picture);

        pictureVIew.setImageBitmap(byteArrayToBitmap(thingsData.picture));

        Button btn = (Button)findViewById(R.id.btn_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn = (Button)findViewById(R.id.btn_done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public Bitmap byteArrayToBitmap(byte[] $byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray($byteArray, 0, $byteArray.length);
        return bitmap;
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
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in_background, R.anim.slide_right_out);
    }
}
