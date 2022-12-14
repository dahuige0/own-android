package com.android.ativitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SecondActivity",this.toString());
        Log.d("SecondActivity","Task id is "+getTaskId());
        setContentView(R.layout.second_layout);
        // 接收数据
//        Intent intent= getIntent(); // 获取用于启动secondActivity 的Intent
//        String data = intent.getStringExtra("extra_data");
//        Log.d("SecondActivity",data);
        // 返回数据
        Button button2 = (Button) findViewById(R.id.button_2);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.putExtra("data_return","Hello FirstActivity");
//                setResult(RESULT_OK,intent);
//                finish();
//            }
//        });

//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
    }

    // 按返回键返回数据
//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent();
//        intent.putExtra("data_return","Hello FirstActivity");
//        setResult(RESULT_OK,intent);
//        finish();
//    }
    public static void actionStart(Context context, String data1, String data2){
        Intent intent = new Intent(context,SecondActivity.class);
        intent.putExtra("param1",data1);
        intent.putExtra("param2",data2);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity","onDestroy");
    }

}