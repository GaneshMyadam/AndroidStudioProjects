package com.example.audiomanagerex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    Button mode,ring,vibrate,silent,raise;
    private AudioManager myAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrate=(Button)findViewById(R.id.vibrate);
        ring=(Button)findViewById(R.id.ring);
        raise=(Button)findViewById(R.id.raise);
        mode=(Button)findViewById(R.id.mode);
        silent=(Button)findViewById(R.id.silent);
        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

       /*  NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !notificationManager.isNotificationPolicyAccessGranted()) {

            Intent intent = new Intent(
                    android.provider.Settings
                            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);

            startActivity(intent);
        }*/

        raise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.adjustVolume(AudioManager.ADJUST_RAISE, 1);
                Toast.makeText(MainActivity.this,"Volume raised",
                        Toast.LENGTH_LONG).show();
            }
        });
        vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(MainActivity.this,"Now in Vibrate Mode",
                        Toast.LENGTH_LONG).show();
            }
        });

        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(MainActivity.this,"Now in Ringing Mode",
                        Toast.LENGTH_LONG).show();
            }
        });

        silent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(MainActivity.this,"Now in silent Mode",
                        Toast.LENGTH_LONG).show();
            }
        });

        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mod=myAudioManager.getRingerMode();
                if(mod==AudioManager.RINGER_MODE_VIBRATE){
                    Toast.makeText(MainActivity.this,"Now in Vibrate Mode",
                            Toast.LENGTH_LONG).show();
                } else if(mod==AudioManager.RINGER_MODE_NORMAL){
                    Toast.makeText(MainActivity.this,"Now in Ringing Mode",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this,"Now in Vibrate Mode",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}