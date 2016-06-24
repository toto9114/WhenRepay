package com.whenrepay.rnd.whenrepay.Transactions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.whenrepay.rnd.whenrepay.R;

/**
 * Created by RND on 2016-06-24.
 */
public class FilterPopupWindow extends PopupWindow{
    public FilterPopupWindow(Context context) {
        super(context);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setOutsideTouchable(true);
        View view = LayoutInflater.from(context).inflate( R.layout.view_filter_popup, null);

        Button btn = (Button)view.findViewById(R.id.btn_money);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn = (Button)view.findViewById(R.id.btn_things);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn = (Button)view.findViewById(R.id.btn_dutch);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
