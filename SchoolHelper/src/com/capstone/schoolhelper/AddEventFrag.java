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

public class AddEventFrag extends Fragment {

	private int year;
	private int month;
	private int day;

	static final int DATE_DIALOG_ID = 999;
	static public TextView tvDate;
	static public TextView tvTime;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.eventORclass = false;
		View view = inflater.inflate(R.layout.add_event, null);

		final EditText etClassName = (EditText) view
				.findViewById(R.id.etClassName);
		final EditText etEventName = (EditText) view
				.findViewById(R.id.etEventName);
		final EditText etLocation = (EditText) view
				.findViewById(R.id.etLocation);
		tvDate = (TextView) view.findViewById(R.id.tvDate);
		tvTime = (TextView) view.findViewById(R.id.tvTime);

		Button btnAddDoc = (Button) view.findViewById(R.id.btnAddDoc);
		Button btnAddEvent = (Button) view.findViewById(R.id.btnAddEvent);

		btnAddEvent.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				String className = etClassName.getText().toString();
				String eventName = etEventName.getText().toString();
				String location = etLocation.getText().toString();

				if (!eventName.isEmpty()
						&& !className.isEmpty()
						&& !location.isEmpty()
						&& !tvDate.getText().toString()
								.equals("Press above button.")
						&& !tvTime.getText().toString()
								.equals("Press above button.")) {
					SQLHandler db = new SQLHandler(getActivity()
							.getApplicationContext());
					SQLEvent c = new SQLEvent(eventName, className, location,
							tvDate.getText().toString(), tvTime.getText()
									.toString(), "No Comments", "No documents");
					db.createEvent(c);
					// create a new fragment and specify the planet to show
					// based on
					// position
					Fragment fragment = new ClassMenuFrag();
					// Insert the fragment by replacing any existing fragment
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction()
							.replace(R.id.content_frame, fragment).commit();
				} else {

				}
			}

		});

		Button btnChangeDate = (Button) view.findViewById(R.id.btnChangeDate);

		btnChangeDate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				DialogFragment picker = new DatePickerFrag();
				picker.show(getFragmentManager(), "datePicker");
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