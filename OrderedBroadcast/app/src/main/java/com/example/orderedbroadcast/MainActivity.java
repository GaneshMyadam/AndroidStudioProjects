package com.example.orderedbroadcast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG=MainActivity.class.getSimpleName();

    private static final String BREAD_CRUMB = "Breadcrumb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.buttonStartBroadCast);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setAction("com.br");
        sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle bundle=getResultExtras(true);
                String breadcrumb=bundle.getString(BREAD_CRUMB);
                breadcrumb=breadcrumb+"->"+TAG;
                Log.i(TAG,"On Receive: "+breadcrumb);
            }
        }, null, MainActivity.RESULT_OK,null,null);


    }
}