package com.capstone.schoolhelper;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class MainActivityFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.main_activity_frag, container, false);
	}

	public void onStart() {
		onStart();
		initControls();
	}

	private void initControls() {
		Button btnSettings = (Button) getView().findViewById(R.id.btnSettings);
		Button btnCalendar = (Button) getView().findViewById(R.id.btnCalendar);
		Button btnClasses = (Button) getView().findViewById(R.id.btnClasses);

		FragmentManager fragmentManager = getFragmentManager();

		btnClasses.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {

				FragmentTransaction transaction = getFragmentManager()
						.beginTransaction();
				ClassesMenuFrag frag = new ClassesMenuFrag();
				transaction.replace(R.layout.classes_menu, frag);
				transaction.addToBackStack(null);
				transaction.commit();

			}

		});
	}

}
