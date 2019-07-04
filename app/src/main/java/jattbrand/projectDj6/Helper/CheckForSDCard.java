package jattbrand.projectDj6.Helper;

import android.os.Environment;

public class CheckForSDCard {
    //Check If SD Card is present or not method
    public static boolean isSDCardPresent() {
        return Environment.getExternalStorageState().equals(

                Environment.MEDIA_MOUNTED);
    }
}