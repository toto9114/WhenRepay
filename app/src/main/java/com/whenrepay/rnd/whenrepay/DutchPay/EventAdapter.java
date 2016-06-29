package com.whenrepay.rnd.whenrepay.DutchPay;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.whenrepay.rnd.whenrepay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RND on 2016-06-29.
 */
public class EventAdapter extends RecyclerView.Adapter {
    List<EventData> items = new ArrayList<>();

    public void add(EventData data){
        items.add(data);
        notifyDataSetChanged();
    }
    public void setData(EventData data) {
        items.set(items.size() - 1, data);
        notifyDataSetChanged();
    }
    public EventData getItem(){
        EventData data = new EventData();
        if(!TextUtils.isEmpty(editMoney.getText().toString())) {
            data.money = Integer.parseInt(editMoney.getText().toString());
        }
        return data;
    }
    public List<EventData> getItemList(){
        return items;
    }

    EditText editMoney;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_edit_event, parent, false);
        editMoney = (EditText)view.findViewById(R.id.edit_money);
        return new EditEventView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((EditEventView)holder).setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
