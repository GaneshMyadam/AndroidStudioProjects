package com.example.multiactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //calling the button in activity1
        //creating  a button variable
        Button button = findViewById(R.id.button);
        Button btn;
        btn = findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
            startActivity(i);
            }
        });
        //action performed after clicking button
        button.setOnClickListener(new View.OnClickListener(){
            //after clicking call openActivity2 method
            public void onClick(View v){
                openActivity2();
            }
        });
    }//openActivity2 Method
    //creating an Intent to to call Activity 2 by clicking button in activity1
            public void openActivity2(){
                    Intent intent = new Intent(this, Activity2.class);
                    startActivity(intent);
            }
}
