package com.whenrepay.rnd.whenrepay.Contact;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import com.whenrepay.rnd.whenrepay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RND on 2016-07-13.
 */
public class MultiRecentAdapter extends BaseAdapter {
    List<String> items = new ArrayList<>();

    public void add(String data) {
        items.add(data);
        notifyDataSetChanged();
    }

    public void addAll(List<String> list) {
        items.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MultiContactView view;
        if (convertView == null) {
            view = new MultiContactView(parent.getContext());
        } else {
            view = (MultiContactView) convertView;
        }
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox_name);
        checkBox.setChecked(((ListView) parent).isItemChecked(position));
        checkBox.setFocusable(false);
        checkBox.setClickable(false);

        checkBox.setText(items.get(position));
        return view;
    }
}
