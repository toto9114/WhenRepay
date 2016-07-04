package com.whenrepay.rnd.whenrepay.Group;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.R;

/**
 * Created by RND on 2016-07-04.
 */
public class PersonView extends RecyclerView.ViewHolder {
    TextView borrowerName;
    public PersonView(View itemView) {
        super(itemView);
        borrowerName = (TextView)itemView.findViewById(R.id.text_borrower);
        ImageView btn = (ImageView)itemView.findViewById(R.id.image_delete);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void setName(String name){
        borrowerName.setText(name);
    }
}
