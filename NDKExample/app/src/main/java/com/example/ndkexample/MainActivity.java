package com.example.ndkexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
static {
    System.loadLibrary("jniex");
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text1= findViewById(R.id.text_helloWorld);
        text1.setText(HelloWorld());
        setContentView(text1);
    }
    public native String HelloWorld();
}