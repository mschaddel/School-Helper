package com.capstone.schoolhelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

public class DocumentViewFrag extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.document_viewer, null);
		SQLHandler db = new SQLHandler(this.getActivity());
		ListView lvListDocs = (ListView) view.findViewById(R.id.lvListDocs);
		List<String> allDocNames = db
				.getClassDocuments(DocumentMenuFrag.docCurrentClassID);
		if (!allDocNames.isEmpty()) {
			final String[] allDocsArray = allDocNames
					.toArray(new String[allDocNames.size()]);

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					this.getActivity(), android.R.layout.simple_list_item_1,
					allDocsArray);
			lvListDocs.setAdapter(adapter);

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
			String[] lvItems = { "No Documents. Click to Add a Document." };
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					this.getActivity(), android.R.layout.simple_list_item_1,
					lvItems);
			lvListDocs.setAdapter(adapter);
			lvListDocs
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						public void onItemClick(AdapterView parent, View v,
								int position, long id) {
							MainActivity.docvieworclassmenu = false;
							Intent intent = new Intent(getActivity(),
									TransitionDialog.class);
							startActivity(intent);
						}
					});
		}
		return view;
	}
}