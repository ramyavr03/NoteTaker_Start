package com.example.notetaker_start;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListKeyActivity extends Activity {

	
	 String subject;
	 ListView listView;
     List<String> keyList;
     List<String> a;
     @Override
	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_key);
		SharedPreferences sp = getSharedPreferences("App6", Context.MODE_PRIVATE); // Open SharedPreferences with name AppSharedPref
		Intent intent = getIntent();
		subject = intent.getStringExtra("subject");
		String prevString = sp.getString (subject, "");
		keyList = Arrays.asList(prevString.split(","));
		String s;
		for(int i=0;i<keyList.size();i++)
    	{
			s=keyList.get(i);
			a = new ArrayList<String>();
			a.add(s);
    	}
	    listView = (ListView) findViewById(R.id.KeysList);
	      if(a.isEmpty())
	      {
	    	  	Toast.makeText(getApplicationContext(), "No Tag", Toast.LENGTH_SHORT).show();
	      }
	      else
	      {
	       final StableArrayAdapter adapter = new StableArrayAdapter(this,
	              android.R.layout.simple_list_item_1, a);
	          listView.setAdapter(adapter);
	      }
	      
	     
	    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() 
	    {
	    	
	    @Override
	    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
	    	String name = adapter.getItemAtPosition(position).toString();
	    	Intent myIntent = new Intent(ListKeyActivity.this,ExpandableMainActivity.class);
			myIntent.putExtra("tag",name);
			ListKeyActivity.this.startActivity(myIntent);
	    }});}

     	@Override
     	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
     		getMenuInflater().inflate(R.menu.list_key, menu);
     		return true;
     	}
     	private class StableArrayAdapter extends ArrayAdapter<String> {
	        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
	        public StableArrayAdapter(Context context, int textViewResourceId,
	          List<String> objects) {
	        super(context, textViewResourceId, objects);
	       for (int i = 0; i < objects.size(); ++i) {
	         mIdMap.put(objects.get(i), i);
	    }
	 }
	@Override
	public long getItemId(int position) {
	 String item = getItem(position);
	 return mIdMap.get(item);
	}
	@Override
	public boolean hasStableIds() {
	 return true;
	}
	}
}
