package com.capstone.schoolhelper;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;

public class ClassesMenuFrag extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classes_menu);

		ListView lvClasses = (ListView) findViewById(R.id.lvClasses);
		//String[] items = SQLHandler.getClassesNameLoc();
		
		/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		lvClasses.setAdapter(adapter);
*/
		Button btnAddClass = (Button) findViewById(R.id.btnAddClass);
		btnAddClass.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Intent nextScreen = new Intent(getApplicationContext(),
						AddClassFrag.class);
				startActivity(nextScreen);
			}

		});

	}
}