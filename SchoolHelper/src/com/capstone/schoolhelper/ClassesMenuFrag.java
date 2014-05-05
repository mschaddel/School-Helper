package com.capstone.schoolhelper;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ClassesMenuFrag extends Fragment {

	public static String currentClass;
	public static Long currentClassID;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.classes_menu, null);

		SQLHandler db = new SQLHandler(this.getActivity());
		ListView lvClasses = (ListView) view.findViewById(R.id.lvClasses);

		List<String> allClassesNames = db.getClassNames();
		List<Long> allClassesId = db.getClassIds();

		if (!allClassesNames.isEmpty()) {
			final String[] allClassesArray = allClassesNames
					.toArray(new String[allClassesNames.size()]);
			final Long[] allClassesIdArray = allClassesId
					.toArray(new Long[allClassesNames.size()]);

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					this.getActivity(), android.R.layout.simple_list_item_1,
					allClassesArray);

			lvClasses.setAdapter(adapter);

			lvClasses
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						public void onItemClick(AdapterView parent, View v,
								int position, long id) {
							currentClass = allClassesArray[position];
							currentClassID = allClassesIdArray[position];
							Fragment fragment = new ClassMenuFrag();
							FragmentManager fragmentManager = getFragmentManager();
							fragmentManager.beginTransaction()
									.replace(R.id.content_frame, fragment)
									.addToBackStack(null).commit();
						}
					});
		}

		Button btnAddClass = (Button) view.findViewById(R.id.btnAddClass);
		btnAddClass.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// create a new fragment and specify the planet to show based on
				// position
				Fragment fragment = new AddClassFrag();
				// Insert the fragment by replacing any existing fragment
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment)
						.addToBackStack(null).commit();
			}
		});

		return view;
	}

}