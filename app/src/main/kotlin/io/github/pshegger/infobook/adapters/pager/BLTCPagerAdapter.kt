package io.github.pshegger.infobook.adapters.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.github.pshegger.infobook.fragments.ExampleFragment

/**
 * Created by pshegger on 2015. 11. 10..
 */
class BLTCPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val fragments = arrayOf(
            ExampleFragment.newInstance("BLTC"),
            ExampleFragment.newInstance("BLTC"),
            ExampleFragment.newInstance("BLTC")
    )

    override fun getItem(position: Int): Fragment? = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = fragments[position].name()
}