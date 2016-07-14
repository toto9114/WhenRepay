package com.whenrepay.rnd.whenrepay.Main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.R;

/**
 * Created by RND on 2016-07-14.
 */
public class OverDueHeaderView extends RecyclerView.ViewHolder {
    TextView overDueView;
    public OverDueHeaderView(View itemView) {
        super(itemView);
        overDueView = (TextView)itemView.findViewById(R.id.text_overdue);
        Button btn = (Button)itemView.findViewById(R.id.btn_dun);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setOverDueView(int count){

    }
}
