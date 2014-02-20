package com.capstone.schoolhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsFrag extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		Button btnNotifications = (Button) findViewById(R.id.btnNotifications);
		Button btnChangeMode = (Button) findViewById(R.id.btnChangeMode);
		Button btnChangeProfile = (Button) findViewById(R.id.btnChangeProfile);

	}
}
