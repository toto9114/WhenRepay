package com.whenrepay.rnd.whenrepay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by RND on 2016-04-06.
 */
public class IntroPagerAdapter extends FragmentPagerAdapter{
    public IntroPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    IntroFragment introFragment;
    @Override
    public Fragment getItem(int position) {
        introFragment = new IntroFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(IntroFragment.EXTRA_POSITION,position);
        introFragment.setArguments(bundle);
        return introFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
