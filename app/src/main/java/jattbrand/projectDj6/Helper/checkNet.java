package jattbrand.projectDj6.Helper;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.Context;

public class checkNet {

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager
                    .getActiveNetworkInfo();
        }
        return networkInfo != null && networkInfo.isConnected();
    }
}
