package com.example.launchmodes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("First Activity", "OnCreate Call");
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openActivity1();
            }
        });
    }
    public void openActivity1(){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}