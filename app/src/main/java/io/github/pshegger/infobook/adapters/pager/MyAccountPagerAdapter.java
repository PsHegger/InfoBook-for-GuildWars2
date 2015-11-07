package io.github.pshegger.infobook.adapters.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import io.github.pshegger.infobook.fragments.NamedFragment;
import io.github.pshegger.infobook.fragments.account.BankFragment;
import io.github.pshegger.infobook.fragments.account.CharactersFragment;
import io.github.pshegger.infobook.fragments.account.DyesFragment;
import io.github.pshegger.infobook.fragments.account.OtherFragment;
import io.github.pshegger.infobook.fragments.account.PvPFragment;
import io.github.pshegger.infobook.fragments.account.SkinsFragment;
import io.github.pshegger.infobook.fragments.account.WalletFragment;

/**
 * Created by pshegger on 2015.10.04..
 */
public class MyAccountPagerAdapter extends FragmentPagerAdapter {
    private NamedFragment[] mFragments;

    public MyAccountPagerAdapter(FragmentManager fm) {
        super(fm);

        mFragments = new NamedFragment[] {
                new CharactersFragment(),
                new BankFragment(),
                new WalletFragment(),
                new SkinsFragment(),
                new DyesFragment(),
                new PvPFragment(),
                new OtherFragment()
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
