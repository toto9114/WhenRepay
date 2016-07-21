package com.whenrepay.rnd.whenrepay.OverDue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.whenrepay.rnd.whenrepay.BorrowMoney.AccountData;
import com.whenrepay.rnd.whenrepay.BorrowThings.ThingsData;
import com.whenrepay.rnd.whenrepay.DutchPay.DutchPayData;
import com.whenrepay.rnd.whenrepay.Group.OnItemCheckedListener;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;
import com.whenrepay.rnd.whenrepay.TransactionData;
import com.whenrepay.rnd.whenrepay.Transactions.MoneyTransaction.MoneyDunData;
import com.whenrepay.rnd.whenrepay.Transactions.IOUDialog;
import com.whenrepay.rnd.whenrepay.Transactions.TransactionFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import io.realm.Realm;

public class OverDueActivity extends AppCompatActivity {

    public static final String EXTRA_OVERDUE = "overdue";
    FamiliarRecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    DetailOverDueAdapter mAdapter;

    Realm mRealm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_due);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out_background);
        recyclerView = (FamiliarRecyclerView) findViewById(R.id.recycler);
        mAdapter = new DetailOverDueAdapter();
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        mRealm = Realm.getInstance(this);

        recyclerView.setOnItemClickListener(new FamiliarRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {
//                Toast.makeText(OverDueActivity.this, "" + mAdapter.getItemAtPosition(position).getPrice(),Toast.LENGTH_SHORT).show();
                if (mAdapter.getItemAtPosition(position) instanceof AccountData) {
                    IOUDialog dialog = new IOUDialog();
                    Bundle args = new Bundle();
                    args.putSerializable(IOUDialog.EXTRA_ACCOUNT_DATA, (AccountData) mAdapter.getItemAtPosition(position));
                    dialog.setArguments(args);
                    dialog.show(getSupportFragmentManager(), "dialog");
                } else {
                    Toast.makeText(OverDueActivity.this, "not yet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        initData();

        mAdapter.setOnCheckedListener(new OnItemCheckedListener() {
            @Override
            public void OnItemChecked(boolean isChecked, int position) {
                checkedList[position] = isChecked;
            }
        });
    }

    private void initData() {
        mAdapter.clear();
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
        checkedList = new boolean[mAdapter.getItemCount()];
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

    boolean[] checkedList;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
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
