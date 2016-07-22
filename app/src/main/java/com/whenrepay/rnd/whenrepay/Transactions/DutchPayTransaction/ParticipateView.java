package com.whenrepay.rnd.whenrepay.Transactions.DutchPayTransaction;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.DutchPay.DutchPersonData;
import com.whenrepay.rnd.whenrepay.R;

import java.util.List;

/**
 * Created by RND on 2016-07-22.
 */
public class ParticipateView extends FrameLayout {
    TextView countView, peopleView;
    public ParticipateView(Context context) {
        super(context);
        inflate(getContext(), R.layout.view_dutch_child, this);
        countView = (TextView)findViewById(R.id.text_count);
        peopleView = (TextView)findViewById(R.id.text_people);
    }
    public void setData(List<DutchPersonData> list){
        countView.setText("참가자("+list.size()+")");
        StringBuilder sb = new StringBuilder();
        for(DutchPersonData data: list) {
            if(data.attended) {
                sb.append(data.name+"/");
            }
        }
        peopleView.setText(sb.toString());
    }
}
