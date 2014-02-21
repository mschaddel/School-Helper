package com.capstone.schoolhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ClassMenuFrag extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.class_menu);

		ListView lvEvents = (ListView) findViewById(R.id.lvEvents);
		String[] items = { "Homework", "Test", "Project", "Group Study" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		lvEvents.setAdapter(adapter);
		
		TextView tvClassName = (TextView) findViewById(R.id.tvClassName);
		tvClassName.setText("Imaginary Class");
		TextView tvProfName = (TextView) findViewById(R.id.tvProfName);
		tvProfName.setText("Imaginary Professor");
		TextView tvClassLoc = (TextView) findViewById(R.id.tvClassLoc);
		tvClassLoc.setText("Room Imagination");
		TextView tvClassTime = (TextView) findViewById(R.id.tvClassTime);
		tvClassTime.setText("Imaginary Time");
		
		Button btnAddEvent = (Button) findViewById(R.id.btnAddEvent);
		btnAddEvent.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Intent nextScreen = new Intent(getApplicationContext(),
						AddEventFrag.class);
				startActivity(nextScreen);
			}

		});
		
		Button btnDeleteClass = (Button) findViewById(R.id.btnDeleteClass);
		btnDeleteClass.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Intent nextScreen = new Intent(getApplicationContext(),
						ClassesMenuFrag.class);
				startActivity(nextScreen);
			}

		});

	}
}