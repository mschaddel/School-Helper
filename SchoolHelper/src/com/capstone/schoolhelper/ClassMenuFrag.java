package com.capstone.schoolhelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.lamerman.FileDialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.database.sqlite.SQLiteException;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ClassMenuFrag extends Fragment {

	public boolean test = true;
	private static final int ACTION_TAKE_PHOTO_B = 1;
	static final int REQUEST_IMAGE_CAPTURE = 1;
	static final int REQUEST_TAKE_PHOTO = 1;
	private ImageView mImageView;
	protected static final int RESULT_OK = 0;
	protected static final int REQUEST_SAVE = 0;
	String mCurrentPhotoPath;
	Intent takePictureIntent;

	// Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.class_menu, null);

		SQLHandler db = new SQLHandler(this.getActivity());

		try {
			List<String> allEventNames = db
					.getallEventsClass(ClassesMenuFrag.currentClass);

			final String[] allEventArray = allEventNames
					.toArray(new String[allEventNames.size()]);

			ListView lvEvents = (ListView) view.findViewById(R.id.lvEvents);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					this.getActivity(), android.R.layout.simple_list_item_1,
					allEventArray);
			lvEvents.setAdapter(adapter);

		} catch (SQLiteException e) {
		}

		List<String> sqlClass = db.getClassesInfo(ClassesMenuFrag.currentClass);

		TextView tvClassName = (TextView) view.findViewById(R.id.tvClassName);
		tvClassName.setText(ClassesMenuFrag.currentClass);
		TextView tvProfName = (TextView) view.findViewById(R.id.tvProfName);
		tvProfName.setText(sqlClass.get(1));
		TextView tvClassLoc = (TextView) view.findViewById(R.id.tvClassLoc);
		tvClassLoc.setText(sqlClass.get(2));
		TextView tvClassTime = (TextView) view.findViewById(R.id.tvClassTime);
		tvClassTime.setText(sqlClass.get(3));

		Button btnAddEvent = (Button) view.findViewById(R.id.btnAddEvent);
		btnAddEvent.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// create a new fragment and specify the planet to show based on
				// position
				Fragment fragment = new AddEventFrag();
				// Insert the fragment by replacing any existing fragment
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment).commit();
			}

		});

		Button btnDeleteClass = (Button) view.findViewById(R.id.btnDeleteClass);
		btnDeleteClass.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// create a new fragment and specify the planet to show based on
				// position
				Fragment fragment = new ClassesMenuFrag();
				// Insert the fragment by replacing any existing fragment
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment).commit();
			}
		});

		Button btnAddDoc = (Button) view.findViewById(R.id.btnAddDoc);
		btnAddDoc.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(),
						TransitionDialog.class);
				startActivity(intent);
				// getActivity().finish(intent);
				// Fragment fragment = new ClassMenuFrag();
				// // Insert the fragment by replacing any existing fragment
				// FragmentManager fragmentManager = getFragmentManager();
				// fragmentManager.beginTransaction()
				// .replace(R.id.content_frame, fragment).commit();
			}

		});

		return view;
	}
}