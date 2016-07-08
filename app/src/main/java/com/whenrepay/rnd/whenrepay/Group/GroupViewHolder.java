package com.whenrepay.rnd.whenrepay.Group;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;

/**
 * Created by RND on 2016-06-23.
 */
public class GroupViewHolder extends RecyclerView.ViewHolder{

    OnItemCheckedListener itemCheckedListener;
    public void setOnItemCheckedListener(OnItemCheckedListener listener){
        itemCheckedListener = listener;
    }

    TextView titleView, numberView;
    CheckBox checkBox;
    public GroupViewHolder(View itemView) {
        super(itemView);
        titleView = (TextView)itemView.findViewById(R.id.text_title);
        numberView = (TextView)itemView.findViewById(R.id.text_number);
        checkBox = (CheckBox)itemView.findViewById(R.id.check_delete);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(itemCheckedListener != null){
                    itemCheckedListener.OnItemChecked(isChecked,getAdapterPosition());
                }
            }
        });
    }

    public void setData(GroupData data,boolean isCheckBoxVisible){
        titleView.setText(data.getGroupName());
        if(DataManager.getInstance().getMemberList(data._id).size()>0) {
            numberView.setText(DataManager.getInstance().getMemberList(data._id).get(0).getName() + " 외 " +
                    (DataManager.getInstance().getMemberList(data._id).size() - 1) + "명");
        }
        checkBox.setChecked(false);
        if(isCheckBoxVisible){
            checkBox.setVisibility(View.VISIBLE);
        }else{
            checkBox.setVisibility(View.GONE);
        }
    }

}
