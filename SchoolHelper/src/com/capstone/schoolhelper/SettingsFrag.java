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
import com.capstone.schoolhelper.MainActivity;

public class SettingsFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.settings, null);
		Button btnNotifications = (Button) view.findViewById(R.id.btnNotifications);
		Button btnChangeMode = (Button) view.findViewById(R.id.btnChangeMode);
		Button btnChangeProfile = (Button) view.findViewById(R.id.btnChangeProfile);
		
		btnChangeProfile.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				SQLHandler db = new SQLHandler(getActivity());
				db.deleteProfile(MainActivity.profileID);
				Fragment fragment = new ProfileCreatorFrag();
				// Insert the fragment by replacing any existing fragment
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment).commit();
			}
		});
		return view;
	}
}
