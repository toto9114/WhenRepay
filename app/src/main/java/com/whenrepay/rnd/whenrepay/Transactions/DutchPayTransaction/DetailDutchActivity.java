package com.whenrepay.rnd.whenrepay.Transactions.DutchPayTransaction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.whenrepay.rnd.whenrepay.DutchPay.DutchPayData;
import com.whenrepay.rnd.whenrepay.R;

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
        dutchPayData = (DutchPayData)getIntent().getSerializableExtra(EXTRA_DUTCH_DATA);

        titleView = (TextView)findViewById(R.id.text_title);
        categoryView = (TextView)findViewById(R.id.text_category);
        dateView = (TextView)findViewById(R.id.text_date);
        moneyView = (TextView)findViewById(R.id.text_money);
        completeSwitcher = (ViewSwitcher)findViewById(R.id.switcher_complete);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
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
