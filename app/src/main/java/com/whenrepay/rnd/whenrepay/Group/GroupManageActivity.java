package com.whenrepay.rnd.whenrepay.Group;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.whenrepay.rnd.whenrepay.R;

public class GroupManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manage);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,new GroupPaymentManageFragment())
                    .commit();
        }

        Button btn = (Button)findViewById(R.id.btn_payment_manage);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new GroupPaymentManageFragment())
                        .commit();
            }
        });

        btn = (Button)findViewById(R.id.btn_member_manage);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,new GroupMemberManageFragment())
                        .commit();
            }
        });


    }
}
