package com.whenrepay.rnd.whenrepay.Group;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RND on 2016-07-05.
 */
public class SendPersonAdapter extends BaseAdapter {
    List<PersonData> items = new ArrayList<>();

    public void addAll(List<PersonData> list){
        items.addAll(list);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SendPersonView view;
        if(convertView == null){
            view= new SendPersonView(parent.getContext());
        }else{
            view = (SendPersonView)convertView;
        }
        view.setData(items.get(position));
        return view;
    }
}
