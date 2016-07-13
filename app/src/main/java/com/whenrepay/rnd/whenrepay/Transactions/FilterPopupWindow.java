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
public class FilterPopupWindow extends PopupWindow {
    private OnFilterItemClickListener itemClickListener;

    public void setOnItemClickListener(OnFilterItemClickListener listener) {
        itemClickListener = listener;
    }

    public static final int TYPE_MONEY = 0;
    public static final int TYPE_THINGS = 1;
    public static final int TYPE_DUTCH = 2;
    public static final int TYPE_ALL = 3;

    public FilterPopupWindow(Context context) {
        super(context);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setOutsideTouchable(true);
        View view = LayoutInflater.from(context).inflate(R.layout.view_filter_popup, null);

        Button btn = (Button) view.findViewById(R.id.btn_money);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.OnFilterItemClick(TYPE_MONEY);
                }
                dismiss();
            }
        });
        btn = (Button) view.findViewById(R.id.btn_things);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.OnFilterItemClick(TYPE_THINGS);
                }
                dismiss();
            }
        });

        btn = (Button) view.findViewById(R.id.btn_dutch);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.OnFilterItemClick(TYPE_DUTCH);
                }
                dismiss();
            }
        });

        btn = (Button) view.findViewById(R.id.btn_all);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.OnFilterItemClick(TYPE_ALL);
                }
                dismiss();
            }
        });

        setContentView(view);
    }
}
