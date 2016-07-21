package com.whenrepay.rnd.whenrepay.Transactions.MoneyTransaction;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.R;

import java.text.NumberFormat;

/**
 * Created by RND on 2016-06-16.
 */
public class SubTransViewHolder extends RecyclerView.ViewHolder {
    TextView dateView,transView,remainView;
    public SubTransViewHolder(View itemView) {
        super(itemView);
        dateView = (TextView)itemView.findViewById(R.id.text_date);
        transView =(TextView)itemView.findViewById(R.id.text_trans);
        remainView = (TextView)itemView.findViewById(R.id.text_remain);
    }

    DetailTransData data;
    public void setData(DetailTransData data){
        this.data = data;
        NumberFormat nf = NumberFormat.getInstance();
        dateView.setText(data.date);
        transView.setText(nf.format(data.repay));
        remainView.setText(nf.format(data.remain));
    }
}
