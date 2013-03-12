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

import com.placepinner.android.RetrievePlaces;

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
        		
            	Log.i ("info", "Detected list click");

            	Object o = list.getItemAtPosition(position);

            	// As you are using Default String Adapter
            	String str = (String) o;
            	
            	Toast.makeText(getBaseContext(), "Clicked " + str, Toast.LENGTH_SHORT).show();
        	}
        });
    }

    public void populateCountriesList(String[] values){
        ListView list= (ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        list.setAdapter(adapter); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    
}
