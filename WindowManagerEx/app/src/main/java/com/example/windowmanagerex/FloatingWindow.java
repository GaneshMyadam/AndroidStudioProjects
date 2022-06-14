package com.example.windowmanagerex;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.windowmanagerex.Common.Common;

public class FloatingWindow extends Service {
    private ViewGroup floatView;
    private int LAYOUT_TYPE;
    private WindowManager.LayoutParams floatWindowLayoutParam;
    private WindowManager windowManager;
    private Button maximizeBtn;
    private EditText descEditArea;
    private Button saveBtn;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate() {
        super.onCreate();
        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        floatView = (ViewGroup) inflater.inflate(R.layout.floating_layout, null);
        maximizeBtn = floatView.findViewById(R.id.buttonMaximize);
        descEditArea = floatView.findViewById(R.id.descEditText);
        saveBtn = floatView.findViewById(R.id.saveBtn);
        descEditArea.setText(Common.currentDesc);
        descEditArea.setSelection(descEditArea.getText().toString().length());
        descEditArea.setCursorVisible(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_TYPE = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            LAYOUT_TYPE = WindowManager.LayoutParams.TYPE_TOAST;
        }
        floatWindowLayoutParam = new WindowManager.LayoutParams(
                (int) (width * (0.55f)),
                (int) (height * (0.58f)),
                LAYOUT_TYPE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );
        floatWindowLayoutParam.gravity = Gravity.CENTER;
        floatWindowLayoutParam.x = 0;
        floatWindowLayoutParam.y = 0;
        windowManager.addView(floatView, floatWindowLayoutParam);
        maximizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSelf();
                windowManager.removeView(floatView);
                Intent backToHome = new Intent(FloatingWindow.this, MainActivity.class);
                backToHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(backToHome);
            }
        });
        descEditArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not Necessary
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Common.currentDesc = descEditArea.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not Necessary
            }
        });
        floatView.setOnTouchListener(new View.OnTouchListener() {
            final WindowManager.LayoutParams floatWindowLayoutUpdateParam = floatWindowLayoutParam;
            double x;
            double y;
            double px;
            double py;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x = floatWindowLayoutUpdateParam.x;
                        y = floatWindowLayoutUpdateParam.y;
                        px = event.getRawX();
                        py = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        floatWindowLayoutUpdateParam.x = (int) ((x + event.getRawX()) - px);
                        floatWindowLayoutUpdateParam.y = (int) ((y + event.getRawY()) - py);
                        windowManager.updateViewLayout(floatView, floatWindowLayoutUpdateParam);
                        break;
                }
                return false;
            }
        });
        descEditArea.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                descEditArea.setCursorVisible(true);
                WindowManager.LayoutParams floatWindowLayoutParamUpdateFlag = floatWindowLayoutParam;
                floatWindowLayoutParamUpdateFlag.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
                windowManager.updateViewLayout(floatView, floatWindowLayoutParamUpdateFlag);
                return false;
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.savedDesc = descEditArea.getText().toString();
                descEditArea.setCursorVisible(false);
                WindowManager.LayoutParams floatWindowLayoutParamUpdateFlag = floatWindowLayoutParam;
                floatWindowLayoutParamUpdateFlag.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                windowManager.updateViewLayout(floatView, floatWindowLayoutParamUpdateFlag);
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(floatView.getApplicationWindowToken(), 0);
                Toast.makeText(FloatingWindow.this, "Text Saved!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
        windowManager.removeView(floatView);
    }
}