package com.whenrepay.rnd.whenrepay.BorrowThings;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.whenrepay.rnd.whenrepay.BorrowMoney.PersonView;
import com.whenrepay.rnd.whenrepay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContractThingsFragment extends Fragment {


    public ContractThingsFragment() {
        // Required empty public constructor
    }

    LinearLayout cameraView, galleryView;
    ViewSwitcher viewSwitcher;
    ImageView pictureView;
    EditText nameView, memoView;
    PersonView personView;
    private static final int REQUEST_CAMERA = 100;
    private static final int REQUEST_GALLERY = 200;
    private static final int REQUEST_CONTACT = 300;

    ThingsData thingsData;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contract_things, container, false);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    getActivity().finish();
                    return true;
                } else {
                    return false;
                }
            }
        });
        thingsData = new ThingsData();
        viewSwitcher = (ViewSwitcher) view.findViewById(R.id.view_switcher_contact);
        cameraView = (LinearLayout) view.findViewById(R.id.btn_camera);
        galleryView = (LinearLayout) view.findViewById(R.id.btn_gallery);
        pictureView = (ImageView) view.findViewById(R.id.image_things);
        nameView = (EditText) view.findViewById(R.id.edit_things);
        memoView = (EditText) view.findViewById(R.id.edit_memo);
        personView = (PersonView) view.findViewById(R.id.person);

        cameraView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        });
        galleryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_GALLERY);
            }
        });

        Button btn = (Button) view.findViewById(R.id.btn_done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(thingsData.borrowerName) || !TextUtils.isEmpty(nameView.getText().toString())) {
                    thingsData.thingsName = nameView.getText().toString();
                    thingsData.memo = memoView.getText().toString();
                    ((LendThingsActivity) getActivity()).changeSignature(thingsData);
                }else{
                    Toast.makeText(getContext(),"필수정보를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn = (Button) view.findViewById(R.id.btn_contact);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CONTACT);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (resultCode != Activity.RESULT_CANCELED) {
                    Uri selectedImageUri = data.getData();
                    Cursor c = getContext().getContentResolver().query(selectedImageUri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                    if (!c.moveToNext()) {
                        c.close();
                        return;
                    }
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    c.close();
                    thingsData.pictureUri = selectedImageUri.toString();
                    cameraView.setVisibility(View.GONE);
                    galleryView.setVisibility(View.GONE);
                    Glide.with(getContext()).load(selectedImageUri).into(pictureView);
                }
                break;
            case REQUEST_GALLERY:
                if (resultCode != Activity.RESULT_CANCELED) {
                    Uri selectedImageUri = data.getData();
                    Cursor c = getContext().getContentResolver().query(selectedImageUri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                    if (!c.moveToNext()) {
                        c.close();
                        return;
                    }
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    c.close();
                    thingsData.pictureUri = selectedImageUri.toString();
                    cameraView.setVisibility(View.GONE);
                    galleryView.setVisibility(View.GONE);
                    Glide.with(getContext()).load(selectedImageUri).into(pictureView);
                }
                break;
            case REQUEST_CONTACT:
                if (resultCode != Activity.RESULT_CANCELED) {
                    Cursor cursor = getActivity().getContentResolver().query(data.getData(),
                            new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                                    ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
                    cursor.moveToFirst();
                    String name = cursor.getString(0);        //0은 이름을 얻어옵니다.
                    thingsData.borrowerName = name;
                    personView.setName(name);
                    viewSwitcher.showNext();
                    cursor.close();
                }
                break;
        }
    }
}
