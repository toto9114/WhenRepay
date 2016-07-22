package com.whenrepay.rnd.whenrepay.Transactions.DutchPayTransaction;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whenrepay.rnd.whenrepay.Group.OnItemCheckedListener;
import com.whenrepay.rnd.whenrepay.Group.PersonData;
import com.whenrepay.rnd.whenrepay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RND on 2016-07-21.
 */
public class DutchPersonAdapter extends RecyclerView.Adapter implements OnItemCheckedListener{
    List<PersonData> items = new ArrayList<>();

    public void add(PersonData data){
        items.add(data);
        notifyDataSetChanged();
    }

    public void addAll(List<PersonData> list){
        items.addAll(list);
        notifyDataSetChanged();
    }
    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    public PersonData getItemAtPosition(int position){
        return items.get(position);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_dutch_check,parent,false);

        return new DutchCheckView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DutchCheckView)holder).setData(items.get(position));
        ((DutchCheckView)holder).setOnItemCheckedListener(this);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public OnItemCheckedListener itemCheckedListener;
    public void setOnItemCheckedListener(OnItemCheckedListener listener){
        itemCheckedListener = listener;
    }
    @Override
    public void OnItemChecked(boolean isChecked, int position) {
        if(itemCheckedListener!=null){
            itemCheckedListener.OnItemChecked(isChecked,position);
        }
    }
}
