package com.capstone.schoolhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class TransitionDialog extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.class_menu);

		SimpleFileDialog FileOpenDialog = new SimpleFileDialog(
				TransitionDialog.this, "FileOpen",
				new SimpleFileDialog.SimpleFileDialogListener() {
					String m_chosen;

					@Override
					public void onChosenDir(String chosenDir) {
						// The code in this function will be executed when the
						// dialog OK button is pushed
						m_chosen = chosenDir;
						Toast.makeText(TransitionDialog.this,
								"Chosen FileOpenDialog File: " + m_chosen,
								Toast.LENGTH_LONG).show();
					}
				});
		// You can change the default filename using the public variable
		// "Default_File_Name"
		FileOpenDialog.Default_File_Name = "";
		FileOpenDialog.chooseFile_or_Dir();

	}
}
