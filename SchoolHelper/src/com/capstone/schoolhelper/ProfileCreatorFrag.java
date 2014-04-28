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
import android.widget.EditText;

public class ProfileCreatorFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.profile_creator, null);

		final EditText etName = (EditText) view.findViewById(R.id.etName);
		final EditText etEmail = (EditText) view.findViewById(R.id.etEmail);
		final EditText etSchool = (EditText) view.findViewById(R.id.etSchool);

		
		Button btnRegister = (Button) view.findViewById(R.id.btnRegister);
		
		btnRegister.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				
				String name = etName.getText().toString();
				String email = etEmail.getText().toString();
				String school = etSchool.getText().toString();
			
				if (name.length()>0 && email.length()>0 && school.length()>0) {
					
					SQLHandler db = new SQLHandler(getActivity().getApplicationContext());
					SQLProfile profile = new SQLProfile(name, email, school, 0, "student");
					MainActivity.profileID = db.createProfile(profile);
														
					// create a new fragment and specify the planet to show based on
					// position
					Fragment fragment = new MainFrag();
					// Insert the fragment by replacing any existing fragment
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction()
							.replace(R.id.content_frame, fragment).commit();
				}
				
				else{
					
					if (name.length()==0){
						etName.setHint("ENTER A NAME");
						etName.setHintTextColor(-65536);
					}
					if (email.length()==0){
						etEmail.setHint("ENTER AN EMAIL");
						etEmail.setHintTextColor(-65536);
					}
					if (school.length()==0){
						etSchool.setHint("ENTER A SCHOOL");
						etSchool.setHintTextColor(-65536);
					}
				}
					
				
			}

		});
		return view;
	}
}