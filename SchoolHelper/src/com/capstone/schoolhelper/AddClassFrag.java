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
import android.widget.ToggleButton;

public class AddClassFrag extends Fragment {
	static final int DATE_DIALOG_ID = 999;
	static public TextView tvTime;
	private static String days = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		final View view = inflater.inflate(R.layout.add_class, null);

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

				ToggleButton btM = (ToggleButton) view.findViewById(R.id.tbM);
				ToggleButton btT = (ToggleButton) view.findViewById(R.id.tbT);
				ToggleButton btW = (ToggleButton) view.findViewById(R.id.tbW);
				ToggleButton btR = (ToggleButton) view.findViewById(R.id.tbR);
				ToggleButton btF = (ToggleButton) view.findViewById(R.id.tbF);

				days = "";

				if (btM.isChecked()) {
					days = days + "2";
				}
				if (btT.isChecked()) {
					days = days + "3";
				}
				if (btW.isChecked()) {
					days = days + "4";
				}
				if (btR.isChecked()) {
					days = days + "5";
				}
				if (btF.isChecked()) {
					days = days + "6";
				}
				if (!className.isEmpty()
						&& !profName.isEmpty()
						&& !school.isEmpty()
						&& !tvTime.getText().toString()
								.equals("Press above button.")
						&& !days.isEmpty()
						&& !tvTime.getText().toString()
								.equals("Press above button.")) {

					SQLHandler db = new SQLHandler(getActivity()
							.getApplicationContext());

					SQLClass c = new SQLClass(className, profName, school,
							tvTime.getText().toString(), days, "No Documents");
					db.createClass(c);
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