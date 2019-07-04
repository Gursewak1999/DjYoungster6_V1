package jattbrand.projectDj6.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

import jattbrand.projectDj6.PlayerActivity;
import jattbrand.projectDj6.R;
import jattbrand.projectDj6.data.RunTimeData;


class LocalSongsAdapter extends RecyclerView.Adapter<LocalSongsAdapter.artistViewHolder> {

    private Context context;
    private ArrayList<File> files = new ArrayList<>();
    private String urlStr, artistStr, nameStr;
    private byte[] localCoverArt;

    public LocalSongsAdapter(ArrayList<File> files) {
        this.files = files;
    }

    @NonNull
    @Override
    public artistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_local_cards, viewGroup, false);

        return new artistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final artistViewHolder viewHolder, final int position) {

        File file = files.get(position);
        urlStr = file.getPath();
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(context, Uri.parse(urlStr));
        artistStr = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);

        localCoverArt = mmr.getEmbeddedPicture();
        nameStr = file.getName().replace(".mp3", "");

        viewHolder.name.setText(nameStr);
        viewHolder.name.setSingleLine();

        if (localCoverArt != null)
            Glide.with(context)
                    .asBitmap()
                    .centerCrop()
                    .load(localCoverArt)
                    .into(viewHolder.cover);
        else
            Glide.with(context)
                    .asBitmap()
                    .centerCrop()
                    .load(R.drawable.nocoverart)
                    .into(viewHolder.cover);


        viewHolder.cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = files.get(position);
                urlStr = file.getPath();
                MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                mmr.setDataSource(context, Uri.parse(urlStr));
                artistStr = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);

                localCoverArt = mmr.getEmbeddedPicture();
                nameStr = file.getName().replace(".mp3", "");

                context.startActivity(new Intent(context, PlayerActivity.class)
                        .putExtra("url",urlStr)
                        .putExtra("cover", "local")
                        .putExtra("coverBytes", localCoverArt)
                        .putExtra("name", nameStr)
                        .putExtra("artist", artistStr));

            }
        });
    }

    @Override
    public int getItemCount() {
        return RunTimeData.topArtists.size();
    }

    class artistViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final ImageView cover;

        artistViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.card_text);
            cover = itemView.findViewById(R.id.card_image);
        }
    }
}
