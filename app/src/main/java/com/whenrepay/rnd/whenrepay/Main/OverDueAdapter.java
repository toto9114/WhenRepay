package com.whenrepay.rnd.whenrepay.Main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whenrepay.rnd.whenrepay.BorrowMoney.AccountData;
import com.whenrepay.rnd.whenrepay.BorrowThings.ThingsData;
import com.whenrepay.rnd.whenrepay.DutchPay.DutchPayData;
import com.whenrepay.rnd.whenrepay.R;
import com.whenrepay.rnd.whenrepay.TransactionData;
import com.whenrepay.rnd.whenrepay.Transactions.TransactionViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by RND on 2016-07-04.
 */
public class OverDueAdapter extends RecyclerView.Adapter {
    List<TransactionData> items = new ArrayList<>();


    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }
    public void add(TransactionData data) {
        items.add(data);
        notifyDataSetChanged();
    }

    public TransactionData getItemAtPosition(int position){
        if (position < items.size()) {
            return items.get(position);
        }
        return null;
    }
    public void sort() {
        Collections.sort(items, new Comparator<TransactionData>() {
            @Override
            public int compare(TransactionData item1, TransactionData item2) {
                return item1.getDate() -item2.getDate()>0 ? 1:-1;
            }
        });
        notifyDataSetChanged();
    }

    public TransactionData getItem(int position){
        return items.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_contract, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (items.get(position) instanceof AccountData) {
            AccountData data = (AccountData) items.get(position);
            ((TransactionViewHolder) holder).setAddressData(data);
        } else if(items.get(position) instanceof ThingsData){
            ThingsData data = (ThingsData) items.get(position);
            ((TransactionViewHolder) holder).setAddressData(data);
        }else{
            DutchPayData data = (DutchPayData) items.get(position);
            ((TransactionViewHolder) holder).setAddressData(data);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
