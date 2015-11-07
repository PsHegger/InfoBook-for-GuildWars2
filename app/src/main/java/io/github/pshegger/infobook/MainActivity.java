package io.github.pshegger.infobook;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import io.github.pshegger.infobook.fragments.PageFragment;
import io.github.pshegger.infobook.model.GWProfession;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public enum PageType {
        Account, BLTC, Other
    }

    GWProfession mSelectedProfession;
    GWApiService mService;
    PageType mCurrentPage = null;
    ViewGroup mContentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setProfessionTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setElevation(0);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mService = InfoBookApplication.getInstance().getService();

        mContentLayout = (ViewGroup) findViewById(R.id.contentLayout);
        setPage(PageType.Account);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_change_theme:
                changeProfessionTheme();
                break;
            case R.id.nav_my_account:
                setPage(PageType.Account);
                break;
            case R.id.nav_bltc:
                setPage(PageType.BLTC);
                break;
            case R.id.nav_game_info:
                setPage(PageType.Other);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setPage(PageType type) {
        if (type == mCurrentPage) return;

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.contentLayout);
        if (currentFragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentLayout, PageFragment.newInstance(type))
                    .commit();
        }else{
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentLayout, PageFragment.newInstance(type))
                    .commit();
        }

        mCurrentPage = type;
    }

    private void changeProfessionTheme() {
        final GWProfession[] availableProfessions = new GWProfession[] {
                GWProfession.Guardian, GWProfession.Revenant, GWProfession.Warrior,
                GWProfession.Engineer, GWProfession.Ranger, GWProfession.Thief,
                GWProfession.Elementalist, GWProfession.Mesmer, GWProfession.Necromancer
        };
        final String[] names = new String[availableProfessions.length];

        for (int i=0; i<availableProfessions.length; i++) {
            names[i] = availableProfessions[i].name();
        }

        int selected = 0;
        for (int i=0; i<availableProfessions.length; i++) {
            if (availableProfessions[i] == mSelectedProfession) {
                selected = i;
                break;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(names, selected, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PreferenceManager.getDefaultSharedPreferences(MainActivity.this)
                        .edit()
                        .putString(Constants.Preferences.PROFESSION_THEME, availableProfessions[which].name())
                        .apply();
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
        });
        builder.show();
    }

    private void setProfessionTheme() {
        String selectedTheme = PreferenceManager
                .getDefaultSharedPreferences(this)
                .getString(Constants.Preferences.PROFESSION_THEME, "Elementalist");

        mSelectedProfession = GWProfession.valueOf(selectedTheme);
        setTheme(mSelectedProfession.getTheme());
    }
}
