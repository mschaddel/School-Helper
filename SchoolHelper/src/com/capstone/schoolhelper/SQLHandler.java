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
	private static final String TABLE_DOC = "documents";
	// Profile fields
	private static final String KEY_PROFILEID = "profile_id";
	private static final String KEY_NAME = "name";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_SCHOOL = "school";
	// Class Fields
	private static final String KEY_CLASSID = "class_id";
	private static final String KEY_PROFESSOR = "professor";
	private static final String KEY_CLASS_LOCATION = "location";
	private static final String KEY_CLASS_TIME = "class_time";
	private static final String KEY_CLASS_DOCUMENTS = "documents";
	private static final String KEY_CLASS_NAME = "class_name";
	private static final String KEY_DAYS = "class_days";
	// Event Fields
	private static final String KEY_EVENTID = "event_id";
	private static final String KEY_EVENT_NAME = "event_name";
	private static final String KEY_EVENT_CLASS_NAME = "event_class_name";
	private static final String KEY_EVENT_LOCATION = "event_location";
	private static final String KEY_EVENT_DATE = "event_date";
	private static final String KEY_EVENT_TIME = "event_time";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_EVENT_DOCUMENTS = "event_documents";
	// Doc Fields
	private static final String KEY_DOCID = "doc_id";
	private static final String KEY_DOC_CLASS_ID = "doc_class_id";
	private static final String KEY_DOC_NAME = "doc_name";

	private static final String CREATE_PROFILE_TABLE = "CREATE TABLE "
			+ TABLE_PROFILE + "(" + KEY_PROFILEID + " INTEGER PRIMARY KEY,"
			+ KEY_NAME + " TEXT," + KEY_EMAIL + " TEXT," + KEY_SCHOOL + " TEXT"
			+ ")";

	private static final String CREATE_CLASS_TABLE = "CREATE TABLE "
			+ TABLE_CLASS + "(" + KEY_CLASSID + " INTEGER PRIMARY KEY,"
			+ KEY_CLASS_NAME + " TEXT," + KEY_PROFESSOR + " TEXT,"
			+ KEY_CLASS_LOCATION + " TEXT," + KEY_CLASS_TIME + " TEXT,"
			+ KEY_DAYS + " TEXT," + KEY_CLASS_DOCUMENTS + " TEXT" + ")";

	private static final String CREATE_EVENT_TABLE = "CREATE TABLE "
			+ TABLE_EVENT + "(" + KEY_EVENTID + " INTEGER PRIMARY KEY,"
			+ KEY_EVENT_NAME + " TEXT," + KEY_EVENT_CLASS_NAME + " TEXT,"
			+ KEY_EVENT_LOCATION + " TEXT," + KEY_EVENT_DATE + " TEXT,"
			+ KEY_EVENT_TIME + " TEXT," + KEY_DESCRIPTION + " TEXT,"
			+ KEY_EVENT_DOCUMENTS + " TEXT" + ")";

	private static final String CREATE_DOC_TABLE = "CREATE TABLE " + TABLE_DOC
			+ "(" + KEY_DOCID + " INTEGER PRIMARY KEY," + KEY_DOC_CLASS_ID
			+ " INTEGER," + KEY_DOC_NAME + " TEXT" + ")";

	public SQLHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROFILE_TABLE);
		db.execSQL(CREATE_CLASS_TABLE);
		db.execSQL(CREATE_EVENT_TABLE);
		db.execSQL(CREATE_DOC_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOC);

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
	public List<String> getProfile() {
		List<String> profile = new ArrayList<String>();

		String Query = "SELECT * FROM " + TABLE_PROFILE;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				profile.add(String.valueOf(c.getLong(c.getColumnIndex(KEY_PROFILEID))));
				profile.add(c.getString(c.getColumnIndex(KEY_NAME)));
				profile.add(c.getString(c.getColumnIndex(KEY_EMAIL)));
				profile.add(c.getString(c.getColumnIndex(KEY_SCHOOL)));

			} while (c.moveToNext());
		}
		return profile;
	}
	

	// delete profile
	public void deleteProfile(long profile_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PROFILE, KEY_PROFILEID + " = ?",
				new String[] { String.valueOf(profile_id) });
	}

	// create event
	// need possible event ids
	public long createEvent(SQLEvent event) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_EVENT_NAME, event.geteventname());
		values.put(KEY_EVENT_CLASS_NAME, event.getclassname());
		values.put(KEY_EVENT_LOCATION, event.geteventlocation());
		values.put(KEY_EVENT_DATE, event.geteventdate());
		values.put(KEY_EVENT_TIME, event.geteventtime());
		values.put(KEY_DESCRIPTION, event.getdescription());
		values.put(KEY_EVENT_DOCUMENTS, event.geteventdocuments());

		long event_id = db.insert(TABLE_EVENT, null, values);

		return event_id;
	}

	// get event information
	public List<String> getEventInfo(String event_name) {
		List<String> event = new ArrayList<String>();

		String Query = "SELECT * FROM " + TABLE_EVENT + " WHERE "
				+ KEY_EVENT_NAME + " = '" + event_name + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				event.add(c.getString(c.getColumnIndex(KEY_EVENT_NAME)));
				event.add(c.getString(c.getColumnIndex(KEY_EVENT_CLASS_NAME)));
				event.add(c.getString(c.getColumnIndex(KEY_EVENT_LOCATION)));
				event.add(c.getString(c.getColumnIndex(KEY_EVENT_DATE)));
				event.add(c.getString(c.getColumnIndex(KEY_EVENT_TIME)));
				event.add(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
				event.add(c.getString(c.getColumnIndex(KEY_EVENT_DOCUMENTS)));

			} while (c.moveToNext());
		}
		return event;
	}

	// get all event names
	public List<SQLEvent> getEventNames() {
		List<SQLEvent> event = new ArrayList<SQLEvent>();

		String Query = "SELECT * FROM " + TABLE_EVENT;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				SQLEvent ne = new SQLEvent();
				ne.seteventname(c.getString(c.getColumnIndex(KEY_EVENT_NAME)));
				ne.seteventtime(c.getString(c.getColumnIndex(KEY_EVENT_TIME)));
				ne.seteventdate(c.getString(c.getColumnIndex(KEY_EVENT_DATE)));

				// adding to event list
				event.add(ne);
			} while (c.moveToNext());
		}
		return event;
	}

	// get all events for a class
	public List<String> getallEventsClass(String class_name) {
		List<String> event = new ArrayList<String>();

		String Query = "SELECT * FROM " + TABLE_EVENT + " WHERE "
				+ KEY_EVENT_CLASS_NAME + " = '" + class_name + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				// adding to event list
				event.add(c.getString(c.getColumnIndex(KEY_EVENT_NAME)));
			} while (c.moveToNext());
		}
		return event;
	}

	// get all events for a class
	public List<Long> getallEventsClassID(String class_name) {
		List<Long> event = new ArrayList<Long>();

		String Query = "SELECT * FROM " + TABLE_EVENT + " WHERE "
				+ KEY_EVENT_CLASS_NAME + " = '" + class_name + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				// adding to event list
				event.add(c.getLong(c.getColumnIndex(KEY_EVENTID)));
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
				ne.seteventtime(c.getString(c.getColumnIndex(KEY_EVENT_TIME)));

				// adding to event list
				event.add(ne);
			} while (c.moveToNext());
		}
		return event;
	}

	// get all events for a Date
	public List<Long> getallEventsDateIds(String date) {
		List<Long> event = new ArrayList<Long>();

		String Query = "SELECT * FROM " + TABLE_EVENT + " WHERE "
				+ KEY_EVENT_DATE + " = '" + date + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				event.add(c.getLong(c.getColumnIndex(KEY_EVENTID)));

			} while (c.moveToNext());
		}
		return event;
	}

	// update an event
	public int updateEvent(SQLEvent event) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_EVENT_NAME, event.geteventname());
		values.put(KEY_EVENT_CLASS_NAME, event.getclassname());
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
	public void deleteEvent(long event_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_EVENT, KEY_EVENTID + " = ?",
				new String[] { String.valueOf(event_id) });
	}

	// create class
	public long createClass(SQLClass class_name) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_CLASS_NAME, class_name.getclassname());
		values.put(KEY_PROFESSOR, class_name.getprofessor());
		values.put(KEY_CLASS_LOCATION, class_name.getclasslocation());
		values.put(KEY_CLASS_TIME, class_name.getclasstime());
		values.put(KEY_DAYS, class_name.getclassdays());
		values.put(KEY_CLASS_DOCUMENTS, class_name.getclassdocuments());

		long class_id = db.insert(TABLE_CLASS, null, values);

		return class_id;
	}

	// get all class names
	public List<String> getClassNames() {
		List<String> class_names = new ArrayList<String>();

		String Query = "SELECT class_name FROM " + TABLE_CLASS;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				class_names.add(c.getString(c.getColumnIndex(KEY_CLASS_NAME)));
			} while (c.moveToNext());
		}
		return class_names;
	}

	// get all class ids
	public List<Long> getClassIds() {
		List<Long> class_ids = new ArrayList<Long>();

		String Query = "SELECT class_id FROM " + TABLE_CLASS;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				class_ids.add(c.getLong(c.getColumnIndex(KEY_CLASSID)));
			} while (c.moveToNext());
		}
		return class_ids;
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
				nc.setprofessor(c.getString(c.getColumnIndex(KEY_PROFESSOR)));
				nc.setclasstime(c.getString(c.getColumnIndex(KEY_CLASS_TIME)));
				nc.setclassdays(c.getString(c.getColumnIndex(KEY_DAYS)));
				nc.setclassdocuments(c.getString(c
						.getColumnIndex(KEY_CLASS_DOCUMENTS)));

				// adding to class list
				class_names.add(nc);
			} while (c.moveToNext());
		}
		return class_names;
	}

	// get class information
	public List<String> getClassesInfo(String class_name) {
		List<String> sqlClass = new ArrayList<String>();

		String Query = "SELECT * FROM " + TABLE_CLASS + " WHERE "
				+ KEY_CLASS_NAME + " = '" + class_name + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				sqlClass.add(c.getString(c.getColumnIndex(KEY_CLASS_NAME)));
				sqlClass.add(c.getString(c.getColumnIndex(KEY_CLASS_LOCATION)));
				sqlClass.add(c.getString(c.getColumnIndex(KEY_PROFESSOR)));
				sqlClass.add(c.getString(c.getColumnIndex(KEY_CLASS_TIME)));
				sqlClass.add(c.getString(c.getColumnIndex(KEY_DAYS)));
				sqlClass.add(c.getString(c.getColumnIndex(KEY_CLASS_DOCUMENTS)));
				// adding to class list
			} while (c.moveToNext());
		}
		return sqlClass;
	}

	// put document in Database
	public void addDocument(String class_id, String doc_name) {

		SQLiteDatabase db = this.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_CLASS_DOCUMENTS, doc_name);

		// updating row
		db.update(TABLE_CLASS, values, KEY_CLASSID + " = ?",
				new String[] { class_id });
	}

	// get documents from class

	public List<String> getDocuments(long class_id) {
		List<String> classDocs = new ArrayList<String>();
		String Query = "SELECT documents FROM " + TABLE_CLASS + " WHERE "
				+ KEY_CLASSID + " = '" + class_id + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			classDocs.add(c.getString(c.getColumnIndex(KEY_CLASS_DOCUMENTS)));
		}
		return classDocs;

	}

	// update class
	public int updateClass(SQLClass class_name) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_CLASS_NAME, class_name.getclassname());
		values.put(KEY_CLASS_LOCATION, class_name.getclasslocation());
		values.put(KEY_PROFESSOR, class_name.getprofessor());
		values.put(KEY_CLASS_TIME, class_name.getclasstime());
		values.put(KEY_DAYS, class_name.getclassdays());
		values.put(KEY_CLASS_DOCUMENTS, class_name.getclassdocuments());

		// updating row
		return db.update(TABLE_CLASS, values, KEY_CLASS_NAME + " = ?",
				new String[] { String.valueOf(class_name.getclassname()) });
	}

	// delete class
	public void deleteClass(long class_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CLASS, KEY_CLASSID + " = ?",
				new String[] { String.valueOf(class_id) });
	}

	// create doc
	public long createDoc(SQLDoc doc) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_DOC_CLASS_ID, doc.getclassid());
		values.put(KEY_DOC_NAME, doc.getdocname());

		long doc_id = db.insert(TABLE_DOC, null, values);

		return doc_id;
	}

	// find docs for a class
	public List<String> getClassDocuments(long class_id) {
		List<String> classDocs = new ArrayList<String>();
		String Query = "SELECT doc_name FROM " + TABLE_DOC + " WHERE "
				+ KEY_DOC_CLASS_ID + " = '" + class_id + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				classDocs.add(c.getString(c.getColumnIndex(KEY_DOC_NAME)));
			} while (c.moveToNext());
		}
		return classDocs;
	}
	
	public List<Long> getClassDocumentsID(long class_id) {
		List<Long> classDocsID = new ArrayList<Long>();
		String Query = "SELECT doc_id FROM " + TABLE_DOC + " WHERE "
				+ KEY_DOC_CLASS_ID + " = '" + class_id + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(Query, null);
		if (c.moveToFirst()) {
			do {
				classDocsID.add(c.getLong(c.getColumnIndex(KEY_DOCID)));
			} while (c.moveToNext());
		}
		return classDocsID;
	}

	public void deleteDoc(long doc_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_DOC, KEY_DOCID + " = ?",
				new String[] { String.valueOf(doc_id) });
	}
}
