package com.capstone.schoolhelper;

import java.io.File;
import java.util.List;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class DocumentViewFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.document_viewer, null);
		SQLHandler db = new SQLHandler(this.getActivity());
		ListView lvListDocs = (ListView) view.findViewById(R.id.lvListDocs);
		List<String> allDocNames = db
				.getClassDocs(DocumentMenuFrag.docCurrentClassID);
		if (!allDocNames.isEmpty()) {
			final String[] allDocsArray = allDocNames
					.toArray(new String[allDocNames.size()]);

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					this.getActivity(), android.R.layout.simple_list_item_1,
					allDocsArray);
			lvListDocs.setAdapter(adapter);
			lvListDocs.setClickable(true);
			lvListDocs
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						public void onItemClick(AdapterView parent, View v,
								int position, long id) {
							// Intent to view document
							File file = new File(allDocsArray[position]);
							Intent target = new Intent(Intent.ACTION_VIEW);
							target.setDataAndType(Uri.fromFile(file),
									"application/pdf");
							target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

							Intent intent = Intent.createChooser(target,
									"Open File");
							try {
								startActivity(intent);
							} catch (ActivityNotFoundException e) {
								// Instruct the user to install a PDF reader
								// here,
								// or something
								Toast.makeText(
										getActivity().getApplicationContext(),
										"Install a PDF reader",
										Toast.LENGTH_LONG).show();
							}
						}
					});
		} else {
			String[] lvItems = { "No Documents." };
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					this.getActivity(), android.R.layout.simple_list_item_1,
					lvItems);
			lvListDocs.setAdapter(adapter);
			lvListDocs.setClickable(false);

		}

		Button btnAddDoc = (Button) view.findViewById(R.id.addDoc);

		btnAddDoc.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				MainActivity.docvieworclassmenu = false;
				Intent intent = new Intent(getActivity(),
						TransitionActivity.class);
				startActivity(intent);
			}
		});
		return view;
	}
}