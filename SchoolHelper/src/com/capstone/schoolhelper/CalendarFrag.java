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
		System.out.println("HELOOOOOOOOOOOOOOOOOOOOOOO");
		final View view = inflater.inflate(R.layout.calendar, null);

		System.out.println("HELOOOOOOOOOOOOOOOOOOOOOOO1");
		CalendarView cvCalendar = (CalendarView) view
				.findViewById(R.id.cvCalendar);

		Calendar c = Calendar.getInstance();
		SimpleDateFormat ss = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String formattedDate = ss.format(date);

		SQLHandler db = new SQLHandler(getActivity());

//		final List<SQLClass> classes = db.getClassesNameLoc();
//		final String[] classesOfDay = new String[classes.size()];
//		int y = 0;
//		for (int x = 0; x < classes.size(); x++) {
//			if (classes.get(x).getclassdays()
//					.contains(String.valueOf(c.get(Calendar.DAY_OF_WEEK)))) {
//				classesOfDay[y] = classes.get(x).getclasstime() + " : "
//						+ classes.get(x).getclassname();
//				y++;
//			}
//		}

		System.out.println("HELOOOOOOOOOOOOOOOOOOOOOOO2");
		
		
		final List<SQLEvent> events = db.getallEventsDate(formattedDate);
		final String[] eventNameDate = new String[events.size()];
//				+ classesOfDay.length];

		List<Long> eventsIds = db.getallEventsDateIds(formattedDate);
		final Long[] eventNameDateIds = eventsIds.toArray(new Long[eventsIds
				.size()]);

		for (int x = 0; x < events.size(); x++) {
			eventNameDate[x] = (events.get(x).geteventtime() + " : " + events
					.get(x).geteventname());
		}

//		y = 0;
//		for (int x = events.size(); x < classesOfDay.length; x++) {
//			eventNameDate[x] = classesOfDay[y];
//			y++;
//		}

		ListView lvCalendar = (ListView) view.findViewById(R.id.lvCalendar);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				view.getContext(), android.R.layout.simple_list_item_1,
				eventNameDate);
		lvCalendar.setAdapter(adapter);

		System.out.println("HELOOOOOOOOOOOOOOOOOOOOOOO3");
		lvCalendar
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView parent, View v,
							int position, long id) {
						currentEvent = events.get(position).geteventname();
						currentEventID = eventNameDateIds[position];

						Fragment fragment = new EventFrag();
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager.beginTransaction()
								.replace(R.id.content_frame, fragment).commit();
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
						.getallEventsDate(formattedDate);
				final String[] eventNameDate = new String[events.size()];

				List<Long> eventsIds = db.getallEventsDateIds(formattedDate);
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
										.commit();
							}
						});

			}
		});

		System.out.println("HELOOOOOOOOOOOOOOOOOOOOOOO4");

		return view;
	}
}