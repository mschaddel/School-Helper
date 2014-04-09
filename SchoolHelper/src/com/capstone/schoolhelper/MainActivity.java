package com.capstone.schoolhelper;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.app.ActionBar;

public class MainActivity extends Activity {

	/* Testin */

	private String[] mPlanetTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence title;

	/* Tesin */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_layout);

		/** TESTING */

		title = getActionBar().getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Set the adapter for the list view
		// mDrawerList.setAdapter(new ArrayAdapter<String>(this,
		// R.layout.drawer_list_item, mPlanetTitles));

		String[] items = { "Classes", "Calender", "Today's Events", "Settings" };
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		//		android.R.layout.simple_list_item_1, items);
		//mDrawerList.setAdapter(adapter);

		 // Set the adapter for the list view
        
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_item,R.id.content, items));
		
		// Set the list's click listener
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerToggle = new ActionBarDrawerToggle(
			this, /* host Activity */
			mDrawerLayout, /* DrawerLayout object */
			R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
			R.string.drawer_open, /* "open drawer" description */
			R.string.drawer_close /* "close drawer" description */) 
			{

			/** Called when a drawer has settled in a completely closed state. */

			public void onDrawerClosed(View view) {
				getActionBar().setTitle(title);
			}

			/** Called when a drawer has settled in a completely open state. */

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("Open Drawer");
			}
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// Testin */


		/*Button btnSettings = (Button) findViewById(R.id.btnSettings);
		Button btnCalendar = (Button) findViewById(R.id.btnCalendar);
		Button btnClasses = (Button) findViewById(R.id.btnClasses);

		btnClasses.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Intent nextScreen = new Intent(getApplicationContext(),
						ClassesMenuFrag.class);
				startActivity(nextScreen);
			}

		});

		btnCalendar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Intent nextScreen = new Intent(getApplicationContext(),
						CalendarFrag.class);
				startActivity(nextScreen);
			}

		});

		btnSettings.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Intent nextScreen = new Intent(getApplicationContext(),
						SettingsFrag.class);
				startActivity(nextScreen);
			}

		});*/
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
		// create a new fragment and specify the planet to show based on
		// position
		Fragment fragment = new OpertingSystemFrag();
		Bundle args = new Bundle();
		args.putInt(OpertingSystemFrag.ARG_OS, position);
		fragment.setArguments(args);

		// Insert the fragment by replacing any existing fragment
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		// Highlight the selected item, update the title, and close the drawer
		mDrawerList.setItemChecked(position, true);
		getActionBar().setTitle((mPlanetTitles[position]));
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	/*
	 * 
	 * // NEW
	 * 
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { MenuInflater
	 * inflater = getMenuInflater(); inflater.inflate(R.menu.actionbar, menu);
	 * return true; }
	 * 
	 * // NEW
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { switch
	 * (item.getItemId()) { case R.id.action_refresh: Toast.makeText(this,
	 * "Action refresh selected", Toast.LENGTH_SHORT) .show(); break; case
	 * R.id.action_settings: Toast.makeText(this, "Action Settings selected",
	 * Toast.LENGTH_SHORT) .show(); break;
	 * 
	 * default: break; }
	 * 
	 * return true; }
	 */

}
