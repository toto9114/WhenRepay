package com.whenrepay.rnd.whenrepay.Transactions;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.whenrepay.rnd.whenrepay.BorrowMoney.AccountData;
import com.whenrepay.rnd.whenrepay.Manager.DBContants;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.MyProfile;
import com.whenrepay.rnd.whenrepay.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

public class IOUActivity extends AppCompatActivity {

    public static final String EXTRA_ACCOUNT_DATA = "account";

    AccountData accountData;

    RelativeLayout iou;
    TextView creditorName, borrowerName, priceView, interestView, dateView, userAccountView, bottomDate;
    ImageView mySign, borrowerSign;
    Realm mRealm;
    private static final String FILE_NAME = "iou_image.jpg";
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 100;
    private static final int REQUEST_SEND_IMAGE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iou);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out_background);

        Intent i = getIntent();
        accountData = (AccountData) i.getSerializableExtra(EXTRA_ACCOUNT_DATA);

        creditorName = (TextView) findViewById(R.id.text_creditor);
        borrowerName = (TextView) findViewById(R.id.text_borrower);
        priceView = (TextView) findViewById(R.id.text_money);
        interestView = (TextView) findViewById(R.id.text_interest);
        dateView = (TextView) findViewById(R.id.text_date);
        userAccountView = (TextView) findViewById(R.id.text_user_account);
        bottomDate = (TextView) findViewById(R.id.text_bottom_date);
        borrowerSign = (ImageView) findViewById(R.id.image_borrower_sign);
        mySign = (ImageView) findViewById(R.id.image_mysign);
        iou = (RelativeLayout) findViewById(R.id.iou);

        mRealm = Realm.getInstance(this);
        MyProfile profile = mRealm.where(MyProfile.class).findFirst();
        byte[] byteArray = profile.getSignature();
        Bitmap mSignature = byteArrayToBitmap(byteArray);
        mySign.setImageBitmap(mSignature);
        creditorName.setText(profile.getName());    //채권자 이름
        userAccountView.setText(profile.getBank() + " " + profile.getAccount());

        if (accountData != null) {
            borrowerName.setText(accountData.name);
            priceView.setText("" + accountData.money);
            if (accountData.interest != 0) {
                interestView.setText(accountData.interest);
            } else {
                interestView.setText("해당없음");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
            Date date= new Date(accountData.date);
            dateView.setText(accountData.repayDate);
            bottomDate.setText(sdf.format(date));
            borrowerSign.setImageBitmap(byteArrayToBitmap(accountData.sign));
        }
        Button btn = (Button) findViewById(R.id.btn_done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accountData != null) {
                    DataManager.getInstance().insertContract(accountData);
                    if (ContextCompat.checkSelfPermission(IOUActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(IOUActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                        } else {
                            ActivityCompat.requestPermissions(IOUActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
                        }
                    } else {
                        saveIOU();
                    }

                }
                sendIOU();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveIOU();
                Toast.makeText(IOUActivity.this, "done", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveIOU() {
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), DBContants.IOU_FOLDER_NAME);
        File saveFile = new File(dir, FILE_NAME);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            FileOutputStream fos = new FileOutputStream(saveFile);
            Bitmap bitmap = getViewBitmap(iou);
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
                bitmap.recycle();
            }
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendIOU() {
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), DBContants.IOU_FOLDER_NAME);
        File saveFile = new File(dir, FILE_NAME);
        Uri uri = Uri.fromFile(saveFile);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpg");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        mRealm.beginTransaction();
        DunData data = mRealm.createObject(DunData.class);
        data.set_id(accountData._id);
        Calendar c = Calendar.getInstance();
        data.setDate(c.getTimeInMillis());
        mRealm.commitTransaction();
        startActivityForResult(Intent.createChooser(intent, "공유"), REQUEST_SEND_IMAGE);
    }

    Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SEND_IMAGE) {
            Toast.makeText(this, "전송되었습니다", Toast.LENGTH_SHORT).show();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 1000);

        }
    }

    private Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);
        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(Color.WHITE);
        if (color != Color.WHITE) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);

        return bitmap;
    }

    public Bitmap byteArrayToBitmap(byte[] $byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray($byteArray, 0, $byteArray.length);
        return bitmap;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in_background, R.anim.slide_right_out);
    }
}
