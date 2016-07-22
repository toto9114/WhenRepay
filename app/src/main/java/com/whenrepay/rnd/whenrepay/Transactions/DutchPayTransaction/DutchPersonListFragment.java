package com.whenrepay.rnd.whenrepay.Transactions.DutchPayTransaction;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whenrepay.rnd.whenrepay.DutchPay.DutchPayData;
import com.whenrepay.rnd.whenrepay.Group.OnItemCheckedListener;
import com.whenrepay.rnd.whenrepay.Group.PersonData;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DutchPersonListFragment extends Fragment {


    public DutchPersonListFragment() {
        // Required empty public constructor
    }


    FamiliarRecyclerView recyclerView;
    DutchPersonAdapter mAdapter;

    DutchPayData dutchPayData;
    public static final String EXTRA_DUTCH_DATA ="dutch";

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
        View view = inflater.inflate(R.layout.fragment_dutch_person_list, container, false);
        recyclerView = (FamiliarRecyclerView)view.findViewById(R.id.recycler);
        mAdapter = new DutchPersonAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        init();
        mAdapter.setOnItemCheckedListener(new OnItemCheckedListener() {
            @Override
            public void OnItemChecked(boolean isChecked, int position) {
                PersonData data = mAdapter.getItemAtPosition(position);
                data.setIsPay(isChecked);
                DataManager.getInstance().updateDutchPayPerson(dutchPayData._id,data);
            }
        });

        return view;
    }

    private void init(){
        mAdapter.clear();
        mAdapter.addAll(DataManager.getInstance().getDutchPersonList(dutchPayData._id));
    }

}
