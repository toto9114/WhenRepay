package com.whenrepay.rnd.whenrepay.Contact;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.whenrepay.rnd.whenrepay.R;

public class SingleContactActivity extends AppCompatActivity {

    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_contact);
        setTitle("Contract");
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out_background);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);

        tabLayout.addTab(tabLayout.newTab().setText("연락처"));
        tabLayout.addTab(tabLayout.newTab().setText("직접입력"));
        tabLayout.addTab(tabLayout.newTab().setText("최근"));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        SingleContactFragment contactFragment = new SingleContactFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, contactFragment)
                                .commit();
                        break;
                    case 1:
                        DirectFragment directFragment = new DirectFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, directFragment)
                                .commit();
                        break;
                    case 2:
                        SingleRecentFragment recentFragment = new SingleRecentFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, recentFragment)
                                .commit();
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

        if (savedInstanceState == null) {
            SingleContactFragment f = new SingleContactFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, f)
                    .commit();
        }
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in_background, R.anim.slide_right_out);
    }
}
