package com.whenrepay.rnd.whenrepay.Transactions.DutchPayTransaction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.whenrepay.rnd.whenrepay.DutchPay.DutchPayData;
import com.whenrepay.rnd.whenrepay.Group.PersonData;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailDutchActivity extends AppCompatActivity {

    public static final String EXTRA_DUTCH_DATA = "dutch";
    DutchPayData dutchPayData;

    TextView titleView, categoryView, dateView, moneyView;
    ViewSwitcher completeSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dutch);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out_background);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dutchPayData = (DutchPayData) getIntent().getSerializableExtra(EXTRA_DUTCH_DATA);

        titleView = (TextView) findViewById(R.id.text_title);
        categoryView = (TextView) findViewById(R.id.text_category);
        dateView = (TextView) findViewById(R.id.text_date);
        moneyView = (TextView) findViewById(R.id.text_money);
        completeSwitcher = (ViewSwitcher) findViewById(R.id.switcher_complete);

        if (savedInstanceState == null) {
            DutchPersonListFragment f = new DutchPersonListFragment();
            Bundle args = new Bundle();
            args.putSerializable(DutchPersonListFragment.EXTRA_DUTCH_DATA, dutchPayData);
            f.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, f)
                    .commit();
        }

        Button btn = (Button) findViewById(R.id.btn_person_list);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePersonList();
            }
        });

        btn = (Button) findViewById(R.id.btn_info);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeInfo();
            }
        });

        btn = (Button) findViewById(R.id.btn_complete);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                completeSwitcher.showNext();
                dutchPayData.isCompleted = true;
                DataManager.getInstance().updateDutchPay(dutchPayData);
                //체크리스트 모두 체크
                for(PersonData data : DataManager.getInstance().getDutchPersonList(dutchPayData._id)){
                    data.setIsPay(true);
                    DataManager.getInstance().updateDutchPayPerson(dutchPayData._id,data);
                }
                changePersonList();
            }
        });

        init();
    }


    private void init() {
        if(dutchPayData.isCompleted){
            completeSwitcher.showNext();
        }
        titleView.setText(dutchPayData.title);
        categoryView.setText("더치페이");
        SimpleDateFormat sdf = new SimpleDateFormat("MM월 dd일");
        dateView.setText(sdf.format(new Date(dutchPayData.date)));
        moneyView.setText(""+dutchPayData.totalPrice);
    }

    public void allCheckedPersonList(){
        dutchPayData.isCompleted = true;
        DataManager.getInstance().updateDutchPay(dutchPayData);
        init();
        changePersonList();
    }
    public void changePersonList() {
        DutchPersonListFragment f = new DutchPersonListFragment();
        Bundle args = new Bundle();
        args.putSerializable(DutchPersonListFragment.EXTRA_DUTCH_DATA, dutchPayData);
        f.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, f)
                .commit();
    }

    public void changeInfo() {
        DutchInfoFragment f = new DutchInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(DutchInfoFragment.EXTRA_DUTCH_DATA, dutchPayData);
        f.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, f)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
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
