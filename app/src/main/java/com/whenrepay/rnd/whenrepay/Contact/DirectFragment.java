package com.whenrepay.rnd.whenrepay.Contact;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whenrepay.rnd.whenrepay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DirectFragment extends Fragment {


    public DirectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_direct, container, false);

        return view;
    }

}
