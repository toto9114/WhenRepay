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
public class GroupAdapter extends RecyclerView.Adapter implements OnItemCheckedListener{
    List<GroupData> items = new ArrayList<>();
    public void add(GroupData data){
        items.add(data);
        notifyDataSetChanged();
    }

    public void addAll(List<GroupData> list){
        items.addAll(list);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    public GroupData getItem(int position){
        return items.get(position);
    }
    boolean mCheckBoxStable = false;
    public void setCheckBoxVisible(boolean isVisible){
        mCheckBoxStable =isVisible;
        notifyDataSetChanged();
    }


    OnItemCheckedListener itemCheckedListener;
    public void setOnItemCheckedListener(OnItemCheckedListener listener){
        itemCheckedListener = listener;
    }

    @Override
    public void OnItemChecked(boolean isChecked, int position) {
        if(itemCheckedListener!=null){
            itemCheckedListener.OnItemChecked(isChecked,position);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_group,parent,false);
        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GroupViewHolder)holder).setData(items.get(position),mCheckBoxStable);
        ((GroupViewHolder)holder).setOnItemCheckedListener(this);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
