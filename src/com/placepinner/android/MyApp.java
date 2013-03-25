package com.placepinner.android;

import java.util.ArrayList;
import android.app.Application;
import android.util.Log;

import com.placepinner.android.Place;
import com.androidrecord.db.DatabaseManager;

public class MyApp extends Application {

	private ArrayList<Place> places;

	public void createDatabase(){
		DatabaseManager databaseManager = new DatabaseManager(getApplicationContext());
		databaseManager.bootStrapDatabase();
	}
	
	public Place getPlaceByUuid(String uuid){
		return new Place().find("uuid='" + uuid + "'");
//		for (Place p : places){
//			if(p.getId().equals(id)){
//				return p;
//			}
//		}
//		return null;
	}
	
	public void resetPlaces(ArrayList<Place> places){
		this.places = places;
	}
	
}