package com.example.notetaker_start;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Addingcontents extends Activity {
	 String subject;
	 EditText txtData;
	 Button but;
	 List<String> str;
	 SharedPreferences sp;
	 HashMap<String,String> entry;
	 ArrayList<String> list;
	 boolean exists=false;
	 SharedPreferences sp1;
	 String prevString1;
	
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addingcontents);
		txtData = (EditText) findViewById(R.id.editText1);
		but = (Button) findViewById(R.id.button2);
		txtData.setHint("Enter key value here...");
		sp = getSharedPreferences("SharedredPrefernces", 0);
		Intent intent = getIntent();
   	    subject = intent.getStringExtra("subject");// Open SharedPreferences with name AppSharedPref
		
	}
	 
	@SuppressWarnings("unchecked")
	public void AddToSharedPreference(View view)
	{
		if(txtData.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(),"Enter Key", Toast.LENGTH_LONG).show();
		}
		else
		{
			exists=true;
			SharedPreferences sp = getSharedPreferences("App6", Context.MODE_PRIVATE);
			Editor editor = sp.edit();
			String prevString=sp.getString(subject,"");
	   	    editor.putString(subject,prevString.concat(txtData.getText().toString()));
	   	    editor.commit();
	   	   entry = new HashMap<String,String>();
		    entry=(HashMap<String, String>) sp.getAll();//Get the strings from shared preferences
		    list = new ArrayList<String>();
		    for ( String key : entry.keySet()) {
		    	  list .add(key);
		    	 Toast.makeText(getApplicationContext(), key, Toast.LENGTH_LONG).show();
		    	}  
		   
	   	   
	   	    sp1 = getSharedPreferences("AddPreferences",Context.MODE_PRIVATE); // Open SharedPreferences with name AppSharedPref
	   	    editor.putString(txtData.getText().toString(),"");
	   	    editor.commit();
	   	    
	   	    //get message value from intent
	   	  
	   	    Toast.makeText(getApplicationContext(), "tag saved", Toast.LENGTH_LONG).show();
	   	}
    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.addingcontents, menu);
		return true;
	}
	
	public void Addimage(View view) 
	{
		
		if(txtData.getText().toString().equals("") )
		{
			Toast.makeText(getApplicationContext(), "Enter Key", Toast.LENGTH_LONG).show();
		}
		else
		{
			
			for(int i=0;i<list.size();i++)
			{
				if(txtData.equals(list.get(i)))
				{
					exists=true;	
					break;
				}
				else
				{
					continue;
							
				}
					
			}
			if(exists)
			{
				Intent myIntent= new Intent(Addingcontents.this,AddImage.class);
				myIntent.putExtra("subject",subject);
				myIntent.putExtra("tag",txtData.getText().toString());
				Addingcontents.this.startActivity(myIntent);
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Save the key first", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	public void Addtext(View view) 
	{
		
		if(txtData.getText().toString().equals("") )
		{
			Toast.makeText(getApplicationContext(), "Enter Key", Toast.LENGTH_LONG).show();
		}
		else
		{
			
			for(int i=0;i<list.size();i++)
			{
				if(txtData.equals(list.get(i)))
				{
					exists=true;	
					break;
				}
				else
				{
					continue;
				}
					
			}
		}
		if(exists)
		{
			Intent myIntent= new Intent(Addingcontents.this,Addtext.class);
			myIntent.putExtra("subject",subject);
			myIntent.putExtra("tag",txtData.getText().toString());
			Addingcontents.this.startActivity(myIntent);
		}
		else
		{
				Toast.makeText(getApplicationContext(), "Save the key first", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void OpenStory(View view)
	{
		if(txtData.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(), "Enter Key", Toast.LENGTH_LONG).show();
		}
		else
		{
			Intent myIntent= new Intent(Addingcontents.this,ExpandableMainActivity.class);
			myIntent.putExtra("tag",txtData.getText().toString());
			Addingcontents.this.startActivity(myIntent);
		}
	}
}
