package com.whenrepay.rnd.whenrepay.OverDue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.Toast;

import com.whenrepay.rnd.whenrepay.AccountData;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;
import com.whenrepay.rnd.whenrepay.TransactionData;
import com.whenrepay.rnd.whenrepay.Transactions.IOUActivity;
import com.whenrepay.rnd.whenrepay.Transactions.TransactionFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

public class OverDueActivity extends AppCompatActivity {

    public static final String EXTRA_OVERDUE = "overdue";
    FamiliarRecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    OverDueAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_due);

        recyclerView = (FamiliarRecyclerView)findViewById(R.id.recycler);
        mAdapter = new OverDueAdapter();
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        recyclerView.setOnItemClickListener(new FamiliarRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
//                Toast.makeText(OverDueActivity.this, "" + mAdapter.getItem(position).getPrice(),Toast.LENGTH_SHORT).show();
                if(mAdapter.getItem(position) instanceof AccountData){
                    Intent i = new Intent(OverDueActivity.this, IOUActivity.class);
                    i.putExtra(IOUActivity.EXTRA_ACCOUNT_DATA,(AccountData)mAdapter.getItem(position));
                    startActivity(i);
                }else{
                    Toast.makeText(OverDueActivity.this,"not yet",Toast.LENGTH_SHORT).show();
                }
            }
        });

        initData();
    }

    private void initData(){
        if(DataManager.getInstance().getContractList(TransactionFragment.SORT_TYPE_DATE).size() != 0) {
            for (TransactionData data : DataManager.getInstance().getContractList(TransactionFragment.SORT_TYPE_DATE)) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
                if (data.getDate().compareTo(sdf.format(c.getTime())) < 0) {
                    mAdapter.add(data);
                }
            }
        }
        if (DataManager.getInstance().getContractThingsList().size() != 0) {
            for (TransactionData data : DataManager.getInstance().getContractThingsList()) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
                if (data.getDate().compareTo(sdf.format(c.getTime())) < 0) {
                    mAdapter.add(data);
                }
            }
        }
        mAdapter.sort();
    }
}
