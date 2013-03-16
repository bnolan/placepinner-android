package com.placepinner.android;

import android.util.Log;
import android.os.AsyncTask;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import android.widget.ImageButton;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DownloadImagesTask extends AsyncTask<ImageButton, Void, Bitmap> {
	
	ImageButton button = null;
	
	@Override
	protected Bitmap doInBackground(ImageButton... b) {
	    this.button = b[0];
	    return downloadImage((String)button.getTag());
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
	    button.setImageBitmap(result);
	}
	
	
	private Bitmap downloadImage(String url) {
	    Bitmap bm = null;

	    try {
	        URL aURL = new URL(url);
	        URLConnection conn = aURL.openConnection();
	        conn.connect();
	        InputStream is = conn.getInputStream();
	        BufferedInputStream bis = new BufferedInputStream(is);
	        bm = BitmapFactory.decodeStream(bis);
	        bis.close();
	        is.close();
	    } catch (IOException e) {
	        Log.e("Hub","Error getting the image from server : " + e.getMessage().toString());
	    } 
	    return bm;		
	}
}

