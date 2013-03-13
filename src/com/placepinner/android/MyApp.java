package com.placepinner.android;

import java.util.ArrayList;
import android.app.Application;
import android.util.Log;

import com.placepinner.android.Place;

public class MyApp extends Application {

	private ArrayList<Place> places;
	
	public Place getPlace(String id){
		for (Place p : places){
			if(p.getId().equals(id)){
				return p;
			}
		}
		return null;
	}
	
	public void resetPlaces(ArrayList<Place> places){
		this.places = places;
	}
	
}