package com.example.jogo;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Sensor accelerometer;
    SensorManager sensorManager;
    private EditText textView;
    private Button tiro;
    private float x;
    private int posAtual = 0;
    private String text = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        textView = findViewById(R.id.textView);
        tiro = findViewById(R.id.tiro);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        x = sensorEvent.values[0];

        if (x > 0)
            posAtual++;
        else if (x < 0) {
            if (posAtual > 0) {
                posAtual--;
            }
        }

        text = "";

        for (int i = 0; i < posAtual; i++) {
            if (posAtual < 64) {
                text += " ";
            }
            else {
                posAtual--;
            }
        }

        text += "A";

        textView.setText(text);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
