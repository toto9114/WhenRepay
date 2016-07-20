package com.whenrepay.rnd.whenrepay.Transactions;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whenrepay.rnd.whenrepay.BorrowMoney.AccountData;
import com.whenrepay.rnd.whenrepay.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notifyfragment extends Fragment {


    public static final String EXTRA_ACCOUNT_DATA = "account";
    public Notifyfragment() {
        // Required empty public constructor
    }

    FamiliarRecyclerView recyclerView;
    NotifyAdapter mAdapter;
    AccountData accountData;
    Realm mRealm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            accountData = (AccountData)getArguments().getSerializable(EXTRA_ACCOUNT_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifyfragment, container, false);
        mRealm = Realm.getInstance(getContext());
        recyclerView = (FamiliarRecyclerView) view.findViewById(R.id.recycler);
        mAdapter = new NotifyAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        initData();
        return view;
    }

    private void initData(){
        mAdapter.clear();
        List<DunData> list = mRealm.where(DunData.class).equalTo("_id", accountData._id).findAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        NotifyData notifyData;
        if (accountData.isCompleted) {
            notifyData = new NotifyData();
            notifyData.date = sdf.format(new Date());
            notifyData.message = "전액 상환되었습니다";
            mAdapter.add(notifyData);
            Log.i("detail", "complete");
        }
        for (DunData data : list) {
            Date date = new Date(data.getDate());
            Log.i("detail", "date : " + sdf.format(date));
            notifyData = new NotifyData();
            notifyData.date = sdf.format(date);
            notifyData.message = "차용인에게 독촉하였습니다";
            mAdapter.add(notifyData);
        }
        if (accountData.getRepayDate().compareTo(sdf.format(new Date())) < 0 && !accountData.isCompleted) {
            notifyData = new NotifyData();
            try {
                Date date = sdf.parse(accountData.getRepayDate());
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
