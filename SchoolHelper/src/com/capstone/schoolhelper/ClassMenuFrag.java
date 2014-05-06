package com.capstone.schoolhelper;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ClassMenuFrag extends Fragment {

	public static String currentEvent;
	public static Long currentEventID;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.class_menu, null);

		MainActivity.calendarORclass = true;

		SQLHandler db = new SQLHandler(this.getActivity());
		ListView lvEvents = (ListView) view.findViewById(R.id.lvEvents);
		List<String> allEventNames = db
				.getallEventsClass(ClassesMenuFrag.currentClass);
		List<Long> allEventNamesIds = db
				.getallEventsClassID(ClassesMenuFrag.currentClass);

		final String[] allEventArray = allEventNames
				.toArray(new String[allEventNames.size()]);
		final Long[] allEventArrayIds = allEventNamesIds
				.toArray(new Long[allEventNamesIds.size()]);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.simple_list_item_1,
				allEventArray);
		lvEvents.setAdapter(adapter);

		lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {
				currentEvent = allEventArray[position];
				currentEventID = allEventArrayIds[position];

				Fragment fragment = new EventFrag();
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment)
						.addToBackStack(null).commit();
			}
		});

		List<String> sqlClass = db.getClassesInfo(ClassesMenuFrag.currentClass);

		TextView tvClassName = (TextView) view.findViewById(R.id.tvClassName);
		tvClassName.setText(ClassesMenuFrag.currentClass);
		TextView tvProfName = (TextView) view.findViewById(R.id.tvProfName);
		tvProfName.setText(sqlClass.get(1));
		TextView tvClassLoc = (TextView) view.findViewById(R.id.tvClassLoc);
		tvClassLoc.setText(sqlClass.get(2));
		TextView tvClassTime = (TextView) view.findViewById(R.id.tvClassTime);
		tvClassTime.setText(sqlClass.get(3));
		TextView tvClassDays = (TextView) view.findViewById(R.id.tvClassDays);
		String days = "";
		if (sqlClass.get(4).contains("2")){
			days = days + "M ";
		}
		if (sqlClass.get(4).contains("3")){
			days = days + "T ";
		}
		if (sqlClass.get(4).contains("4")){
			days = days + "W ";
		}
		if (sqlClass.get(4).contains("5")){
			days = days + "R ";
		}
		if (sqlClass.get(4).contains("6")){
			days = days + "F ";
		}
		tvClassDays.setText(days);

		Button btnAddEvent = (Button) view.findViewById(R.id.btnAddEvent);
		btnAddEvent.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// create a new fragment and specify the planet to show based on
				// position
				Fragment fragment = new AddEventFrag();
				// Insert the fragment by replacing any existing fragment
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment)
						.addToBackStack(null).commit();
			}

		});

		Button btnDeleteClass = (Button) view.findViewById(R.id.btnDeleteClass);
		btnDeleteClass.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				SQLHandler db = new SQLHandler(getActivity());
				List<Long> classDocs = db
						.getClassDocumentsID(ClassesMenuFrag.currentClassID);
				List<Long> classEvents = db
						.getallEventsClassID(ClassesMenuFrag.currentClass);
				if (!classDocs.isEmpty()) {
					for (int i = 0; i < classDocs.size(); i++) {
						db.deleteDoc(classDocs.get(i));
					}
				}
				if (!classEvents.isEmpty()) {
					for (int i = 0; i < classEvents.size(); i++) {
						db.deleteEvent(classEvents.get(i));
					}
				}
				db.deleteClass(ClassesMenuFrag.currentClassID);
				// create a new fragment and specify the planet to show based on
				// position
				Fragment fragment = new ClassesMenuFrag();
				// Insert the fragment by replacing any existing fragment
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.popBackStack();
				fragmentManager.popBackStack();

				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment)
						.addToBackStack(null).commit();
			}
		});

		Button btnAddDoc = (Button) view.findViewById(R.id.btnAddDoc);
		btnAddDoc.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				MainActivity.docvieworclassmenu = true;
				Intent intent = new Intent(getActivity(),
						TransitionDialog.class);
				startActivity(intent);
			}

		});

		return view;

	}

}