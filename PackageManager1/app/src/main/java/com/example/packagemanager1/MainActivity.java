package com.example.packagemanager1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
String version ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView contact =findViewById(R.id.contacts);
        TextView gallery = findViewById(R.id.gallery);
        TextView youtube = findViewById(R.id.youtube);

        boolean contactInstalled = appInstalled("com.google.android.contacts");
        boolean galleryInstalled = appInstalled("com.android.provision");
        boolean youtubeInstalled = appInstalled("com.google.android.youtube");

        if (contactInstalled) {
            contact.setText("Installed -v"+version);
        }
        if (galleryInstalled) {
            gallery.setText("Installed -v"+version);
        }
        if (youtubeInstalled) {
            youtube.setText("Installed -v"+version);
        }

    }

    private boolean appInstalled(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(),0);
            version =pInfo.versionName;
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }


        return app_installed;
    }
}