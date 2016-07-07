package com.whenrepay.rnd.whenrepay.Group;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.whenrepay.rnd.whenrepay.Group.MemberManage.GroupMemberManageFragment;
import com.whenrepay.rnd.whenrepay.Group.PaymentManage.GroupPaymentManageFragment;
import com.whenrepay.rnd.whenrepay.R;

public class GroupManageActivity extends AppCompatActivity {

    public static final String EXTRA_GROUP_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manage);

        final int groupId = getIntent().getIntExtra(EXTRA_GROUP_ID,-1);
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
                GroupMemberManageFragment f = new GroupMemberManageFragment();
                Bundle args = new Bundle();
                args.putInt(GroupMemberManageFragment.EXTRA_GROUP_ID,groupId);
                f.setArguments(args);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container,f)
                        .commit();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out_background);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in_background, R.anim.slide_right_out);
    }
}
