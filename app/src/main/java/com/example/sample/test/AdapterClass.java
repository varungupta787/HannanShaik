package com.example.sample.test;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sample on 16-03-2016.
 */
public class AdapterClass extends RecyclerView.Adapter<ViewHolder> {
    int SIMPLE_CARD = 0, CHECKIN_CARD = 1, PHOTO_CARD = 2;
    private LayoutInflater inflater;
    private Context mContext;
    private ArrayList<Data.ViewData> viewData;
    private String TAG = "ÄdapterClass";

    AdapterClass(Context context, ArrayList<Data.ViewData> viewData) {
        inflater = LayoutInflater.from(context);
        mContext = context;
        this.viewData = viewData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        int layout;
        boolean isRecyclable = true;
        if (viewType == SIMPLE_CARD) {
            layout = R.layout.simple_card;
        } else if (viewType == CHECKIN_CARD) {
            layout = R.layout.checkin_card;
            isRecyclable = false;
        } else {
            layout = R.layout.photo_card;
        }
        view = inflater.inflate(layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.setIsRecyclable(isRecyclable);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Data.ViewData data = viewData.get(position);
        if (null != holder.title) {
            if (null != data.getTitle())
                holder.title.setText(data.getTitle());
            else
                holder.title.setVisibility(View.GONE);
        }
        if (null != holder.content) {
            if (data.getContent() != null)
                holder.content.setText(data.getContent());
            else
                holder.content.setVisibility(View.GONE);
        }
        if (null != holder.location_url) {
            if (data.getLocationUrl() != null)
                holder.location_url.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        launchBrowser(data.getLocationUrl());
                    }
                });
            else
                holder.location_url.setVisibility(View.GONE);
        }
        if (null != holder.image) {
            if (data.getBitmap() == null)
                new AsyncTaskClass(data, position, holder).execute();
            else
                holder.image.setImageBitmap(data.getBitmap());
        }
        if (null != holder.more_images) {
            if (data.getMoreImageURl() != null)
                holder.more_images.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        launchBrowser(data.getMoreImageURl());
                    }
                });
            else
                holder.more_images.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (viewData.get(position).getType() == null) {
            return PHOTO_CARD;
        } else if (viewData.get(position).getType().equalsIgnoreCase("checkin_card")) {
            return CHECKIN_CARD;
        } else if (viewData.get(position).getType().equalsIgnoreCase("simple_card")) {
            return SIMPLE_CARD;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return viewData.size();
    }

    void launchBrowser(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (browserIntent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(browserIntent);
        }
    }
}