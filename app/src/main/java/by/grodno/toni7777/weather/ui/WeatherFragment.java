package by.grodno.toni7777.weather.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import by.grodno.toni7777.weather.R;
import by.grodno.toni7777.weather.WeatherActivity;
import by.grodno.toni7777.weather.adapter.WeatherAdapter;
import by.grodno.toni7777.weather.app.WeatherApp;
import by.grodno.toni7777.weather.base.DrawerActivity;
import by.grodno.toni7777.weather.db.DBService;
import by.grodno.toni7777.weather.db.mdefault.WeatherDataDefault;
import by.grodno.toni7777.weather.db.model.WeatherDataDSO;
import by.grodno.toni7777.weather.mvp.WeatherPresenter;
import by.grodno.toni7777.weather.mvp.WeatherPresenterImp;
import by.grodno.toni7777.weather.mvp.WeatherView;
import by.grodno.toni7777.weather.network.model.WeatherDataDTO;
import by.grodno.toni7777.weather.ui.detail.DetailWeatherActivity;
import by.grodno.toni7777.weather.ui.model.WeatherDataDVO;
import by.grodno.toni7777.weather.ui.model.WeatherDayDVO;

import static by.grodno.toni7777.weather.util.Constants.*;

import by.grodno.toni7777.weather.util.Constants;
import by.grodno.toni7777.weather.util.ConverterDSO;
import by.grodno.toni7777.weather.util.ConverterDVO;
import by.grodno.toni7777.weather.util.ConverterDefault;
import by.grodno.toni7777.weather.util.ConverterDefaultToDVO;
import by.grodno.toni7777.weather.util.NetworkUtil;
import io.realm.Realm;

