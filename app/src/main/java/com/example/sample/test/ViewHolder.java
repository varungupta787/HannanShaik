package com.example.sample.test;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sample on 16-03-2016.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    TextView title, content, more_images;
    ImageView image, location_url;

    public ViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        content = (TextView) itemView.findViewById(R.id.content);
        image = (ImageView) itemView.findViewById(R.id.photo);
        location_url = (ImageView) itemView.findViewById(R.id.location_url);
        more_images = (TextView) itemView.findViewById(R.id.more_images);
    }
}
