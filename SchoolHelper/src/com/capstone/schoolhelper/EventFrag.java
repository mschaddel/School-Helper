package com.capstone.schoolhelper;

import java.util.List;

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

		SQLHandler db = new SQLHandler(this.getActivity());
		
		List<String> event = db.getEventInfo(ClassMenuFrag.currentEvent);
		
		TextView tvEventName = (TextView) view.findViewById(R.id.tvEventName);
		tvEventName.setText(event.get(0));
		TextView tvClassName = (TextView) view.findViewById(R.id.tvClassName);
		tvClassName.setText(event.get(1));
		TextView tvLocation = (TextView) view.findViewById(R.id.tvLocation);
		tvLocation.setText(event.get(2));
		TextView tvEventDate = (TextView) view.findViewById(R.id.tvEventDate);
		tvEventDate.setText(event.get(3));
		TextView tvEventTime = (TextView) view.findViewById(R.id.tvEventTime);
		tvEventTime.setText(event.get(4));
		TextView tvNotes = (TextView) view.findViewById(R.id.tvNotes);
		tvNotes.setText(event.get(6));

		Button btnDeleteEvent = (Button) view.findViewById(R.id.btnDeleteEvent);
		btnDeleteEvent.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				
				SQLHandler db = new SQLHandler(getActivity());
				db.deleteEvent(ClassMenuFrag.currentEventID);
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
	
	public void onBackPressed(){
		// create a new fragment and specify the planet to show based on
		// position
		Fragment fragment = new ClassMenuFrag();
		// Insert the fragment by replacing any existing fragment
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
	}
}
