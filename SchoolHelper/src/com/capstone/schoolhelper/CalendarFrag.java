package com.capstone.schoolhelper;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CalendarFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.calendar, null);

		ListView lvCalendar = (ListView) view.findViewById(R.id.lvCalendar);
		String[] items = { "THEO210", "HIST112", "CSCI496", "MATH405",
				"FSTD270" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.simple_list_item_1, items);
		lvCalendar.setAdapter(adapter);
		return view;
	}

}