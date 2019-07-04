package jattbrand.projectDj6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import jattbrand.projectDj6.Helper.FILE;
import jattbrand.projectDj6.Helper.checkNet;
import jattbrand.projectDj6.Helper.reqPermissions;
import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.processes.getHomeData;
import jattbrand.projectDj6.processes.getLatest;
import jattbrand.projectDj6.processes.getRecommended;
import jattbrand.projectDj6.processes.getTopArtists;
import jattbrand.projectDj6.processes.getTrendings;
import jattbrand.projectDj6.processes.getYoutubeData;
import pub.devrel.easypermissions.EasyPermissions;

import static jattbrand.projectDj6.Helper.FILE.onLoadDataFile;
import static jattbrand.projectDj6.Helper.reqPermissions.PERMISSIONS;
import static jattbrand.projectDj6.data.RunTimeData.setData;

public class SplashActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private Context context;
    private Activity activity;
    private int granted = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        RunTimeData.isMainActivity = false;
        context = getApplicationContext();
        activity = this;
        //check if loaded data exists locally or not
        // startDataLoad();
///////////////////////////////////////

//startActivity(new Intent(this, ArtistActivity.class).putExtra("cover", "https://art.djyoungster.in/img/250x250/65062.jpg")
//        .putExtra("name", "Diljit Dosanjh"));


      //  finish();
        /////////////////////////////////

      //  new getTopArtists().start();
        init();
    }

    private final int PERMISSION_ALL = 1;

    int i = 0;

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (EasyPermissions.hasPermissions(this, PERMISSIONS)) {
                // Already have permission, do the thing
                Log.e("Check 1 :: ", "Internet");
                checkForInternet();
                // ...
            } else {
                // Do not have permissions, request them now

                EasyPermissions
                        .requestPermissions(this,
                                "This App requires some Permissions to Work upto the Mark." +
                                        "\nWould you like to Grant permissions ??",
                                PERMISSION_ALL, PERMISSIONS);
            }
        }
        //checkForInternet();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private void checkForInternet() {

        if (granted ==1 ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!reqPermissions.isConnectingToInternet(getApplicationContext())) {
                    final Snackbar snackbar = Snackbar.make(findViewById(R.id.post_script), "You are not Connected To Internet", Snackbar.LENGTH_LONG);
                    snackbar.setAction("Turn on.", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // createDialog(getApplicationContext());
                            reqPermissions.enableDisableMobileData(getApplicationContext());
                            snackbar.dismiss();
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    while (true) {
                                        if (reqPermissions.isConnectingToInternet(getApplicationContext())) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    startDataLoad();
                                                }
                                            });
                                            break;
                                        }
                                    }
                                }
                            }).run();
                        }
                    });

                    snackbar.setDuration(10000);
                    snackbar.show();

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startDataLoad();
                        }
                    });
                }
            }
        }
    }

    private void startDataLoad() {
        String data = FILE.ReadFile(context.getCacheDir().getAbsolutePath(), onLoadDataFile);

        if (data != null) {
            if (data.isEmpty()) {
                //empty need to fetch data
                //  Log.e("ONLOADdata EMPTY ", " cd");
                Toast.makeText(context, "First Time Initialising, May take a while.", Toast.LENGTH_LONG).show();

                onStartThread();

            } else {
                //not empty //can load data
                // Log.e("ONLOADdata EXISTS ", data);
                setData(data);
                startActivity(new Intent(context, Main.class));
                activity.finish();
                if (checkNet.isConnectingToInternet(context)) {
                    onStartThread();
                }
            }
        } else {
            //Log.e("ON LOAD data Null ", " cd");
            onStartThread();
        }
    }

    private void checkForProcessCompletion() {
        while (true) {
            if (RunTimeData.isYoutube_loaded
                    && RunTimeData.isHome_loaded
                    && RunTimeData.isLatest_loaded
                    && RunTimeData.isRecommended_loaded
                    && RunTimeData.isTrending_loaded
                    && RunTimeData.isTopArtists_loaded) {
                //TODO
                //context.startActivity(new Intent(context, Main.class));
                if (RunTimeData.isMainActivity) {
                    //refresh MainActivity Recycler

                    //Main.adapter.notifyDataSetChanged();
                    Main.updateRecycler();
                    Log.e(" MAIN Updater ", " now Updating");

                } else {
                    startActivity(new Intent(this, Main.class));
                }
                saveData();
                // activity.finish();
                break;
            }
        }
    }

    private void onStartThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new getRecommended().run();
                new getYoutubeData().run();
                new getHomeData().run();
                new getLatest().run();
                new getTrendings().run();
                new getTopArtists().run();

                checkForProcessCompletion();

                finish();
            }
        }).start();
    }

    private void saveData() {

        boolean saved = FILE.overrideToFile(RunTimeData.getDATA(), context.getCacheDir().getAbsolutePath(), onLoadDataFile);
        // Log.e("isSaved Data ::: ", Boolean.toString(saved));
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

        Log.e("Check 1 :: ", "Internet");

        checkForInternet();

        granted = 0;
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

       // init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < perms.size(); i++) {
                Log.e("perms:: ",perms.get(i));

                if (!EasyPermissions.hasPermissions(context,perms.get(i)))
                EasyPermissions
                        .requestPermissions(this,
                                "This App requires some Permissions to Work upto the Mark." +
                                        "\nWould you like to Grant permissions ??",
                                requestCode, perms.get(i));
            }
        }
    }

}
