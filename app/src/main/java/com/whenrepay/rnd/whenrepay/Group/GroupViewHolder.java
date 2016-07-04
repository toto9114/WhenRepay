package com.whenrepay.rnd.whenrepay.Group;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.R;

/**
 * Created by RND on 2016-06-23.
 */
public class GroupViewHolder extends RecyclerView.ViewHolder{
    TextView titleView, numberView;

    public GroupViewHolder(View itemView) {
        super(itemView);
        titleView = (TextView)itemView.findViewById(R.id.text_title);
        numberView = (TextView)itemView.findViewById(R.id.text_number);
    }

    public void setData(GroupData data){
        titleView.setText(data.groupName);
        numberView.setText(""+data.personList.size());
    }
}
