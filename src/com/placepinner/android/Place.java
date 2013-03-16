package com.placepinner.android;

import android.util.Log;
import org.json.*;

public class Place {
	   String name;
	   String id;
	   String address;
	   String notes;
	   Double latitude;
	   Double longitude;

	   public Place(String id){
		   this.id = id;
	   }
	   
	   public Place(JSONObject obj){
	        try {
	   			this.name = obj.getString("name");
	   			this.address = obj.getString("address");
	   			this.notes = obj.getString("notes");
	   			this.id = obj.getString("id"); // Integer.toString(obj.getInt("id"));
	   			this.latitude = obj.getDouble("latitude");
	   			this.longitude = obj.getDouble("longitude");
	        }catch(Exception e){
	        	Log.i ("info", "Unable to parse json into place...");
	        	// Don't care...
	   		}
	   }

	   public String getId(){
		   return this.id;
	   }
	   
	   public String getName(){
		   return this.name;
	   }
	   
	   public String getAddress(){
		   return this.address;
	   }
	   
	   public String getNotes(){
		   return this.notes;
	   }
	   
	   public Double getLatitude(){
		   return this.latitude;
	   }
	   
	   public Double getLongitude(){
		   return this.longitude;
	   }
	   
	   public Boolean hasGeometry(){
		   return this.latitude != null;
	   }
}
