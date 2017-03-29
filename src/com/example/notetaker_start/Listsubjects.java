package com.example.notetaker_start;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

 
public class Listsubjects extends Activity{ 
     ListView listView;
     List<String> str;
     ArrayList<String> list;
     HashMap<String,String> entry;
     
    /** Called when the activity is first created. */
    @SuppressWarnings("unchecked")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listsubjects);
        SharedPreferences sp = getSharedPreferences("App6", Context.MODE_PRIVATE); // Open SharedPreferences with name AppSharedPref     
        ArrayList<String> list = new ArrayList<String>();
        entry = new HashMap<String,String>();
	    entry=(HashMap<String, String>) sp.getAll();//Get the strings from shared preferences
	    list = new ArrayList<String>();
	    for ( String key : entry.keySet()) {
	    	  list .add(key);
	    	 Toast.makeText(getApplicationContext(), key, Toast.LENGTH_LONG).show();
	    	}  
	   
	         
	      listView = (ListView) findViewById(R.id.subjectsList);
	      if(list.isEmpty())
	      {
	    	  	Toast.makeText(getApplicationContext(), "No Subjects", Toast.LENGTH_SHORT).show();
	      }
	      else
	      {
	    	  
	       final StableArrayAdapter adapter = new StableArrayAdapter(this,
	              android.R.layout.simple_list_item_1, list);
	         listView.setAdapter(adapter);
	      }
	 
	    
       
    /*
     * Parameters:
        adapter - The AdapterView where the click happened.
        view - The view within the AdapterView that was clicked
        position - The position of the view in the adapter.
        id - The row id of the item that was clicked.
     */
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() 
    {
    	
    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
    	String name = adapter.getItemAtPosition(position).toString();
       
    	
    	Intent myIntent = new Intent(Listsubjects.this,ListKeyActivity.class);
 		
			myIntent.putExtra("subject",name);
		
			Listsubjects.this.startActivity(myIntent);

    
    }});
}
  private class StableArrayAdapter extends ArrayAdapter<String> {
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
  public StableArrayAdapter(Context context, int textViewResourceId,
          ArrayList<String> objects) {
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
