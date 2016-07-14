package com.whenrepay.rnd.whenrepay.Main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.R;

/**
 * Created by RND on 2016-07-14.
 */
public class ContractHeaderView extends RecyclerView.ViewHolder {
    TextView contractView;
    public ContractHeaderView(View itemView) {
        super(itemView);
        contractView = (TextView)itemView.findViewById(R.id.text_contract);
        Button btn = (Button)itemView.findViewById(R.id.btn_complete);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setContractView(int count){

    }
}
