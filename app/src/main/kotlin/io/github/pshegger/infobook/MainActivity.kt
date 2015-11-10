package io.github.pshegger.infobook

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import io.github.pshegger.infobook.fragments.PageFragment
import io.github.pshegger.infobook.model.GWProfession

/**
 * Created by pshegger on 2015. 11. 09..
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    enum class PageType {
        Account, BLTC, Other
    }

    var currentPage: PageType? = null
    var selectedProfession = GWProfession.Elementalist

    override fun onCreate(savedInstanceState: Bundle?) {
        setProfessionTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        setPage(PageType.Account)
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout

        if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START)
        else super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem?): Boolean {
        if (item == null) return false

        when (item.itemId) {
            R.id.nav_change_theme -> changeProfessionTheme()
            R.id.nav_my_account -> setPage(PageType.Account)
            R.id.nav_bltc -> setPage(PageType.BLTC)
            R.id.nav_game_info -> setPage(PageType.Other)
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)

        return true
    }

    private fun setPage(type: PageType) {
        if (type == currentPage) return

        val currentFragment = supportFragmentManager.findFragmentById(R.id.contentLayout)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.contentLayout, PageFragment.newInstance(type))
                    .commit()
        } else {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.contentLayout, PageFragment.newInstance(type))
                    .commit()
        }

        currentPage = type
    }

    private fun changeProfessionTheme() {
        val availableProfessions = arrayOf(
                GWProfession.Guardian, GWProfession.Revenant, GWProfession.Warrior,
                GWProfession.Engineer, GWProfession.Ranger, GWProfession.Thief,
                GWProfession.Elementalist, GWProfession.Mesmer, GWProfession.Necromancer
        )

        val names = availableProfessions.map { it.name }.toTypedArray()
        val selected = availableProfessions.indexOf(selectedProfession)

        AlertDialog.Builder(this).apply {
            setSingleChoiceItems(names, selected, object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    PreferenceManager.getDefaultSharedPreferences(baseContext)
                            .edit()
                            .putString(Constants.Preferences.PROFESSION_THEME, availableProfessions[which].name)
                            .apply()
                    startActivity(Intent(context, MainActivity::class.java))
                    finish()
                }
            })
            show()
        }
    }

    private fun setProfessionTheme() {
        val selectedTheme = PreferenceManager.getDefaultSharedPreferences(baseContext)
                .getString(Constants.Preferences.PROFESSION_THEME, GWProfession.Elementalist.name);

        selectedProfession = GWProfession.valueOf(selectedTheme)
        setTheme(selectedProfession.theme())
    }
}