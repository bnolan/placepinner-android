package com.placepinner.android;

import android.location.Location;
import com.androidrecord.*;
import android.location.LocationManager;
import android.util.Log;
import org.json.*;

public class Place extends ActiveRecordBase<Place> {
	   public Long id;
	   public String name;
	   public String uuid;
	   public String address;
	   public String notes;
	   public String country;
	   public String phone;
	   public String website;
	   public String locality;
	   public Double latitude;
	   public Double longitude;
	   public DateTime created_at;
	   
	   public Place(){
		   super();
	   }
	   
	   public Place(String uuid){
		   this.uuid = uuid;
	   }
	   
	   public Place(JSONObject obj){
	        try {
	   			this.name = obj.getString("name");
	   			this.address = obj.getString("address");
	   			this.phone = obj.getString("phone");
	   			this.website = obj.getString("website");
	   			this.notes = obj.getString("notes");
	   			this.country = obj.getString("country");
	   			this.locality = obj.getString("locality");
	   			this.uuid = obj.getString("id"); // Integer.toString(obj.getInt("id"));
	   			this.latitude = obj.getDouble("latitude");
	   			this.longitude = obj.getDouble("longitude");
	        }catch(Exception e){
	        	Log.i ("info", "Unable to parse json into place...");
	        	// Don't care...
	   		}
	   }

	   public String getUuid(){
		   return this.uuid;
	   }
	   
	   public String getName(){
		   return this.name;
	   }
	   
	   public void setName(String x){
		   this.name = x;
	   }
	   
	   public String getAddress(){
		   return this.address;
	   }
	   
	   public void setAddress(String x){
		   this.address = x;
	   }
	   
	   public String getNotes(){
		   return this.notes;
	   }
	   
	   public void setNotes(String x){
		   this.notes = x;
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
	   
	   public String getLocality(){
		   return this.locality;
	   }
	   
	   public String getCountry(){
		   return this.country;
	   }
	   
	   public String getPhone(){
		   return this.phone;
	   }
	   
	   public void setPhone(String x){
		   this.phone = x;
	   }
	   
	   public String getWebsite(){
		   return this.website;
	   }
	   
	   public Location getLocation(){
		   if(!this.hasGeometry()){
			   return null;
		   }

		   Location l = new Location(LocationManager.NETWORK_PROVIDER);
	       
		   l.setLatitude(this.latitude);
	       l.setLongitude(this.longitude);
	       
	       return l;
	   }

       
}
