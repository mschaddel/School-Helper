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

public class AddClassFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.add_class, container, false);
		
		Button btnAddClass = (Button) view.findViewById(R.id.btnAddClass);
		btnAddClass.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
	    	    ClassesMenuFrag frag = new ClassesMenuFrag();
	    		transaction.replace(R.layout.classes_menu, frag);
	    		transaction.addToBackStack(null);
	    		transaction.commit();
			}
		});
		
		return view;
	}

}