package com.example.adambenyahia.lightsensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Sensor sensor;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onResume(){
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    MediaPlayer mp;
    boolean isrunning=false;


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0]>40 && isrunning==false){
            isrunning=true;
            mp=new MediaPlayer();
            try {
                mp.setDataSource("https://www.youtube.com/watch?v=Ow0ILNvbSAs");
                mp.prepare();
                mp.start();
            }   catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
