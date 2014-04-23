package com.capstone.schoolhelper;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ClassMenuFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.add_event, null);

		ListView lvEvents = (ListView) view.findViewById(R.id.lvEvents);
		String[] items = { "Homework", "Test", "Project", "Group Study" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.simple_list_item_1, items);
		lvEvents.setAdapter(adapter);

		TextView tvClassName = (TextView) view.findViewById(R.id.tvClassName);
		tvClassName.setText("Imaginary Class");
		TextView tvProfName = (TextView) view.findViewById(R.id.tvProfName);
		tvProfName.setText("Imaginary Professor");
		TextView tvClassLoc = (TextView) view.findViewById(R.id.tvClassLoc);
		tvClassLoc.setText("Room Imagination");
		TextView tvClassTime = (TextView) view.findViewById(R.id.tvClassTime);
		tvClassTime.setText("Imaginary Time");

		Button btnAddEvent = (Button) view.findViewById(R.id.btnAddEvent);
		btnAddEvent.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// create a new fragment and specify the planet to show based on
				// position
				Fragment fragment = new AddEventFrag();
				// Insert the fragment by replacing any existing fragment
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
			}

		});

		Button btnDeleteClass = (Button) view.findViewById(R.id.btnDeleteClass);
		btnDeleteClass.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// create a new fragment and specify the planet to show based on
				// position
				Fragment fragment = new ClassesMenuFrag();
				// Insert the fragment by replacing any existing fragment
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
			}
		});
		return view;
	}
}