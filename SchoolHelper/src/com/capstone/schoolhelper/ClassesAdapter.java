package com.capstone.schoolhelper;

import com.capstone.schoolhelper.SQLClass;
import com.capstone.schoolhelper.SQLHandler;
import android.widget.ArrayAdapter;
import java.util.List;

public class ClassesAdapter extends ClassesMenuFrag {

	SQLHandler db;
	
	public ArrayAdapter<String> getclassesnames(){
	
	List<SQLClass> allclassesnames = db.getClassNames();
	String[] allclassesarray = allclassesnames.toArray(new String[0]);
	
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1, allclassesarray);
	
	return adapter;


	}
}
