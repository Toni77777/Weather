package by.grodno.toni7777.weather.app;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;


import by.grodno.toni7777.weather.network.NetworkService;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class WeatherApp extends MultiDexApplication{

    private NetworkService networkService;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        networkService = new NetworkService();
    }

    public NetworkService getNetworkService() {
        return networkService;
    }
}
