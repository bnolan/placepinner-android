package com.placepinner.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.placepinner.android.Place;

public class PlaceListAdapter extends ArrayAdapter<Place> {
	private Context context;
	private Place[] places; 

	public PlaceListAdapter(Context context, Place[] places){
		super(context, R.layout.place_list, places);
		this.context = context;
		this.places = places;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		Place place = places[position];
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.place_list, parent, false);
		
		TextView textView = (TextView) rowView.findViewById(R.id.txtName);
		textView.setText(place.getName());


		textView = (TextView) rowView.findViewById(R.id.txtAddress);
		textView.setText(place.getAddress());
		
		return rowView;
	}
}



//public class MySimpleArrayAdapter extends ArrayAdapter<String> {
//  private final Context context;
//  private final String[] values;
//
//  public MySimpleArrayAdapter(Context context, String[] values) {
//    super(context, R.layout.rowlayout, values);
//    this.context = context;
//    this.values = values;
//  }
//
//  @Override
//  public View getView(int position, View convertView, ViewGroup parent) {
//    LayoutInflater inflater = (LayoutInflater) context
//        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
//    TextView textView = (TextView) rowView.findViewById(R.id.label);
//    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
//    textView.setText(values[position]);
//    // Change the icon for Windows and iPhone
//    String s = values[position];
//    if (s.startsWith("iPhone")) {
//      imageView.setImageResource(R.drawable.no);
//    } else {
//      imageView.setImageResource(R.drawable.ok);
//    }
//
//    return rowView;
//  }
//} 