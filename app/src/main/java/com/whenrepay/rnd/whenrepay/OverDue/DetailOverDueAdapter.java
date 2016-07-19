package com.whenrepay.rnd.whenrepay.OverDue;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whenrepay.rnd.whenrepay.Group.OnItemCheckedListener;
import com.whenrepay.rnd.whenrepay.R;
import com.whenrepay.rnd.whenrepay.TransactionData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RND on 2016-07-12.
 */
public class DetailOverDueAdapter extends RecyclerView.Adapter implements OnItemCheckedListener{
    List<TransactionData> items = new ArrayList<>();

    public void add(TransactionData data){
        items.add(data);
        notifyDataSetChanged();
    }
    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }
    boolean mCheckBoxStable = false;

    public void setCheckBoxVisible(boolean isVisible) {
        mCheckBoxStable = isVisible;
        notifyDataSetChanged();
    }
    public TransactionData getItemAtPosition(int position){
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
        ((OverDueViewHolder)holder).setData(items.get(position),mCheckBoxStable);
        ((OverDueViewHolder)holder).setOnCheckedListener(this);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    private OnItemCheckedListener itemCheckedListener;

    public void setOnCheckedListener(OnItemCheckedListener listener) {
        itemCheckedListener = listener;
    }

    @Override
    public void OnItemChecked(boolean isChecked, int position) {
        if (itemCheckedListener != null) {
            itemCheckedListener.OnItemChecked(isChecked, position);
        }
    }
}
