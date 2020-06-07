package com.tadm.network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tadm.framelayout.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class AsyncTaskActivity extends AppCompatActivity {
    private TextView tvInfo;
    private ImageView imgPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        tvInfo = (TextView) findViewById(R.id.tv_info);
        imgPic = (ImageView) findViewById(R.id.img_pic);

        String city = "巴中";
        String path = "https://api.jisuapi.com/weather/query";
        new MyTask().execute(path, city);
    }

    class MyTask extends AsyncTask {
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            String string = (String) o.toString();
//            tvInfo.setText(string);
            Glide.with(AsyncTaskActivity.this)
                    .load("https://i.loli.net/2019/11/24/Tj4hPoFKgXJE7xW.jpg")
                    .into(imgPic);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String path = (String) objects[0];
            String city = (String) objects[1];
            URL url = null;
            StringBuilder stringBuilder = null;
            try {
                url = new URL(path);
                HttpURLConnection httpURLConnection = null;
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                String params = "appkey=d546fee6a85fd842&city=" + URLEncoder.encode(city);
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.writeBytes(params);

                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.connect();
                int statusCode = httpURLConnection.getResponseCode();
                if (statusCode == 200) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    String result;
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    stringBuilder = new StringBuilder();
                    while ((result = bufferedReader.readLine()) != null) {
                        stringBuilder.append(result);
                    }
                    Log.i("Weather POST", stringBuilder.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder;
        }
    }
}
