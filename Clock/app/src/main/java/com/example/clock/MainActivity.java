package com.example.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;

public class MainActivity extends AppCompatActivity {
    private MyClock myClocker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myClocker = (MyClock)findViewById(R.id.myClocker);
    }
}