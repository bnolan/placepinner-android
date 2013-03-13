package com.placepinner.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;
import android.app.ProgressDialog;
import android.widget.AdapterView;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

import com.placepinner.android.RetrievePlaces;
import com.placepinner.android.PlaceDetailActivity;
import com.placepinner.android.Place;

import java.util.ArrayList;

public class MainActivity extends Activity {
	public ProgressDialog progressDialog;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading places...");
        progressDialog.show();
        
        new RetrievePlaces(this).execute("http://placepinner.com/places.json");
        
        final ListView list = (ListView) findViewById(R.id.listView1);
        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	
        	@Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		String placeId = "1234-4567";
        		
            	Object o = list.getItemAtPosition(position);

            	// As you are using Default String Adapter
            	// String str = (String) o;
            	
            	Place place = (Place) o;
            	
            	// Toast.makeText(getBaseContext(), "Clicked " + place.getName(), Toast.LENGTH_SHORT).show();
            	
            	Log.i ("info", "Clicked id: " + place.getId());

            	Intent i = new Intent(getApplicationContext(), PlaceDetailActivity.class);
            	i.putExtra("id", place.getId());
            	startActivity(i);            	
        	}
        });
    }

    public void populatePlaces(Place[] places){
        ListView list= (ListView) findViewById(R.id.listView1);
        
        // ArrayAdapter<Place> adapter = new ArrayAdapter<Place>(this, android.R.layout.simple_list_item_1, android.R.id.text1, places);
        
        PlaceListAdapter adapter = new PlaceListAdapter(this, places);
        
        list.setAdapter(adapter); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    
}
