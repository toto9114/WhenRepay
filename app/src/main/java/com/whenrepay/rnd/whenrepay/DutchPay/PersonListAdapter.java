package com.whenrepay.rnd.whenrepay.DutchPay;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whenrepay.rnd.whenrepay.BorrowMoney.OnDelButtonClickListener;
import com.whenrepay.rnd.whenrepay.Group.PersonData;
import com.whenrepay.rnd.whenrepay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RND on 2016-06-24.
 */
public class PersonListAdapter extends RecyclerView.Adapter implements OnItemClickListener, OnDelButtonClickListener {
    private static final int HEADER_SIZE = 1;
    List<PersonData> items = new ArrayList<>();

    public void add(PersonData data) {
        items.add(data);
        notifyDataSetChanged();
    }

    public void setData(PersonData data) {
        items.set(items.size() - 1, data);
        notifyDataSetChanged();
    }

    public void deleteData(int position){
        items.remove(position);
        notifyDataSetChanged();
    }

    public PersonData getItem(int position){
        return items.get(position);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_switcher, parent, false);
        return new SwitcherView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SwitcherView) holder).setData(items.get(position));
        ((SwitcherView) holder).setOnItemClickListener(this);
        ((SwitcherView)holder).setOnDelButtonClickListener(this);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    @Override
    public void onItemClick(View v, int position) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick(v, position);
        }
    }

    private OnDelButtonClickListener delButtonClickListener;
    public void setOnDelButtonClickListener(OnDelButtonClickListener listener){
        delButtonClickListener = listener;
    }
    @Override
    public void onDelButtonClick(View view,int position) {
        if(delButtonClickListener != null){
            delButtonClickListener.onDelButtonClick(view, position);
        }
    }
}
