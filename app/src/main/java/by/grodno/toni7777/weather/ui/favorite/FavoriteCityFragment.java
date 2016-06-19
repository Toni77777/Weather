package by.grodno.toni7777.weather.ui.favorite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import by.grodno.toni7777.weather.R;
import by.grodno.toni7777.weather.adapter.WeatherAdapter;
import by.grodno.toni7777.weather.app.WeatherApp;
import by.grodno.toni7777.weather.base.DrawerActivity;
import by.grodno.toni7777.weather.db.DBService;
import by.grodno.toni7777.weather.db.model.WeatherDataDSO;
import by.grodno.toni7777.weather.mvp.WeatherPresenter;
import by.grodno.toni7777.weather.mvp.WeatherPresenterImp;
import by.grodno.toni7777.weather.mvp.WeatherView;
import by.grodno.toni7777.weather.network.model.WeatherDataDTO;
import by.grodno.toni7777.weather.ui.model.WeatherDataDVO;
import by.grodno.toni7777.weather.ui.model.WeatherDayDVO;
import by.grodno.toni7777.weather.util.ConverterDSO;
import by.grodno.toni7777.weather.util.ConverterDSOtoDVO;
import by.grodno.toni7777.weather.util.ConverterDVO;
import by.grodno.toni7777.weather.util.NetworkUtil;
import io.realm.Realm;

import static by.grodno.toni7777.weather.util.Constants.*;

public class FavoriteCityFragment extends ListFragment implements WeatherView {

    private String mCity;
    private WeatherAdapter mWeatherAdapter;
    private SharedPreferences mSharedPreferences;
    private static final String STATE_CITY = "citySave";
    private WeatherPresenter presenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Bundle bundle = getArguments();
        mCity = bundle.getString(SHARE_CITY_KEY);
        mWeatherAdapter = new WeatherAdapter(getActivity(), new ArrayList<WeatherDayDVO>());
        setListAdapter(mWeatherAdapter);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(STATE_CITY)) {
                mCity = savedInstanceState.getString(STATE_CITY);
            }
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        presenter = new WeatherPresenterImp(this, ((WeatherApp) getActivity().getApplication()).getNetworkService());
    }

    @Override
    public void onStart() {
        super.onStart();
        List<WeatherDataDSO> data = DBService.getInstance().findCity(Realm.getDefaultInstance(),
                WeatherDataDSO.class,
                mCity);
        WeatherDataDVO dataDVO = ConverterDSOtoDVO.converteDSOtoDVO(data.get(0));
        String format = mSharedPreferences.getString(getString(R.string.list_preference_key), EMPTY);
        mWeatherAdapter.setTempUnit(format);
        mWeatherAdapter.setDaysCount(getDayCount());
        mWeatherAdapter.setNewWeatherData(dataDVO);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_CITY, mCity);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_favorite, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                if (NetworkUtil.hasConnection(getActivity())) {
                    presenter.loadRxData(mCity);
                } else {
                    ((DrawerActivity) getActivity()).showNoInternetConec();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showRxData(WeatherDataDTO data) {
        WeatherDataDVO weatherDataDVO = ConverterDVO.converteDTOtoDVO(data);
        mWeatherAdapter.setNewWeatherData(weatherDataDVO);
        WeatherDataDSO dataDSO = ConverterDSO.converteDVOtoDSO(weatherDataDVO);
        DBService.getInstance().copyToRealmOrUpdate(Realm.getDefaultInstance(), dataDSO);
        Snackbar.make(((DrawerActivity) getActivity()).getCoordinatorLayout(), getString(R.string.refresh_data), Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent favoriteDetail = new Intent(getActivity(), DetailFavoriteActivity.class)
                .putExtra(SHARE_CITY_KEY, mWeatherAdapter.getItem(position).getCityName())
                .putExtra(SHARE_TIME_KEY, mWeatherAdapter.getItem(position).getTime());
        getActivity().startActivity(favoriteDetail);
    }

    @Override
    public void showRxGeoData(WeatherDataDTO data) {

    }

    @Override
    public void showRxError(Throwable throwable) {

    }

    @Override
    public void showRxInProcess() {

    }

    @Override
    public void showCityDialog(String informationChange) {

    }

    @Override
    public void onDestroyView() {
        presenter.rxUnSubscribe();
        super.onDestroyView();
    }

    private int getDayCount() {
        String daysCount = mSharedPreferences.getString(getString(R.string.days_preference_key), DEFAULT_DAYS);
        return Integer.parseInt(daysCount);
    }
}
