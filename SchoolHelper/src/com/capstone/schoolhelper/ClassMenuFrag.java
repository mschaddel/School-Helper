package com.capstone.schoolhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClassMenuFrag extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.class_menu);

		Button btnAddEvent = (Button) findViewById(R.id.btnAddEvent);

		btnAddEvent.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Intent nextScreen = new Intent(getApplicationContext(),
						AddEventFrag.class);
				startActivity(nextScreen);
			}

		});

	}
}