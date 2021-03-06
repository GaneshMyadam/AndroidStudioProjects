package com.example.myserverapk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG=MainActivity.class.getSimpleName();

    private Button buttonStartService, buttonStopService;
    private Context mContext;

    private Intent serviceIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=getApplicationContext();
        buttonStartService=(Button)findViewById(R.id.buttonStartService);
        buttonStopService=(Button)findViewById(R.id.buttonStopService);

        buttonStopService.setOnClickListener(this);
        buttonStartService.setOnClickListener(this);
        serviceIntent=new Intent(getApplicationContext(),MyService.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonStartService: startService(serviceIntent);
                Toast.makeText(mContext,"Service Started",Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonStopService: stopService(serviceIntent);
                break;
            default:break;
        }

    }
}