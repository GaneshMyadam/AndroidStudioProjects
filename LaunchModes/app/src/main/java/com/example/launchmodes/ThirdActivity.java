package com.example.launchmodes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Log.e("SecondActivity", "Activity3");
        Button btn =findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openActivity3();
            }
        });
    }
    public void openActivity3(){
        Intent intent = new Intent(this, FourthActivity.class);
        startActivity(intent);
    }
}