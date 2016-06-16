package com.whenrepay.rnd.whenrepay.Transactions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RND on 2016-06-16.
 */
public class DetailTransactionAdapter extends RecyclerView.Adapter {
    List<DetailTransData> items = new ArrayList<>();

    public void add(DetailTransData data) {
        items.add(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        DetailTransData data = items.get(position);
        switch (data.type) {
            case DataManager.TRANSACTION_ADD:
                return DetailTransactionActivity.TYPE_ADD;

            case DataManager.TRANSACTION_SUB:
                return DetailTransactionActivity.TYPE_SUB;

            case DataManager.TRANSACTION_COMPLETE:
                return DetailTransactionActivity.TYPE_COMPLETE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = null;
        switch (viewType) {
            case DataManager.TRANSACTION_ADD:
                view = inflater.inflate(R.layout.view_detail_trans_add, parent, false);
                AddTransViewHolder addTransViewHolder = new AddTransViewHolder(view);
                return addTransViewHolder;
            case DataManager.TRANSACTION_SUB:
                view = inflater.inflate(R.layout.view_detail_trans_sub, parent, false);
                SubTransViewHolder subTransViewHolder = new SubTransViewHolder(view);
                return subTransViewHolder;
            case DataManager.TRANSACTION_COMPLETE:
                view = inflater.inflate(R.layout.view_detail_trans_complete, parent, false);
                CompleteTransViewHolder completeTransViewHolder = new CompleteTransViewHolder(view);
                return completeTransViewHolder;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DetailTransData data = items.get(position);
        switch (data.type) {
            case DataManager.TRANSACTION_ADD:
                ((AddTransViewHolder) holder).setData(data);
                break;
            case DataManager.TRANSACTION_SUB:
                ((SubTransViewHolder) holder).setData(data);
                break;
            case DataManager.TRANSACTION_COMPLETE:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
