package com.whenrepay.rnd.whenrepay.Group;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whenrepay.rnd.whenrepay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RND on 2016-06-23.
 */
public class GroupAdapter extends RecyclerView.Adapter{
    List<GroupData> items = new ArrayList<>();
    public void add(GroupData data){
        items.add(data);
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_group,parent,false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GroupViewHolder)holder).setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
