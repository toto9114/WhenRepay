package com.whenrepay.rnd.whenrepay.Transactions.ThingsTransaction;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.whenrepay.rnd.whenrepay.BorrowThings.ThingsData;
import com.whenrepay.rnd.whenrepay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThingsPictureFragment extends Fragment {


    public ThingsPictureFragment() {
        // Required empty public constructor
    }

    public static final String EXTRA_THINGS_DATA = "thigns";
    ThingsData thingsData;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            thingsData = (ThingsData)getArguments().getSerializable(EXTRA_THINGS_DATA);
        }
    }

    ImageView pictureView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_things_picture, container, false);
        pictureView = (ImageView)view.findViewById(R.id.image_picture);
        pictureView.setImageBitmap(byteArrayToBitmap(thingsData.picture));
        return view;
    }
    public Bitmap byteArrayToBitmap(byte[] $byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray($byteArray, 0, $byteArray.length);
        return bitmap;
    }
}