public class WeatherFragment extends Fragment implements WeatherView, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String EXTRA_RX = "EXTRA_RX";
    private boolean rxCallInWorks;
    private WeatherPresenter presenter;

    private EditText mSearchCity;
    private ProgressBar mWeatherProgress;
    private ListView mWeatherList;

    private String cityName;
    private boolean showDialog;
    private boolean showProgress;
    private static final String STATE_DIALOG = "showCityDialog";
    private static final String STATE_PROGRESS = "showProgressBar";
    private static final String STATE_CITY = "cityName";

    private WeatherAdapter mWeatherAdapter;
    private SharedPreferences mSharedPreferences;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if (savedInstanceState != null) {
            rxCallInWorks = savedInstanceState.getBoolean(EXTRA_RX);
            showDialog = savedInstanceState.getBoolean(STATE_DIALOG);
            cityName = savedInstanceState.getString(STATE_CITY);

            if (showDialog) {
                showCityDialog(cityName);
            }

            showProgress = savedInstanceState.getBoolean(STATE_PROGRESS);
            if (showProgress) {
                mWeatherProgress.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        mWeatherProgress = (ProgressBar) view.findViewById(R.id.loadBar);
        mWeatherList = (ListView) view.findViewById(R.id.weather);

        FloatingActionsMenu floatMenu = (FloatingActionsMenu) view.findViewById(R.id.menu_fab);
        (view.findViewById(R.id.search_fab)).setOnClickListener(v -> {
            showCityDialog(mSharedPreferences.getString(getString(R.string.text_preference_key), EMPTY));
            floatMenu.collapse();
        });

        (view.findViewById(R.id.location_fab)).setOnClickListener(v -> {
            getLocation();
            floatMenu.collapse();
        });

        presenter = new WeatherPresenterImp(this, ((WeatherApp) getActivity().getApplication()).getNetworkService());

        ListView weatherListView = (ListView) view.findViewById(R.id.weather);
        weatherListView.setEmptyView(view.findViewById(android.R.id.empty));
        mWeatherAdapter = new WeatherAdapter(getActivity(), new ArrayList<WeatherDayDVO>());
        weatherListView.setAdapter(mWeatherAdapter);
        weatherListView.setOnItemClickListener((arg0, arg1, position, arg3) -> {
            Intent detailWeather = new Intent(getActivity(), DetailWeatherActivity.class)
                    .putExtra(SHARE_CITY_KEY, mWeatherAdapter.getItem(position).getCityName())
                    .putExtra(SHARE_TIME_KEY, mWeatherAdapter.getItem(position).getTime());
            getActivity().startActivity(detailWeather);
        });
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_favorite:
                WeatherDataDVO dataDVO = mWeatherAdapter.getWeatherDataDVO();
                if (dataDVO != null) {
                    WeatherDataDSO dataDSO = ConverterDSO.converteDVOtoDSO(dataDVO);
                    DBService.getInstance().copyToRealmOrUpdate(Realm.getDefaultInstance(), dataDSO);
                    Snackbar.make(((WeatherActivity) getActivity()).getCoordinatorLayout(), dataDVO.getCity().getName() + SEPARATOR + getString(R.string.favorite_add), Snackbar.LENGTH_LONG)
                            .show();
                }
                return true;
            case R.id.menu_refresh:

                if (NetworkUtil.hasConnection(getActivity())) {
                    List<WeatherDataDefault> dataDefaults = DBService.getInstance().readAll(Realm.getDefaultInstance(), WeatherDataDefault.class);
                    if (dataDefaults != null) {
                        if (dataDefaults.size() > 0) {
                            WeatherDataDefault weatherDefault = dataDefaults.get(0);
                            WeatherDataDVO weatherDataDVO = ConverterDefaultToDVO.converteDSOtoDVO(weatherDefault);
                            presenter.loadRxData(weatherDataDVO.getCity().getName());
                        }
                    }
                } else {
                    ((DrawerActivity) getActivity()).showNoInternetConec();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(EXTRA_RX, rxCallInWorks);
        outState.putBoolean(STATE_DIALOG, showDialog);
        outState.putBoolean(STATE_PROGRESS, showProgress);

        if (mSearchCity != null) {
            outState.putString(STATE_CITY, mSearchCity.getText().toString());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.rxUnSubscribe();
        if (mGoogleApiClient != null) {
            if (mGoogleApiClient.isConnected()) {
                mGoogleApiClient.disconnect();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (rxCallInWorks) {
            presenter.loadRxData(cityName);
        }
        String format = mSharedPreferences.getString(getString(R.string.list_preference_key), EMPTY);
        mWeatherAdapter.setTempUnit(format);
        if (mSharedPreferences.getString(getString(R.string.text_preference_key), EMPTY).trim().equals(EMPTY)) {
            showCityDialog(EMPTY);
        }
        mWeatherAdapter.setDaysCount(getDayCount());

        List<WeatherDataDefault> dataDefaults = DBService.getInstance().readAll(Realm.getDefaultInstance(), WeatherDataDefault.class);
        if (dataDefaults != null) {
            if (dataDefaults.size() > 0) {
                WeatherDataDefault weatherDefault = dataDefaults.get(0);
                WeatherDataDVO weatherDataDVO = ConverterDefaultToDVO.converteDSOtoDVO(weatherDefault);
                mWeatherAdapter.setNewWeatherData(weatherDataDVO);
            }
        }

    }

    @Override
    public void showRxData(WeatherDataDTO data) {
        WeatherDataDVO weatherDataDVO = ConverterDVO.converteDTOtoDVO(data);
        WeatherDataDefault dataDefault = ConverterDefault.converteDVOtoDefault(weatherDataDVO);
        DBService.getInstance().copyToRealmOrUpdate(Realm.getDefaultInstance(), dataDefault);

        mWeatherAdapter.setNewWeatherData(weatherDataDVO);
        showProgress = false;
        rxCallInWorks = false;
        mWeatherProgress.setVisibility(View.GONE);
        mWeatherList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRxGeoData(WeatherDataDTO data) {
        WeatherDataDVO weatherDataDVO = ConverterDVO.converteDTOtoDVO(data);
        mWeatherAdapter.setNewWeatherData(weatherDataDVO);
        WeatherDataDefault dataDefault = ConverterDefault.converteDVOtoDefault(weatherDataDVO);
        DBService.getInstance().copyToRealmOrUpdate(Realm.getDefaultInstance(), dataDefault);
        Snackbar.make(((WeatherActivity) getActivity()).getCoordinatorLayout(), getString(R.string.geo_you_here) + SEPARATOR + data.getCity().getName(), Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void showRxError(Throwable throwable) {
        rxCallInWorks = false;
        showProgress = false;
        mWeatherProgress.setVisibility(View.GONE);
    }

    @Override
    public void showRxInProcess() {
        showProgress = true;
        mWeatherList.setVisibility(View.INVISIBLE);
        mWeatherProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCityDialog(String informationChange) {
        showDialog = true;
        View promptsView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_city, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptsView);
        mSearchCity = (EditText) promptsView.findViewById(R.id.input_city_name);
        mSearchCity.setText(informationChange);
        mSearchCity.setSelection(informationChange.length());
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_ok,
                        (dialog, id) -> {
                            showDialog = false;
                            mSharedPreferences.edit().putString(getString(R.string.text_preference_key), mSearchCity.getText().toString().trim()).apply();
                            if (NetworkUtil.hasConnection(getActivity())) {
                                rxCallInWorks = true;
                                presenter.loadRxData(mSearchCity.getText().toString());
                            } else {
                                ((DrawerActivity) getActivity()).showNoInternetConec();
                            }
                        })
                .setNegativeButton(R.string.dialog_disable,
                        (dialog, id) -> {
                            dialog.cancel();
                            showDialog = false;
                        })
                .create()
                .show();
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) { // Need Add Request and check Danger Permiss !!!
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    Constants.REQUEST_PERMISSION);
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            if (NetworkUtil.hasConnection(getActivity())) {
                presenter.loadRxGeoData(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            } else {
                ((DrawerActivity) getActivity()).showNoInternetConec();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    public void getLocation() {
        LocationManager locManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showNoGPSDialog();
        } else {
            buildGoogleApiClient();
            mGoogleApiClient.connect();
        }
    }

    private void showNoGPSDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.disabled_gps))
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_ok, (dialog, id) -> {
                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                })
                .setNegativeButton(R.string.dialog_disable, (dialog, id) -> {
                    dialog.cancel();
                })
                .create()
                .show();
    }

    private int getDayCount() {
        String daysCount = mSharedPreferences.getString(getString(R.string.days_preference_key), DEFAULT_DAYS);
        return Integer.parseInt(daysCount);
    }
}
