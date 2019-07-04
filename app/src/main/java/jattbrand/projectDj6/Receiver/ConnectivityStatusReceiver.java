package jattbrand.projectDj6.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityStatusReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {

    final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

    NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

    if (activeNetworkInfo != null) {
     // Toast.makeText(context, activeNetworkInfo.getTypeName() + " connected", Toast.LENGTH_SHORT).show();

    } else {
      //Toast.makeText(context, "No Internet or Network connection available", Toast.LENGTH_LONG).show();
    }
  }

}