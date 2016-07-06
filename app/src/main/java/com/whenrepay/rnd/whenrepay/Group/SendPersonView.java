package com.whenrepay.rnd.whenrepay.Group;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.R;

/**
 * Created by RND on 2016-07-05.
 */
public class SendPersonView extends FrameLayout {
    TextView nameView, moneyView;
    public SendPersonView(Context context) {
        super(context);
        inflate(getContext(), R.layout.view_send_person, this);
        nameView = (TextView)findViewById(R.id.text_name);
        moneyView = (TextView)findViewById(R.id.text_money);

    }

    public void setData(PersonData data){
        nameView.setText(data.getName());
        moneyView.setText(""+data.getMoney());
    }
}
