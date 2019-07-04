package jattbrand.projectDj6;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import jattbrand.projectDj6.data.onGoingAudio;

import static jattbrand.projectDj6.PlayerActivity.primarySeekBarProgressUpdater;
import static jattbrand.projectDj6.data.RunTimeData.isPlayerActivity;


public class MusicService extends Service {

    private static final MediaPlayer mediaPl = new MediaPlayer();
    private String action = "";
    private Context context;
    private static String name;
    private static String artist;
    private static String cover;
    public static String pageUrl = "";
    private static String url = "";
    private static boolean isSongEnded = false;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
/*
    @Override
    public int onStartCommand(Intent _intent, int flags, int startId) {

        context = getApplicationContext();

        if (_intent != null) {
            this.action = _intent.getAction();
            Log.e("Music Service action ", action);
            //url = RunTimeData.serviceSongUrl;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                switch (action) {
                    case "START_OVER":

                        url = _intent.getStringExtra("url");
                        name = _intent.getStringExtra("name");
                        pageUrl = _intent.getStringExtra("pageUrl");
                        artist = _intent.getStringExtra("artist");
                        cover = _intent.getStringExtra("cover");

                        new NotifyTest(context, name, artist, cover).createNotification();
                        startForeground(6167141, NotifyTest.builder.build());
                        startWholeNewPlayingMedia(url);
                       // onGoingAudio.url = url;
                        Log.e(Tag, "starting all new media Playback");

                        break;
                    case "START":

                        url = _intent.getStringExtra("url");
                        name = _intent.getStringExtra("name");
                        pageUrl = _intent.getStringExtra("pageUrl");
                        artist = _intent.getStringExtra("artist");
                        cover = _intent.getStringExtra("cover");

                        new NotifyTest(context, name, artist, cover).createNotification();
                        startForeground(6167141, NotifyTest.builder.build());
                        startPlayingMedia(url);
                       // onGoingAudio.url = url;
                        Log.e(Tag, "starting new media Playback");

                        break;
                    case "STOP":
                        Log.e("Stoped Sticky", " " + url);

                        stopForeground(true);
                        stopSelf();
                        break;
                    case "PAUSE":

                        position = onGoingAudio.mp.getCurrentPosition();
                        onGoingAudio.mp.pause();

                        break;
                    case "RESUME":
                        onGoingAudio.mp.seekTo(position);
                        onGoingAudio.mp.start();
                        break;

                    case "PLAY_TRENDINGS" :
                        startWholeNewPlayingMedia(onGoingAudio.que.get(1).getPageUrl());
                }
            }
        } else {Log.e("Music Service ", " intent null");}
        return START_STICKY;
    }

    private void startPlayingMedia(String url) {
        //new NotifyTest(getApplicationContext(), RunTimeData.currentSongName, RunTimeData.currentArtist, RunTimeData.currentCoverUrl).createNotification();

        onGoingAudio.ismpPlaying = true;
        mediaPl = new MediaPlayer();
        mediaPl.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPl.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPl.prepareAsync();
        mediaPl.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                new PlayerActivity.updateSeekbar().execute();
                Log.e(Tag, "I am Prepared");

                onGoingAudio.mp = mp;
                mediaPl.stop();
                mediaPl.reset();
                new MusicService.updateProgress().execute();

                onGoingAudio.mp.start();
                onGoingAudio.ismpPlaying = true;
                onGoingAudio.mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mp, int percent) {
                        //
                        secondaryProgress = percent;
                    }
                });
            }
        });
        onGoingAudio.mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                Log.e(Tag, "Error I crashed, " + what + "  " + extra);
                return false;
            }
        });
    }

    private void startWholeNewPlayingMedia(String url) {

      //  MediaPlayer onGoingAudio.mp = new MediaPlayer();
        if (mediaPl!=null){
            mediaPl.stop();
            mediaPl.reset();
        }

        mediaPl.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPl.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPl.prepareAsync();
        mediaPl.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                Log.e(Tag, "!whole new ::" + "I am Prepared");
                if(onGoingAudio.mp!=null) {
                    onGoingAudio.mp.stop();
                    onGoingAudio.mp.reset();
                }
                onGoingAudio.mp = mp;
               // if (mediaPl.isPlaying())
               // mediaPl.stop();
               // mediaPl.reset();
              //  new MusicService.updateProgress().execute();
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        if(isPlayerActivity) {
                            PlayerActivity.playBtn.setImageResource(R.drawable.pause_solid);
                            PlayerActivity.playBtn.setVisibility(View.VISIBLE);
                            PlayerActivity.progressBar.setVisibility(View.GONE);
                        }
                    }
                });

                onGoingAudio.mp.start();
                onGoingAudio.ismpPlaying = true;
                
            }
        });
        mediaPl.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                Log.e(Tag, "Error I crashed, " + what + "  " + extra);
                return false;
            }
        });
        
        onGoingAudio.mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                secondaryProgress = percent;
            }
        });
        onGoingAudio.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                isSongEnded = true;
            }
        });
    }
//*/

    @Override
    public int onStartCommand(Intent _intent, int flags, int startId) {

        context = getApplicationContext();

        if (_intent != null) {
            this.action = _intent.getAction();
            Log.e("Music Service action ", action);
            //url = RunTimeData.serviceSongUrl;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                switch (action) {

                    case "START":

                        url = _intent.getStringExtra("url");
                        name = _intent.getStringExtra("name");
                        pageUrl = _intent.getStringExtra("pageUrl");
                        artist = _intent.getStringExtra("artist");
                        cover = _intent.getStringExtra("cover");

                        new NotifyTest(context, name, artist, cover).createNotification();

                        startForeground(6167141, NotifyTest.builder.build());
                        startPlayingMedia(url);
                        // onGoingAudio.url = url;
                        break;

                    case "STOP":
                        Log.e("Stoped Sticky", " " + url);

                        stopForeground(true);
                        stopSelf();
                        break;

                    case "PAUSE":
                        onGoingAudio.mp.pause();
                        break;

                    case "RESUME":
                        onGoingAudio.mp.start();
                        break;
                }
            }
        } else {Log.e("Music Service ", " intent null");}
        return START_STICKY;
    }

    private void startPlayingMedia(String url) {

       if(mediaPl!=null){
           if (mediaPl.isPlaying())
           mediaPl.stop();
           mediaPl.reset();
       }

        mediaPl.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPl.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPl.prepareAsync();

        mediaPl.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                Log.e("Prepared ", "mp");
                onGoingAudio.mp = mp;
                onGoingAudio.mp.start();

                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        if(isPlayerActivity) {
                            PlayerActivity.playBtn.setImageResource(R.drawable.pause_solid);
                            PlayerActivity.playBtn.setVisibility(View.VISIBLE);
                            PlayerActivity.progressBar.setVisibility(View.GONE);
                        }
                    }
                });

                //start Updating progress
               // new updateProgress().execute();
                primarySeekBarProgressUpdater();

            }
        });

        onGoingAudio.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                isSongEnded = true;
            }
        });

        onGoingAudio.mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                onGoingAudio.secondaryProgress = percent;
            }
        });


    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);

    }

}
