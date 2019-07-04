package jattbrand.projectDj6;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import jattbrand.projectDj6.data.onGoingAudio;

import static android.content.Context.NOTIFICATION_SERVICE;
import static jattbrand.projectDj6.data.onGoingAudio.cover;
import static jattbrand.projectDj6.data.onGoingAudio.coverbytes;

class NotifyTest {

    private RemoteViews remoteViews;
    private final Context context;
    private static int NotificationId = 0;
    private final String title;
    private final String artist;
    private final String imageUrl;
    static NotificationCompat.Builder builder;
    private NotificationManager notificationManager;


    NotifyTest(Context context, String song, String artist, String ImageUrl) {
        this.context = context;
        this.imageUrl = ImageUrl;
        this.title = song;
        this.artist = artist;
    }


// --Commented out by Inspection START (23/06/2019 12:43 PM):
//    private void initialise() {
//
//        NotificationId = 6167141;
//
//        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
//        remoteViews = new RemoteViews(context.getPackageName(), R.layout.custom_media_notifications);
//
//        remoteViews.setTextViewText(R.id.notification_title, title);
//        remoteViews.setTextViewText(R.id.notification_sub_title, artist);
//        remoteViews.setImageViewResource(R.id.notification_image, R.drawable.songs_placeholder);
//        remoteViews.setImageViewResource(R.id.notification_btn_play, R.drawable.play_solid);
//        remoteViews.setImageViewResource(R.id.notification_btn_next, R.drawable.step_forward_solid);
//        remoteViews.setImageViewResource(R.id.notification_btn_previous, R.drawable.step_backward_solid);
//
//        Intent intentPlay = new Intent("notify_btn_play").putExtra("id", NotificationId);
//        Intent intentNext = new Intent("notify_btn_playNext").putExtra("id", NotificationId);
//        Intent intentPrevious = new Intent("notify_btn_playPrevious").putExtra("id", NotificationId);
//        Intent intentClose = new Intent("notify_btn_close").putExtra("id", NotificationId);
//
//        PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(context, 0, intentPlay, 0);
//        PendingIntent pendingIntentNext = PendingIntent.getBroadcast(context, 0, intentNext, 0);
//        PendingIntent pendingIntentPrevious = PendingIntent.getBroadcast(context, 0, intentPrevious, 0);
//        PendingIntent pendingIntentClose = PendingIntent.getBroadcast(context, 0, intentClose, 0);
//
//        remoteViews.setOnClickPendingIntent(R.id.notification_btn_play, pendingIntentPlay);
//        remoteViews.setOnClickPendingIntent(R.id.notification_btn_next, pendingIntentNext);
//        remoteViews.setOnClickPendingIntent(R.id.notification_btn_previous, pendingIntentPrevious);
//        remoteViews.setOnClickPendingIntent(R.id.notification_btn_close, pendingIntentClose);
//
//    }
// --Commented out by Inspection STOP (23/06/2019 12:43 PM)

    void createNotification() {

      //  initialise();
        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

  //      Intent notification_intent = new Intent(context, NotifyTest.class);
  //      PendingIntent notification_pending_intent = PendingIntent.getActivity(context, 0, notification_intent, 0);

 /*       builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.icon_76x76);
      //  builder.setCustomContentView(remoteViews);
        builder.setStyle(new androidx.media.app.NotificationCompat.MediaStyle());
        builder.setContentIntent(notification_pending_intent);
        builder.setAutoCancel(false);
        builder.setOngoing(true);
//*/
//        BitmapFactory.decodeByteArray(coverbytes, 0, coverbytes.length);

        Intent intentPlay = new Intent("notify_btn_play").putExtra("id", NotificationId);
        Intent intentNext = new Intent("notify_btn_playNext").putExtra("id", NotificationId);
        Intent intentPrevious = new Intent("notify_btn_playPrevious").putExtra("id", NotificationId);

        PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(context, 0, intentPlay, 0);
        PendingIntent pendingIntentNext = PendingIntent.getBroadcast(context, 0, intentNext, 0);
        PendingIntent pendingIntentPrevious = PendingIntent.getBroadcast(context, 0, intentPrevious, 0);

        builder = new NotificationCompat.Builder(context)

              //  .setDefaults(Notification.BADGE_ICON_LARGE)

                .setSmallIcon(R.drawable.logo_notify)
                .setContentTitle(title)
                .setContentText(artist)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
               // .setLargeIcon(R.drawable.icon_notify)
                .setNumber(1)
                .setContentIntent(
                        PendingIntent.getActivity(
                                context,
                                0,
                                new Intent(context, PlayerActivity.class)
                                        .putExtra("url", onGoingAudio.pageUrl)
                                        .putExtra("cover", cover)
                                        .putExtra("coverBytes", !cover.equals("local") ? "" : coverbytes)
                                        .putExtra("name", onGoingAudio.name)
                                        .putExtra("artist", onGoingAudio.artist),
                                PendingIntent.FLAG_UPDATE_CURRENT))

                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle())
               // .setAutoCancel(true)
                .addAction(
                        R.drawable.ic_skip_previous_black_24dp,
                        "Previous",
                       pendingIntentPrevious)
                .addAction(
                        R.drawable.ic_play_arrow_black_24dp,
                        "Play",
                        pendingIntentPlay)
                .addAction(
                        R.drawable.ic_skip_next_black_24dp,
                        "Next",
                        pendingIntentNext);

//*/
  /*      NotificationTarget notificationTarget = new NotificationTarget(
                context,
                R.id.notification_image,
                remoteViews,
                builder.build(),
                NotificationId);

        Glide
                .with(context.getApplicationContext())
                .asBitmap()
                .load(imageUrl)
                .transform(new RoundedCorners(16))
                .into(notificationTarget);
//*/
       // notificationManager.notify(NotificationId, builder.build());
    }
}
