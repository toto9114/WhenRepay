package com.whenrepay.rnd.whenrepay;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class BetFragment extends Fragment {


    public BetFragment() {
        // Required empty public constructor
    }

    EditText moneyView, personnelView;
    TextView nameView;
    int price, personnel;
    BetCustomDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bet, container, false);
        moneyView = (EditText)view.findViewById(R.id.edit_money);
        personnelView = (EditText)view.findViewById(R.id.edit_personnel);
        nameView = (TextView)view.findViewById(R.id.text_result);
        nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new BetCustomDialog();
                dialog.show(getFragmentManager(),"bet");
            }
        });

        Button btn = (Button)view.findViewById(R.id.btn_regist);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(moneyView.getText().toString()) && !TextUtils.isEmpty(personnelView.getText().toString())) {
                    price = Integer.parseInt(moneyView.getText().toString());
                    personnel = Integer.parseInt(personnelView.getText().toString());
                }else {
                    Toast.makeText(getContext(),"내기정보를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

}
