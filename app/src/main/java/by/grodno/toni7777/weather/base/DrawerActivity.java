package by.grodno.toni7777.weather.base;

import android.app.Activity;
import android.content.ComponentName;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;

import by.grodno.toni7777.weather.R;
import by.grodno.toni7777.weather.WeatherActivity;
import by.grodno.toni7777.weather.ui.favorite.FavoriteActivity;
import by.grodno.toni7777.weather.ui.settings.SettingsActivity;

public class DrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CoordinatorLayout coordinatorLayout;

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, getToolbar(), 0, 0);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        int id = item.getItemId();

        if (id == R.id.nav_city) {
            startSectionActivity(WeatherActivity.class);
        } else if (id == R.id.nav_favorite) {
            startSectionActivity(FavoriteActivity.class);
        } else if (id == R.id.nav_settings) {
            startSectionActivity(SettingsActivity.class);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    protected void startSectionActivity(Class<? extends Activity> clas) {
        if (clas == this.getClass()) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            startActivity(IntentCompat.makeRestartActivityTask(new ComponentName(this, clas)));
        }
    }

    public CoordinatorLayout getCoordinatorLayout() {
        return coordinatorLayout;
    }

    public void showNoInternetConec() {
        Snackbar.make(coordinatorLayout, getString(R.string.no_connection), Snackbar.LENGTH_LONG)
                .show();
    }

}
