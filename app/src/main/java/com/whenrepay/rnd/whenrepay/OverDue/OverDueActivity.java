package com.whenrepay.rnd.whenrepay.OverDue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.whenrepay.rnd.whenrepay.BorrowMoney.AccountData;
import com.whenrepay.rnd.whenrepay.DutchPay.DutchPayData;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;
import com.whenrepay.rnd.whenrepay.TransactionData;
import com.whenrepay.rnd.whenrepay.Transactions.IOUDialog;
import com.whenrepay.rnd.whenrepay.Transactions.TransactionFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

public class OverDueActivity extends AppCompatActivity {

    public static final String EXTRA_OVERDUE = "overdue";
    FamiliarRecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    DetailOverDueAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_due);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out_background);
        recyclerView = (FamiliarRecyclerView) findViewById(R.id.recycler_overdue);
        mAdapter = new DetailOverDueAdapter();
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        recyclerView.setOnItemClickListener(new FamiliarRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
//                Toast.makeText(OverDueActivity.this, "" + mAdapter.getItem(position).getPrice(),Toast.LENGTH_SHORT).show();
                if (mAdapter.getItem(position) instanceof AccountData) {
                    IOUDialog dialog = new IOUDialog();
                    Bundle args = new Bundle();
                    args.putSerializable(IOUDialog.EXTRA_ACCOUNT_DATA, (AccountData) mAdapter.getItem(position));
                    dialog.setArguments(args);
                    dialog.show(getSupportFragmentManager(), "dialog");
                } else {
                    Toast.makeText(OverDueActivity.this, "not yet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        initData();
    }

    private void initData() {
        if (DataManager.getInstance().getContractList(TransactionFragment.SORT_TYPE_DATE).size() != 0) {
            for (TransactionData data : DataManager.getInstance().getContractList(TransactionFragment.SORT_TYPE_DATE)) {
                AccountData accountData = (AccountData)data;
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
                if(!accountData.isCompleted) {
                    if (accountData.getRepayDate().compareTo(sdf.format(c.getTime())) < 0) {
                        mAdapter.add(accountData);
                    }
                }
            }
        }
        if (DataManager.getInstance().getContractThingsList().size() != 0) {
            for (TransactionData data : DataManager.getInstance().getContractThingsList()) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
                if (data.getRepayDate().compareTo(sdf.format(c.getTime())) < 0) {
//                if (data.getDate()- c.getTimeInMillis() < 0) {
                    mAdapter.add(data);
                }
            }
        }
        if (DataManager.getInstance().getDutchPayList().size() != 0) {
            for (TransactionData data : DataManager.getInstance().getDutchPayList()) {
                DutchPayData dutchPayData = (DutchPayData)data;
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
                if (sdf.format(new Date(dutchPayData.date)).compareTo(sdf.format(c.getTime())) < 0) {
//                if (data.getDate()- c.getTimeInMillis() < 0) {
                    mAdapter.add(dutchPayData);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in_background, R.anim.slide_right_out);
    }
}
