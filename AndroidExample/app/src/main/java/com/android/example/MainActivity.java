package com.android.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    private  IntentFilter intentFilter;
//    private  NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        networkChangeReceiver = new NetworkChangeReceiver();
//        registerReceiver(networkChangeReceiver,intentFilter);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent("com.android.example.MY_BROADCAST");
                intent.setPackage(getPackageName());
//                sendBroadcast(intent);
                sendOrderedBroadcast(intent,null);
            }
        });
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(networkChangeReceiver);
//    }

//    class NetworkChangeReceiver extends BroadcastReceiver{
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//            if(networkInfo != null && networkInfo.isAvailable()){
//                Toast.makeText(context,"network is available",Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(context,"network is unavailable",Toast.LENGTH_SHORT).show();
//            }
//
//        }
//    }
}