package com.capstone.schoolhelper;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class EventFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.event, null);

		TextView tvClassName = (TextView) view.findViewById(R.id.tvClassName);
		tvClassName.setText("Imaginary Class");
		TextView tvEventName = (TextView) view.findViewById(R.id.tvEventName);
		tvEventName.setText("Imaginary Event");
		TextView tvLocation = (TextView) view.findViewById(R.id.tvLocation);
		tvLocation.setText("Room Imagination");
		TextView tvEventTime = (TextView) view.findViewById(R.id.tvEventTime);
		tvEventTime.setText("Imaginary Time");
		TextView tvNotes = (TextView) view.findViewById(R.id.tvNotes);
		tvNotes.setText("Imaginary Notesssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");

		Button btnDeleteEvent = (Button) view.findViewById(R.id.btnDeleteEvent);
		btnDeleteEvent.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// create a new fragment and specify the planet to show based on
				// position
				Fragment fragment = new ClassMenuFrag();
				// Insert the fragment by replacing any existing fragment
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment).commit();
			}

		});
		return view;
	}
}
