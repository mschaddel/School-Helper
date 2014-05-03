package com.capstone.schoolhelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddEventFrag extends Fragment {

	static final int DATE_DIALOG_ID = 999;
	static public TextView tvDate;
	static public TextView tvTime;
	static public String closestEventName;
	static public String closestEventTime;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.eventORclass = false;
		View view = inflater.inflate(R.layout.add_event, null);

		final EditText etClassName = (EditText) view
				.findViewById(R.id.etClassName);
		etClassName.setText(ClassesMenuFrag.currentClass);
		etClassName.setEnabled(false);
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

					String[] currentDate = tvDate.getText().toString()
							.split("-");
					String[] currentTime = tvTime.getText().toString()
							.split(":");
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss.S");
					try {
						Date date = sdf.parse(currentDate[2] + "-"
								+ currentDate[0] + "-" + currentDate[1] + " "
								+ currentTime[0] + ":" + currentTime[1]
								+ ":00.0");
						long time = date.getTime();

						final Calendar currenteDate = Calendar.getInstance();
						currenteDate.set(Integer.parseInt(currentDate[2]),
								Integer.parseInt(currentDate[0]),
								Integer.parseInt(currentDate[1]),
								Integer.parseInt(currentTime[0]),
								Integer.parseInt(currentTime[1]));

						// Check next event and set notifications
						List<SQLEvent> events = db.getEventNames();
						boolean newest = true;
//						for (int x = 0; x < events.size(); x++) {
//
//							String[] testDate = events.get(x).geteventdate()
//									.split("-");
//							String[] testTime = events.get(x).geteventtime()
//									.split(":");
//
//							Calendar testeDate = Calendar.getInstance();
//							testeDate.set(Integer.parseInt(testDate[2]),
//									Integer.parseInt(testDate[0]),
//									Integer.parseInt(testDate[1]),
//									Integer.parseInt(testTime[0]),
//									Integer.parseInt(testTime[1]));
//
//							if (testeDate.before(currenteDate)) {
//								newest = false;
//							}
//
//						}

						if (newest) {
							closestEventName = eventName;
							closestEventTime = tvTime.getText().toString();
							Intent myIntent = new Intent(getActivity(),
									AlarmReceiver.class);
							AlarmManager alarmManager = (AlarmManager) getActivity()
									.getSystemService(Context.ALARM_SERVICE);
							PendingIntent pendingIntent = PendingIntent
									.getBroadcast(getActivity()
											.getApplicationContext(), 0,
											myIntent, 0);
							alarmManager.set(AlarmManager.RTC_WAKEUP, time,
									pendingIntent);
							System.out.println("TESTING:          "
									+ currenteDate
									+ "/n/n"
									+ +currenteDate.getTimeInMillis()
									+ "   "
									+ System.currentTimeMillis()
									+ "  "
									+ (currenteDate.getTimeInMillis() - System
											.currentTimeMillis()));
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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