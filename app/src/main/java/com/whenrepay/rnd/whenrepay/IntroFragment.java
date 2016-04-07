package com.whenrepay.rnd.whenrepay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment extends Fragment {
    public static final String EXTRA_POSITION ="position";

    private static final int[] IMAGE = {R.drawable.image1,R.drawable.image2,R.drawable.image3};

    public IntroFragment() {
        // Required empty public constructor
    }

    int position;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!= null) {
            position = getArguments().getInt(EXTRA_POSITION);
        }
    }

    ImageView introView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        introView = (ImageView)view.findViewById(R.id.image_intro);

        introView.setImageResource(IMAGE[position]);

        return view;
    }
}
