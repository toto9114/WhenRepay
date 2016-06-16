package com.whenrepay.rnd.whenrepay.Transactions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.AccountData;
import com.whenrepay.rnd.whenrepay.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;

public class DetailTransactionActivity extends AppCompatActivity {

    public static final String EXTRA_ACCOUNT_DATA = "account";
    TextView totalView, nameView, dateView;
    FamiliarRecyclerView recyclerView;
    DetailTransactionAdapter mAdapter;

    int remainPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaction);

        Intent i = getIntent();
        AccountData accountData = (AccountData) i.getSerializableExtra(EXTRA_ACCOUNT_DATA);
        totalView = (TextView) findViewById(R.id.text_total);
        nameView = (TextView) findViewById(R.id.text_name);
        dateView = (TextView) findViewById(R.id.text_date);
        recyclerView = (FamiliarRecyclerView) findViewById(R.id.recycler);
        mAdapter = new DetailTransactionAdapter();
        recyclerView.setAdapter(mAdapter);
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
                subTrans();
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
        totalView.setText(accountData.money);
        nameView.setText(accountData.name);
        dateView.setText(accountData.date);
        remainPrice = Integer.parseInt(totalView.getText().toString());
    }

    public static final int TYPE_ADD = 0;
    public static final int TYPE_SUB = 1;
    public static final int TYPE_COMPLETE = 2;

    public void addTrans() {
        DetailTransData data = new DetailTransData();
        data.type = TYPE_ADD;
    }

    public void subTrans() {
        DetailTransData data = new DetailTransData();
        data.type = TYPE_SUB;
        data.repay = 10000;
        if (remainPrice - data.repay >= 0) {
            remainPrice = remainPrice - data.repay;
            data.remain = remainPrice;
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일");
        data.date = sdf.format(date);
        mAdapter.add(data);
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
