package com.capstone.schoolhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventFrag extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event);
		
		TextView tvClassName = (TextView) findViewById(R.id.tvClassName);
		tvClassName.setText("Imaginary Class");
		TextView tvEventName = (TextView) findViewById(R.id.tvEventName);
		tvEventName.setText("Imaginary Event");
		TextView tvLocation = (TextView) findViewById(R.id.tvLocation);
		tvLocation.setText("Room Imagination");
		TextView tvEventTime = (TextView) findViewById(R.id.tvEventTime);
		tvEventTime.setText("Imaginary Time");
		TextView tvNotes = (TextView) findViewById(R.id.tvNotes);
		tvNotes.setText("Imaginary Notesssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		
		Button btnDeleteEvent = (Button) findViewById(R.id.btnDeleteEvent);
		btnDeleteEvent.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Intent nextScreen = new Intent(getApplicationContext(),
						ClassMenuFrag.class);
				startActivity(nextScreen);
			}

		});

	}
}
