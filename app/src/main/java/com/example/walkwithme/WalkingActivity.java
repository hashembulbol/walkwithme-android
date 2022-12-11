package com.example.walkwithme;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.walkwithme.helpers.SaveSharedPreference;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WalkingActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "WALKINGACTIVITY";

    TextView tvNoSteps;
    SensorManager sensorManager;
    Sensor stepCounter;
    Boolean isSensorPresent;
    private int milestoneStep;
    Button btnReset;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walking);

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){ // ask for permission
                requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        tvNoSteps = findViewById(R.id.tvNoSteps);
        btnReset = findViewById(R.id.resetBtn);

        tvNoSteps.setText( "" + 0);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePreferences(today(), 0);
                tvNoSteps.setText(String.valueOf(getPreferences(today())));

            }
        });

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){

            stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isSensorPresent = true;

        }
        else
        {
            tvNoSteps.setText("Sensor is not working");
            isSensorPresent = false;
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor == stepCounter){

            int totalStepSinceReboot = (int) sensorEvent.values[0];
            Log.i("TAG","Your total steps "+ String.valueOf(totalStepSinceReboot));

            int todayStep = getPreferences(today());
            if(todayStep == 0 && milestoneStep == totalStepSinceReboot){
                Log.d("TodaySteps0", String.valueOf(getPreferences(today())));
                milestoneStep = totalStepSinceReboot;
                tvNoSteps.setText(" " + 0);
            }
            else{

                int additionStep = totalStepSinceReboot - milestoneStep;
                savePreferences(today(), todayStep + additionStep);
                milestoneStep = totalStepSinceReboot;
                tvNoSteps.setText(String.valueOf(getPreferences(today())));

                Log.i("TAG","Your today step now is "+ getPreferences(today()));
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
            sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
            sensorManager.unregisterListener(this, stepCounter);
    }


    private void savePreferences(String key, int value) {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        Log.d("SavePrefrences by ",  key + " and " + value);

        editor.commit();
    }

    private int getPreferences(String key){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        Log.d("getPreferences by key",  key);
        int val = sharedPreferences.getInt(key, 0);
        Log.d("getPreferences value",  String.valueOf(val));
        return val;

    }

    public String today() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(Calendar.getInstance().getTime());
        Log.d("Date today is",  today);
        return today;
    }
}