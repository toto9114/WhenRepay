package com.whenrepay.rnd.whenrepay.Group;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whenrepay.rnd.whenrepay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendGroupFragment extends Fragment {


    public SendGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_group, container, false);
        return view;
    }

}
