package com.example.notetaker_start;


import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
 
public class AddImage extends Activity {
	
	Button btntakephoto, btnsave;
	ImageView ivdisplayphoto;
    String subject;
    String tag;
    String photoname;
	private File photofile;
	private int TAKENPHOTO = 0;
	Bitmap photo, canvasBitmap;
	Uri s;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_image);
		
		btntakephoto = (Button)findViewById(R.id.btn_takephoto);
		btnsave = (Button)findViewById(R.id.btn_save);
	
		ivdisplayphoto = (ImageView)findViewById(R.id.iv_displayphoto);
		Intent intent = getIntent();
		 
	     // 2. get message value from intent
	      subject = intent.getStringExtra("subject");
	      tag = intent.getStringExtra("tag");
		     
		
		btntakephoto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				
				File photostorage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
				photofile = new File(photostorage, (System.currentTimeMillis()) + ".jpg");
				
				
				Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //intent to start camera
				i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photofile));
				 s=Uri.fromFile(photofile);
				 Toast.makeText(getApplicationContext(),s.toString(),Toast.LENGTH_SHORT ).show();
				startActivityForResult(i, TAKENPHOTO);
			}
		});
		
		btnsave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
					Toast.makeText(getApplicationContext(), "saved ", Toast.LENGTH_SHORT ).show();
			
  
			      ivdisplayphoto.setDrawingCacheEnabled(true);
	               Bitmap bitmap = ivdisplayphoto.getDrawingCache();
	                
	               String root = Environment.getExternalStorageDirectory().toString();
	               File newDir = new File(root + "/"+"HIIII");    
	               newDir.mkdirs();
	               Random gen = new Random();
	               int n = 10000;
	               n = gen.nextInt(n);
	               String fotoname = "photo-"+ n +".jpg";
	               File file = new File (newDir, fotoname);
	               if (file.exists ()) file.delete (); 
	                	try {
	                       FileOutputStream out = new FileOutputStream(file);
	                      bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
	                       out.flush();
	                       out.close();
	                       Toast.makeText(getApplicationContext(), "saved to your folder", Toast.LENGTH_SHORT ).show();
 
		                } catch (Exception e) {
		                       
		                }
	                 
	                
	             }
		});}
		
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == TAKENPHOTO){
			
			try{
				photo = (Bitmap) data.getExtras().get("data");
			}	
			catch(NullPointerException ex){
			photo = BitmapFactory.decodeFile(photofile.getAbsolutePath());
			Toast.makeText(this, s.toString(), Toast.LENGTH_LONG).show();
		}
			
			if(photo != null){
				ivdisplayphoto.setImageBitmap(photo);
				Toast.makeText(this, s.toString(), Toast.LENGTH_LONG).show();
			
 
				
			}
			else{
				 
				Toast.makeText(this, "Oops,can't get the photo from your gallery", Toast.LENGTH_LONG).show();
			}
		}
		
	}
	
	}
	