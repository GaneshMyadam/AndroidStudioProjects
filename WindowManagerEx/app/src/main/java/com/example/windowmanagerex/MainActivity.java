package com.example.windowmanagerex;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.windowmanagerex.Common.Common;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
    private Button minimizeBtn;
    private AlertDialog dialog;
    private EditText descEditArea;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minimizeBtn = findViewById(R.id.buttonMinimize);
        descEditArea = findViewById(R.id.descEditText);
        save = findViewById(R.id.saveBtn);
        if (isMyServiceRunning()) {

            stopService(new Intent(MainActivity.this, FloatingWindow.class));
        }
        descEditArea.setText(Common.currentDesc);
        descEditArea.setSelection(descEditArea.getText().toString().length());
        descEditArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Common.currentDesc = descEditArea.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Common.savedDesc = descEditArea.getText().toString();
                descEditArea.setCursorVisible(false);
                descEditArea.clearFocus();
                Toast.makeText(MainActivity.this, "Text Saved!!!", Toast.LENGTH_SHORT).show();
            }
        });
        minimizeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (checkOverlayDisplayPermission()){
                    startService(new Intent(MainActivity.this, FloatingWindow.class));
                    finish();
                } else {
                    requestOverlayDisplayPermission();
                }
            }
        });
    }

    private boolean isMyServiceRunning(){
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (FloatingWindow.class.getName().equals(service.service.getClassName())){
                return true;
            }
        }
        return false;
    }

    private void requestOverlayDisplayPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Screen Overlay Permission Needed");

        builder.setMessage("Enable 'Display over other apps' from System Settings.");

        builder.setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, RESULT_OK);
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    private boolean checkOverlayDisplayPermission() {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
}