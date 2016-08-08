package com.whenrepay.rnd.whenrepay.OverDue;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.BorrowMoney.AccountData;
import com.whenrepay.rnd.whenrepay.BorrowThings.ThingsData;
import com.whenrepay.rnd.whenrepay.DutchPay.DutchPayData;
import com.whenrepay.rnd.whenrepay.Group.OnItemCheckedListener;
import com.whenrepay.rnd.whenrepay.R;
import com.whenrepay.rnd.whenrepay.TransactionData;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by RND on 2016-07-12.
 */
public class OverDueViewHolder extends RecyclerView.ViewHolder {
    public OnItemCheckedListener itemCheckedListener;
    public void setOnCheckedListener(OnItemCheckedListener listener){
        itemCheckedListener = listener;
    }

    TextView titleView, descView, moneyView,categoryView;
    int year, month, day;
    Calendar today = Calendar.getInstance();
    CheckBox checkBox;
    public OverDueViewHolder(View itemView) {
        super(itemView);
        titleView = (TextView) itemView.findViewById(R.id.text_title);
        descView = (TextView) itemView.findViewById(R.id.text_date);
        moneyView = (TextView) itemView.findViewById(R.id.text_money);
        categoryView = (TextView)itemView.findViewById(R.id.text_category);
        checkBox = (CheckBox)itemView.findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(itemCheckedListener != null){
                    itemCheckedListener.OnItemChecked(isChecked,getAdapterPosition());
                }
            }
        });
    }

    SimpleDateFormat sdf = new SimpleDateFormat("M월 d일");

    public void setData(TransactionData data,boolean isCheckBoxVisible) {
        checkBox.setChecked(false);
        if(isCheckBoxVisible){
            checkBox.setVisibility(View.VISIBLE);
        }else{
            checkBox.setVisibility(View.GONE);
        }
        NumberFormat nf = NumberFormat.getInstance();
        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);
        day = today.get(Calendar.DAY_OF_MONTH);
        long diff = 0;

        if (data instanceof AccountData) {
            AccountData accountData = (AccountData) data;
            categoryView.setText(R.string.category_money);
            titleView.setText(accountData.name);
            moneyView.setText(nf.format(accountData.money)+"원");
            try {
                Date date = sdf.parse(accountData.repayDate);
                Calendar c = Calendar.getInstance();
                c.setTime(date);
            long b = (today.getTimeInMillis() - c.getTimeInMillis()) / 1000;
            diff =  b / (60 * 60 * 24);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date date = new Date(accountData.date);
            descView.setText("상환예정일 "+sdf.format(date) + "/" + "상환일 " + diff + "일 지남");
        } else if (data instanceof ThingsData) {
            ThingsData thingsData = (ThingsData) data;
            categoryView.setText(R.string.category_things);
            titleView.setText(thingsData.borrowerName);
            moneyView.setText(thingsData.thingsName);
            try {
                Date date = sdf.parse(thingsData.repayDate);
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                long b = (today.getTimeInMillis() - c.getTimeInMillis()) / 1000;
                diff =  b / (60 * 60 * 24);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date date = new Date(thingsData.date);
            descView.setText("상환예정일 "+sdf.format(date) + "/" + "상환일 " + diff + "일 지남");
        } else {
            DutchPayData dutchPayData = (DutchPayData) data;
            categoryView.setText(R.string.category_dutch);
            titleView.setText(dutchPayData.title);
            moneyView.setText(nf.format(dutchPayData.totalPrice)+"원");
//            try {
//                Date date = sdf.parse(dutchPayData.getRepayDate());
//                Calendar c = Calendar.getInstance();
//                c.setTime(date);
                long b = (today.getTimeInMillis() - dutchPayData.date) / 1000;
                diff =  b / (60 * 60 * 24);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
            Date date = new Date(dutchPayData.date);
            descView.setText(sdf.format(date));
        }
    }
}
