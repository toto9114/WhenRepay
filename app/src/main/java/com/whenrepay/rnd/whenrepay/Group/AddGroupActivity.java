package com.whenrepay.rnd.whenrepay.Group;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.whenrepay.rnd.whenrepay.BorrowMoney.SuccessFragment;
import com.whenrepay.rnd.whenrepay.R;

public class AddGroupActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("회비등록");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out)
                    .add(R.id.container, new RegistGroupInfoFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
    public void changePayment(){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out)
                .replace(R.id.container, new PaymentSettingFragment())
                .addToBackStack(null)
                .commit();
    }

    public void changeSend(){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out)
                .replace(R.id.container, new SendGroupFragment())
                .addToBackStack(null)
                .commit();
    }

    public void changeSuccess(){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out)
                .replace(R.id.container, new SuccessFragment())
                .addToBackStack(null)
                .commit();
    }


}
