package com.capstone.schoolhelper;

import com.capstone.schoolhelper.SQLEvent;
import com.capstone.schoolhelper.SQLProfile;
import com.capstone.schoolhelper.SQLClass;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "profileManager";

	// Table names
	private static final String TABLE_PROFILE = "profile";
	private static final String TABLE_CLASS = "class";
	private static final String TABLE_EVENT = "event";
	// Profile fields
	private static final String KEY_PROFILEID = "profile_id";
	private static final String KEY_NAME = "name";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_SCHOOL = "school";
	private static final String KEY_NOTIFICATIONS = "notifications";
	private static final String KEY_MODE = "mode";
	// Class Fields
	private static final String KEY_CLASSID = "class_id";
	private static final String KEY_PROFESSOR = "professor";
	private static final String KEY_CLASS_LOCATION = "location";
	private static final String KEY_CLASS_TIME = "class_time";
	private static final String KEY_CLASS_DOCUMENTS = "documents";
	// Event Fields
	private static final String KEY_EVENTID = "event_id";
	private static final String KEY_EVENT_NAME = "event_name";
	private static final String KEY_EVENT_LOCATION = "event_location";
	private static final String KEY_EVENT_DATE = "event_date";
	private static final String KEY_EVENT_TIME = "event_time";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_EVENT_DOCUMENTS = "event_documents";
	// Common Class Name
	private static final String KEY_CLASS_NAME = "class_name";

	private static final String CREATE_PROFILE_TABLE = "CREATE TABLE "
			+ TABLE_PROFILE + "(" + KEY_PROFILEID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_EMAIL
			+ " TEXT," + KEY_SCHOOL + " TEXT," + KEY_NOTIFICATIONS
			+ " INTEGER," + KEY_MODE + " TEXT" + ")";

	private static final String CREATE_CLASS_TABLE = "CREATE TABLE "
			+ TABLE_CLASS + "(" + KEY_CLASSID + " INTEGER PRIMARY KEY," + KEY_CLASS_NAME + " TEXT,"
			+ KEY_PROFESSOR + " TEXT," + KEY_CLASS_LOCATION + " TEXT,"
			+ KEY_CLASS_TIME + " TIME," + KEY_CLASS_DOCUMENTS + " TEXT" + ")";

	private static final String CREATE_EVENT_TABLE = "CREATE TABLE "
			+ TABLE_EVENT + "(" + KEY_EVENTID + " INTEGER PRIMARY KEY," + KEY_EVENT_NAME + " TEXT,"
			+ KEY_EVENT_DATE + " DATE," + KEY_EVENT_LOCATION + " TEXT,"
			+ KEY_EVENT_TIME + " INTEGER," + KEY_DESCRIPTION + " TEXT,"
			+ KEY_EVENT_DOCUMENTS + " TEXT" + ")";

	public SQLHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROFILE_TABLE);
		db.execSQL(CREATE_CLASS_TABLE);
		db.execSQL(CREATE_EVENT_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);

		// create new tables
		onCreate(db);
	}

	// all below needs to be looked at for ids.
	// create profile table
	public long createProfile(SQLProfile profile) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, profile.getname());
		values.put(KEY_EMAIL, profile.getemail());
		values.put(KEY_SCHOOL, profile.getschool());
		values.put(KEY_NOTIFICATIONS, profile.getnotifications());
		values.put(KEY_MODE, profile.getmode());

		long profile_id = db.insert(TABLE_PROFILE, null, values);

		return profile_id;
	}

	// get profile name
	public SQLProfile getName(long profile_id) {
		SQLiteDatabase db = this.getWritableDatabase();

		String Query = "SELECT KEY_NAME FROM " + TABLE_PROFILE;
		Cursor c = db.rawQuery(Query, null);

		if (c != null)
			c.moveToFirst();
		SQLProfile np = new SQLProfile();
		np.setname(c.getString(c.getColumnIndex(KEY_NAME)));
		return np;
	}

	// get all profile
	public List<SQLProfile> getProfile() {
		List<SQLProfile> profile = new ArrayList<SQLProfile>();

		String Query = "SELECT * FROM " + TABLE_PROFILE;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				SQLProfile np = new SQLProfile();
				np.setname(c.getString(c.getColumnIndex(KEY_NAME)));
				np.setemail(c.getString(c.getColumnIndex(KEY_EMAIL)));
				np.setschool(c.getString(c.getColumnIndex(KEY_SCHOOL)));
				np.setmode(c.getString(c.getColumnIndex(KEY_MODE)));
				np.setnotifications(c.getInt(c
						.getColumnIndex(KEY_NOTIFICATIONS)));

				// adding to profile list
				profile.add(np);
			} while (c.moveToNext());
		}
		return profile;
	}

	// delete profile
	public void deleteProfile(long profile_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PROFILE, KEY_NAME + " = ?",
				new String[] { String.valueOf(profile_id) });
	}

	// create event
	// need possible event ids
	public long createEvent(SQLEvent event) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_EVENT_NAME, event.geteventname());
		values.put(KEY_CLASS_NAME, event.getclassname());
		values.put(KEY_EVENT_LOCATION, event.geteventlocation());
		values.put(KEY_EVENT_DATE, event.geteventdate());
		values.put(KEY_EVENT_TIME, event.geteventtime());
		values.put(KEY_DESCRIPTION, event.getdescription());
		values.put(KEY_EVENT_DOCUMENTS, event.geteventdocuments());

		long event_id = db.insert(TABLE_PROFILE, null, values);

		return event_id;
	}

	// get all event names
	public List<SQLEvent> getEventNames() {
		List<SQLEvent> event = new ArrayList<SQLEvent>();

		String Query = "SELECT KEY_EVENT_NAME FROM " + TABLE_EVENT;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				SQLEvent ne = new SQLEvent();
				ne.seteventname(c.getString(c.getColumnIndex(KEY_EVENT_NAME)));

				// adding to event list
				event.add(ne);
			} while (c.moveToNext());
		}
		return event;
	}

	// get all events for a class
	public List<SQLEvent> getallEventsClass(String class_name) {
		List<SQLEvent> event = new ArrayList<SQLEvent>();

		String Query = "SELECT * FROM " + TABLE_EVENT + " WHERE "
				+ KEY_CLASS_NAME + " = '" + class_name + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				SQLEvent ne = new SQLEvent();
				ne.seteventname(c.getString(c.getColumnIndex(KEY_EVENT_NAME)));

				// adding to event list
				event.add(ne);
			} while (c.moveToNext());
		}
		return event;
	}

	// get all events for a Date
	public List<SQLEvent> getallEventsDate(String date) {
		List<SQLEvent> event = new ArrayList<SQLEvent>();

		String Query = "SELECT * FROM " + TABLE_EVENT + " WHERE "
				+ KEY_EVENT_DATE + " = '" + date + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				SQLEvent ne = new SQLEvent();
				ne.seteventname(c.getString(c.getColumnIndex(KEY_EVENT_NAME)));

				// adding to event list
				event.add(ne);
			} while (c.moveToNext());
		}
		return event;
	}

	// update an event
	public int updateEvent(SQLEvent event) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_EVENT_NAME, event.geteventname());
		values.put(KEY_CLASS_NAME, event.getclassname());
		values.put(KEY_EVENT_LOCATION, event.geteventlocation());
		values.put(KEY_EVENT_DATE, event.geteventdate());
		values.put(KEY_EVENT_TIME, event.geteventtime());
		values.put(KEY_DESCRIPTION, event.getdescription());
		values.put(KEY_EVENT_DOCUMENTS, event.geteventdocuments());

		// updating row
		return db.update(TABLE_EVENT, values, KEY_EVENT_NAME + " = ?",
				new String[] { String.valueOf(event.geteventname()) });
	}

	// delete an event
	public void deleteEvent(long event) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PROFILE, KEY_EVENT_NAME + " = ?",
				new String[] { String.valueOf(event) });
	}

	// create class
	public long createClass(SQLClass class_name) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_CLASS_NAME, class_name.getclassname());
		values.put(KEY_PROFESSOR, class_name.getprofessor());
		values.put(KEY_CLASS_LOCATION, class_name.getclasslocation());
		values.put(KEY_CLASS_TIME, class_name.getclasstime());
		values.put(KEY_CLASS_DOCUMENTS, class_name.getclassdocuments());

		long class_id = db.insert(TABLE_CLASS, null, values);

		return class_id;
	}

	// get all class names
	public List<SQLClass> getClassNames() {
		List<SQLClass> class_names = new ArrayList<SQLClass>();

		String Query = "SELECT KEY_CLASS_NAME FROM " + TABLE_CLASS;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				SQLClass nc = new SQLClass();
				nc.setclassname(c.getString(c.getColumnIndex(KEY_CLASS_NAME)));

				// adding to class list
				class_names.add(nc);
			} while (c.moveToNext());
		}
		return class_names;
	}

	// get class names and their location
	public List<SQLClass> getClassesNameLoc() {
		List<SQLClass> class_names = new ArrayList<SQLClass>();

		String Query = "SELECT * FROM " + TABLE_CLASS;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				SQLClass nc = new SQLClass();
				nc.setclassname(c.getString(c.getColumnIndex(KEY_CLASS_NAME)));
				nc.setclasslocation(c.getString(c
						.getColumnIndex(KEY_CLASS_LOCATION)));

				// adding to class list
				class_names.add(nc);
			} while (c.moveToNext());
		}
		return class_names;
	}

	// get class information
	public List<SQLClass> getClassesInfo(String class_name) {
		List<SQLClass> class_names = new ArrayList<SQLClass>();

		String Query = "SELECT * FROM " + TABLE_CLASS + " WHERE "
				+ KEY_CLASS_NAME + " = '" + class_name + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				SQLClass nc = new SQLClass();
				nc.setclassname(c.getString(c.getColumnIndex(KEY_CLASS_NAME)));
				nc.setclasslocation(c.getString(c
						.getColumnIndex(KEY_CLASS_LOCATION)));
				nc.setprofessor(c.getString(c.getColumnIndex(KEY_PROFESSOR)));
				nc.setclasstime(c.getString(c.getColumnIndex(KEY_CLASS_TIME)));
				nc.setclassdocuments(c.getString(c
						.getColumnIndex(KEY_CLASS_DOCUMENTS)));
				// adding to class list
				class_names.add(nc);
			} while (c.moveToNext());
		}
		return class_names;
	}

	// update class
	public int updateClass(SQLClass class_name) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_CLASS_NAME, class_name.getclassname());
		values.put(KEY_CLASS_LOCATION, class_name.getclasslocation());
		values.put(KEY_PROFESSOR, class_name.getprofessor());
		values.put(KEY_CLASS_TIME, class_name.getclasstime());
		values.put(KEY_CLASS_DOCUMENTS, class_name.getclassdocuments());

		// updating row
		return db.update(TABLE_CLASS, values, KEY_CLASS_NAME + " = ?",
				new String[] { String.valueOf(class_name.getclassname()) });
	}

	// delete class
	public void deleteClass(long class_name) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CLASS, KEY_CLASS_NAME + " = ?",
				new String[] { String.valueOf(class_name) });
	}
}
