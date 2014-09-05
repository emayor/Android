package com.example.finalproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.Calendar;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	Calendar calendar = Calendar.getInstance();
	Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());

	private double time = timestamp.getTime();
	private String session = "1";
	private String user = "1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		File tarjeta = Environment.getExternalStorageDirectory();
		File file = new File(tarjeta.getAbsolutePath(), "session.txt");
		BufferedReader reader = null;
		OutputStreamWriter osw = null;

		if (file.exists()) {
			try {
				reader = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			String line = null;
			String lastLine = null;

			try {
				while ((line = reader.readLine()) != null) {
					lastLine = line;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (lastLine != null) {
				int x = Integer.parseInt(lastLine);
				x = x + 1;
				setSession(Integer.toString(x));
				try {
					osw = new OutputStreamWriter(new FileOutputStream(file,
							true));
					osw.write(getSession() + "\n");
					osw.flush();
					osw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				osw = new OutputStreamWriter(new FileOutputStream(file, true));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				osw = new OutputStreamWriter(new FileOutputStream(file, true));
				osw.write(getSession() + "\n");
				osw.flush();
				osw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		tarjeta = Environment.getExternalStorageDirectory();
		file = new File(tarjeta.getAbsolutePath(), "user.txt");
		reader = null;
		osw = null;

		if (file.exists()) {
			try {
				reader = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			String line = null;
			String lastLine = null;

			try {
				while ((line = reader.readLine()) != null) {
					lastLine = line;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (lastLine != null) {
				int x = Integer.parseInt(lastLine);
				x = x + 1;
				setUser(Integer.toString(x));
				try {
					osw = new OutputStreamWriter(new FileOutputStream(file,
							true));
					osw.write(getUser() + "\n");
					osw.flush();
					osw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				osw = new OutputStreamWriter(new FileOutputStream(file, true));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				osw = new OutputStreamWriter(new FileOutputStream(file, true));
				osw.write(getUser() + "\n");
				osw.flush();
				osw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		TextView textUser = (TextView) findViewById(R.id.textView2);
		textUser.setText(getUser());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String ses) {
		session = ses;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String us) {
		user = us;
	}

	public void user(View view) {

		EditText editUser = (EditText) findViewById(R.id.editText1);
		TextView textUser = (TextView) findViewById(R.id.textView2);

		textUser.setText(editUser.getText().toString());
		setUser(editUser.getText().toString());

		try {
			File tarjeta = Environment.getExternalStorageDirectory();
			File file = new File(tarjeta.getAbsolutePath(), "user.txt");
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(file, true));
			osw.write(user + "\n");
			osw.flush();
			osw.close();
		} catch (IOException e) {
		}
	}

	public void fourDigits(View view) {

		Intent intent = new Intent(this, FourDigits.class);
		intent.putExtra("user", user);
		intent.putExtra("session", session);
		intent.putExtra("time", time);
		startActivity(intent);
	}

	public void eightDigits(View view) {

		Intent intent = new Intent(this, EightDigits.class);
		intent.putExtra("user", user);
		intent.putExtra("session", session);
		intent.putExtra("time", time);
		startActivity(intent);
	}

	public void alphabetical(View view) {

		Intent intent = new Intent(this, Alphabetical.class);
		intent.putExtra("user", user);
		intent.putExtra("session", session);
		intent.putExtra("time", time);
		startActivity(intent);
	}
}