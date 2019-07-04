package jattbrand.projectDj6.Helper;

import java.io.File;
import java.util.ArrayList;

public class Helper {

    public static ArrayList<File> getLocalSongs(File file){

        ArrayList<File>  fileArrayList = new ArrayList<>();

        File[] files = file.listFiles();

        for(File file1 : files){

            if (file1.isDirectory() && !file1.isHidden()){
                fileArrayList.addAll(getLocalSongs(file1));
            }else{
                if (file1.getName().endsWith(".mp3")){

                    fileArrayList.add(file1);
                }
            }
        }

        return fileArrayList;
    }

/*    private void createDialog(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_dialog, null);

        ArrayList<String> data = new ArrayList<>();
        final ArrayList<String> links = new ArrayList<>();

        for (int i = 1; i < RunTimeData.currentLinksDataKey.size(); i++) {
            data.add(RunTimeData.currentLinksDataKey.get(i));
            links.add(RunTimeData.currentLinksDataValue.get(i));
        }

       ListView gridView = view.findViewById(R.id.download_list);

        gridView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data));


        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Download Options");
        // this is set the view from XML inside AlertDialog
        alert.setView(view);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        //alert.setButton
        //  animationDrawable.start();
        final AlertDialog dialog = alert.create();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.MyDialogAnimation;
        }
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DownloadData.DownloadSong(context, links.get(position));
                dialog.dismiss();
            }
        });

    }

//*/
}
