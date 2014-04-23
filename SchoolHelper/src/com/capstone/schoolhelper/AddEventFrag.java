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

public class AddEventFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.add_event, null);

		Button btnAddDoc = (Button) view.findViewById(R.id.btnAddDoc);
		Button btnAddEvent = (Button) view.findViewById(R.id.btnAddEvent);


		btnAddEvent.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// create a new fragment and specify the planet to show based on
				// position
				Fragment fragment = new EventFrag();
				// Insert the fragment by replacing any existing fragment
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
			}

		});
		
		return view;

	}
}