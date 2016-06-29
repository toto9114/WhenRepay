package com.whenrepay.rnd.whenrepay.Transactions;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.R;

import java.text.NumberFormat;

/**
 * Created by RND on 2016-06-16.
 */
public class AddTransViewHolder extends RecyclerView.ViewHolder{

    TextView dateView,transView,remainView;
    public AddTransViewHolder(View itemView) {
        super(itemView);
        dateView = (TextView)itemView.findViewById(R.id.text_date);
        transView =(TextView)itemView.findViewById(R.id.text_trans);
        remainView = (TextView)itemView.findViewById(R.id.text_remain);
    }

    DetailTransData data;
    public void setData(DetailTransData data){
        NumberFormat nf = NumberFormat.getInstance();
        this.data = data;
        dateView.setText(data.date);
        transView.setText(nf.format(data.repay));
        remainView.setText(nf.format(data.remain));
    }
}
