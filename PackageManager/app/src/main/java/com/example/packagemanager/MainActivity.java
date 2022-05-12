package com.example.packagemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Object PickAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       TextView textview = findViewById(R.id.Text1);
       TextView textView1 = findViewById(R.id.text3);
       String version="";
       try {
           PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(),0);
           version =pInfo.versionName;
       }catch (PackageManager.NameNotFoundException e){
           e.printStackTrace();
       }
       String appVersion = version;
       textview.setText("Version:"+appVersion);
       final PackageManager pm = getApplicationContext().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo(this.getPackageName(), 0);
        }catch (final PackageManager.NameNotFoundException e){
            ai=null;
        }
        final String applicationName = (String) (ai!=null? pm.getApplicationLabel(ai):"unknown");
        textView1.setText("Name:" +applicationName);

    }
}