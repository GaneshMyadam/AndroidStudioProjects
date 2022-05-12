package com.example.myclientapk;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //private static final String TAG= MainActivity.class.getSimpleName();
    private Context myContext;

    public static final int GET_RANDOM_NUMBER_FLAG =0;
    private boolean myBound;
    private int randomNumberValue;
    Messenger randomNumberRequestMessenger, randomNumberReceiveMessenger;
    private Intent serviceIntent;
    private TextView textViewRandomNumber;
    private Button buttonBindService,buttonUnBindService,buttonGetRandomNumber;

    class RecieveRandomNumberHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            randomNumberValue =0;
            switch (msg.what) {
                case GET_RANDOM_NUMBER_FLAG:
                    randomNumberValue =msg.arg1;
                    textViewRandomNumber.setText("Random Number: "+ randomNumberValue);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    }

    ServiceConnection randomNumberServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            randomNumberRequestMessenger=null;
            randomNumberReceiveMessenger=null;
            myBound = false;
        }
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder binder) {
            randomNumberRequestMessenger=new Messenger(binder);
            randomNumberReceiveMessenger=new Messenger(new RecieveRandomNumberHandler());
            myBound=true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myContext=getApplicationContext();
        textViewRandomNumber=findViewById(R.id.textViewRandomNumber);

        buttonBindService=findViewById(R.id.buttonBindService);
        buttonUnBindService=findViewById(R.id.buttonUnBindService);
        buttonGetRandomNumber=findViewById(R.id.buttonGetRandomNumber);
        buttonGetRandomNumber.setOnClickListener(this);
        buttonBindService.setOnClickListener(this);
        buttonUnBindService.setOnClickListener(this);
        serviceIntent=new Intent();
        serviceIntent.setComponent(new ComponentName("com.example.myserverapk","com.example.myserverapk.MyService"));
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonBindService: bindToRemoteService();
                break;
            case R.id.buttonUnBindService: unbindFromRemoteSevice();
                break;
            case R.id.buttonGetRandomNumber: fetchRandomNumber();
                break;
            default:
                break;
        }
    }
    private void bindToRemoteService(){
        bindService(serviceIntent, randomNumberServiceConnection, BIND_AUTO_CREATE);
        Toast.makeText(myContext,"Service bound",Toast.LENGTH_SHORT).show();
    }

    private void unbindFromRemoteSevice(){
        if(myBound){
            unbindService(randomNumberServiceConnection);
            myBound=false;
            Toast.makeText(myContext,"Service Unbound",Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchRandomNumber(){
        if (myBound == true) {
            Message requestMessage=Message.obtain(null, GET_RANDOM_NUMBER_FLAG);
            requestMessage.replyTo=randomNumberReceiveMessenger;
            try {
                randomNumberRequestMessenger.send(requestMessage);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(myContext,"Service Unbound, can't get random number",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        randomNumberServiceConnection=null;
    }
}