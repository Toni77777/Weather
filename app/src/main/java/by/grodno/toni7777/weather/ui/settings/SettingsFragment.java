package by.grodno.toni7777.weather.ui.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import by.grodno.toni7777.weather.R;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

}
