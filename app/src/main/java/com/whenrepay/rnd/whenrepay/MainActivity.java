package com.whenrepay.rnd.whenrepay;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isBackPressed = false;
    public static final int MESSAGE_BACK_KEY_TIMEOUT =0;
    public static final int BACK_KEY_TIME = 2000;

    Handler mHandler = new Handler(Looper.myLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_BACK_KEY_TIMEOUT:
                    isBackPressed = false;
                    return true;
            }
            return false;
        }
    });

    TabLayout tabLayout;
    Button homeButton;
    ViewPager pager;

    MainPagerAdapter mAdapter;
    ProfileDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // pager = (ViewPager)findViewById(R.id.pager);
      //  pager.setAdapter();
     //   mAdapter = new MainPagerAdapter(getSupportFragmentManager());
       // pager.setAdapter(mAdapter);

        tabLayout = (TabLayout)findViewById(R.id.tablayout);

        tabLayout.addTab(tabLayout.newTab().setText("받을돈"));
        tabLayout.addTab(tabLayout.newTab().setText("갚을돈"));
        tabLayout.addTab(tabLayout.newTab().setText("HOME"));
        tabLayout.addTab(tabLayout.newTab().setText("N빵하기"));
        tabLayout.addTab(tabLayout.newTab().setText("내기하기"));

        tabLayout.setScrollPosition(2,0,true);
        if(savedInstanceState ==null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new HomeFragment())
                    .commit();
            setTitle("언제줄래");
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new ReceiveFragment())
                                .commit();
                        setTitle("받을돈");
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new RepayFragment())
                                .commit();
                        setTitle("갚을 돈");
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new HomeFragment())
                                .commit();
                        setTitle("언제줄래");
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new DutchFragment())
                                .commit();
                        setTitle("N빵하기");
                        break;
                    case 4:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new BetFragment())
                                .commit();
                        setTitle("내기하기");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        if(!PropertyManager.getInstance().getLog()) {
            PropertyManager.getInstance().setLog(true);
            dialog = new ProfileDialog();
            dialog.setCancelable(false);
            dialog.show(getSupportFragmentManager(), "dialog");
        }else {
            Toast.makeText(this, PropertyManager.getInstance().getName() + "님 환영합니다", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //noinspection SimplifiableIfStatement
        if (id == R.id.evaluate_app) {
            builder.setTitle("앱 평가하기")
                    .setMessage(R.string.alert_evaluate_app_message)
                    .setPositiveButton("추천하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //마켓이동
                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
            return true;
        }
        else {
            startActivity(new Intent(this,ContractActivity.class));
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(!isBackPressed){
            Toast.makeText(this, R.string.back_pressed_message, Toast.LENGTH_SHORT).show();
            isBackPressed = true;
            mHandler.sendEmptyMessageDelayed(MESSAGE_BACK_KEY_TIMEOUT, BACK_KEY_TIME);
        }
        else {
            mHandler.removeMessages(MESSAGE_BACK_KEY_TIMEOUT);
            finish();
        }
    }
}
