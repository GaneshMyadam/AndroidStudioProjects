package com.example.myapplication;
//importing needful packages
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//implementing activity class
public class MainActivity extends AppCompatActivity {

    @Override
    //create layout
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //referring text fields in layout
        TextView username = (TextView) findViewById(R.id.Email);
        TextView password = (TextView) findViewById(R.id.Password);
        //referring button in layout
        Button login = (Button) findViewById(R.id.Login);
        //Action performed when clicking login button
        //lamda expression
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (username.getText().toString().equals("Ganesh") && password.getText().toString().equals("qwerty@10")){
                //show message if given credentials are valid or correct
                    Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
            }else
                //show message if credentials not valid or incorrect
                Toast.makeText(MainActivity.this, "LOGIN FAILED!!!", Toast.LENGTH_SHORT).show();

               }
        });
    }
}