package by.grodno.toni7777.weather.ui.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import by.grodno.toni7777.weather.R;
import by.grodno.toni7777.weather.base.BaseActivity;

import static by.grodno.toni7777.weather.util.Constants.DEFAULT_TIME;
import static by.grodno.toni7777.weather.util.Constants.SHARE_CITY_KEY;
import static by.grodno.toni7777.weather.util.Constants.SHARE_TIME_KEY;

public class DetailFavoriteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Bundle fragmentBundle = new Bundle();
        Intent share = getIntent();
        if (share.hasExtra(SHARE_CITY_KEY)) {
            fragmentBundle.putString(SHARE_CITY_KEY, share.getStringExtra(SHARE_CITY_KEY));
        }
        if (share.hasExtra(SHARE_TIME_KEY)) {
            fragmentBundle.putLong(SHARE_TIME_KEY, share.getLongExtra(SHARE_TIME_KEY, DEFAULT_TIME));
        }

        DetailFavoriteFragment fragment = new DetailFavoriteFragment();
        fragment.setArguments(fragmentBundle);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
        }
    }
}
