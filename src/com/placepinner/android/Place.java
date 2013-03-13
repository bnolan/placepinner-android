package com.placepinner.android;

import android.util.Log;
import org.json.*;

public class Place {
	   String name;
	   String id;
	   String address;

	   public Place(String id){
		   this.id = id;
	   }
	   
	   public Place(JSONObject obj){
	        try {
	   			this.name = obj.getString("name");
	   			this.address = obj.getString("address");
	   			this.id = Integer.toString(obj.getInt("id"));
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
}
