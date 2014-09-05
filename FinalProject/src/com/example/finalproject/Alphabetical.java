package com.example.finalproject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.Calendar;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Alphabetical extends Activity implements SensorEventListener {

	private double time = 0;
	private String session = "";
	private String user = "";
	private String word = "";
	private TextView textView1 = null;
	private TextView textView2 = null;
	private TextView textView3 = null;
	private TextView textView4 = null;
	private TextView textView5 = null;
	private TextView textView6 = null;
	private TextView textView7 = null;
	private TextView textView8 = null;
	private TextView textView9 = null;
	private TextView textView10 = null;
	private Button buttonA;
	private Button buttonB;
	private Button buttonC;
	private Button buttonD;
	private Button buttonE;
	private Button buttonF;
	private Button buttonG;
	private Button buttonH;
	private Button buttonI;
	private Button buttonJ;
	private Button buttonK;
	private Button buttonL;
	private Button buttonM;
	private Button buttonN;
	private Button buttonÑ;
	private Button buttonO;
	private Button buttonP;
	private Button buttonQ;
	private Button buttonR;
	private Button buttonS;
	private Button buttonT;
	private Button buttonU;
	private Button buttonV;
	private Button buttonW;
	private Button buttonX;
	private Button buttonY;
	private Button buttonZ;
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
		setContentView(R.layout.activity_alphabetical);
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
		textView6 = (TextView) findViewById(R.id.textView11);
		textView7 = (TextView) findViewById(R.id.textView12);
		textView8 = (TextView) findViewById(R.id.textView13);
		textView9 = (TextView) findViewById(R.id.textView14);
		textView10 = (TextView) findViewById(R.id.textView15);

		buttonA = (Button) findViewById(R.id.buttonA);
		buttonA.setOnTouchListener(new MyTouchListener());
		buttonB = (Button) findViewById(R.id.buttonB);
		buttonB.setOnTouchListener(new MyTouchListener());
		buttonC = (Button) findViewById(R.id.buttonC);
		buttonC.setOnTouchListener(new MyTouchListener());
		buttonD = (Button) findViewById(R.id.buttonD);
		buttonD.setOnTouchListener(new MyTouchListener());
		buttonE = (Button) findViewById(R.id.buttonE);
		buttonE.setOnTouchListener(new MyTouchListener());
		buttonF = (Button) findViewById(R.id.buttonF);
		buttonF.setOnTouchListener(new MyTouchListener());
		buttonG = (Button) findViewById(R.id.buttonG);
		buttonG.setOnTouchListener(new MyTouchListener());
		buttonH = (Button) findViewById(R.id.buttonH);
		buttonH.setOnTouchListener(new MyTouchListener());
		buttonI = (Button) findViewById(R.id.buttonI);
		buttonI.setOnTouchListener(new MyTouchListener());
		buttonJ = (Button) findViewById(R.id.buttonJ);
		buttonJ.setOnTouchListener(new MyTouchListener());
		buttonK = (Button) findViewById(R.id.buttonK);
		buttonK.setOnTouchListener(new MyTouchListener());
		buttonL = (Button) findViewById(R.id.buttonL);
		buttonL.setOnTouchListener(new MyTouchListener());
		buttonM = (Button) findViewById(R.id.buttonM);
		buttonM.setOnTouchListener(new MyTouchListener());
		buttonN = (Button) findViewById(R.id.buttonN);
		buttonN.setOnTouchListener(new MyTouchListener());
		buttonÑ = (Button) findViewById(R.id.buttonÑ);
		buttonÑ.setOnTouchListener(new MyTouchListener());
		buttonO = (Button) findViewById(R.id.buttonO);
		buttonO.setOnTouchListener(new MyTouchListener());
		buttonP = (Button) findViewById(R.id.buttonP);
		buttonP.setOnTouchListener(new MyTouchListener());
		buttonQ = (Button) findViewById(R.id.buttonQ);
		buttonQ.setOnTouchListener(new MyTouchListener());
		buttonR = (Button) findViewById(R.id.buttonR);
		buttonR.setOnTouchListener(new MyTouchListener());
		buttonS = (Button) findViewById(R.id.buttonS);
		buttonS.setOnTouchListener(new MyTouchListener());
		buttonT = (Button) findViewById(R.id.buttonT);
		buttonT.setOnTouchListener(new MyTouchListener());
		buttonU = (Button) findViewById(R.id.buttonU);
		buttonU.setOnTouchListener(new MyTouchListener());
		buttonV = (Button) findViewById(R.id.buttonV);
		buttonV.setOnTouchListener(new MyTouchListener());
		buttonW = (Button) findViewById(R.id.buttonW);
		buttonW.setOnTouchListener(new MyTouchListener());
		buttonX = (Button) findViewById(R.id.buttonX);
		buttonX.setOnTouchListener(new MyTouchListener());
		buttonY = (Button) findViewById(R.id.buttonY);
		buttonY.setOnTouchListener(new MyTouchListener());
		buttonZ = (Button) findViewById(R.id.buttonZ);
		buttonZ.setOnTouchListener(new MyTouchListener());
		buttonDelete = (Button) findViewById(R.id.buttonDelete);
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
		getMenuInflater().inflate(R.menu.alphabetical, menu);
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

		if (word.equals("BIOMETRIC")) {
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
					+ holdTime + ", " + (xAt / 9) + ", " + (yAt / 9) + ", "
					+ (zAt / 9) + ", " + (xGt / 9) + ", " + (yGt / 9) + ", "
					+ (zGt / 9) + "\n");
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
		textView6.setText("");
		textView7.setText("");
		textView8.setText("");
		textView9.setText("");
		textView10.setText("");

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
			word = s;
			b = true;
		}

		if (!textView2.getText().equals("") && textView3.getText().equals("")
				&& textView4.getText().equals("")
				&& textView5.getText().equals("") && b == false) {
			textView3.setText(s);
			word = word + s;
			b = true;
		}

		if (!textView2.getText().equals("") && !textView3.getText().equals("")
				&& textView4.getText().equals("")
				&& textView5.getText().equals("") && b == false) {
			textView4.setText(s);
			word = word + s;
			b = true;
		}

		if (!textView2.getText().equals("") && !textView3.getText().equals("")
				&& !textView4.getText().equals("")
				&& textView5.getText().equals("") && b == false) {
			textView5.setText(s);
			word = word + s;
			b = true;
		}

		if (!textView2.getText().equals("") && !textView3.getText().equals("")
				&& !textView4.getText().equals("")
				&& !textView5.getText().equals("")
				&& textView6.getText().equals("") && b == false) {
			textView6.setText(s);
			word = word + s;
			b = true;
		}

		if (!textView2.getText().equals("") && !textView3.getText().equals("")
				&& !textView4.getText().equals("")
				&& !textView5.getText().equals("")
				&& !textView6.getText().equals("")
				&& textView7.getText().equals("") && b == false) {
			textView7.setText(s);
			word = word + s;
			b = true;
		}

		if (!textView2.getText().equals("") && !textView3.getText().equals("")
				&& !textView4.getText().equals("")
				&& !textView5.getText().equals("")
				&& !textView6.getText().equals("")
				&& !textView7.getText().equals("")
				&& textView8.getText().equals("") && b == false) {
			textView8.setText(s);
			word = word + s;
			b = true;
		}

		if (!textView2.getText().equals("") && !textView3.getText().equals("")
				&& !textView4.getText().equals("")
				&& !textView5.getText().equals("")
				&& !textView6.getText().equals("")
				&& !textView7.getText().equals("")
				&& !textView8.getText().equals("")
				&& textView9.getText().equals("") && b == false) {
			textView9.setText(s);
			word = word + s;
			b = true;
		}
		
		if (!textView2.getText().equals("") && !textView3.getText().equals("")
				&& !textView4.getText().equals("")
				&& !textView5.getText().equals("")
				&& !textView6.getText().equals("")
				&& !textView7.getText().equals("")
				&& !textView8.getText().equals("")
				&& !textView9.getText().equals("") && textView10.getText().equals("") && b == false) {
			textView9.setText(s);
			word = word + s;
			save(word);
		}
	}

	public void push(View view) {

		if (counter > 0) {

			switch (view.getId()) {

			case R.id.buttonA:
				comprobe("A");
				break;
			case R.id.buttonB:
				comprobe("B");
				break;
			case R.id.buttonC:
				comprobe("C");
				break;
			case R.id.buttonD:
				comprobe("D");
				break;
			case R.id.buttonE:
				comprobe("E");
				break;
			case R.id.buttonF:
				comprobe("F");
				break;
			case R.id.buttonG:
				comprobe("G");
				break;
			case R.id.buttonH:
				comprobe("H");
				break;
			case R.id.buttonI:
				comprobe("I");
				break;
			case R.id.buttonJ:
				comprobe("J");
				break;
			case R.id.buttonK:
				comprobe("K");
				break;
			case R.id.buttonL:
				comprobe("L");
				break;
			case R.id.buttonM:
				comprobe("M");
				break;
			case R.id.buttonN:
				comprobe("N");
				break;
			case R.id.buttonÑ:
				comprobe("Ñ");
				break;
			case R.id.buttonO:
				comprobe("O");
				break;
			case R.id.buttonP:
				comprobe("P");
				break;
			case R.id.buttonQ:
				comprobe("Q");
				break;
			case R.id.buttonR:
				comprobe("R");
				break;
			case R.id.buttonS:
				comprobe("S");
				break;
			case R.id.buttonT:
				comprobe("T");
				break;
			case R.id.buttonU:
				comprobe("U");
				break;
			case R.id.buttonV:
				comprobe("V");
				break;
			case R.id.buttonW:
				comprobe("W");
				break;
			case R.id.buttonX:
				comprobe("X");
				break;
			case R.id.buttonY:
				comprobe("Y");
				break;
			case R.id.buttonZ:
				comprobe("Z");
				break;
			case R.id.buttonDelete:
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