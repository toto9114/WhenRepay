package com.whenrepay.rnd.whenrepay.Transactions.ThingsTransaction;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whenrepay.rnd.whenrepay.BorrowThings.ThingsData;
import com.whenrepay.rnd.whenrepay.R;
import com.whenrepay.rnd.whenrepay.Transactions.NotifyAdapter;
import com.whenrepay.rnd.whenrepay.Transactions.NotifyData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThingsNotifyFragment extends Fragment {


    public ThingsNotifyFragment() {
        // Required empty public constructor
    }
    FamiliarRecyclerView recyclerView;
    NotifyAdapter mAdapter;
    LinearLayoutManager layoutManager;
    Realm mRealm;
    public static final String EXTRA_THINGS_DATA = "thins";

    ThingsData thingsData;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            thingsData = (ThingsData)getArguments().getSerializable(EXTRA_THINGS_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_things_notify, container, false);
        mRealm = Realm.getInstance(getContext());
        recyclerView = (FamiliarRecyclerView) view.findViewById(R.id.container);
        mAdapter = new NotifyAdapter();
        layoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL,false);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);
        init();
        return view;
    }

    private void init(){
        mAdapter.clear();
        List<ThingsDunData> dunList =  mRealm.where(ThingsDunData.class).equalTo("_id", thingsData._id).findAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        NotifyData notifyData;
        if(thingsData.isCompleted){
            notifyData = new NotifyData();
            notifyData.date = sdf.format(new Date());
            notifyData.message = thingsData.thingsName + " 반납되었습니다.";
            mAdapter.add(notifyData);
        }
        for(ThingsDunData data : dunList){
            notifyData = new NotifyData();
            notifyData.date = sdf.format(new Date(data.getDate()));
            notifyData.message = "대여자에게 독촉하였습니다.";
            mAdapter.add(notifyData);
        }
        if(!thingsData.isCompleted && thingsData.getRepayDate().compareTo(sdf.format(new Date()))<0){
            notifyData = new NotifyData();
            try {
                Date date = sdf.parse(thingsData.getRepayDate());
                date.setTime(date.getTime() + 1000 * 60 * 60 * 24);
                notifyData.date = sdf.format(date);
                notifyData.message = "거래가 연체되었습니다.";
                mAdapter.add(notifyData);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

}
