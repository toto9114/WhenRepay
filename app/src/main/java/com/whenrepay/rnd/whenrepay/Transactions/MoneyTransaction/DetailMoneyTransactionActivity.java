package com.whenrepay.rnd.whenrepay.Transactions.MoneyTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.whenrepay.rnd.whenrepay.BorrowMoney.AccountData;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;
import com.whenrepay.rnd.whenrepay.Transactions.IOUActivity;
import com.whenrepay.rnd.whenrepay.Transactions.NotifyAdapter;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import io.realm.Realm;

public class DetailMoneyTransactionActivity extends AppCompatActivity implements TransactionDialog.OnEditButtonClickListener {

    public static final String EXTRA_ACCOUNT_DATA = "account";

    private static final String TRANS_FRAGMENT = "trans";
    private static final String NOTIFY_FRAGMENT = "notify";

    TextView totalView, nameView, dateView;

    int remainPrice;
    TransactionDialog dialog;
    AccountData accountData;
    FamiliarRecyclerView recyclerView;
    DetailTransactionAdapter transAdapter;
    NotifyAdapter notifyAdapter;
    LinearLayoutManager layoutManager;
    LinearLayout btnLayout;
    Realm mRealm;
    TextView completeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaction);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out_background);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        accountData = (AccountData) i.getSerializableExtra(EXTRA_ACCOUNT_DATA); //리스트에서 뽑아온 AccountData
        totalView = (TextView) findViewById(R.id.text_total);
        nameView = (TextView) findViewById(R.id.text_name);
        dateView = (TextView) findViewById(R.id.text_date);
        btnLayout = (LinearLayout) findViewById(R.id.linear_btn);
        completeView = (TextView) findViewById(R.id.text_complete);
//        recyclerView = (FamiliarRecyclerView) findViewById(R.id.recycler);
//        transAdapter = new DetailTransactionAdapter();
//        notifyAdapter = new NotifyAdapter();
//        layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
//        layoutManager.setReverseLayout(true);
//        layoutManager.setStackFromEnd(true);
//        recyclerView.setLayoutManager(layoutManager);
//        //recyclerView.setAdapter(transAdapter);
//        recyclerView.setItemAnimator(new FadeInDownAnimator());
//
//        mRealm = Realm.getInstance(this);
//
        dialog = new TransactionDialog();
