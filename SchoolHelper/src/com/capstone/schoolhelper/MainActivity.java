package com.capstone.schoolhelper;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }
    
    Button btnSettings = (Button) findViewById(R.id.btnSettings);
    Button btnCalendar = (Button) findViewById(R.id.btnCalendar);
    Button btnClasses = (Button) findViewById(R.id.btnClasses);
    
    FragmentManager fragmentManager = getFragmentManager();
    
    btnClasses.setOnClickListener(new View.OnClickListener() {
    	
    	public void onClick(View arg0){
    		
    	    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    	    ClassesMenuFrag frag = new ClassesMenuFrag();
    		transaction.replace(R.id.classes_menu, frag);
    		transaction.addToBackStack(null);
    		transaction.commit();
    		
    	}
    	
    });

}
