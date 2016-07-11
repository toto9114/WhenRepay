package com.whenrepay.rnd.whenrepay.Group;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.whenrepay.rnd.whenrepay.Group.MemberManage.GroupMemberManageFragment;
import com.whenrepay.rnd.whenrepay.Group.PaymentManage.GroupPaymentManageFragment;
import com.whenrepay.rnd.whenrepay.Manager.DataManager;
import com.whenrepay.rnd.whenrepay.R;

public class GroupManageActivity extends AppCompatActivity {

    public static final String EXTRA_GROUP_ID = "id";


    GroupData groupData;
    TextView titleView, memberView,paymentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manage);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out_background);

       groupData = (GroupData)getIntent().getSerializableExtra(EXTRA_GROUP_ID);


        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,new GroupPaymentManageFragment())
                    .commit();
        }

        titleView = (TextView)findViewById(R.id.text_title);
        memberView = (TextView)findViewById(R.id.text_member);
        paymentView = (TextView)findViewById(R.id.text_payment);

        init();

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
                args.putInt(GroupMemberManageFragment.EXTRA_GROUP_ID, groupData._id);
                f.setArguments(args);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, f)
                        .commit();
            }
        });
    }

    private void init(){

        titleView.setText(groupData.getGroupName());
        memberView.setText(DataManager.getInstance().getMemberList(groupData._id).get(0).getName() +" 외 "+
                (DataManager.getInstance().getMemberList(groupData._id).size()-1) +"인");
        paymentView.setText("매 달"+ groupData.getPaymentDate() +"일/" + groupData.getMoneyPerPerson() +"원");
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in_background, R.anim.slide_right_out);
    }
}
