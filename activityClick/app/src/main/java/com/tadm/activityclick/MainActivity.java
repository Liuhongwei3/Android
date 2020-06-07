package com.tadm.activityclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.xiuxian);

        //  Joke
        TextView textView1 = findViewById(R.id.sec_tv3);
        Listener listener =  new Listener();
        textView1.setOnClickListener(listener);

        //  Pu
        TextView textView2 = findViewById(R.id.sec_tv4);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Pu.class);
                startActivity(intent);
            }
        });
    }

    //  Rao
    public void doRao(View view) {
        Intent intent = new Intent(MainActivity.this,Rao.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //  browser
            case R.id.sec_tv5: {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://liuhongwei3.github.io"));
                startActivity(intent);
                break;
            }
            //  Call
            case R.id.sec_tv6: {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
                break;
            }
        }
    }

    class Listener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,Joke.class);
            startActivity(intent);
        }
    }
}
