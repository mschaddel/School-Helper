package com.capstone.schoolhelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ListView;

public class CalendarFrag extends Fragment {
	public static String currentEvent;
	public static long currentEventID = 2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final View view = inflater.inflate(R.layout.calendar, null);

		CalendarView cvCalendar = (CalendarView) view
				.findViewById(R.id.cvCalendar);

		Calendar c = Calendar.getInstance();
		SimpleDateFormat ss = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String formattedDate = ss.format(date);

		SQLHandler db = new SQLHandler(getActivity());

		final List<SQLEvent> events = db.getAllEventsDate(formattedDate);
		final String[] eventNameDate = new String[events.size()];
		List<Long> eventsIds = db.getAllEventsDateID(formattedDate);
		final Long[] eventNameDateIds = eventsIds.toArray(new Long[eventsIds
				.size()]);

		for (int x = 0; x < events.size(); x++) {
			eventNameDate[x] = (events.get(x).geteventtime() + " : " + events
					.get(x).geteventname());
		}

		ListView lvCalendar = (ListView) view.findViewById(R.id.lvCalendar);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				view.getContext(), android.R.layout.simple_list_item_1,
				eventNameDate);
		lvCalendar.setAdapter(adapter);

		lvCalendar
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView parent, View v,
							int position, long id) {
						currentEvent = events.get(position).geteventname();
						currentEventID = eventNameDateIds[position];

						Fragment fragment = new EventFrag();
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager.beginTransaction()
								.replace(R.id.content_frame, fragment)
								.addToBackStack(null).commit();
					}
				});

		cvCalendar.setOnDateChangeListener(new OnDateChangeListener() {

			@Override
			public void onSelectedDayChange(CalendarView cview, int year,
					int month, int dayOfMonth) {

				MainActivity.calendarORclass = false;

				ListView lvCalendar = (ListView) view
						.findViewById(R.id.lvCalendar);
				Calendar c = Calendar.getInstance();
				c.set(year, month, dayOfMonth);
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				String formattedDate = sdf.format(c.getTime());

				SQLHandler db = new SQLHandler(getActivity());

				final List<SQLEvent> events = db
						.getAllEventsDate(formattedDate);
				final String[] eventNameDate = new String[events.size()];

				List<Long> eventsIds = db.getAllEventsDateID(formattedDate);
				final Long[] eventNameDateIds = eventsIds
						.toArray(new Long[eventsIds.size()]);

				for (int x = 0; x < events.size(); x++) {
					eventNameDate[x] = (events.get(x).geteventtime() + " : " + events
							.get(x).geteventname());
				}

				ArrayAdapter<String> adapter = new ArrayAdapter<String>(view
						.getContext(), android.R.layout.simple_list_item_1,
						eventNameDate);
				lvCalendar.setAdapter(adapter);

				lvCalendar
						.setOnItemClickListener(new AdapterView.OnItemClickListener() {
							public void onItemClick(AdapterView parent, View v,
									int position, long id) {
								currentEvent = events.get(position)
										.geteventname();
								currentEventID = eventNameDateIds[position];

								Fragment fragment = new EventFrag();
								FragmentManager fragmentManager = getFragmentManager();
								fragmentManager.beginTransaction()
										.replace(R.id.content_frame, fragment)
										.addToBackStack(null).commit();
							}
						});

			}
		});

		return view;
	}
}