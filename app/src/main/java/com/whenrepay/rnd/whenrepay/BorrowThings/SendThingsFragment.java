package com.whenrepay.rnd.whenrepay.BorrowThings;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.whenrepay.rnd.whenrepay.BorrowMoney.BorrowerData;
import com.whenrepay.rnd.whenrepay.Manager.DBContants;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.MyProfile;
import com.whenrepay.rnd.whenrepay.R;
import com.whenrepay.rnd.whenrepay.TransactionData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendThingsFragment extends Fragment {


    public SendThingsFragment() {
        // Required empty public constructor
    }

    public static final String EXTRA_THINGS_DATA = "things";
    public static final String EXTRA_BORROWER_DATA = "borrower";
    private static final String FILE_NAME = "iou_image.jpg";
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 100;
    private static final int REQUEST_SEND_IMAGE = 200;
    ThingsData thingsData = null;
    BorrowerData borrowerData = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            thingsData = (ThingsData) getArguments().getSerializable(EXTRA_THINGS_DATA);
            borrowerData = (BorrowerData) getArguments().getSerializable(EXTRA_BORROWER_DATA);
        }
    }

    Realm mRealm;
    RelativeLayout iou;
    TextView thingsNameView, dateView, myNameView, borrowerNameView;
    ImageView thingsView, mySignView, borrowerSignView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_things, container, false);
        mRealm = Realm.getInstance(getContext());
        thingsNameView = (TextView) view.findViewById(R.id.text_things);
        dateView = (TextView) view.findViewById(R.id.text_date);
        myNameView = (TextView) view.findViewById(R.id.text_myname);
        borrowerNameView = (TextView) view.findViewById(R.id.text_borrower);
        thingsView = (ImageView) view.findViewById(R.id.image_things);
        mySignView = (ImageView) view.findViewById(R.id.image_mysign);
        borrowerSignView = (ImageView) view.findViewById(R.id.image_borrower_sign);
        iou = (RelativeLayout) view.findViewById(R.id.iou);

        Button btn = (Button) view.findViewById(R.id.btn_dun);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i("SendThings", "name:" + thingsData.borrowerName + "things:" + thingsData.thingsName + "\ndate:" + thingsData.date + "memo" + thingsData.memo);
                DataManager.getInstance().insertContractThings(thingsData);
                for(TransactionData data : DataManager.getInstance().getContractThingsList()){
                    ThingsData thingsData = (ThingsData)data;
                    Log.i("SendThings", "name:" + thingsData.borrowerName + " things:" + thingsData.thingsName + "\ndate:" + thingsData.date + " memo:" + thingsData.memo);
                }
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
                    }
                } else {
                    saveIOU();
                }
                sendIOU();
            }
        });
        initData();
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveIOU();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_SEND_IMAGE){
            ((LendThingsActivity)getActivity()).changeSuccess();
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

        startActivityForResult(Intent.createChooser(intent, "공유"), REQUEST_SEND_IMAGE);
    }

    private void initData() {
        thingsNameView.setText(thingsData.thingsName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        Date date = new Date();
        dateView.setText(sdf.format(date));
        MyProfile profile = mRealm.where(MyProfile.class).findFirst();
        myNameView.setText(profile.getName());
        mySignView.setImageBitmap(byteArrayToBitmap(profile.getSignature()));
        borrowerNameView.setText(thingsData.borrowerName);
        borrowerSignView.setImageBitmap(byteArrayToBitmap(borrowerData.byteBitmap));
        if(!TextUtils.isEmpty(thingsData.pictureUri)) {
            Glide.with(getContext()).load(thingsData.pictureUri).into(thingsView);
        }else{
//            thingsView.setImageResource();
        }

    }

    private Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);
        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(Color.TRANSPARENT);
        if (color != Color.TRANSPARENT) {
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
}
