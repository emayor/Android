package com.example.gb2s;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Portada extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.portada, menu);
        return true;
    }
    
    public void goAboutUs(View view){

    	Intent intent = new Intent(this, AboutUs.class);
    	startActivity(intent);
    }
    
    public void goPeople(View view){
    	
    	Intent intent = new Intent(this, People.class);
    	startActivity(intent);
    }
    
    public void goWebsite(View view){
    	
    	Intent intent = new Intent(Intent.ACTION_VIEW);
    	intent.setData(Uri.parse("http://www.gb2s.es"));
    	startActivity(intent);
    }
    
    public void goTwitter(View view){
    	
    	Intent intent = new Intent(Intent.ACTION_VIEW);
    	intent.setData(Uri.parse("http://www.twitter.com/gb2s"));
    	startActivity(intent);
    	
    }
}
