package jattbrand.projectDj6.Helper;


import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FILE {

    /**
     * Created by Tan on 2/18/2016.
     */
    public static final String onLoadDataFile = "data1.txt";
    private final static String basicPath = "";//Environment.getDataDirectory().getAbsolutePath();
    private final static String TAG = FILE.class.getName();

    public static String ReadFile(String folder, String fileName) {
        String line = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(basicPath + folder +"/"+ fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + System.getProperty("line.separator"));
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            Log.e(TAG, ex.getMessage());
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage());
        }
        return line;
    }

// --Commented out by Inspection START (23/06/2019 12:43 PM):
//    public static boolean saveToFile(String data, String folder, String fileName) {
//
//        try {
//            boolean pathCreated = new File(basicPath+folder).mkdir();
//
//            File file = new File(basicPath+folder +"/"+ fileName);
//            if (!file.exists()) {
//                boolean fileCreated = file.createNewFile();
//                Log.e("File Created == ", Boolean.toString(fileCreated));
//            }
//            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
//            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());
//            return true;
//        } catch (FileNotFoundException ex) {
//            Log.e(TAG, ex.getMessage());
//        } catch (IOException ex) {
//            Log.e(TAG, ex.getMessage());
//        }
//        return false;
//    }
// --Commented out by Inspection STOP (23/06/2019 12:43 PM)

    public static boolean overrideToFile(String data, String folder, String fileName) {

        try {
            boolean pathCreated = new File(basicPath+folder).mkdir();

            File file = new File(basicPath+folder +"/"+ fileName);
            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
            }else {
                file.delete();
                file.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());
            return true;
        } catch (FileNotFoundException ex) {
            Log.e(TAG, ex.getMessage());
        } catch (IOException ex) {
            Log.e(TAG, ex.getMessage());
        }
        return false;
    }


}
