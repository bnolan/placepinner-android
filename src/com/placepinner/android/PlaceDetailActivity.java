package com.placepinner.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class PlaceDetailActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_detail);
        
        String id = getIntent().getExtras().getString("id");

        MyApp app = (MyApp)getApplicationContext();

    	Place place = app.getPlace(id);
        
    	Log.i ("info", "Showing id #" + id);
    	
    	if(place==null){
        	Log.i ("info", "Couldnt find place #" + id);
    	}else{
			TextView textName = (TextView) findViewById(R.id.textName);
			textName.setText(place.getName());
	
			TextView textNotes = (TextView) findViewById(R.id.textNotes);
			textNotes.setText(place.getNotes());	
    	}
			
    }

    // Inflate the menu; this adds items to the action bar if it is present.
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

}
