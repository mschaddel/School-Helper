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
import android.widget.EditText;

public class AddEventFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.add_event, null);

		final EditText etClassName = (EditText) view.findViewById(R.id.etClassName);
		final EditText etEventName = (EditText) view.findViewById(R.id.etEventName);
		final EditText etLocation = (EditText) view.findViewById(R.id.etLocation);
		
		Button btnAddDoc = (Button) view.findViewById(R.id.btnAddDoc);
		Button btnAddEvent = (Button) view.findViewById(R.id.btnAddEvent);


		btnAddEvent.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				String className = etClassName.getText().toString();
				String eventName = etEventName.getText().toString();
				String location = etLocation.getText().toString();
				
				if (!eventName.isEmpty()&&!className.isEmpty()&&!location.isEmpty()){
					SQLHandler db = new SQLHandler(getActivity().getApplicationContext());
					SQLEvent c = new SQLEvent(eventName, className,location,"1905","12:00", "No Comments", "No documents");
					db.createEvent(c);
					// create a new fragment and specify the planet to show based on
					// position
					Fragment fragment = new ClassMenuFrag();
					// Insert the fragment by replacing any existing fragment
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
				}
				else{
					
					
				}
			}

		});
		
		return view;

	}
}