package com.capstone.schoolhelper;

import java.util.Calendar;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainFrag extends Fragment {

	static ArrayAdapter<String> adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.main_activity, null);
		TextView lvWelcome = (TextView) view.findViewById(R.id.tvWelcome);

		
		SQLHandler db = new SQLHandler(getActivity());
		List<String> profile = db.getProfile();
		lvWelcome.setText("Welcome " + profile.get(1));

		ListView lvMain = (ListView) view.findViewById(R.id.lvMain);

		List<SQLClass> classes = db.getClassesNameLoc();

		String[] classesOfDay = new String[classes.size()];
		String[] classesOfDayName = new String[classes.size()];
		Long[] classesOfDayId = new Long[classes.size()];

		int y = 0;
		boolean ever = false;
		for (int x = 0; x < classes.size(); x++) {
			if (classes
					.get(x)
					.getclassdays()
					.contains(
							String.valueOf(Calendar.getInstance().get(
									Calendar.DAY_OF_WEEK)))) {
				classesOfDay[y] = classes.get(x).getclasstime() + " - "
						+ classes.get(x).getclassname();
				classesOfDayName[y] = classes.get(x).getclassname();
				classesOfDayId[y] = classes.get(x).getclassid();

				y++;
				ever = true;
			}
		}

		String[] classes2 = new String[y];
		for (int x = 0; x < y; x++) {
			classes2[x] = classesOfDay[x];
		}

		if (ever) {
			adapter = new ArrayAdapter<String>(view.getContext(),
					android.R.layout.simple_list_item_1, classes2);
			lvMain.setAdapter(adapter);
			lvMain.setClickable(true);

		} else {
			String[] lvItems = { "No Classes Today!" };
			adapter = new ArrayAdapter<String>(this.getActivity(),
					android.R.layout.simple_list_item_1, lvItems);
			lvMain.setAdapter(adapter);
			lvMain.setClickable(false);
		}

		final String[] allClassesArray = classesOfDayName;
		final Long[] allClassesIdArray = classesOfDayId;

		if (ever) {
			lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				public void onItemClick(AdapterView parent, View v,
						int position, long id) {
					ClassesMenuFrag.currentClass = allClassesArray[position];
					ClassesMenuFrag.currentClassID = allClassesIdArray[position];
					Fragment fragment = new ClassMenuFrag();
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction()
							.replace(R.id.content_frame, fragment)
							.addToBackStack(null).commit();
				}
			});
		}

		return view;

	}
}