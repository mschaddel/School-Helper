package com.capstone.schoolhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CalendarFrag extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);
		
		ListView lvCalendar = (ListView) findViewById(R.id.lvCalendar);
		String[] items = { "THEO210", "HIST112", "CSCI496", "MATH405",
				"FSTD270" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		lvCalendar.setAdapter(adapter);
		
	}
}