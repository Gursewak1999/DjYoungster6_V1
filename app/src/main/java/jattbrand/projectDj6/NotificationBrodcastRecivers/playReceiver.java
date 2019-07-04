package jattbrand.projectDj6.NotificationBrodcastRecivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import jattbrand.projectDj6.PlayerActivity;

public class playReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Notification ", "play btn Clicked");

        //context.startService(new Intent(context, MusicService.class).setAction("PLAY_PAUSE"));

        PlayerActivity.playBtn.callOnClick();

    }
}
