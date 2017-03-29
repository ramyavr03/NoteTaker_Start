package com.example.notetaker_start;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ExpandableListView;


public class ExpandableMainActivity extends Activity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	List<String> keyList;
	String tag;
	String subject;
	HashMap<String, List<String>> listDataChild;
	Intent intent;
	    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expandable_main);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        Intent intent = getIntent();
        tag = intent.getStringExtra("tag");
        subject = intent.getStringExtra("subject"); 
        // preparing list data
        try {
			prepareListData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        listAdapter = new ExpandableListAdapter(this,listDataHeader,listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
	}

	 private void prepareListData() throws Exception{
	        listDataHeader = new ArrayList<String>();
	        List<String> keyList; 
	        listDataChild = new HashMap<String, List<String>>();	
	    	SharedPreferences sp = getSharedPreferences("AddPreferences", 0); // Open SharedPreferences with name AppSharedPref
	    	String prevString = sp.getString (tag, "");
	    	keyList = Arrays.asList(prevString.split(","));
	    	String s;         
	    	for(int i=0;i<keyList.size();i++)
	    	{
	    		s=keyList.get(i);
	    	    List<String> a = new ArrayList<String>();	  
	    	    File file = new File(s);
	    	    FileInputStream fin = new FileInputStream(file);
	    	    String ret = convertStreamToString(fin);
	    	    //Make sure you close all streams.
	    	    fin.close(); 
	    	    a.add(ret);
	    	    listDataHeader.add(s);
	    	    listDataChild.put(listDataHeader.get(i), a);
	    	 }
	    }
	       	 
	    	           	 
	 
	 public static String convertStreamToString(InputStream is) throws Exception {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	          sb.append(line).append("\n");
	        }
	        reader.close();
	        return sb.toString();
	    }
	
}
