package com.whenrepay.rnd.whenrepay.Transactions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.whenrepay.rnd.whenrepay.AccountData;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;

public class DetailTransactionActivity extends AppCompatActivity implements TransactionDialog.OnEditButtonClickListener {

    public static final String EXTRA_ACCOUNT_DATA = "account";
    public static final String EXTRA_ACCOUNT_ID = "accountid";
    public static final String EXTRA_ACCOUNT_NAME = "name";
    TextView totalView, nameView, dateView;
    FamiliarRecyclerView recyclerView;
    DetailTransactionAdapter mAdapter;
    LinearLayoutManager layoutManager;
    int remainPrice;
    TransactionDialog dialog;
    AccountData accountData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaction);

        Intent i = getIntent();
        accountData = (AccountData) i.getSerializableExtra(EXTRA_ACCOUNT_DATA);
        totalView = (TextView) findViewById(R.id.text_total);
        nameView = (TextView) findViewById(R.id.text_name);
        dateView = (TextView) findViewById(R.id.text_date);
        recyclerView = (FamiliarRecyclerView) findViewById(R.id.recycler);
        mAdapter = new DetailTransactionAdapter();
        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        dialog = new TransactionDialog();
        recyclerView.setItemAnimator(new FadeInDownAnimator());

        Button btn = (Button) findViewById(R.id.btn_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn = (Button) findViewById(R.id.btn_done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn = (Button) findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTrans();
            }
        });
        btn = (Button) findViewById(R.id.btn_sub);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putInt(TransactionDialog.EXTRA_TYPE, TYPE_SUB);
                dialog.setArguments(args);
                dialog.show(getSupportFragmentManager(), null);
            }
        });
        btn = (Button) findViewById(R.id.btn_repay_all);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complete();
            }
        });
        initData(accountData);

    }

    private void initData(AccountData accountData) {
        DetailTransData data = new DetailTransData();
        data.type = TYPE_ADD;
        data.repay = Integer.parseInt(accountData.money);
        data.remain = Integer.parseInt(accountData.money);
        data.date = accountData.date;
        mAdapter.add(data);

        if (DataManager.getInstance().getTransactionList(accountData._id).size() > 0) {
            mAdapter.addAll(DataManager.getInstance().getTransactionList(accountData._id));
        }
        totalView.setText(accountData.money);
        nameView.setText(accountData.name);
        dateView.setText(accountData.date);
        remainPrice = Integer.parseInt(totalView.getText().toString());
    }


    @Override
    public void onButtonClick(int type, int price) {  //금액 입력 다이얼로그로부터 받는 값
        switch (type) {
            case TYPE_ADD:
                addTrans();
                break;
            case TYPE_SUB:
                subTrans(price);
                break;
        }
    }

    public static final int TYPE_ADD = 0;   //금액추가
    public static final int TYPE_SUB = 1;   //금액차감
    public static final int TYPE_COMPLETE = 2;  //완전상환

    public void addTrans() {
        DetailTransData data = new DetailTransData();
        data.type = TYPE_ADD;
//        mAdapter.add(data);
    }

    public void subTrans(int price) {
        DetailTransData data = new DetailTransData();
        data.type = TYPE_SUB;
        data.repay = price;
        if (remainPrice - price >= 0) {
            remainPrice = remainPrice - price;
            data.remain = remainPrice;
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일");
            data.date = sdf.format(date);
            DataManager.getInstance().insertTransaction(accountData._id, data.repay, data.remain, data.type, data.date);
            for (DetailTransData d : DataManager.getInstance().getTransactionList(accountData._id)) {
                Log.i("DetailTrans", "repay: " + d.repay);
            }
            mAdapter.add(data);
        } else {
            Toast.makeText(this, "전액 상환되었습니다", Toast.LENGTH_SHORT).show();
        }
    }

    public void complete() {
        DetailTransData data = new DetailTransData();
        data.type = TYPE_COMPLETE;
        data.remain = 0;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일");
        data.date = sdf.format(date);
        mAdapter.add(data);
    }
}
