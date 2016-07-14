package com.whenrepay.rnd.whenrepay.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;

import com.whenrepay.rnd.whenrepay.BorrowMoney.AccountData;
import com.whenrepay.rnd.whenrepay.BorrowThings.ThingsData;
import com.whenrepay.rnd.whenrepay.DutchPay.DutchPayData;
import com.whenrepay.rnd.whenrepay.Manager.DBContants;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;
import com.whenrepay.rnd.whenrepay.TransactionData;
import com.whenrepay.rnd.whenrepay.Transactions.DetailDutchActivity;
import com.whenrepay.rnd.whenrepay.Transactions.DetailMoneyTransactionActivity;
import com.whenrepay.rnd.whenrepay.Transactions.DetailThingsTransactionActivity;
import com.whenrepay.rnd.whenrepay.Transactions.TransactionAdapter;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

public class CompleteActivity extends AppCompatActivity {

    FamiliarRecyclerView recycler;
    TransactionAdapter mAdapter;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out_background);
        recycler = (FamiliarRecyclerView) findViewById(R.id.recycler);
        mAdapter = new TransactionAdapter();
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        recycler.setAdapter(mAdapter);
        recycler.setLayoutManager(layoutManager);
        initData();

        recycler.setOnItemClickListener(new FamiliarRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
                TransactionData data = mAdapter.getItemAtPosition(position);
                if (data instanceof AccountData) {
                    Intent intent = new Intent(CompleteActivity.this, DetailMoneyTransactionActivity.class);
                    intent.putExtra(DetailMoneyTransactionActivity.EXTRA_ACCOUNT_DATA, (AccountData) data);
                    startActivity(intent);
                } else if (data instanceof ThingsData) {
                    Intent intent = new Intent(CompleteActivity.this, DetailThingsTransactionActivity.class);
                    intent.putExtra(DetailThingsTransactionActivity.EXTRA_THINGS_DATA, (ThingsData) data);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(CompleteActivity.this, DetailDutchActivity.class);
                    intent.putExtra(DetailDutchActivity.EXTRA_DUTCH_DATA, (DutchPayData) data);
                    startActivity(intent);
                }
            }
        });
    }

    private void initData() {
        if (DataManager.getInstance().getContractList(DBContants.SORT_TYPE_DATE).size() > 0) {
            for (TransactionData data : DataManager.getInstance().getContractList(DBContants.SORT_TYPE_DATE)) {
                AccountData accountData = (AccountData) data;
                if (accountData.isCompleted) {
                    mAdapter.add(accountData);
                }
            }
        }
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in_background, R.anim.slide_right_out);
    }
}
