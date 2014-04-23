package com.capstone.schoolhelper;

import com.capstone.schoolhelper.SQLEvent;
import com.capstone.schoolhelper.SQLHandler;
import android.widget.ArrayAdapter;
import java.util.List;

public class EventAdapter extends ClassMenuFrag{

	SQLHandler db;
	
	public ArrayAdapter<String> geteventnames(){
	
	List<SQLEvent> alleventnames = db.getEventNames();
	String[] alleventsarray = alleventnames.toArray(new String[0]);
	
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1, alleventsarray);
	
	return adapter;
	}
}
