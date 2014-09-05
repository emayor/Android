package com.example.acelerometro;

import android.os.Bundle;
import android.app.Activity;
import java.util.List;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{
    
    private float x = 0, y = 0, z = 0;
    	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);        
        if (sensors.size() > 0) {
        	sm.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_GAME);
        }
    }
    
    @Override
    protected void onPause() {
    	SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);    	
        sm.unregisterListener(this);
        super.onPause();
    }
    
    @Override
    protected void onStop() {
    	SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);    	
        sm.unregisterListener(this);
        super.onStop();
    }

	@Override
	public void onSensorChanged(SensorEvent event) {
                    
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
            
        ((TextView) findViewById(R.id.txtAccX)).setText("Acelerómetro X " + x);
        ((TextView) findViewById(R.id.txtAccY)).setText("Acelerómetro Y " + y);
        ((TextView) findViewById(R.id.txtAccZ)).setText("Acelerómetro Z " + z);
     	
	}  
	
    @Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {}

}