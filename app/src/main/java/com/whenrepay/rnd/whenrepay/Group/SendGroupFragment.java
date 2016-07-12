package com.whenrepay.rnd.whenrepay.Group;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.whenrepay.rnd.whenrepay.Manager.DBContants;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendGroupFragment extends Fragment {


    public SendGroupFragment() {
        // Required empty public constructor
    }


    TextView moneyView, dateView, accountView, bottomDateView;
    ListView listView;
    SendPersonAdapter mAdapter;

    public static final String EXTRA_GROUP_DATA = "group";
    private static final String FILE_NAME = "iou_image.jpg";
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 100;

    GroupData groupData = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            groupData = (GroupData) getArguments().getSerializable(EXTRA_GROUP_DATA);
        }

    }

    RelativeLayout iou;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_group, container, false);

        iou = (RelativeLayout) view.findViewById(R.id.iou);
        moneyView = (TextView) view.findViewById(R.id.text_money);
        dateView = (TextView) view.findViewById(R.id.text_date);
        accountView = (TextView) view.findViewById(R.id.text_account);
        bottomDateView = (TextView) view.findViewById(R.id.text_bottom_date);
        listView = (ListView) view.findViewById(R.id.listView);
        mAdapter = new SendPersonAdapter();
        listView.setAdapter(mAdapter);
        initData();
//        listView.setEnabled(false);

        Button btn = (Button) view.findViewById(R.id.btn_dun);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGroup();
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

        return view;
    }

    private void initData() {
        mAdapter.clear();
        if (groupData != null) {
//            mAdapter.addAll(groupData.personList);
//            moneyView.setText("" + groupData.moneyPerPerson);
//            accountView.setText(groupData.account);
//            dateView.setText(groupData.paymentDate);
            mAdapter.addAll(groupData.getPersonList());
            moneyView.setText("" + groupData.getMoneyPerPerson());
            accountView.setText(groupData.getAccount());
            dateView.setText(groupData.getPaymentDate());

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
            bottomDateView.setText(sdf.format(date));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveIOU();
                Toast.makeText(getContext(), "done", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveGroup() {
        DataManager.getInstance().insertGroup(groupData);
//        for(PersonData data : groupData.getPersonList()){
//            data.group = groupData._id;
//            DataManager.getInstance().insertMember(groupData._id,data);
//        }
        int id = DataManager.getInstance().getGroupList().get(DataManager.getInstance().getGroupList().size() - 1)._id;
        for (int i = 0; i < groupData.getPersonList().size(); i++) {
            PersonData data;
            data = groupData.getPersonList().get(i);
            DataManager.getInstance().insertMember(id, data);
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

    private static final int REQUEST_SEND_IMAGE = 200;

    private void sendIOU() {
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), DBContants.IOU_FOLDER_NAME);
        File saveFile = new File(dir, FILE_NAME);
        Uri uri = Uri.fromFile(saveFile);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpg");
        intent.putExtra(Intent.EXTRA_STREAM, uri);

        startActivityForResult(Intent.createChooser(intent, "공유"), REQUEST_SEND_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SEND_IMAGE) {
            ((AddGroupActivity) getActivity()).changeSuccess();
        }
    }
}
