package com.placepinner.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;

//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.net.URLConnection;
 
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ListView listView = (ListView) findViewById(R.id.listView1);
        String[] values = new String[] { "Australia", "Austria", "Germany", "New Zealand", "United States", "Australia", "Austria", "Germany", "New Zealand", "United States", "Australia", "Austria", "Germany", "New Zealand", "United States", "Australia", "Austria", "Germany", "New Zealand", "United States" };
        
        InputStream response = this.getStream("http://placepinner.com/");
        Log.i ("info", "hello!" );
        
        String str = "blah";

        try{
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        byte[] buffer = new byte[1024];
	        int length = 0;
	        while ((length = response.read(buffer)) != -1) {
	            baos.write(buffer, 0, length);
	        }
	        str = new String(baos.toByteArray());
    	} catch (Exception ex) {
    		Log.i ("Exception!", ex.toString() );
    	}
        
        
        Log.i ("info", str);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter); 
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private InputStream getStream(String inputUrl) {
        try {
            URL url = new URL(inputUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(5000);
            return urlConnection.getInputStream();
        } catch (Exception ex) {
            Log.i ("Exception!", ex.toString() );
            Log.i ("info", ex.toString() );
            return null;
        }
    }
    
}
