package com.example.launchmodes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FourthActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        Log.e("FourthActivity", "Activity4");
        Button btn = findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openActivity4();
            }
        });
    }
    public void openActivity4(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}