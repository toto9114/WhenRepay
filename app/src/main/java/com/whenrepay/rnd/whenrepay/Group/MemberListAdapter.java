package com.whenrepay.rnd.whenrepay.Group;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whenrepay.rnd.whenrepay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RND on 2016-07-04.
 */
public class MemberListAdapter extends RecyclerView.Adapter {
    List<PersonData> items = new ArrayList<>();

    public void add(PersonData data){
        items.add(data);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_person,parent,false);

        return new PersonView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PersonView)holder).setName(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
