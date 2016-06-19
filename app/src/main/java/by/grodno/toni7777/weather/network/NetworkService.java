package by.grodno.toni7777.weather.network;

import android.support.v4.util.LruCache;

import by.grodno.toni7777.weather.util.Constants;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public final class NetworkService {

    private WeatherService weatherService;
    private OkHttpClient okHttpClient;
    private LruCache<Class<?>, Observable<?>> apiObservables;

    public NetworkService() {
        init();
    }

    private void init() {
        okHttpClient = newClient();
        apiObservables = new LruCache<>(10);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        weatherService = retrofit.create(WeatherService.class);
    }

    public WeatherService geWeatherService() {
        return weatherService;
    }

    public OkHttpClient newClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(chain -> {
            Response response = chain.proceed(chain.request());
            return response;
        });

        builder.addInterceptor(chain -> {
            Request request = chain.request().newBuilder().build();
            return chain.proceed(request);
        });

        return builder.build();
    }

    public void clearCache() {
        apiObservables.evictAll();
    }

    //I  failed to properly use Cache  =((
    public Observable<?> getPreparedObservable(Observable<?> unPreparedObservable, Class<?> clazz, boolean cacheObservable, boolean useCache) {
        Observable<?> preparedObservable = null;
        if (useCache) {
            preparedObservable = apiObservables.get(clazz);
        }
        if (preparedObservable != null) {
            return preparedObservable;
        }
        preparedObservable = unPreparedObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        if (cacheObservable) {
            preparedObservable = preparedObservable.cache();
            apiObservables.put(clazz, preparedObservable);
        }
        return preparedObservable;
    }
}