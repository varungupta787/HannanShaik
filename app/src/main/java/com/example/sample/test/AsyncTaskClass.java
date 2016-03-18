package com.example.sample.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

/**
 * Created by sample on 17-03-2016.
 */
public class AsyncTaskClass extends AsyncTask<Void, Void, Bitmap> {
    Data.ViewData data;
    ViewHolder holder;
    String TAG = "ÄsyncTaskClass";

    public AsyncTaskClass(Data.ViewData data, int position, ViewHolder holder) {
        this.data = data;
        this.holder = holder;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (result != null) {
            holder.image.setImageBitmap(result);
            data.setBitmap(result);
        }
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        Bitmap bitmap;
        try {
            URL imageURL = new URL(data.getImageUrl());
            Log.e(TAG, "doInBackground : Starting download");
            bitmap = BitmapFactory.decodeStream(imageURL.openStream());
        } catch (IOException e) {
            Log.e(TAG, "Downloading Image Failed");
            bitmap = null;
        }
        return bitmap;
    }
}
