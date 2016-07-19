package com.whenrepay.rnd.whenrepay.Transactions;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.R;

/**
 * Created by RND on 2016-07-19.
 */
public class NotifyView extends RecyclerView.ViewHolder{
    TextView dateView, messageView, descView;
    public NotifyView(View itemView) {
        super(itemView);
        dateView = (TextView)itemView.findViewById(R.id.text_date);
        messageView = (TextView)itemView.findViewById(R.id.text_message);
        descView = (TextView)itemView.findViewById(R.id.text_desc);
    }

    public void setData(DunData data){

    }
}
