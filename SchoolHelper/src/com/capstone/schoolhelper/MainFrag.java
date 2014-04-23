package com.capstone.schoolhelper;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import com.capstone.schoolhelper.SQLHandler;

public class MainFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.main_activity, null);
		TextView lvWelcome = (TextView) view.findViewById(R.id.tvWelcome);
		SQLHandler db = new SQLHandler(getActivity());
		String name = db.getname();
		lvWelcome.setText("Welcome " + name);
		ListView lvMain = (ListView) view.findViewById(R.id.lvMain);
		String[] lvItems = { "Homework", "Test", "Project", "Group Study" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1, lvItems);
		lvMain.setAdapter(adapter);
		
		return view;

	}
}