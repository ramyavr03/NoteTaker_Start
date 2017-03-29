package com.example.notetaker_start;


import java.io.File;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText sub;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	/*	if (this.getApplicationContext().getResources().coorientation == Configuration.ORIENTATION_LANDSCAPE) {
        	setContentView(R.layout.activity_main);
        }  else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
        	setContentView(R.layout.activity_another);
        }
		*/
		sub = (EditText) findViewById(R.id.editText1);
	}
@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
 
        Toast.makeText(getApplicationContext(),"orientation",Toast.LENGTH_SHORT).show();
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
       
        }  else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
   
        }
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		sub.setHint("Enter subject Here...");
		return true;
	}
	
	public void FolderCreate(View view)
	{
		if( sub.getText().toString().equals(""))
		{
			 Toast.makeText(getApplicationContext(),"Enter Subject",Toast.LENGTH_SHORT).show();
		}
		else
		{
			
			File rootPath = new File(Environment.getExternalStorageDirectory(), sub.getText().toString());
			Toast.makeText(getApplicationContext(),sub.getText().toString(),Toast.LENGTH_SHORT).show();
				if(!rootPath.exists()) 
				{
					rootPath.mkdirs();
				}
				SharedPreferences sp = getSharedPreferences("App6", Context.MODE_PRIVATE); // Open SharedPreferences with name AppSharedPref
				Editor editor = sp.edit();
				editor.putString(sub.getText().toString(),"");
				editor.commit();
				Intent myIntent = new Intent(MainActivity.this,Addingcontents.class);
				myIntent.putExtra("subject",sub.getText().toString());
				MainActivity.this.startActivity(myIntent);
			
		}   
	}
	public void ViewAllSubject(View view) 
	{
		Intent myIntent = new Intent(MainActivity.this,Listsubjects.class);
		MainActivity.this.startActivity(myIntent);
	}
	
}



