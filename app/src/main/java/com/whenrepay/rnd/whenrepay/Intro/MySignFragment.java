package com.whenrepay.rnd.whenrepay.Intro;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.whenrepay.rnd.whenrepay.BorrowMoney.FingerPaintView;
import com.whenrepay.rnd.whenrepay.Main.MainActivity;
import com.whenrepay.rnd.whenrepay.Manager.PropertyManager;
import com.whenrepay.rnd.whenrepay.MyProfile;
import com.whenrepay.rnd.whenrepay.R;

import java.io.ByteArrayOutputStream;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class MySignFragment extends Fragment {


    public MySignFragment() {
        // Required empty public constructor
    }

    Realm mRealm;
    FingerPaintView paint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_sign, container, false);

        mRealm = Realm.getInstance(getContext());
        paint = (FingerPaintView) view.findViewById(R.id.paint);

        Button btn = (Button) view.findViewById(R.id.btn_resign);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paint.undoAll();
            }
        });
        btn = (Button) view.findViewById(R.id.btn_done);
        btn.setOnClickListener(new View.OnClickListener() { //서명 저장하기
            @Override
            public void onClick(View v) {
                if(paint.isDraw()) {
                    Bitmap bmp = getViewBitmap(paint);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    mRealm.beginTransaction();
                    MyProfile profile = mRealm.where(MyProfile.class).findFirst();
                    profile.setSignature(byteArray);
                    mRealm.commitTransaction();
                    PropertyManager.getInstance().setMember(true);
                    startActivity(new Intent(getContext(), MainActivity.class));
                    getActivity().finish();
                }else{
                    Toast.makeText(getContext(),"서명해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
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
}
