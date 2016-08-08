package com.whenrepay.rnd.whenrepay.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.whenrepay.rnd.whenrepay.BorrowMoney.AccountData;
import com.whenrepay.rnd.whenrepay.BorrowThings.ThingsData;
import com.whenrepay.rnd.whenrepay.DutchPay.DutchPayData;
import com.whenrepay.rnd.whenrepay.Group.OnItemCheckedListener;
import com.whenrepay.rnd.whenrepay.Manager.DBContants;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;
import com.whenrepay.rnd.whenrepay.TransactionData;
import com.whenrepay.rnd.whenrepay.Transactions.DutchPayTransaction.DetailDutchActivity;
import com.whenrepay.rnd.whenrepay.Transactions.MoneyTransaction.DetailMoneyTransactionActivity;
import com.whenrepay.rnd.whenrepay.Transactions.ThingsTransaction.DetailThingsTransactionActivity;
import com.whenrepay.rnd.whenrepay.Transactions.MoneyTransaction.MoneyDunData;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import io.realm.Realm;

public class CompleteActivity extends AppCompatActivity {

    FamiliarRecyclerView recycler;
    CompleteAdapter mAdapter;
    LinearLayoutManager layoutManager;

    boolean[] checkedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out_background);
        recycler = (FamiliarRecyclerView) findViewById(R.id.container);
        mAdapter = new CompleteAdapter();
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        recycler.setAdapter(mAdapter);
        recycler.setLayoutManager(layoutManager);
        mRealm = Realm.getInstance(this);
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
        mAdapter.setOnCheckedListener(new OnItemCheckedListener() {
            @Override
            public void OnItemChecked(boolean isChecked, int position) {
                checkedList[position] = isChecked;
            }
        });
    }

    private void initData() {
        mAdapter.clear();
        checkedList = new boolean[DataManager.getInstance().getContractList(DBContants.SORT_TYPE_DATE).size()];
        if (DataManager.getInstance().getContractList(DBContants.SORT_TYPE_DATE).size() > 0) {
            for (TransactionData data : DataManager.getInstance().getContractList(DBContants.SORT_TYPE_DATE)) {
                AccountData accountData = (AccountData) data;
                if (accountData.isCompleted) {
                    mAdapter.add(accountData);
                }
            }
        }
        if(DataManager.getInstance().getContractThingsList().size()>0){
            for(TransactionData data : DataManager.getInstance().getContractThingsList()){
                ThingsData thingsData = (ThingsData)data;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_repayment, menu);
        return true;
    }

    boolean isDelClicked = false;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!isDelClicked) {
            menu.getItem(0).setTitle("삭제");
        } else {
            menu.getItem(0).setTitle("완료");
        }
        return true;
//        return super.onPrepareOptionsMenu(menu);
    }

    Realm mRealm;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        if (id == R.id.repayment) {
            mAdapter.setCheckBoxVisible(false);
            if (isDelClicked) {
                for (int i = 0; i < checkedList.length; i++) {
                    if (checkedList[i]) {
                        TransactionData data = mAdapter.getItemAtPosition(i);
                        if (data instanceof AccountData) {
                            DataManager.getInstance().deleteContract((AccountData) data);
                            mRealm.beginTransaction();
                            if (mRealm.where(MoneyDunData.class).equalTo("_id", ((AccountData) data)._id).findAll().size() > 0) {
                                mRealm.where(MoneyDunData.class).equalTo("_id", ((AccountData) data)._id).findAll().clear();
                            }
                            mRealm.commitTransaction();
                        } else if (data instanceof ThingsData) {
                            DataManager.getInstance().deleteThingsContract(((ThingsData) data));
                            mRealm.beginTransaction();
                            if (mRealm.where(MoneyDunData.class).equalTo("_id", ((ThingsData) data)._id).findAll().size() > 0) {
                                mRealm.where(MoneyDunData.class).equalTo("_id", ((ThingsData) data)._id).findAll().clear();
                            }
                            mRealm.commitTransaction();
                        } else {
                            DataManager.getInstance().deleteDutchData(((DutchPayData) data));
                            mRealm.beginTransaction();
                            if(mRealm.where(MoneyDunData.class).equalTo("_id",((DutchPayData)data)._id).findAll().size()>0){
                                mRealm.where(MoneyDunData.class).equalTo("_id",((DutchPayData)data)._id).findAll().clear();
                            }
                            mRealm.commitTransaction();
                        }
                        Log.i("checkedList", "" + i + "," + data.getPrice());
                    }
                }
                isDelClicked = false;
                invalidateOptionsMenu();

            } else {
                mAdapter.setCheckBoxVisible(true);
                isDelClicked = true;
                invalidateOptionsMenu();

            }
            initData();

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
