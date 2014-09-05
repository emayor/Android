package com.example.gb2s;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class People extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.people, menu);
		return true;
	}
	
	public void goCarmen(View view){

    	Intent intent = new Intent(this, Carmen.class);
    	startActivity(intent);
    }
	
	public void goDaniel(View view){

    	Intent intent = new Intent(this, Daniel.class);
    	startActivity(intent);
    }
	
	public void goVicente(View view){

    	Intent intent = new Intent(this, Vicente.class);
    	startActivity(intent);
    }
	
	public void goGonzalo(View view){

    	Intent intent = new Intent(this, Gonzalo.class);
    	startActivity(intent);
    }
	
	public void goMiguel(View view){

    	Intent intent = new Intent(this, Miguel.class);
    	startActivity(intent);
    }
	
	public void goEnrique(View view){

    	Intent intent = new Intent(this, Enrique.class);
    	startActivity(intent);
    }
	
	public void goAna(View view){

    	Intent intent = new Intent(this, Ana.class);
    	startActivity(intent);
    }
	
	public void goJavier(View view){

    	Intent intent = new Intent(this, Javier.class);
    	startActivity(intent);
    }
	
	public void goRodrigo(View view){

    	Intent intent = new Intent(this, Rodrigo.class);
    	startActivity(intent);
    }
	
	public void goIgnacio(View view){

    	Intent intent = new Intent(this, Ignacio.class);
    	startActivity(intent);
    }
	
	public void goIdola(View view){

    	Intent intent = new Intent(this, Idola.class);
    	startActivity(intent);
    }
	
}
