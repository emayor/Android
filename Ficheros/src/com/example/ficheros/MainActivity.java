package com.example.ficheros;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.Activity;
import java.sql.Timestamp;
import java.util.Calendar;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.io.*;

@SuppressLint("InlinedApi")
public class MainActivity extends Activity implements SensorEventListener {

	private Sensor sensorAcelerometro = null;
	private Sensor sensorGiroscopio = null;
	private Sensor sensorAceleracionLineal = null;
	private Sensor sensorOrientacion = null;
	private Sensor sensorRotacion = null;
	private TextView textViewAcelerometro = null;
	private TextView textViewGiroscopio = null;
	private TextView textViewAceleracionLineal = null;
	private TextView textViewOrientacion = null;
	private TextView textViewRotacion = null;
	private float xA = 0, yA = 0, zA = 0;
	private float xG = 0, yG = 0, zG = 0;
	private float xAL = 0, yAL = 0, zAL = 0;
	private float xO = 0, yO = 0, zO = 0;
	private float xR = 0, yR = 0, zR = 0;
	private int usuario;

	public void setUser(int user) {
		usuario = user;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		File tarjeta = Environment.getExternalStorageDirectory();
		File file = new File(tarjeta.getAbsolutePath(), "resultados.txt");
		BufferedReader reader = null;
		OutputStreamWriter osw = null;
		if (file.exists()) {
			try {
				reader = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			String line = null;
			String ultimaLinea = null;
			
			try {
				while ((line = reader.readLine()) != null) {
					ultimaLinea = line;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (ultimaLinea != null) {
				String[] elementos = ultimaLinea.split(", ");
				int x = Integer.parseInt(elementos[0]);
				setUser(x + 1);
			}
			Toast.makeText(this, "Usuario número: " + usuario,
					Toast.LENGTH_SHORT).show();
		} else {
			try {
				osw = new OutputStreamWriter(new FileOutputStream(file, true));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				osw.write("");
				osw.flush();
				osw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			usuario = 1;
			Toast.makeText(this, "Usuario número: " + usuario,
					Toast.LENGTH_SHORT).show();
		}

		SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);

		sensorAcelerometro = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorGiroscopio = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		sensorAceleracionLineal = sm
				.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
		sensorOrientacion = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		sensorRotacion = sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

		if (sensorAcelerometro != null) {
			sm.registerListener(this, sensorAcelerometro,
					SensorManager.SENSOR_DELAY_NORMAL);
		}

		if (sensorGiroscopio != null) {
			sm.registerListener(this, sensorGiroscopio,
					SensorManager.SENSOR_DELAY_NORMAL);
		}

		if (sensorAceleracionLineal != null) {
			sm.registerListener(this, sensorAceleracionLineal,
					SensorManager.SENSOR_DELAY_NORMAL);
		}

		if (sensorOrientacion != null) {
			sm.registerListener(this, sensorOrientacion,
					SensorManager.SENSOR_DELAY_NORMAL);
		}

		if (sensorRotacion != null) {
			sm.registerListener(this, sensorRotacion,
					SensorManager.SENSOR_DELAY_NORMAL);
		}

		textViewAcelerometro = (TextView) findViewById(R.id.textView1);
		textViewAcelerometro.setTextSize(12);

		textViewGiroscopio = (TextView) findViewById(R.id.textView2);
		textViewGiroscopio.setTextSize(12);

		textViewAceleracionLineal = (TextView) findViewById(R.id.textView3);
		textViewAceleracionLineal.setTextSize(12);

		textViewOrientacion = (TextView) findViewById(R.id.textView4);
		textViewOrientacion.setTextSize(12);

		textViewRotacion = (TextView) findViewById(R.id.textView5);
		textViewRotacion.setTextSize(12);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onSensorChanged(SensorEvent event) {

		synchronized (this) {

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

			case Sensor.TYPE_LINEAR_ACCELERATION:
				data = event.values;
				xAL = data[0];
				yAL = data[1];
				zAL = data[2];
				break;

			case Sensor.TYPE_ORIENTATION:
				data = event.values;
				xO = data[0];
				yO = data[1];
				zO = data[2];
				break;

			case Sensor.TYPE_ROTATION_VECTOR:
				data = event.values;
				xR = data[0];
				yR = data[1];
				zR = data[2];
				break;

			default:
				break;
			}
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

	public void guardarDatos(View view) {
		String nomarchivo = "resultados.txt";
		Calendar calendar = Calendar.getInstance();
		Timestamp tiempo = new java.sql.Timestamp(calendar.getTime().getTime());
		double time = tiempo.getTime();
		try {
			File tarjeta = Environment.getExternalStorageDirectory();
			File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(file, true));
			osw.write(usuario + ", " + time + ", " + xA + ", " + yA + ", "
					+ zA + ", " + xG + ", " + yG + ", " + zG + ", " + xAL
					+ ", " + yAL + ", " + zAL + ", " + xO + ", " + yO + ", "
					+ zO + ", " + xR + ", " + yR + ", " + zR + "\n");
			osw.flush();
			osw.close();
			Toast.makeText(this, "Los datos fueron grabados correctamente",
					Toast.LENGTH_SHORT).show();

			textViewAcelerometro.setText("ACELERÓMETRO" + "\nx: " + xA
					+ "\ny: " + yA + "\nz: " + zA + "\n");
			textViewGiroscopio.setText("GIROSCOPIO" + "\nx: " + xG + "\ny: "
					+ yG + "\nz: " + zG + "\n");
			textViewAceleracionLineal.setText("ACELERACIÓN LINEAL" + "\nx: "
					+ xAL + "\ny: " + yAL + "\nz: " + zAL + "\n");
			textViewOrientacion.setText("ORIENTACIÓN" + "\nx: " + xO + "\ny: "
					+ yO + "\nz: " + zO + "\n");
			textViewRotacion.setText("ROTACIÓN" + "\nx: " + xR + "\ny: " + yR
					+ "\nz: " + zR);

		} catch (IOException ioe) {
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
}