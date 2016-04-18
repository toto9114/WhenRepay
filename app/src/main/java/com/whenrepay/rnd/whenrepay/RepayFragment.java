package com.whenrepay.rnd.whenrepay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RepayFragment extends Fragment {


    public RepayFragment() {
        // Required empty public constructor
    }

    ExpandableListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_repay, container, false);
        listView = (ExpandableListView)view.findViewById(R.id.expandableListView);

        return view;
    }

}
