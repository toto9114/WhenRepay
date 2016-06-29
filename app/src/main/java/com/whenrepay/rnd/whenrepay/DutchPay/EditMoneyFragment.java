package com.whenrepay.rnd.whenrepay.DutchPay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
            dutchPayData = (DutchPayData) getArguments().getSerializable(EXTRA_DUTCH_DATA);
        }
    }

    RecyclerView recyclerView;
    EventAdapter mAdapter;
    LinearLayoutManager layoutManager;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_money, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mAdapter = new EventAdapter();
        layoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);


        Button btn = (Button) view.findViewById(R.id.btn_done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventData data;
                data = mAdapter.getItem();
                mAdapter.setData(data);
                dutchPayData.eventList = mAdapter.getItemList();
                ((DutchPayActivity)getActivity()).changeSend(dutchPayData);
            }
        });

        btn = (Button)view.findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventData data;
                data = mAdapter.getItem();
                mAdapter.setData(data);
                mAdapter.add(null);
            }
        });
        init();
        return view;
    }
    private void init(){
        EventData eventData = new EventData();
        eventData.people = dutchPayData.personList;
        mAdapter.add(eventData);
    }
}
