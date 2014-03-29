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
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHandler extends SQLiteOpenHelper{

	 // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "profileManager";
 
    // Table names
    private static final String TABLE_PROFILE = "profile";
    private static final String TABLE_CLASS = "class";
    private static final String TABLE_EVENT = "event";
    //Profile fields
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_SCHOOL = "school";
    private static final String KEY_NOTIFICATIONS = "notifications";
    private static final String KEY_MODE = "mode";
    //Class Fields
    private static final String KEY_CLASS_NAME = "class_name";
    private static final String KEY_PROFESSOR = "professor";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_CLASS_TIME = "class_time";
    private static final String KEY_CLASS_DOCUMENTS = "documents";
    //Event Fields
    private static final String KEY_EVENT_NAME = "event_name";
    private static final String KEY_EVENT_DATE = "event_date";
    private static final String KEY_EVENT_TIME = "event_time";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_EVENT_DOCUMENTS = "event_documents";
    
    private static final String CREATE_PROFILE_TABLE = "CREATE TABLE " + TABLE_PROFILE + "("
            + KEY_NAME + " STRING PRIMARY KEY," + KEY_EMAIL + " TEXT,"
            + KEY_SCHOOL + " TEXT" + KEY_NOTIFICATIONS +" TEXT" + KEY_MODE + " INTEGER" + ")";
    
    private static final String CREATE_CLASS_TABLE = "CREATE TABLE " + TABLE_CLASS + "("
            + KEY_CLASS_NAME + " STRING PRIMARY KEY," + KEY_PROFESSOR + " TEXT,"
            + KEY_LOCATION + " TEXT" + KEY_CLASS_TIME +" TIME" + KEY_CLASS_DOCUMENTS + " TEXT" + ")";
    
    private static final String CREATE_EVENT_TABLE = "CREATE TABLE " + TABLE_EVENT + "("
            + KEY_EVENT_NAME + " STRING PRIMARY KEY," + KEY_EVENT_DATE + " DATE,"
            + KEY_EVENT_TIME + " INTEGER" + KEY_DESCRIPTION +" TEXT" + KEY_EVENT_DOCUMENTS + " TEXT" + ")";
    
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
	//profile table	
	public long createProfile(SQLProfile profile,long[] profile_ids){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, SQLProfile.getname());
		values.put(KEY_EMAIL, SQLProfile.getemail());
		values.put(KEY_SCHOOL, SQLProfile.getschool());
		values.put(KEY_NOTIFICATIONS, SQLProfile.getnotifications());
		values.put(KEY_MODE, SQLProfile.getmode());
		
		long profile_id = db.insert(TABLE_PROFILE,null,values);
		
	}
}

