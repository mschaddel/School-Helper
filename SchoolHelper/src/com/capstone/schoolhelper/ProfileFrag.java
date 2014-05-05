package com.capstone.schoolhelper;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ProfileFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.profile, null);
		
		SQLHandler db = new SQLHandler(getActivity());

		List<String> profile = db.getProfile();

		TextView tvProfileName = (TextView) view
				.findViewById(R.id.tvProfileName);
		tvProfileName.setText(profile.get(1));
		TextView tvProfileEmail = (TextView) view
				.findViewById(R.id.tvProfileEmail);
		tvProfileEmail.setText(profile.get(2));
		TextView tvProfileSchool = (TextView) view
				.findViewById(R.id.tvProfileSchool);
		tvProfileSchool.setText(profile.get(3));

		Button btnChangeProfile = (Button) view
				.findViewById(R.id.btnDeleteProfile);
		btnChangeProfile.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				SQLHandler db = new SQLHandler(getActivity());
				List<String> profile = db.getProfile();

				db.deleteProfile(Long.parseLong(profile.get(0)));
				Intent intent = getActivity().getIntent();
				getActivity().finish();
				startActivity(intent);
				
			}
		});
		return view;
	}
}
