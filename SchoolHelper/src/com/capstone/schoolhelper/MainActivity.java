package com.capstone.schoolhelper;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static long profileID = 0;
	private String[] mPlanetTitles;
	private DrawerLayout mDrawerLayout;
	public static ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence title;
	public static boolean eventORclass; // false is event - true is class
	public static boolean calendarORclass; // false is calendar - true is class
	public static boolean docvieworclassmenu; // false is docview - true is classmenu

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_layout);

		SQLHandler db = new SQLHandler(this);
		List<String> profile = db.getProfile();

		Fragment fragment;

		// Check if profile has been created
		if (profile.isEmpty()) {
			fragment = new ProfileCreatorFrag();
			// Insert the fragment by replacing any existing fragment
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment)
					.commit();

		} else {
			fragment = new MainFrag();
			// Insert the fragment by replacing any existing fragment
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment)
					.commit();

		}

		
		getActionBar().setTitle("EduTech");
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Set the adapter for the list view
		String[] items = { "Today's Events", "Classes", "Calender", "Profile",
				"Documents" };

		// Set the adapter for the list view
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_item, R.id.content, items));

		// Set the list's click listener
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description */
		R.string.drawer_close /* "close drawer" description */) {

			/** Called when a drawer has settled in a completely closed state. */

			// public void onDrawerClosed(View view) {
			// getActionBar().setTitle(title);
			// }

			/** Called when a drawer has settled in a completely open state. */

			// public void onDrawerOpened(View drawerView) {
			// getActionBar().setTitle("Open Drawer");
			// }
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.actionbar, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...
		switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(this, "Settings selected", Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	// Swaps fragments in the main content view

	private void selectItem(int position) {

		if (position == 0) {
			// create a new fragment and specify the planet to show based on
			// position
			Fragment fragment = new MainFrag();
			// Insert the fragment by replacing any existing fragment
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.popBackStack(null,
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).addToBackStack(null)
					.commit();

			// Highlight the selected item, update the title, and close the
			// drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerLayout.closeDrawer(mDrawerList);

		}
		if (position == 1) {
			// create a new fragment and specify the planet to show based on
			// position
			Fragment fragment = new ClassesMenuFrag();
			// Insert the fragment by replacing any existing fragment
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.popBackStack(null,
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).addToBackStack(null)
					.commit();

			// Highlight the selected item, update the title, and close the
			// drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerLayout.closeDrawer(mDrawerList);

		}
		if (position == 2) {
			// create a new fragment and specify the planet to show based on
			// position
			Fragment fragment = new CalendarFrag();
			// Insert the fragment by replacing any existing fragment
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.popBackStack(null,
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).addToBackStack(null)
					.commit();

			// Highlight the selected item, update the title, and close the
			// drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerLayout.closeDrawer(mDrawerList);

		}
		if (position == 3) {
			// create a new fragment and specify the planet to show based on
			// position
			Fragment fragment = new ProfileFrag();
			// Insert the fragment by replacing any existing fragment
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.popBackStack(null,
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).addToBackStack(null)
					.commit();

			// Highlight the selected item, update the title, and close the
			// drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerLayout.closeDrawer(mDrawerList);

		}
		if (position == 4) {
			// create a new fragment and specify the planet to show based on
			// position
			Fragment fragment = new DocumentMenuFrag();
			// Insert the fragment by replacing any existing fragment
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.popBackStack(null,
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).addToBackStack(null)
					.commit();

			// Highlight the selected item, update the title, and close the
			// drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerLayout.closeDrawer(mDrawerList);

		}
	}

	@Override
	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() == 0) {
		} else {
			getFragmentManager().popBackStack();
		}
	}

}
