package com.whenrepay.rnd.whenrepay.Transactions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whenrepay.rnd.whenrepay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RND on 2016-07-19.
 */
public class NotifyAdapter extends RecyclerView.Adapter {
    List<DunData> items = new ArrayList<>();

    public void add(DunData data){
        items.add(data);
        notifyDataSetChanged();
    }
    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_notify,parent,false);
        return new NotifyView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((NotifyView)holder).setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
