package com.whenrepay.rnd.whenrepay.Transactions.DutchPayTransaction;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.whenrepay.rnd.whenrepay.DutchPay.EventData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RND on 2016-07-22.
 */
public class DutchInfoAdapter extends BaseExpandableListAdapter {
    List<EventData> items = new ArrayList<>();

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    public void add(EventData data){
        items.add(data);
        notifyDataSetChanged();
    }
    @Override
    public int getGroupCount() {
        return items.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return items.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).people;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return ((long)groupPosition)<<32|0xFFFFFFFF;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return ((long)groupPosition)<<32|childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupView view;
        if(convertView == null){
            view = new GroupView(parent.getContext());
        }else{
            view = (GroupView)convertView;
        }
        view.setData(items.get(groupPosition));
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ParticipateView view;
        if(convertView == null){
            view = new ParticipateView(parent.getContext());
        }else{
            view = (ParticipateView)convertView;
        }
        view.setData(items.get(groupPosition).people);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
