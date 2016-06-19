package by.grodno.toni7777.weather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
public class NetworkUtil {

    public static boolean hasConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private NetworkUtil() {
    }
}
