package com.example.gb2s;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Daniel extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daniel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.daniel, menu);
		return true;
	}

}
