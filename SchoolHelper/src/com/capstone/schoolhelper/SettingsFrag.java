package com.capstone.schoolhelper;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SettingsFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.settings, null);
		Button btnNotifications = (Button) view.findViewById(R.id.btnNotifications);
		Button btnChangeMode = (Button) view.findViewById(R.id.btnChangeMode);
		Button btnChangeProfile = (Button) view.findViewById(R.id.btnChangeProfile);
		return view;
	}
}
