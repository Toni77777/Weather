package by.grodno.toni7777.weather;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import by.grodno.toni7777.weather.base.DrawerActivity;
import by.grodno.toni7777.weather.ui.WeatherFragment;

public class WeatherActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, new WeatherFragment())
                    .commit();
        }

    }
}

