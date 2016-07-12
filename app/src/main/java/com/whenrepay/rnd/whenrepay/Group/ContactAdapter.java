package com.whenrepay.rnd.whenrepay.Group;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import com.whenrepay.rnd.whenrepay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RND on 2016-07-04.
 */
public class ContactAdapter extends BaseAdapter {
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

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public PersonData getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ContactView view;
        if(convertView == null){
            view = new ContactView(parent.getContext());
        }else{
            view = (ContactView)convertView;
        }
        CheckBox checkBox = (CheckBox)view.findViewById(R.id.checkBox_name);
        checkBox.setChecked(((ListView) parent).isItemChecked(position));
        checkBox.setFocusable(false);
        checkBox.setClickable(false);

        checkBox.setText(items.get(position).getName());
        return view;
    }
}