package com.placepinner.android;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.util.Log;
import android.os.AsyncTask;
import android.app.ProgressDialog;
import org.json.*;
import java.util.ArrayList;
import java.util.Collections;

import com.placepinner.android.MainActivity;

public class RetrievePlaces extends AsyncTask<String, Void, String> {
    private Exception exception;
    private MainActivity activity;

    public RetrievePlaces(MainActivity a){
    	this.activity = a;
    }
    
    protected String doInBackground(String... urls) {
    	InputStream stream;
    	
        try {
            URL url = new URL(urls[0]);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(5000);
            stream = urlConnection.getInputStream();
        } catch (Exception ex) {
            Log.i ("Exception!", ex.toString() );
            Log.i ("info", ex.toString() );
            return null;
        }

    	String str = "";
    	
        try{
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        byte[] buffer = new byte[1024];
	        int length = 0;
	        while ((length = stream.read(buffer)) != -1) {
	            baos.write(buffer, 0, length);
	        }
	        str = new String(baos.toByteArray());
    	} catch (Exception ex) {
    		Log.i ("Exception!", ex.toString() );
    	}

        return str; 
    }

//    protected RSSFeed doInBackground(String... urls) {
//        try {
//            URL url= new URL(urls[0]);
//            SAXParserFactory factory =SAXParserFactory.newInstance();
//            SAXParser parser=factory.newSAXParser();
//            XMLReader xmlreader=parser.getXMLReader();
//            RssHandler theRSSHandler=new RssHandler();
//            xmlreader.setContentHandler(theRSSHandler);
//            InputSource is=new InputSource(url.openStream());
//            xmlreader.parse(is);
//            return theRSSHandler.getFeed();
//        } catch (Exception e) {
//            this.exception = e;
//            return null;
//        }
//    }

    protected void onPostExecute(String output) {
    	Log.i ("info", "HTTP Fetch complete");
        // Log.i ("info", output);

    	ArrayList<String> countries = new ArrayList<String>();

        try{
	        JSONArray jArray = new JSONArray(output);
	        
	        for (int i=0; i < jArray.length(); i++)
	        {
	            JSONObject place = jArray.getJSONObject(i);
	            String country = place.getString("country");
	        	countries.add(country);
	        }
        }catch(Exception e){
        	Log.i ("info", "Error parsing json");
        }
        
        Collections.sort(countries);

        activity.progressDialog.dismiss();

        activity.populateCountriesList(countries.toArray(
        	new String[countries.size()]
        ));
        
        // TODO: check this.exception 
        // TODO: do something with the feed
    }
}
