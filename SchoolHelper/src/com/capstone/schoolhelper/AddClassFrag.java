package com.capstone.schoolhelper;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;

public class AddClassFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.add_class, null);
		
		final EditText etClassName = (EditText) view.findViewById(R.id.etClassName);
		final EditText etProfName = (EditText) view.findViewById(R.id.etProfName);
		final EditText etLocation = (EditText) view.findViewById(R.id.etLocation);
		
		Button btnAddDoc = (Button) view.findViewById(R.id.btnAddDoc);
		Button btnAddClass = (Button) view.findViewById(R.id.btnAddClass);

		

		btnAddClass.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				
				String className = etClassName.getText().toString();
				String profName = etProfName.getText().toString();
				String school = etLocation.getText().toString();
				
				if (!className.isEmpty()&&!profName.isEmpty()&&!school.isEmpty()){
					SQLHandler db = new SQLHandler(getActivity().getApplicationContext());
					
					SQLClass c = new SQLClass(className,profName,school,1,"No Documents");
					db.createClass(c);
					// create a new fragment and specify the planet to show based on
					// position
					Fragment fragment = new ClassesMenuFrag();
					// Insert the fragment by replacing any existing fragment
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
				}
				else{
					
					
				}

				
				
			}

		});
		
		return view;

	}
}