package com.example.finalproject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.Calendar;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FourDigits extends Activity implements SensorEventListener {

	private double time = 0;
	private String session = "";
	private String user = "";
	private String number = "";
	private TextView textView1 = null;
	private TextView textView2 = null;
	private TextView textView3 = null;
	private TextView textView4 = null;
	private TextView textView5 = null;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private Button button7;
	private Button button8;
	private Button button9;
	private Button button0;
	private Button buttonDelete;
	private int counter = 10;
	private Timestamp[] downTime = new Timestamp[4];
	private Timestamp[] upTime = new Timestamp[4];
	private Sensor sensorAccelerometer = null;
	private Sensor sensorGyroscope = null;
	private float xA = 0, yA = 0, zA = 0;
	private float xAt = 0, yAt = 0, zAt = 0;
	private float xG = 0, yG = 0, zG = 0;
	private float xGt = 0, yGt = 0, zGt = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_four_digits);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		time = getIntent().getExtras().getDouble("time");
		user = getIntent().getExtras().getString("user");
		session = getIntent().getExtras().getString("session");

		TextView textViewUser = (TextView) findViewById(R.id.textView2);
		textViewUser.setText(user);

		textView1 = (TextView) findViewById(R.id.textView6);
		textView2 = (TextView) findViewById(R.id.textView7);
		textView3 = (TextView) findViewById(R.id.textView8);
		textView4 = (TextView) findViewById(R.id.textView9);
		textView5 = (TextView) findViewById(R.id.textView10);

		button1 = (Button) findViewById(R.id.button1);
		button1.setOnTouchListener(new MyTouchListener());
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnTouchListener(new MyTouchListener());
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnTouchListener(new MyTouchListener());
		button4 = (Button) findViewById(R.id.button4);
		button4.setOnTouchListener(new MyTouchListener());
		button5 = (Button) findViewById(R.id.button5);
		button5.setOnTouchListener(new MyTouchListener());
		button6 = (Button) findViewById(R.id.button6);
		button6.setOnTouchListener(new MyTouchListener());
		button7 = (Button) findViewById(R.id.button7);
		button7.setOnTouchListener(new MyTouchListener());
		button8 = (Button) findViewById(R.id.button8);
		button8.setOnTouchListener(new MyTouchListener());
		button9 = (Button) findViewById(R.id.button9);
		button9.setOnTouchListener(new MyTouchListener());
		button0 = (Button) findViewById(R.id.button11);
		button0.setOnTouchListener(new MyTouchListener());
		buttonDelete = (Button) findViewById(R.id.button12);
		buttonDelete.setOnTouchListener(new MyTouchListener());

		SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);

		sensorAccelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorGyroscope = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

		if (sensorAccelerometer != null) {
			sm.registerListener(this, sensorAccelerometer,
					SensorManager.SENSOR_DELAY_NORMAL);
		}

		if (sensorGyroscope != null) {
			sm.registerListener(this, sensorGyroscope,
					SensorManager.SENSOR_DELAY_NORMAL);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.four_digits, menu);
		return true;
	}

	public double averageTime(Timestamp[] t) {

		double average = 0;

		for (int i = 0; i < t.length; i++) {
			average += t[i].getTime();
		}

		average = (average / t.length);
		return average;
	}

	public void save(String key) {

		String nomarchivo;

		if (number.equals("2761")) {
			nomarchivo = "result.dat";
			counter--;
			textView1.setText(Integer.toString(counter));
		} else {
			nomarchivo = "result.err";
		}

		double holdTime = averageTime(upTime) - averageTime(downTime);
		try {
			File tarjeta = Environment.getExternalStorageDirectory();
			File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(file, true));
			osw.write(time + ", " + user + ", " + session + ", " + key + ", "
					+ averageTime(downTime) + ", " + averageTime(upTime) + ", "
					+ holdTime + ", " + (xAt / 4) + ", " + (yAt / 4) + ", "
					+ (zAt / 4) + ", " + (xGt / 4) + ", " + (yGt / 4) + ", "
					+ (zGt / 4) + "\n");
			osw.flush();
			osw.close();
		} catch (IOException ioe) {
		}
		clean();
	}

	public void clean() {
		textView2.setText("");
		textView3.setText("");
		textView4.setText("");
		textView5.setText("");

		for (int i = 0; i < downTime.length; i++) {
			downTime[i] = null;
		}
		for (int i = 0; i < downTime.length; i++) {
			upTime[i] = null;
		}
		xAt = 0;
		yAt = 0;
		zAt = 0;
		xGt = 0;
		yGt = 0;
		zGt = 0;
	}

	public void comprobe(String s) {

		boolean b = false;

		if (textView2.getText().equals("") && textView3.getText().equals("")
				&& textView4.getText().equals("")
				&& textView5.getText().equals("") && b == false) {
			textView2.setText(s);
			number = s;
			b = true;
		}

		if (!textView2.getText().equals("") && textView3.getText().equals("")
				&& textView4.getText().equals("")
				&& textView5.getText().equals("") && b == false) {
			textView3.setText(s);
			number = number + s;
			b = true;
		}

		if (!textView2.getText().equals("") && !textView3.getText().equals("")
				&& textView4.getText().equals("")
				&& textView5.getText().equals("") && b == false) {
			textView4.setText(s);
			number = number + s;
			b = true;
		}

		if (!textView2.getText().equals("") && !textView3.getText().equals("")
				&& !textView4.getText().equals("")
				&& textView5.getText().equals("") && b == false) {
			textView5.setText(s);
			number = number + s;
			save(number);
		}
	}

	public void push(View view) {

		if (counter > 0) {

			switch (view.getId()) {

			case R.id.button1:
				comprobe("1");
				break;
			case R.id.button2:
				comprobe("2");
				break;
			case R.id.button3:
				comprobe("3");
				break;
			case R.id.button4:
				comprobe("4");
				break;
			case R.id.button5:
				comprobe("5");
				break;
			case R.id.button6:
				comprobe("6");
				break;
			case R.id.button7:
				comprobe("7");
				break;
			case R.id.button8:
				comprobe("8");
				break;
			case R.id.button9:
				comprobe("9");
				break;
			case R.id.button11:
				comprobe("0");
				break;
			case R.id.button12:
				clean();
				break;
			}
		} else {
			Toast.makeText(this, "You have finished the task",
					Toast.LENGTH_SHORT).show();
		}
	}

	public class MyTouchListener implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent m) {

			Calendar calendar = Calendar.getInstance();
			Boolean b;

			switch (m.getAction()) {

			case (MotionEvent.ACTION_DOWN):

				b = false;
				for (int i = 0; i < downTime.length; i++) {
					if (downTime[i] == null && b == false) {
						downTime[i] = new java.sql.Timestamp(calendar.getTime()
								.getTime());
						b = true;
					}
				}
				break;
			case (MotionEvent.ACTION_UP):

				b = false;
				for (int i = 0; i < downTime.length; i++) {
					if (upTime[i] == null && b == false) {
						upTime[i] = new java.sql.Timestamp(calendar.getTime()
								.getTime());
						b = true;
					}
				}

				xAt += xA;
				yAt += yA;
				zAt += zA;
				xGt += xG;
				yGt += yG;
				zGt += zG;
				push(v);
				break;

			}
			return true;
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		float[] data;

		switch (event.sensor.getType()) {

		case Sensor.TYPE_ACCELEROMETER:
			data = event.values;
			xA = data[0];
			yA = data[1];
			zA = data[2];
			break;

		case Sensor.TYPE_GYROSCOPE:
			data = event.values;
			xG = data[0];
			yG = data[1];
			zG = data[2];
			break;

		default:
			break;
		}
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {}
}
