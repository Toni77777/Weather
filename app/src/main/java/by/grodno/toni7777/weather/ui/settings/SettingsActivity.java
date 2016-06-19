package by.grodno.toni7777.weather.ui.settings;

import android.os.Bundle;

import by.grodno.toni7777.weather.R;
import by.grodno.toni7777.weather.base.DrawerActivity;

public class SettingsActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.content, new SettingsFragment())
                    .commit();
        }
    }
}
