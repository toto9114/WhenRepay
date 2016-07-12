package com.whenrepay.rnd.whenrepay.OverDue;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whenrepay.rnd.whenrepay.R;
import com.whenrepay.rnd.whenrepay.TransactionData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RND on 2016-07-12.
 */
public class DetailOverDueAdapter extends RecyclerView.Adapter {
    List<TransactionData> items = new ArrayList<>();

    public void add(TransactionData data){
        items.add(data);
        notifyDataSetChanged();
    }
    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    public TransactionData getItem(int position){
        return items.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_overdue, parent, false);

        return new OverDueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((OverDueViewHolder)holder).setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