//
//        initData(accountData);
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
                Intent i = new Intent(DetailMoneyTransactionActivity.this, IOUActivity.class);
                i.putExtra(IOUActivity.EXTRA_ACCOUNT_DATA, accountData);
                startActivity(i);
            }
        });

        btn = (Button) findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (remainPrice > 0) {
                    Bundle args = new Bundle();
                    args.putInt(TransactionDialog.EXTRA_TYPE, TYPE_ADD);
                    dialog.setArguments(args);
                    dialog.show(getSupportFragmentManager(), null);
                } else {
                    Toast.makeText(DetailMoneyTransactionActivity.this, "이미 상환되었습니다", Toast.LENGTH_SHORT).show();
                }
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
                    Toast.makeText(DetailMoneyTransactionActivity.this, "이미 상환되었습니다", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(DetailMoneyTransactionActivity.this, "이미 상환되었습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btn = (Button) findViewById(R.id.btn_detail_contract);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDetailTrans();
            }
        });

        btn = (Button) findViewById(R.id.btn_notify);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNotify();
            }
        });
        if (savedInstanceState == null) {
            DetailMoneyTransFragment f = new DetailMoneyTransFragment();
            Bundle args = new Bundle();
            args.putSerializable(DetailMoneyTransFragment.EXTRA_ACCOUNT_DATA, accountData);
            f.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, f, TRANS_FRAGMENT)
                    .commit();
        }
        initData(accountData);
    }

    @Override
    public void onButtonClick(int type, int price) {  //금액 입력 다이얼로그로부터 받는 값
        switch (type) {
            case TYPE_ADD:
                addTrans(price);
                break;
            case TYPE_SUB:
                subTrans(price);
                break;
        }
    }

    public static final int TYPE_ADD = 0;   //금액추가
    public static final int TYPE_SUB = 1;   //금액차감
    public static final int TYPE_COMPLETE = 2;  //완전상환

    private void initData(AccountData accountData) {
        if (accountData.isCompleted) {
            btnLayout.setVisibility(View.GONE);
            completeView.setVisibility(View.VISIBLE);
        }
//        DetailTransData data = new DetailTransData();
//        data.type = TYPE_ADD;
//        data.repay = accountData.money;
//        data.remain = accountData.money;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        Date date = new Date(accountData.date);
//        data.date = sdf.format(date);
//        transAdapter.add(data);
//
        if (DataManager.getInstance().getTransactionList(accountData._id).size() > 0) {
//            transAdapter.addAll(DataManager.getInstance().getTransactionList(accountData._id));
            remainPrice = DataManager.getInstance().getTransactionList(accountData._id).
                    get(DataManager.getInstance().getTransactionList(accountData._id).size() - 1).remain;
        } else {
            remainPrice = accountData.money;
        }
        NumberFormat nf = NumberFormat.getInstance();
        totalView.setText(nf.format(accountData.money));
        nameView.setText(accountData.name);
        dateView.setText(sdf.format(date));
    }

    public void addTrans(int price) {
        DetailTransData data = new DetailTransData();
        if (DataManager.getInstance().getTransactionList(accountData._id).size() > 0) {
            remainPrice = DataManager.getInstance().getTransactionList(accountData._id).
                    get(DataManager.getInstance().getTransactionList(accountData._id).size() - 1).remain;
        }
        data.type = TYPE_ADD;
        data.repay = price;
        data.remain = remainPrice + price;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일");
        data.date = sdf.format(date);
        DataManager.getInstance().insertTransaction(accountData._id, data.repay, data.remain, data.type, data.date);
        if (getSupportFragmentManager().findFragmentByTag(TRANS_FRAGMENT).isVisible()) {
            changeDetailTrans();
        }
    }

    public void subTrans(int price) {
        DetailTransData data = new DetailTransData();
        if (DataManager.getInstance().getTransactionList(accountData._id).size() > 0) {
            remainPrice = DataManager.getInstance().getTransactionList(accountData._id).
                    get(DataManager.getInstance().getTransactionList(accountData._id).size() - 1).remain;
        }
        data.type = TYPE_SUB;
        data.repay = price;
        int subMoney = remainPrice - price;
        if (subMoney >= 0) {
            if (subMoney == 0) {
                complete();
                return;
            }
            remainPrice = remainPrice - price;
            data.remain = remainPrice;
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일");
            data.date = sdf.format(date);
            DataManager.getInstance().insertTransaction(accountData._id, data.repay, data.remain, data.type, data.date);
        } else {
            Toast.makeText(this, "잔여금보다 액수가 많습니다", Toast.LENGTH_SHORT).show();
        }
        if (getSupportFragmentManager().findFragmentByTag(TRANS_FRAGMENT).isVisible()) {
            changeDetailTrans();
        }
    }

    public void complete() {
        btnLayout.setVisibility(View.GONE);
        completeView.setVisibility(View.VISIBLE);
        DetailTransData data = new DetailTransData();
        if (DataManager.getInstance().getTransactionList(accountData._id).size() == 0) { //부분상환된게 없다면
            data.repay = accountData.money;
        } else {
            data.repay = DataManager.getInstance().getTransactionList(accountData._id).
                    get(DataManager.getInstance().getTransactionList(accountData._id).size() - 1).remain;
        }
        accountData.isCompleted = true;
        data.type = TYPE_SUB;
        data.remain = 0;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일");
        data.date = sdf.format(date);
        DataManager.getInstance().insertTransaction(accountData._id, data.repay, data.remain, data.type, data.date);
        DataManager.getInstance().updateContract(accountData);
        if (getSupportFragmentManager().findFragmentByTag(TRANS_FRAGMENT).isVisible()) {
            changeDetailTrans();
        }
//        transAdapter.add(data);
    }

    public void changeDetailTrans() {
        Bundle args = new Bundle();
        DetailMoneyTransFragment f = new DetailMoneyTransFragment();
        args.putSerializable(DetailMoneyTransFragment.EXTRA_ACCOUNT_DATA, accountData);
        f.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, f, TRANS_FRAGMENT)
                .commit();
    }

    public void changeNotify() {
        MoneyNotifyfragment f = new MoneyNotifyfragment();
        Bundle args = new Bundle();
        args.putSerializable(MoneyNotifyfragment.EXTRA_ACCOUNT_DATA, accountData);
        f.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, f, NOTIFY_FRAGMENT)
                .commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_send_iou,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        if(id == R.id.send_iou){
            Intent i = new Intent(DetailMoneyTransactionActivity.this, IOUActivity.class);
            i.putExtra(IOUActivity.EXTRA_ACCOUNT_DATA, accountData);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in_background, R.anim.slide_right_out);
    }
}
