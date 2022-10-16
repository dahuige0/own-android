package com.android.networktest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.send_request){
            sendRequestWithHttpUrlConneciton();
        }
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(response);
            }
        });
    }

    private void parseJSONWithJSONObject(String jsonData){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d("MainActivity","id is " + id);
                Log.d("MainActivity","name is " + name);
                Log.d("MainActivity","version is " + version);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendRequestWithHttpUrlConneciton() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtil.sendOkHttpRequest("http://www.baidu.com",new okhttp3.Callback(){

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String responseData = response.body().string();
                        Log.d("MainActivity",""+responseData);
                    }

                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }
                });

//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder().url("http://192.168.31.218:8000/get_data.json").build();
//                    Response response = client.newCall(request).execute();
//                    String responseData = null;
//                    responseData = response.body().string();
//                    parseJSONWithGSON(responseData);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder().url("http://192.168.31.218:8000/get_data.json").build();
//                    Response response = client.newCall(request).execute();
//                    String responseData = null;
//                    responseData = response.body().string();
//                    parseJSONWithJSONObject(responseData);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//                try {
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder().url("https://www.baidu.com").build();
//                    Response response = client.newCall(request).execute();
//                    String responseData = null;
//                    responseData = response.body().string();
//                    showResponse(responseData);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                // HttpURLConnection
//                HttpURLConnection httpURLConnection =null;
//                BufferedReader reader= null;
//                try {
//                    URL url = new URL("https://www.baidu.com");
//                    httpURLConnection = (HttpURLConnection) url.openConnection();
//                    httpURLConnection.setRequestMethod("GET");
//                    httpURLConnection.setConnectTimeout(8000);
//                    httpURLConnection.setReadTimeout(8000);
//                    InputStream in = httpURLConnection.getInputStream();
//                    reader = new BufferedReader(new InputStreamReader(in));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    while ((line = reader.readLine()) !=null){
//                        response.append(line);
//                    }
//                    showResponse(response.toString());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }finally {
//                    if (reader != null){
//                        try {
//                            reader.close();
//                        }catch (IOException e){
//                            e.printStackTrace();
//                        }
//                    }
//                    if (httpURLConnection != null){
//                        httpURLConnection.disconnect();
//                    }
//                }

            }
        }).start();
    }

    private void parseJSONWithGSON(String responseData) {
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(responseData,new TypeToken<List<App>>(){}.getType());
        for (App app:appList){
            Log.d("MainActivity","id is " + app.getId());
            Log.d("MainActivity","name is " + app.getName());
            Log.d("MainActivity","version is " + app.getVersion());
        }
    }
}