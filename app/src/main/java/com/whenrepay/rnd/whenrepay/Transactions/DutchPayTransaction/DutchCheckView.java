package com.whenrepay.rnd.whenrepay.Transactions.DutchPayTransaction;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.Group.OnItemCheckedListener;
import com.whenrepay.rnd.whenrepay.Group.PersonData;
import com.whenrepay.rnd.whenrepay.R;

import java.text.NumberFormat;

/**
 * Created by RND on 2016-07-22.
 */
public class DutchCheckView extends RecyclerView.ViewHolder {
    TextView nameView, moneyView;
    CheckBox checkBox;

    public OnItemCheckedListener itemCheckedListener;
    public void setOnItemCheckedListener(OnItemCheckedListener listener){
        itemCheckedListener = listener;
    }
    public DutchCheckView(View itemView) {
        super(itemView);
        nameView = (TextView)itemView.findViewById(R.id.text_name);
        moneyView = (TextView)itemView.findViewById(R.id.text_money);
        checkBox = (CheckBox)itemView.findViewById(R.id.checkBox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isCompleted) {
                    checkBox.setChecked(true);
                }
                if(itemCheckedListener!=null){
                    itemCheckedListener.OnItemChecked(isChecked,getAdapterPosition());
                }
            }
        });
    }

    PersonData data;
    boolean isCompleted = false;
    public void setData(PersonData data, boolean isCompleted){
        this.data = data;
        this.isCompleted = isCompleted;
        NumberFormat nf = NumberFormat.getInstance();
        nameView.setText(data.getName());
        moneyView.setText(nf.format(data.getMoney()));
        checkBox.setChecked(data.isPay());
    }
}
