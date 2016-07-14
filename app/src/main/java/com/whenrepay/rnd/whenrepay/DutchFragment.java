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
public class DutchFragment extends Fragment {


    public DutchFragment() {
        // Required empty public constructor
    }

    EditText moneyView, personnelView;
    TextView resultView;
    int price, personnel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dutch, container, false);
        moneyView = (EditText)view.findViewById(R.id.edit_money);
        personnelView = (EditText)view.findViewById(R.id.edit_personnel);
        resultView = (TextView)view.findViewById(R.id.text_operation);

        Button btn = (Button)view.findViewById(R.id.btn_regist);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(moneyView.getText().toString()) && !TextUtils.isEmpty(personnelView.getText().toString())) {
                    price = Integer.parseInt(moneyView.getText().toString());
                    personnel = Integer.parseInt(personnelView.getText().toString());
                    int cost = price/personnel;
                    if(cost>=10000){
                        cost = (cost - (cost%1000))+ 1000;
                    }
                    else if(cost>=1000){
                        cost = (cost - (cost%100))+ 100;
                    }
                    else{
                        Toast.makeText(getContext(),"금액이 너무 낮습니다.",Toast.LENGTH_SHORT).show();
                    }
                    int myCost = cost - (cost*personnel - price);
                    resultView.setText("계산결과\n\r" + "1인당 "+ cost+ "\n\r"+ "내가 낼 금액은 "+ myCost + "\n\r"+"입니다.");
                }else {
                    Toast.makeText(getContext(), "N빵정보를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

}
