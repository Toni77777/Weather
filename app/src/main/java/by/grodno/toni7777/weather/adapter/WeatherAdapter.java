package by.grodno.toni7777.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.grodno.toni7777.weather.R;
import by.grodno.toni7777.weather.ui.model.WeatherDataDVO;
import by.grodno.toni7777.weather.ui.model.WeatherDayDVO;

import static by.grodno.toni7777.weather.util.Constants.*;

import by.grodno.toni7777.weather.util.TemperatureUtil;
import by.grodno.toni7777.weather.util.TimeUtil;
import by.grodno.toni7777.weather.util.WeatherUtil;

public class WeatherAdapter extends BaseAdapter {

    private List<WeatherDayDVO> mWeatherDays;
    private LayoutInflater mLayoutInflater;
    private String mTempUnit;
    private WeatherDataDVO mWeatherDataDVO;
    private int mDaysCount;
    private Context mContext;

    public WeatherAdapter(Context context, List<WeatherDayDVO> data) {
        this.mContext = context;
        this.mWeatherDays = data;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mWeatherDays.size();
    }

    @Override
    public WeatherDayDVO getItem(int position) {
        return mWeatherDays.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ProjectViewHolder holder = null;

        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.item_weather_days, parent, false);
            holder = new ProjectViewHolder();

            holder.weatherImage = (ImageView) view.findViewById(R.id.weather_image);
            holder.time = (TextView) view.findViewById(R.id.time);
            holder.weather = (TextView) view.findViewById(R.id.weather_cond);
            holder.dayTemp = (TextView) view.findViewById(R.id.day_temperature);
            holder.nightTemp = (TextView) view.findViewById(R.id.night_temperature);
            view.setTag(holder);
        } else {
            holder = (ProjectViewHolder) view.getTag();

        }

        WeatherDayDVO weatherDay = getItem(position);

        int id = WeatherUtil.getResWeatherImageId(weatherDay.getWeather().getId());
        if (id != NOT_FOUND) {
            holder.weatherImage.setImageResource(id);
        } else {
            holder.weatherImage.setImageResource(R.mipmap.ic_launcher);
        }

        holder.time.setText(TimeUtil.getDateTime(mContext, weatherDay.getTime()));
        holder.weather.setText(weatherDay.getWeather().getDescription());
        if (mTempUnit == null) {
            holder.dayTemp.setText(TemperatureUtil.tempDegreesCelsius(weatherDay.getTemperature().getDay()));
            holder.nightTemp.setText(TemperatureUtil.tempDegreesCelsius(weatherDay.getTemperature().getNight()));
        } else if (mTempUnit.equals(UNIT_CELSIUS)) {
            holder.dayTemp.setText(TemperatureUtil.tempDegreesCelsius(weatherDay.getTemperature().getDay()));
            holder.nightTemp.setText(TemperatureUtil.tempDegreesCelsius(weatherDay.getTemperature().getNight()));
        } else if (mTempUnit.equals(UNIT_FAHRENHEIT)) {
            holder.dayTemp.setText(TemperatureUtil.tempFahrenheit(weatherDay.getTemperature().getDay()));
            holder.nightTemp.setText(TemperatureUtil.tempFahrenheit(weatherDay.getTemperature().getNight()));
        } else {
            holder.dayTemp.setText(TemperatureUtil.tempDegreesCelsius(weatherDay.getTemperature().getDay()));
            holder.nightTemp.setText(TemperatureUtil.tempDegreesCelsius(weatherDay.getTemperature().getNight()));
        }

        return view;
    }

    private static class ProjectViewHolder {
        protected ImageView weatherImage;
        protected TextView time;
        protected TextView weather;
        protected TextView dayTemp;
        protected TextView nightTemp;
    }

    public void setNewWeatherData(WeatherDataDVO weatherDataDVO) {
        mWeatherDataDVO = weatherDataDVO;
        mWeatherDays.clear();
        List<WeatherDayDVO> validDays = new ArrayList<>();
        for (WeatherDayDVO day : weatherDataDVO.getWeatherDays()) {
            long utc = day.getTime();
            if (TimeUtil.validDate(utc)) {
                validDays.add(day);
            }
        }
        int dayCount = validDays.size();

        int days;
        if (dayCount >= mDaysCount) {
            days = mDaysCount;
        } else {
            days = dayCount;
        }

        for (int index = 0; index < days; index++) {
            mWeatherDays.add(validDays.get(index));
        }
        notifyDataSetChanged();
    }

    public void setTempUnit(String format) {
        mTempUnit = format;
        notifyDataSetChanged();
    }

    public WeatherDataDVO getWeatherDataDVO() {
        return mWeatherDataDVO;
    }

    public void setDaysCount(int count) {
        this.mDaysCount = count;

        if (mWeatherDataDVO != null) {
            mWeatherDays.clear();
            for (int index = 0; index < count; index++) {
                mWeatherDays.add(mWeatherDataDVO.getWeatherDays().get(index));
            }
            notifyDataSetChanged();
        }

    }

}
