package com.example.notetaker_start;

import java.io.*;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class  Addtext extends Activity {
	// GUI controls
	EditText txtData;
	Button btnWriteSDFile;
	Button btnReadSDFile;
	Button btnClearScreen;
	Button btnClose;
	String textname ;
    String subject;
    String tag;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_addtext);
	// bind GUI elements with local controls
	txtData = (EditText) findViewById(R.id.txtData);
	txtData.setHint("Enter some lines of data here...");
	Intent intent = getIntent();
    subject = intent.getStringExtra("subject");
    tag = intent.getStringExtra("tag");
	btnWriteSDFile = (Button) findViewById(R.id.btnWriteSDFile);
	btnWriteSDFile.setOnClickListener(new OnClickListener() {

	public void onClick(View v) {
		// write on SD card file data in the text box
		try {
			String root = Environment.getExternalStorageDirectory().toString();
			final File newDir = new File(root + "/"+subject+"/"); 
			Toast.makeText(getApplicationContext(), root, Toast.LENGTH_SHORT ).show();
            newDir.mkdirs();
            Random gen = new Random();
            int n = 10000;
            n = gen.nextInt(n);
            textname ="text-"+ n +".txt";
            File myFile = new File (newDir, textname);
             
            if (myFile.exists ()) myFile.delete ();
            try {
                FileOutputStream out = new FileOutputStream(myFile);      
                out.close();
                SharedPreferences sp = getSharedPreferences("AddPreferences", 0); // Open SharedPreferences with name AppPreferences
   	         	Editor editor = sp.edit();
   	         	String prevString=sp.getString(tag,"");
   	         	editor.putString(tag,prevString.concat(root + "/"+subject+"/"+textname+","));
   	         	editor.commit();
   	         	String prevString1=sp.getString(tag,"");
   	         	Toast.makeText(getApplicationContext(),"Hello "+ prevString1, Toast.LENGTH_SHORT ).show();
   	         	editor.commit();
               } 
            	catch (Exception e) {
                    
            	}	
		
            	myFile.createNewFile();
            	FileOutputStream fOut = new FileOutputStream(myFile);
            	OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            	myOutWriter.append(txtData.getText());
				myOutWriter.close();
				fOut.close();
				Toast.makeText(getBaseContext(),
					"Done writing SD 'mysdfile.txt'",
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
	}// onClick
	}); 

	

		btnClearScreen = (Button) findViewById(R.id.btnClearScreen);
		btnClearScreen.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// clear text box
				txtData.setText("");
			}
		}); // btnClearScreen

	

	}// onCreate

}// AndSDcard


