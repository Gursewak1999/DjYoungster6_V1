package jattbrand.projectDj6.Helper;


import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.content.Context;
import android.os.Environment;


class DownloadData {

 
    public static void DownloadSong(Context context, String StringUrl){

        String[] Garbage_url_split = StringUrl.split("/");
        String FileName = Garbage_url_split[Garbage_url_split.length-1];

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(StringUrl));

        Environment
                .getExternalStoragePublicDirectory("/Music/DjYoungster.Com")
                .mkdirs();

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        downloadManager.enqueue(request
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                        DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(true)
                .setVisibleInDownloadsUi(true)
                .setTitle(FileName)
                .setDescription("By DjYoungster.Com")
 
                .setDestinationInExternalFilesDir(context,"/Music/DjYoungster.Com", FileName));
               // .setDestinationInExternalPublicDir("/Music/DjYoungster.Com", FileName));

    }
 

    public static void ShowDownload(Context context){
 

        Intent intent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
        context.startActivity(intent);
    }
}
