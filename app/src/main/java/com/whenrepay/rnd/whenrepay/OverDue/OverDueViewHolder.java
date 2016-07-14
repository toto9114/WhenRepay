package com.whenrepay.rnd.whenrepay.OverDue;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.BorrowMoney.AccountData;
import com.whenrepay.rnd.whenrepay.BorrowThings.ThingsData;
import com.whenrepay.rnd.whenrepay.DutchPay.DutchPayData;
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
    TextView titleView, descView, moneyView;

    int year, month, day;
    Calendar today = Calendar.getInstance();

    public OverDueViewHolder(View itemView) {
        super(itemView);
        titleView = (TextView) itemView.findViewById(R.id.text_title);
        descView = (TextView) itemView.findViewById(R.id.text_desc);
        moneyView = (TextView) itemView.findViewById(R.id.text_money);

    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");

    public void setData(TransactionData data) {
        NumberFormat nf = NumberFormat.getInstance();
        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);
        day = today.get(Calendar.DAY_OF_MONTH);
        long diff = 0;

        if (data instanceof AccountData) {
            AccountData accountData = (AccountData) data;
            titleView.setText(accountData.name);
            moneyView.setText(nf.format(accountData.money));
            try {
                Date date = sdf.parse(accountData.repayDate);
                Calendar c = Calendar.getInstance();
                c.setTime(date);
            long b = (today.getTimeInMillis() - c.getTimeInMillis()) / 1000;
            diff =  b / (60 * 60 * 24);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
            Date date = new Date(accountData.date);
            descView.setText(sdf.format(date) + "/" + "상환일 " + diff + "일 지남");
        } else if (data instanceof ThingsData) {
            ThingsData thingsData = (ThingsData) data;
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
            Date date = new Date(thingsData.date);
            descView.setText(sdf.format(date) + "/" + "상환일 " + diff + "일 지남");
        } else {
            DutchPayData dutchPayData = (DutchPayData) data;
            titleView.setText(dutchPayData.title);
            moneyView.setText(nf.format(dutchPayData.totalPrice));
            try {
                Date date = sdf.parse(dutchPayData.getRepayDate());
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                long b = (today.getTimeInMillis() - c.getTimeInMillis()) / 1000;
                diff =  b / (60 * 60 * 24);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
            Date date = new Date(dutchPayData.date);
            descView.setText(sdf.format(date));
        }
    }
}
