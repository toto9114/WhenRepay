package com.whenrepay.rnd.whenrepay.Transactions.DutchPayTransaction;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.whenrepay.rnd.whenrepay.DutchPay.DutchPayData;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DutchInfoFragment extends Fragment {


    public DutchInfoFragment() {
        // Required empty public constructor
    }

    DutchPayData dutchPayData;
    public static final String EXTRA_DUTCH_DATA = "dutch";

    ExpandableListView listView;
    DutchInfoAdapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            dutchPayData = (DutchPayData)getArguments().getSerializable(EXTRA_DUTCH_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dutch_info, container, false);
        listView = (ExpandableListView)view.findViewById(R.id.listView);
        mAdapter = new DutchInfoAdapter();
        listView.setAdapter(mAdapter);

        init();
        return view;
    }
    private void init(){
        mAdapter.clear();
        for(int i = 0 ; i< DataManager.getInstance().getDutchEventList(dutchPayData._id).size(); i++) {
            mAdapter.add(DataManager.getInstance().getDutchEventList(dutchPayData._id).get(i));
        }
    }
}
