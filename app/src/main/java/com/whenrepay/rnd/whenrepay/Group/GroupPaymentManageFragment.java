package com.whenrepay.rnd.whenrepay.Group;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.whenrepay.rnd.whenrepay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupPaymentManageFragment extends Fragment {


    public GroupPaymentManageFragment() {
        // Required empty public constructor
    }

    ExpandableListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_payment_manage, container, false);

        listView = (ExpandableListView)view.findViewById(R.id.expandableListView);
        listView.setIndicatorBounds(listView.getRight() -40, listView.getWidth());

        return view;
    }

}
