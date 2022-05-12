package com.example.ndkexample;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static {
        System.loadLibrary("ndkexample");
    }
    private TextView textView;
    private EditText editText;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }
    public native int getTextLen(String txt);
    private void enterText(){
        String txt= editText.getText().toString();
        int txtLen = getTextLen(txt);
        textView.setText("Text Length is:"+txtLen);
    }
    public void onClick(View view){
        if(view==button){
            enterText();
        }
    }
}