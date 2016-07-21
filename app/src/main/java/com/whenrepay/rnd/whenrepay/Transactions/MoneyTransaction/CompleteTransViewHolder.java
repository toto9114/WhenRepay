package com.whenrepay.rnd.whenrepay.Transactions.MoneyTransaction;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.R;

/**
 * Created by RND on 2016-06-16.
 */
public class CompleteTransViewHolder extends RecyclerView.ViewHolder {
    TextView dateView, remainView;
    public CompleteTransViewHolder(View itemView) {
        super(itemView);
        dateView = (TextView)itemView.findViewById(R.id.text_date);
        remainView = (TextView)itemView.findViewById(R.id.text_remain);
    }
    DetailTransData data;
    public void setData(DetailTransData data){
        this.data = data;
        dateView.setText(data.date);
        remainView.setText(""+data.remain);
    }
}
