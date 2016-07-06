package com.whenrepay.rnd.whenrepay.DutchPay;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ViewSwitcher;

import com.whenrepay.rnd.whenrepay.BorrowMoney.OnDelButtonClickListener;
import com.whenrepay.rnd.whenrepay.BorrowMoney.PersonView;
import com.whenrepay.rnd.whenrepay.Group.PersonData;
import com.whenrepay.rnd.whenrepay.R;

/**
 * Created by RND on 2016-06-24.
 */
public class SwitcherView extends RecyclerView.ViewHolder {

    private OnItemClickListener itemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }

    private OnDelButtonClickListener delButtonClickListener;
    public void setOnDelButtonClickListener(OnDelButtonClickListener listener){
        delButtonClickListener = listener;
    }
    ViewSwitcher switcher;
   PersonView person;
    public SwitcherView(View itemView) {
        super(itemView);
        switcher = (ViewSwitcher) itemView.findViewById(R.id.view_switcher_contact);
        person = (PersonView) itemView.findViewById(R.id.person);

        Button btn = (Button)itemView.findViewById(R.id.btn_contact);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(itemClickListener != null){
                   itemClickListener.onItemClick(v, getAdapterPosition());
               }
            }
        });

        person.setOnDelButtonClickListener(new OnDelButtonClickListener() {
            @Override
            public void onDelButtonClick(View view,int position) {
                if(delButtonClickListener != null){
                    delButtonClickListener.onDelButtonClick(view,getAdapterPosition());
                }
            }
        });
        btn = (Button)itemView.findViewById(R.id.btn_directly);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setData(PersonData data){
        if(data != null) {
            View view = switcher.getNextView();
            if(view.getId() == R.id.person){
                switcher.showNext();
            }
            person.setName(data.getName());
        }else{
            View view = switcher.getNextView();
            if(view.getId()!=R.id.person){
                switcher.showNext();
            }
        }
    }
}
