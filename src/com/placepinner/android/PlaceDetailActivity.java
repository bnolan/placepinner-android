package com.placepinner.android;

import com.placepinner.android.DownloadImagesTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.Toast;

import android.net.Uri;

public class PlaceDetailActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_detail);
        
        MyApp app = (MyApp)getApplicationContext();
    	final Place place = app.getPlaceByUuid(getIntent().getExtras().getString("uuid"));
    	
    	if(place==null){
        	Log.i ("info", "Couldnt find place!");
        	return;
    	}
    	
		TextView textfield = (TextView) findViewById(R.id.textName);
		textfield.setText(place.getName());

		textfield = (TextView) findViewById(R.id.textAddress);
		textfield.setText(place.getAddress());

		textfield = (TextView) findViewById(R.id.textCity);
		textfield.setText("Locality: " + place.getLocality());

		textfield = (TextView) findViewById(R.id.textCountry);
		textfield.setText("Country: " + place.getCountry());

		textfield = (TextView) findViewById(R.id.textNotes);
		textfield.setText(place.getNotes());
		
		if(place.hasGeometry()){
			String mapUrl = "http://api.tiles.mapbox.com/v3/examples.map-vyofok3q/pin-m-star+88c(" +
				Double.toString(place.getLongitude()) + "," + Double.toString(place.getLatitude()) + ")/" + 
				Double.toString(place.getLongitude()) + "," + Double.toString(place.getLatitude()) + ",15/400x300.png";
			
			Log.i("info", mapUrl);
			
			ImageButton mButton = (ImageButton) findViewById(R.id.imageButtonMap);
			mButton.setTag(mapUrl);
	
			new DownloadImagesTask().execute(mButton);
			
			// Open the google maps app onclick
	        mButton.setOnClickListener(new View.OnClickListener() {
	        	@Override
	            public void onClick(View v) {
	            	Log.i ("info", "Clicked place " + place.getName());

	            	Uri uri = Uri.parse("geo:0,0?q=" + Double.toString(place.getLatitude()) + "," + Double.toString(place.getLongitude()) + "(" + place.getName() + ")");
	            	Intent intent = new Intent(Intent.ACTION_VIEW, uri);
	            	startActivity(intent);
	        	}
	        });
		}else{
        	Toast.makeText(getBaseContext(), "This place has no map location", Toast.LENGTH_SHORT).show();
		}
		
		Button button = (Button) findViewById(R.id.buttonSave);

		button.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View v) {
            	Intent i = new Intent(getApplicationContext(), PlaceEditActivity.class);
            	i.putExtra("uuid", place.getUuid());
            	startActivity(i);            	
        	}
        });
		
    }

    // Inflate the menu; this adds items to the action bar if it is present.
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

}
