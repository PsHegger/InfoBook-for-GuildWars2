package io.github.pshegger.infobook.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.pshegger.infobook.Constants
import io.github.pshegger.infobook.MainActivity
import io.github.pshegger.infobook.R
import io.github.pshegger.infobook.adapters.pager.BLTCPagerAdapter
import io.github.pshegger.infobook.adapters.pager.GameInfoPagerAdapter
import io.github.pshegger.infobook.adapters.pager.MyAccountPagerAdapter
import io.github.pshegger.infobook.model.GWProfession

/**
 * Created by pshegger on 2015. 11. 10..
 */
class PageFragment : Fragment() {
    companion object {
        fun newInstance(type: MainActivity.PageType): PageFragment {
            val fragment = PageFragment()

            fragment.type = type

            return fragment
        }
    }

    var type: MainActivity.PageType = MainActivity.PageType.Account

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.pager_content, container, false)

        val pager = root.findViewById(R.id.mainPager) as ViewPager
        val tabs = root.findViewById(R.id.mainTabs) as TabLayout
        val fm = childFragmentManager

        when (type) {
            MainActivity.PageType.Account -> pager.adapter = MyAccountPagerAdapter(fm)
            MainActivity.PageType.BLTC -> pager.adapter = BLTCPagerAdapter(fm)
            MainActivity.PageType.Other -> pager.adapter = GameInfoPagerAdapter(fm)
        }

        tabs.setupWithViewPager(pager)
        tabs.tabMode = TabLayout.MODE_SCROLLABLE
        tabs.setBackgroundColor(tabBgColor(container?.context ?: context))
        tabs.setTabTextColors(Color.WHITE, Color.WHITE) // todo

        pager.offscreenPageLimit = pager.adapter.count

        return root
    }

    private fun tabBgColor(ctx: Context): Int {
        val selectedTheme = PreferenceManager.getDefaultSharedPreferences(ctx)
                .getString(Constants.Preferences.PROFESSION_THEME, GWProfession.Elementalist.name)

        return ctx.resources.getColor(GWProfession.valueOf(selectedTheme).primaryColorRes())
    }
}