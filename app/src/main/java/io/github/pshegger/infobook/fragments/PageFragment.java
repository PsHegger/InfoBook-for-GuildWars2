package io.github.pshegger.infobook.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.pshegger.infobook.Constants;
import io.github.pshegger.infobook.MainActivity;
import io.github.pshegger.infobook.R;
import io.github.pshegger.infobook.adapters.pager.BLTCPagerAdapter;
import io.github.pshegger.infobook.adapters.pager.GameInfoPagerAdapter;
import io.github.pshegger.infobook.adapters.pager.MyAccountPagerAdapter;
import io.github.pshegger.infobook.model.GWProfession;

/**
 * Created by pshegger on 2015.10.04..
 */
public class PageFragment extends Fragment {
    private MainActivity.PageType mPage;

    public static PageFragment newInstance(MainActivity.PageType type) {
        PageFragment pf = new PageFragment();

        pf.mPage = type;

        return pf;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.pager_content, container, false);

        ViewPager pager = (ViewPager) root.findViewById(R.id.mainPager);
        TabLayout tabs = (TabLayout) root.findViewById(R.id.mainTabs);
        FragmentManager fm = getChildFragmentManager();

        switch (mPage) {
            case Account:
                pager.setAdapter(new MyAccountPagerAdapter(fm));
                break;
            case BLTC:
                pager.setAdapter(new BLTCPagerAdapter(fm));
                break;
            case Other:
                pager.setAdapter(new GameInfoPagerAdapter(fm));
                break;
        }
        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.setBackgroundColor(getTabBgColor(root.getContext()));
        tabs.setTabTextColors(Color.WHITE, Color.WHITE); // todo

        return root;
    }

    private int getTabBgColor(Context ctx) {
        String selectedTheme = PreferenceManager
                .getDefaultSharedPreferences(ctx)
                .getString(Constants.Preferences.PROFESSION_THEME, "Elementalist");

        return ctx.getResources().getColor(GWProfession.valueOf(selectedTheme).getPrimaryColorResource());
    }
}
