package com.capstone.schoolhelper;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class AddClassFrag extends Fragment {
	static final int DATE_DIALOG_ID = 999;
	private int year;
	private int month;
	private int day;
	static public TextView tvTime;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.add_class, null);

		MainActivity.eventORclass = true;

		final EditText etClassName = (EditText) view
				.findViewById(R.id.etClassName);
		final EditText etProfName = (EditText) view
				.findViewById(R.id.etProfName);
		final EditText etLocation = (EditText) view
				.findViewById(R.id.etLocation);
		tvTime = (TextView) view.findViewById(R.id.tvTime);

		Button btnAddDoc = (Button) view.findViewById(R.id.btnAddDoc);
		Button btnAddClass = (Button) view.findViewById(R.id.btnAddClass);

		btnAddClass.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {

				String className = etClassName.getText().toString();
				String profName = etProfName.getText().toString();
				String school = etLocation.getText().toString();

				if (!className.isEmpty() && !profName.isEmpty()
						&& !school.isEmpty() && !tvTime.getText()
						.toString().equals("Press above button.")) {
					SQLHandler db = new SQLHandler(getActivity()
							.getApplicationContext());

					SQLClass c = new SQLClass(className, profName, school,
							tvTime.getText().toString(), "No Documents");
					db.createClass(c);
					// create a new fragment and specify the planet to show
					// based on
					// position
					Fragment fragment = new ClassesMenuFrag();
					// Insert the fragment by replacing any existing fragment
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction()
							.replace(R.id.content_frame, fragment).commit();
				} else {
				}
			}
		});

		Button btnChangeTime = (Button) view.findViewById(R.id.btnChangeTime);

		btnChangeTime.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				DialogFragment picker = new TimePickerFrag();
				picker.show(getFragmentManager(), "timePicker");
			}

		});

		return view;

	}

}