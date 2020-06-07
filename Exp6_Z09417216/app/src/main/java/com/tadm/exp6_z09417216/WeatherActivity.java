/*
 * https://liuhongwei3.github.io Inc.
 * Name: WeatherActivity.java
 * Date: 20-6-3 下午2:28
 * Author: Tadm
 * Copyright (c) 2020 All Rights Reserved.
 */

package com.tadm.exp6_z09417216;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {
    private TextView tvCity;
    private TextView tvTemperature;
    private TextView tvWind;
    private TextView tvHum;
    private TextView tvUpdateTime;
    private ListView listWeather;
    private String city = "巴中";
    private String unit = "℃";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initViews();
        new Thread() {
            @Override
            public void run() {
                super.run();
//                getWeatherData();
                okHttpConn();
            }
        }.start();
//        okHttpConn();
    }

    private void okHttpConn() {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url("https://api.jisuapi.com/weather/query?appkey=d546fee6a85fd842&city=" + city);
        Request request = builder.build();
        Call call = client.newCall(request);
//        try {
//            Response response = call.execute(); //  sync
//            if (response.isSuccessful()) {
//                String s = response.body().string();
//                Log.i("OkHttp sync: ", s);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Looper.prepare();
                Toast.makeText(WeatherActivity.this, "网络连接不成功！请检查！", Toast.LENGTH_LONG).show();
                Looper.loop();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Log.i("OkHttp async: ", result);
//                    parseJson(s);
                    //  Gson
                    Gson gson = new Gson();
                    WeatherBean weather = gson.fromJson(result, WeatherBean.class);
                    Message msg = handler.obtainMessage();
                    msg.what = 2;
                    msg.obj = weather;
                    handler.sendMessage(msg);
                }
            }
        });
    }

    private void parseJson(String s) {
        JSONObject json = null;
        try {
            json = new JSONObject(s);
            int status = json.getInt("status");
            String msg = json.getString("msg");
            if (status == 0) {
                String result = json.getString("result");
                JSONObject data = new JSONObject(result);
                String daily = data.getString("daily");
                JSONArray jsonArray = new JSONArray(daily);
                String Thursday = jsonArray.get(1).toString();
                JSONObject jsonObject = new JSONObject(Thursday);
                String day = jsonObject.getString("day");
                Log.i("day: ", day);
            } else if (status == 101) {
                Looper.prepare();
                Toast.makeText(WeatherActivity.this, msg, Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String string = (String) msg.obj;
                tvCity.setText(string);
            } else if (msg.what == 2) {
                WeatherBean weather = (WeatherBean) msg.obj;
                tvCity.setText(weather.getResult().getCity());
                tvTemperature.setText(weather.getResult().getTemp() + unit);
                tvWind.setText(weather.getResult().getWinddirect() + weather.getResult().getWindpower());
                tvHum.setText(weather.getResult().getHumidity() + "%");
                tvUpdateTime.setText(weather.getResult().getUpdatetime());
                setListView(weather);
            }
        }
    };

    private void setListView(WeatherBean weather) {
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < weather.getResult().getDaily().size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("date", weather.getResult().getDaily().get(i).getDate());
            map.put("week", weather.getResult().getDaily().get(i).getWeek());
            map.put("temp_high", weather.getResult().getDaily().get(i).getDay().getTemphigh() + unit);
            map.put("temp_low", weather.getResult().getDaily().get(i).getNight().getTemplow() + unit);
            String high_pic = weather.getResult().getDaily().get(i).getDay().getImg() + ".png";
            String low_pic = weather.getResult().getDaily().get(i).getNight().getImg() + ".png";
            map.put("temp_high_pic", high_pic);
            map.put("temp_low_pic", low_pic);
            data.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(WeatherActivity.this, data, R.layout.weather_list_item_layout,
                new String[]{"date", "week", "temp_high", "temp_low", "temp_high_pic", "temp_low_pic"},
                new int[]{R.id.tv_date, R.id.tv_week, R.id.tv_high, R.id.tv_low, R.id.img_high, R.id.img_low});
        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if (view instanceof ImageView) {
                    try {
                        InputStream in = getResources().getAssets().open(data.toString());
                        Bitmap bitmap = BitmapFactory.decodeStream(in);
                        ((ImageView) view).setImageBitmap(bitmap);
                        return true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
        listWeather.setAdapter(adapter);
    }

    private void getWeatherData() {
//        String path = "https://api.jisuapi.com/weather/query?appkey=d546fee6a85fd842&city=" + city;
        String path = "https://api.jisuapi.com/weather/query";
        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setRequestMethod("GET");
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
                StringBuilder stringBuilder = new StringBuilder();
                while ((result = bufferedReader.readLine()) != null) {
                    stringBuilder.append(result);
                }
                Message message = handler.obtainMessage();
                message.what = 1;
                message.obj = stringBuilder.toString();
                handler.sendMessage(message);
            } else {
                Toast.makeText(WeatherActivity.this, "网络连接不成功！请检查！", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        tvCity = (TextView) findViewById(R.id.tv_city);
        tvTemperature = (TextView) findViewById(R.id.tv_temperature);
        tvWind = (TextView) findViewById(R.id.tv_wind);
        tvHum = (TextView) findViewById(R.id.tv_hum);
        tvUpdateTime = (TextView) findViewById(R.id.tv_update_time);
        listWeather = (ListView) findViewById(R.id.list_weather);
    }
}
