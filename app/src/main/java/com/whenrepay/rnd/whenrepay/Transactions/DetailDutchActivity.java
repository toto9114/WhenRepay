package com.whenrepay.rnd.whenrepay.Transactions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.whenrepay.rnd.whenrepay.DutchPay.DutchPayData;
import com.whenrepay.rnd.whenrepay.R;

public class DetailDutchActivity extends AppCompatActivity {

    public static final String EXTRA_DUTCH_DATA = "dutch";
    DutchPayData dutchPayData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dutch);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out_background);
        dutchPayData = (DutchPayData)getIntent().getSerializableExtra(EXTRA_DUTCH_DATA);

        Toast.makeText(DetailDutchActivity.this, dutchPayData.title , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in_background, R.anim.slide_right_out);
    }
}
