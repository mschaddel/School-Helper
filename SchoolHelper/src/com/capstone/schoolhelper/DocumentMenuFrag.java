package com.capstone.schoolhelper;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DocumentMenuFrag extends Fragment {

	public static String docCurrentClass;
	public static Long docCurrentClassID;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.document_menu, null);
		SQLHandler db = new SQLHandler(this.getActivity());
		ListView lvDocs = (ListView) view.findViewById(R.id.lvDocs);

		List<String> allClassesNames = db.getClassNames();
		List<Long> allClassesId = db.getClassIDs();

		if (!allClassesNames.isEmpty()) {
			final String[] allClassesArray = allClassesNames
					.toArray(new String[allClassesNames.size()]);
			final Long[] allClassesIdArray = allClassesId
					.toArray(new Long[allClassesNames.size()]);

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					this.getActivity(), android.R.layout.simple_list_item_1,
					allClassesArray);

			lvDocs.setAdapter(adapter);
			lvDocs.setClickable(true);
			lvDocs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				public void onItemClick(AdapterView parent, View v,
						int position, long id) {
					docCurrentClass = allClassesArray[position];
					docCurrentClassID = allClassesIdArray[position];
					Fragment fragment = new DocumentViewFrag();
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction()
							.replace(R.id.content_frame, fragment)
							.addToBackStack(null).commit();
				}
			});
		}
		else{
			String[] lvItems = { "No Classes" };
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
					android.R.layout.simple_list_item_1, lvItems);
			lvDocs.setAdapter(adapter);
			lvDocs.setClickable(false);
		}
		return view;
	}

}
