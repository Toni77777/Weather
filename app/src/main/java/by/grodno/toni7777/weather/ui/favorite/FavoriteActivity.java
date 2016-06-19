package by.grodno.toni7777.weather.ui.favorite;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import by.grodno.toni7777.weather.R;
import by.grodno.toni7777.weather.base.DrawerActivity;

import static by.grodno.toni7777.weather.util.Constants.SHARE_CITY_KEY;

public class FavoriteActivity extends DrawerActivity implements FavoriteFragment.OnDataPass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, new FavoriteFragment())
                    .commit();
        }
    }

    @Override
    public void onDataPass(String cityName) {
        Bundle city = new Bundle();
        city.putString(SHARE_CITY_KEY, cityName);
        FavoriteCityFragment cityFragment = new FavoriteCityFragment();
        cityFragment.setArguments(city);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, cityFragment)
                .commit();
    }
}
