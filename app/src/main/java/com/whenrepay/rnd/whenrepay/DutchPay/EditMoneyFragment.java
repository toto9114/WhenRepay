package com.whenrepay.rnd.whenrepay.DutchPay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whenrepay.rnd.whenrepay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditMoneyFragment extends Fragment {


    public EditMoneyFragment() {
        // Required empty public constructor
    }

    public static final String EXTRA_DUTCH_DATA = "dutch";
    DutchPayData dutchPayData;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dutchPayData = (DutchPayData)getArguments().getSerializable(EXTRA_DUTCH_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_money, container, false);

        return view;
    }

}
