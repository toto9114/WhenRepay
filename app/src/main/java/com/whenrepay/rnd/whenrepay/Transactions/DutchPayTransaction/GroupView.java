package com.whenrepay.rnd.whenrepay.Transactions.DutchPayTransaction;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.DutchPay.EventData;
import com.whenrepay.rnd.whenrepay.R;

/**
 * Created by RND on 2016-07-22.
 */
public class GroupView extends FrameLayout {
    TextView titleView, moneyView;

    public GroupView(Context context) {
        super(context);
        inflate(getContext(), R.layout.view_dutch_group, this);
        titleView = (TextView)findViewById(R.id.text_title);
        moneyView = (TextView)findViewById(R.id.text_money);
    }
    public void setData(EventData data){
        titleView.setText(data.title);
        moneyView.setText(""+data.money);
    }
}
