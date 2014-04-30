package com.capstone.schoolhelper;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
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
		View view = inflater.inflate(R.layout.class_menu, null);

		SQLHandler db = new SQLHandler(this.getActivity());

		try {
			List<String> allEventNames = db
					.getallEventsClass(ClassesMenuFrag.currentClass);

			final String[] allEventArray = allEventNames
					.toArray(new String[allEventNames.size()]);

			ListView lvEvents = (ListView) view.findViewById(R.id.lvEvents);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					this.getActivity(), android.R.layout.simple_list_item_1,
					allEventArray);
			lvEvents.setAdapter(adapter);

		} catch (SQLiteException e) {
		}

		List<String> sqlClass = db.getClassesInfo(ClassesMenuFrag.currentClass);

		TextView tvClassName = (TextView) view.findViewById(R.id.tvClassName);
		tvClassName.setText(ClassesMenuFrag.currentClass);
		TextView tvProfName = (TextView) view.findViewById(R.id.tvProfName);
		tvProfName.setText(sqlClass.get(1));
		TextView tvClassLoc = (TextView) view.findViewById(R.id.tvClassLoc);
		tvClassLoc.setText(sqlClass.get(2));
		TextView tvClassTime = (TextView) view.findViewById(R.id.tvClassTime);
		tvClassTime.setText(sqlClass.get(3));

		Button btnAddEvent = (Button) view.findViewById(R.id.btnAddEvent);
		btnAddEvent.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// create a new fragment and specify the planet to show based on
				// position
				Fragment fragment = new AddEventFrag();
				// Insert the fragment by replacing any existing fragment
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment).commit();
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
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment).commit();
			}
		});
		return view;
	}
}