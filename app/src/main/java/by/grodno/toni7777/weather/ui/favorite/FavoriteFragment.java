package by.grodno.toni7777.weather.ui.favorite;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import by.grodno.toni7777.weather.R;
import by.grodno.toni7777.weather.db.DBService;
import by.grodno.toni7777.weather.db.model.WeatherDataDSO;
import io.realm.Realm;

public class FavoriteFragment extends ListFragment {

    private ArrayAdapter<String> mCityAdapter;
    private OnDataPass mDataPasser;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<String> cities = getFavoriteСities();
        mCityAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, cities);
        setListAdapter(mCityAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        passData(mCityAdapter.getItem(position));
    }

    private List<String> getFavoriteСities() {
        List<String> result = new ArrayList<>();
        List<WeatherDataDSO> favCities = DBService.getInstance().readAll(Realm.getDefaultInstance(), WeatherDataDSO.class);
        if (favCities != null) {
            for (WeatherDataDSO dataDSO : favCities) {
                result.add(dataDSO.getCity());
            }
        }
        return result;
    }

    public interface OnDataPass {
        void onDataPass(String cityName);
    }

    public void passData(String cityName) {
        mDataPasser.onDataPass(cityName);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnDataPass) {
            mDataPasser = (OnDataPass) activity;
        } else {
            throw new RuntimeException("Host activity must implements OnDataPass interface.");
        }
    }

}
