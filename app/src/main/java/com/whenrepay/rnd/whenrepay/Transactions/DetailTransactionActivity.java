package com.whenrepay.rnd.whenrepay.Transactions;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.whenrepay.rnd.whenrepay.AccountData;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;

public class DetailTransactionActivity extends AppCompatActivity implements TransactionDialog.OnEditButtonClickListener {

    public static final String EXTRA_ACCOUNT_DATA = "account";

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
        accountData = (AccountData) i.getSerializableExtra(EXTRA_ACCOUNT_DATA); //리스트에서 뽑아온 AccountData
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

        initData(accountData);
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
                Intent i =new Intent(DetailTransactionActivity.this, IOUActivity.class);
                i.putExtra(IOUActivity.EXTRA_ACCOUNT_DATA,accountData);
                startActivity(i);

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
                if (remainPrice > 0) {
                    Bundle args = new Bundle();
                    args.putInt(TransactionDialog.EXTRA_TYPE, TYPE_SUB);
                    dialog.setArguments(args);
                    dialog.show(getSupportFragmentManager(), null);
                } else {
                    Toast.makeText(DetailTransactionActivity.this, "이미 상환되었습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn = (Button) findViewById(R.id.btn_repay_all);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (remainPrice > 0) {
                    complete();
                } else {
                    Toast.makeText(DetailTransactionActivity.this, "이미 상환되었습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initData(AccountData accountData) {
        DetailTransData data = new DetailTransData();
        data.type = TYPE_ADD;
        data.repay = accountData.money;
        data.remain = accountData.money;
        data.date = accountData.date;
        mAdapter.add(data);

        if (DataManager.getInstance().getTransactionList(accountData._id).size() > 0) {
            mAdapter.addAll(DataManager.getInstance().getTransactionList(accountData._id));
            remainPrice = mAdapter.getLastItem().remain;
        } else {
            remainPrice = accountData.money;
        }
        NumberFormat nf= NumberFormat.getInstance();
        totalView.setText(nf.format(accountData.money));
        nameView.setText(accountData.name);
        dateView.setText(accountData.date);
    }

    public Bitmap byteArrayToBitmap(byte[] $byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray($byteArray, 0, $byteArray.length);
        return bitmap;
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
        if (DataManager.getInstance().getTransactionList(accountData._id).size() > 0) {
            remainPrice = mAdapter.getLastItem().remain;
        }
        data.type = TYPE_SUB;
        data.repay = price;
        if (remainPrice - price >= 0) {
            remainPrice = remainPrice - price;
            data.remain = remainPrice;
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일");
            data.date = sdf.format(date);
            DataManager.getInstance().insertTransaction(accountData._id, data.repay, data.remain, data.type, data.date);
            mAdapter.add(data);
            if (remainPrice == 0) {
                complete();
            }
        } else {
            Toast.makeText(this, "전액 상환되었습니다", Toast.LENGTH_SHORT).show();
        }
    }

    public void complete() {
        DetailTransData data = new DetailTransData();
        if (DataManager.getInstance().getTransactionList(accountData._id).size() == 0) { //부분상환된게 없다면
            data.repay = accountData.money;
        } else {
            data.repay = mAdapter.getLastItem().remain;
        }
        data.type = TYPE_COMPLETE;
        data.remain = 0;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일");
        data.date = sdf.format(date);
        DataManager.getInstance().insertTransaction(accountData._id, data.repay, data.remain, data.type, data.date);
        mAdapter.add(data);
    }
}
