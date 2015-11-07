package io.github.pshegger.infobook.adapters.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.github.pshegger.infobook.fragments.ExampleFragment;
import io.github.pshegger.infobook.fragments.NamedFragment;

/**
 * Created by pshegger on 2015.10.04..
 */
public class BLTCPagerAdapter extends FragmentPagerAdapter {
    private NamedFragment[] mFragments;

    public BLTCPagerAdapter(FragmentManager fm) {
        super(fm);

        mFragments = new NamedFragment[]{
                ExampleFragment.newInstance("BLTC"),
                ExampleFragment.newInstance("BLTC"),
                ExampleFragment.newInstance("BLTC")
        };
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments[position].getName();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }
}
