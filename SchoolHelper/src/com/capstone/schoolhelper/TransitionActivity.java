package com.capstone.schoolhelper;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class TransitionActivity extends Activity {
	public static String m_chosen;
	SQLDoc doc;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.class_menu);

		SimpleFileDialog FileOpenDialog = new SimpleFileDialog(
				TransitionActivity.this, "FileOpen",
				new SimpleFileDialog.SimpleFileDialogListener() {

					@Override
					public void onChosenDir(String chosenDir) {
						// The code in this function will be executed when the
						// dialog OK button is pushed
						SQLHandler db = new SQLHandler(getApplicationContext());
						m_chosen = chosenDir;
						//String id = (ClassesMenuFrag.currentClassID).toString();
						if (MainActivity.docvieworclassmenu == true){
							doc = new SQLDoc(ClassesMenuFrag.currentClassID, m_chosen);
						}
						else{
							doc = new SQLDoc(DocumentMenuFrag.docCurrentClassID, m_chosen);
						}
						db.createDoc(doc);
						// db.addDocument(id, m_chosen);
						Toast.makeText(TransitionActivity.this,
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
