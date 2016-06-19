package by.grodno.toni7777.weather.ui.favorite;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import by.grodno.toni7777.weather.R;
import by.grodno.toni7777.weather.db.DBService;
import by.grodno.toni7777.weather.db.model.WeatherDataDSO;
import by.grodno.toni7777.weather.ui.model.WeatherDataDVO;
import by.grodno.toni7777.weather.ui.model.WeatherDayDVO;
import by.grodno.toni7777.weather.util.ConverterDSOtoDVO;
import by.grodno.toni7777.weather.util.TemperatureUtil;
import by.grodno.toni7777.weather.util.TimeUtil;
import by.grodno.toni7777.weather.util.WeatherUtil;
import io.realm.Realm;

import static by.grodno.toni7777.weather.util.Constants.DEFAULT_UNIT;
import static by.grodno.toni7777.weather.util.Constants.INTETEST;
import static by.grodno.toni7777.weather.util.Constants.SEPARATOR;
import static by.grodno.toni7777.weather.util.Constants.SHARE_CITY_KEY;
import static by.grodno.toni7777.weather.util.Constants.SHARE_TIME_KEY;
import static by.grodno.toni7777.weather.util.Constants.UNIT_CELSIUS;
import static by.grodno.toni7777.weather.util.Constants.UNIT_FAHRENHEIT;

public class DetailFavoriteFragment extends Fragment {

    private String mCity;
    private long mTime;
    private static final String STATE_CITY = "citysave";
    private static final String STATE_TIME = "timesave";

    private TextView mCityText;
    private TextView mDayText;
    private TextView mWeekText;
    private ImageView mWeatherImage;
    private TextView mTempDay;
    private TextView mTempNight;
    private TextView mHumidity;
    private TextView mPressure;
    private TextView mWind;
    private SharedPreferences mSharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_detail, container, false);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        mCityText = (TextView) view.findViewById(R.id.city_favorite_detail);
        mDayText = (TextView) view.findViewById(R.id.time_day_favorite_detail);
        mWeekText = (TextView) view.findViewById(R.id.week_favorite_detail);
        mWeatherImage = (ImageView) view.findViewById(R.id.weather_image_favorite_detail);
        mTempDay = (TextView) view.findViewById(R.id.day_favorite_detail);
        mTempNight = (TextView) view.findViewById(R.id.night_favorite_detail);
        mHumidity = (TextView) view.findViewById(R.id.humidity_favorite_detail);
        mPressure = (TextView) view.findViewById(R.id.pressure_favorite_detail);
        mWind = (TextView) view.findViewById(R.id.wind_speed_favorite_detail);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mCity = bundle.getString(SHARE_CITY_KEY);
            mTime = bundle.getLong(SHARE_TIME_KEY);
        }

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(STATE_CITY)) {
                mCity = savedInstanceState.getString(STATE_CITY);
            }
            if (savedInstanceState.containsKey(STATE_TIME)) {
                mCity = savedInstanceState.getString(STATE_CITY);
            }
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        WeatherDataDSO weatherDataDSO = DBService.getInstance().findFirstCity(Realm.getDefaultInstance(),
                WeatherDataDSO.class,
                mCity);
        WeatherDataDVO dataDVO = ConverterDSOtoDVO.converteDSOtoDVO(weatherDataDSO);

        WeatherDayDVO dayDVO = getDayWeather(dataDVO.getWeatherDays());

        mCityText.setText(dayDVO.getCityName());
        mDayText.setText(TimeUtil.getDayOfWeek(getContext(), dayDVO.getTime()));
        mWeekText.setText(TimeUtil.getMonthWithDay(getContext(), dayDVO.getTime()));
        int id = WeatherUtil.getResWeatherImageId(dayDVO.getWeather().getId());
        mWeatherImage.setImageResource(id);
        String format = mSharedPreferences.getString(getString(R.string.list_preference_key), DEFAULT_UNIT).trim();
        Log.e("FORMAT", "UNIT " + format);
        if (format.equals(UNIT_CELSIUS)) {
            mTempDay.setText(TemperatureUtil.tempDegreesCelsius(dayDVO.getTemperature().getDay()));
            mTempNight.setText(TemperatureUtil.tempDegreesCelsius(dayDVO.getTemperature().getNight()));
        } else if (format.equals(UNIT_FAHRENHEIT)) {
            mTempDay.setText(TemperatureUtil.tempFahrenheit(dayDVO.getTemperature().getDay()));
            mTempNight.setText(TemperatureUtil.tempFahrenheit(dayDVO.getTemperature().getNight()));
        }
        mHumidity.setText(getString(R.string.text_humidity) + SEPARATOR + dayDVO.getHumidity() + INTETEST);
        mPressure.setText(getString(R.string.text_pressure) + SEPARATOR + Math.round(dayDVO.getPressure()) + getString(R.string.text_pressure_unit));
        mWind.setText(getString(R.string.text_wind) + SEPARATOR + Math.round(dayDVO.getSpeed()) + getString(R.string.text_wind_speed));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_CITY, mCity);
        outState.putLong(STATE_TIME, mTime);
        super.onSaveInstanceState(outState);
    }

    private WeatherDayDVO getDayWeather(List<WeatherDayDVO> list) {
        for (WeatherDayDVO sourseDVO : list) {
            long time = sourseDVO.getTime();
            if (time == mTime) {
                return sourseDVO;
            }
        }
        throw new RuntimeException("Weather not found with time" + mTime);
    }

}
