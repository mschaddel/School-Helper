package com.capstone.schoolhelper;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;

public class AddClassFrag extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_class);

		Button btnAddDoc = (Button) findViewById(R.id.btnAddDoc);
		Button btnAddClass = (Button) findViewById(R.id.btnAddClass);


		btnAddClass.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				Intent nextScreen = new Intent(getApplicationContext(),
						ClassMenuFrag.class);
				startActivity(nextScreen);
				finish();
			}

		});

	}
}