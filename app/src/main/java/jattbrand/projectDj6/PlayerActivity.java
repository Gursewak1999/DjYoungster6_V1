package jattbrand.projectDj6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jackandphantom.blurimage.BlurImage;

import java.io.IOException;

import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.data.onGoingAudio;
import jattbrand.projectDj6.processes.getSongsData;

import static jattbrand.projectDj6.data.RunTimeData.isPlayerActivity;
import static jattbrand.projectDj6.data.RunTimeData.isSong_loaded;

public class PlayerActivity extends AppCompatActivity {

    private static float mediaFileLengthInMilliseconds;
    private static final Handler handler = new Handler();
    private String name;
    private static String url;
    private String cover;
    private String artist;
    private TextView artistTxt;
    private TextView nameTxt;
    private static TextView onGoingText;
    private static TextView totalText;
    private static SeekBar progressSeekBar;
    private ImageView coverImage;
    private ImageView bgCover;
    public static ImageButton playBtn;
    private ImageButton forwardBtn;
    private ImageButton reverseBtn;
    static ProgressBar progressBar;
    private static Boolean isPrepared;
    private static MediaPlayer mediaPlayer;
    private byte[] localCoverImg;
    private String totalTime;
    private String currentTime;
    private ImageButton backBtn;
    private static boolean isUpdating = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_player);

        totalTime = "--:--";
        currentTime = "--:--";


      Log.e("PlayerActivity :: ", "onCreate");

        isPlayerActivity = true;
        isPrepared = false;
        isSong_loaded = false;
        if (getIntent() == null) {

            Toast.makeText(this, "Error occured", Toast.LENGTH_SHORT).show();
            finish();
        }

        name = getIntent().getStringExtra("name");
        url = getIntent().getStringExtra("url");
        cover = getIntent().getStringExtra("cover");
        artist = getIntent().getStringExtra("artist");
        if (cover==null || cover.equals("")){
            new loadCover().execute();
        }else if (cover.equals("local")){
            localCoverImg = getIntent().getByteArrayExtra("coverBytes");
        }

        if(!url.contains(".mp3") && !url.contains("/storage")) {
            new getSongsData(url).start();
        }
        else {
            isSong_loaded = true;

        }
        initIDs();

        if (onGoingAudio.mp.isPlaying())
            playBtn.setImageResource(R.drawable.pause_solid);
        else
            playBtn.setImageResource(R.drawable.play_solid);


            if (url.equals(MusicService.pageUrl)) {
           // playBtn.setImageResource(R.drawable.pause_solid);
          //  new updateSeekbar().execute();
            //show now playing bar and time.
        } else {
            playBtn.setImageResource(R.drawable.play_solid);
            //show new bar and time.
        }


  /*      Glide
                .with(getApplicationContext())
                .asBitmap()
                .load(cover)
                .into(coverImage);
*/

        nameTxt.setText(name);
        artistTxt.setText(artist);
        // String songUrl = null;
        final String finalSongUrl = url;
     /*   playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSong_loaded) {
                    Log.e(url+" -- ", MusicService.pageUrl);

                    if (url.equals(MusicService.pageUrl)) {
                        //same song //should be paused

                        if (ismpPlaying) {
                            if (ismpPaused) {

                                //resume
                                ismpPaused = false;
                                startService(new Intent(getApplicationContext(), MusicService.class).setAction("RESUME"));
                                PlayerActivity.playBtn.setImageResource(R.drawable.pause_solid);

                            } else {

                                ismpPaused = true;
                                startService(new Intent(getApplicationContext(), MusicService.class).setAction("PAUSE"));
                                PlayerActivity.playBtn.setImageResource(R.drawable.play_solid);

                            }
                        } else {
                            //  onGoingAudio.url = url;
                            RunTimeData.serviceSongUrl = RunTimeData.currentSongData.getUrl_48();
                            //  onGoingAudio.ismpPlaying = true;

                            onGoingAudio.name = name;
                            onGoingAudio.pageUrl = url;
                            onGoingAudio.cover = cover;
                            onGoingAudio.artist = artist;
                            onGoingAudio.url = RunTimeData.currentSongData.getUrl_48();

                            startService(new Intent(getApplicationContext(), MusicService.class)
                                    .putExtra("name", name)
                                    .putExtra("pageUrl", url)
                                    .putExtra("cover", cover)
                                    .putExtra("artist", artist)
                                    .putExtra("url", RunTimeData.currentSongData.getUrl_48())
                                    .setAction("START"));
                            progressBar.setVisibility(View.VISIBLE);
                            playBtn.setVisibility(View.GONE);
                            ismpPaused = false;
                        }

                    } else {
                        //is a new song
                        if (isPrepared) {
                            onGoingAudio.mp = mediaPlayer;
                        }
                        //onGoingAudio.setRawData(name,url, RunTimeData.currentSongData.getUrl_48(), cover);
                        //onGoingAudio.url = url;
                        RunTimeData.serviceSongUrl = RunTimeData.currentSongData.getUrl_48();
                        //onGoingAudio.ismpPlaying = true;
                        onGoingAudio.name = name;
                        onGoingAudio.pageUrl = url;
                        onGoingAudio.cover = cover;
                        onGoingAudio.artist = artist;
                        onGoingAudio.url = RunTimeData.currentSongData.getUrl_48();

                        startService(new Intent(getApplicationContext(), MusicService.class)
                                .putExtra("name", name)
                                .putExtra("pageUrl", url)
                                .putExtra("cover", cover)
                                .putExtra("artist", artist)
                                .putExtra("url", RunTimeData.currentSongData.getUrl_48())
                                .setAction("START_OVER"));
                        progressBar.setVisibility(View.VISIBLE);
                        playBtn.setVisibility(View.GONE);
                        ismpPaused = false;
                    }

                } else Log.e("PLayer Activity:: ", "PlayBtn -- not ready yet");
            }
        });

*/
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSong_loaded) {
                    Log.e(url + " -- ", MusicService.pageUrl);

                        //same song //should be paused
                    if(url.equals(MusicService.pageUrl)){
                        //same Song
                        if (onGoingAudio.mp.isPlaying()){
                            //pause
                            onGoingAudio.mp.pause();
                            PlayerActivity.playBtn.setImageResource(R.drawable.play_solid);
                        }else{
                            onGoingAudio.mp.start();
                            PlayerActivity.playBtn.setImageResource(R.drawable.pause_solid);
                        }

                    }else{
                        //is a new Song
                        onGoingAudio.name = name;
                        onGoingAudio.pageUrl = url;
                        onGoingAudio.cover = cover;

                        if (cover.contains("local")){
                            onGoingAudio.coverbytes = localCoverImg;
                        }
                        onGoingAudio.artist = artist;
                        onGoingAudio.url = cover.contains("local")?url:RunTimeData.currentSongData.getUrl_48();

                        startService(new Intent(getApplicationContext(), MusicService.class)
                                .putExtra("name", name)
                                .putExtra("pageUrl", url)
                                .putExtra("cover", cover)
                                .putExtra("artist", artist)
                                .putExtra("url", cover.contains("local")?url:RunTimeData.currentSongData.getUrl_48())
                                .setAction("START"));
                        progressBar.setVisibility(View.VISIBLE);
                        playBtn.setVisibility(View.GONE);
                    }

                } else Log.e("PLayer Activity:: ", "PlayBtn -- not ready yet");
            }

        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        progressSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                updateProgressBar();
            }
        });
    }

    private void initIDs() {

        bgCover = findViewById(R.id.player_bg_cover);
        artistTxt = findViewById(R.id.player_artist);
        progressBar = findViewById(R.id.progressBar);
        nameTxt = findViewById(R.id.player_title);
        coverImage = findViewById(R.id.detail_cover);
        progressSeekBar = findViewById(R.id.player_seekbar);
        playBtn = findViewById(R.id.player_btn_play);
        forwardBtn = findViewById(R.id.player_btn_forward);
        reverseBtn = findViewById(R.id.player_btn_backward);
        backBtn = findViewById(R.id.detail_back_btn);
        onGoingText = findViewById(R.id.player_onGoingTime);
        totalText = findViewById(R.id.player_totalTime);
    }

    @Override
    protected void onDestroy() {

        Log.e("PlayerActivity :: ", "onDestroy");
        isSong_loaded = false;
        RunTimeData.currentSongData = null;
        isPlayerActivity = false;

        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e("PlayerActivity :: ", "onResume");

        new updateUi().execute();
        onGoingText.setText(currentTime);
        totalText.setText(totalTime);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.e("PlayerActivity :: ", "onPause");

        isUpdating = false;

        if (isSong_loaded) {
            totalTime = String.valueOf(onGoingAudio.mp.getDuration());
            currentTime = "00:00";
        }
    }

    class updateUi extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (cover!=null)
           Glide
                    .with(getApplicationContext())
                    .asBitmap()
                    .centerCrop()
                    .load(cover.equals("local")?localCoverImg:cover)
                    .addListener(new RequestListener<Bitmap>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(final Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    coverImage.setImageBitmap(resource);
                                    BlurImage.with(getApplicationContext())
                                            .load(resource)
                                            .intensity(15)
                                            .Async(false)
                                            .into(bgCover);

                         /*           Palette p = Palette.from(resource).generate();

                                    int dark = p.getLightVibrantSwatch().getRgb();
                                    nameTxt.setTextColor(dark);
                                    artistTxt.setTextColor(dark);
                                    onGoingText.setTextColor(dark);
                                    totalText.setTextColor(dark);
*/
                                }
                            });

                            return false;
                        }
                    }).submit();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (isSong_loaded) {
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(cover.contains("local")?url:RunTimeData.currentSongData.getUrl_48());
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.setOnPreparedListener(
                        new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                isPrepared = true;
                            }
                        });

                ///////set new time
                onGoingText.setText("00:00");
                totalText.setText("00:00");
                Log.e("Player ", "updating resources");

            }
        }

        @Override
        protected Void doInBackground(Void... voids) {

            while (!isSong_loaded) {
            }

            return null;
        }

    }


    private static String convertToClock(int tyme) {

        String time;
        int secondary_sec = tyme / 1000;
        int min = secondary_sec / 60;
        int sec = secondary_sec % 60;

        if (min <= 9) {

            if (sec <= 9) {
                time = "0" + min + ":" + "0" + sec;
            } else time = "0" + min + ":" + sec;
        } else {
            if (sec <= 9) {
                time = min + ":" + "0" + sec;
            } else time = min + ":" + sec;
        }

        return time;
    }

    public static void primarySeekBarProgressUpdater() {

        if(url.equals(MusicService.pageUrl)) {
            mediaFileLengthInMilliseconds = onGoingAudio.mp.getDuration();
            totalText.setText(convertToClock(onGoingAudio.mp.getDuration()));
            if (progressSeekBar != null) {
                progressSeekBar.setProgress((int) (((float) onGoingAudio.mp.getCurrentPosition() / mediaFileLengthInMilliseconds) * 100));
            }
            Runnable notification = new Runnable() {
                public void run() {
                    primarySeekBarProgressUpdater();
                }
            };
            handler.postDelayed(notification, 10);
            onGoingText.setText(convertToClock(onGoingAudio.mp.getCurrentPosition()));
        }

    }

    private static void updateProgressBar() {
       // if (sameSong)
            onGoingAudio.mp.seekTo((int) (progressSeekBar.getProgress() * mediaFileLengthInMilliseconds / 100));

    }

    private class loadCover extends AsyncTask<Void, Void, Void>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Glide
                    .with(getApplicationContext())
                    .asBitmap()
                    .centerCrop()
                    .load(cover.equals("local")?localCoverImg:cover)
                    .addListener(new RequestListener<Bitmap>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                            return false;
                        }
                        @Override
                        public boolean onResourceReady(final Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    coverImage.setImageBitmap(resource);
                                    BlurImage.with(getApplicationContext())
                                            .load(resource)
                                            .intensity(15)
                                            .Async(false)
                                            .into(bgCover);
                                }
                            });
                            return false;
                        }
                    }).submit();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            while(true){

                if (isSong_loaded){
                    break;
                }
            }
            cover = RunTimeData.currentSongData.getCoverUrl();

            return null;
        }
    }
}
