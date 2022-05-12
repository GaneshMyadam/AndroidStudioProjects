package com.example.broadcastsender;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn =findViewById(R.id.button);
        btn.setOnClickListener((view) -> {
 Intent intent = new Intent();
 intent.setAction("com.example.EXAMPLE_ACTION");
 sendOrderedBroadcast(intent, null);
        });
    }
}