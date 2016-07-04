package com.whenrepay.rnd.whenrepay.BorrowMoney;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.whenrepay.rnd.whenrepay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DirectlyEditDialog extends DialogFragment {


    public DirectlyEditDialog() {
        // Required empty public constructor
    }

    private OnButtonClickListener buttonClickListener;
    public void setOnButtonClickListener(OnButtonClickListener listener){
        buttonClickListener = listener;
    }

    EditText nameView, phoneView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_directly_edit_dialog, container, false);
        nameView = (EditText) view.findViewById(R.id.text_name);
        phoneView = (EditText) view.findViewById(R.id.edit_phone);

        Button btn = (Button)view.findViewById(R.id.btn_cancel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btn = (Button)view.findViewById(R.id.btn_ok);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonClickListener!=null){
                    buttonClickListener.OnButtonClick(nameView.getText().toString(),phoneView.getText().toString());
                }
                dismiss();
            }
        });

        return view;
    }

}
