package com.whenrepay.rnd.whenrepay.BorrowMoney;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.R;

/**
 * Created by RND on 2016-06-21.
 */
public class PersonView extends FrameLayout{
    TextView borrowerName;

    public OnDelButtonClickListener onDelButtonClickListener;
    public void setOnDelButtonClickListener(OnDelButtonClickListener listener){
        onDelButtonClickListener = listener;
    }
    public PersonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(),R.layout.view_person,this);
        borrowerName = (TextView)findViewById(R.id.text_borrower);
        ImageView btn = (ImageView)findViewById(R.id.image_delete);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onDelButtonClickListener != null){
                    onDelButtonClickListener.onDelButtonClick(v,0);
                }
            }
        });
    }
    public void setName(String name){
        borrowerName.setText(name);
    }
}
