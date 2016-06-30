package com.whenrepay.rnd.whenrepay.Transactions;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        Intent i = getIntent();
        thingsData = (ThingsData)i.getSerializableExtra(EXTRA_THINGS_DATA);
        pictureVIew = (ImageView)findViewById(R.id.image_picture);

        pictureVIew.setImageBitmap(byteArrayToBitmap(thingsData.picture));
    }
    public Bitmap byteArrayToBitmap(byte[] $byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray($byteArray, 0, $byteArray.length);
        return bitmap;
    }
}
