package by.grodno.toni7777.weather.ui.detail;

import android.content.Intent;
import android.os.Bundle;

import by.grodno.toni7777.weather.R;
import by.grodno.toni7777.weather.base.BaseActivity;
import by.grodno.toni7777.weather.ui.favorite.DetailFavoriteFragment;

import static by.grodno.toni7777.weather.util.Constants.*;

public class DetailWeatherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        Bundle fragmentBundle = new Bundle();
        Intent share = getIntent();
        if (share.hasExtra(SHARE_CITY_KEY)) {
            fragmentBundle.putString(SHARE_CITY_KEY, share.getStringExtra(SHARE_CITY_KEY));
        }
        if (share.hasExtra(SHARE_TIME_KEY)) {
            fragmentBundle.putLong(SHARE_TIME_KEY, share.getLongExtra(SHARE_TIME_KEY, DEFAULT_TIME));
        }

        DetailWeatherFragment fragment = new DetailWeatherFragment();
        fragment.setArguments(fragmentBundle);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
        }
    }
}
