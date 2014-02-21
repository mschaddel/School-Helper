package com.capstone.schoolhelper;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.app.ActionBar;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		Button btnSettings = (Button) findViewById(R.id.btnSettings);
		Button btnCalendar = (Button) findViewById(R.id.btnCalendar);
		Button btnClasses = (Button) findViewById(R.id.btnClasses);

		btnClasses.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Intent nextScreen = new Intent(getApplicationContext(),
						ClassesMenuFrag.class);
				startActivity(nextScreen);
			}

		});
		

		btnCalendar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Intent nextScreen = new Intent(getApplicationContext(),
						CalendarFrag.class);
				startActivity(nextScreen);
			}

		});

		btnSettings.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Intent nextScreen = new Intent(getApplicationContext(),
						SettingsFrag.class);
				startActivity(nextScreen);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.actionbar, menu);
		return super.onCreateOptionsMenu(menu);

	}

}
