package com.placepinner.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlaceEditActivity extends Activity{

	private Place getPlace(){
        String id = getIntent().getExtras().getString("uuid");
        MyApp app = (MyApp)getApplicationContext();
    	return app.getPlaceByUuid(id);
	}
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_edit);
        
    	final Place place = getPlace();
    	if(place==null){
        	Log.i ("info", "Couldnt find place!");
        	return;
    	}
    	
		EditText field = (EditText) findViewById(R.id.editTextName);
		field.setText(place.getName());
		
		field = (EditText) findViewById(R.id.editTextAddress);
		field.setText(place.getAddress());
    	
		field = (EditText) findViewById(R.id.editTextNotes);
		field.setText(place.getNotes());
    	
		field = (EditText) findViewById(R.id.editTextPhone);
		field.setText(place.getPhone());
		
		Button button = (Button) findViewById(R.id.buttonSave);

		button.setOnClickListener(new View.OnClickListener() {
        	@Override
            public void onClick(View v) {
        		PlaceEditActivity.this.onSave();
        	}
        });
		
    }
	
	public void onSave(){
    	final Place place = getPlace();
    	
		EditText field = (EditText) findViewById(R.id.editTextName);
		place.setName(field.getText().toString());

		field = (EditText) findViewById(R.id.editTextAddress);
		place.setAddress(field.getText().toString());

		field = (EditText) findViewById(R.id.editTextNotes);
		place.setNotes(field.getText().toString());

		field = (EditText) findViewById(R.id.editTextPhone);
		place.setPhone(field.getText().toString());
		
		// So that we know to sync it with the website
		// place.setChanged();
		
		place.save();
		
		Intent i = new Intent(getApplicationContext(), PlaceDetailActivity.class);
    	i.putExtra("uuid", place.getUuid());
    	startActivity(i);            	
	}
    
}
