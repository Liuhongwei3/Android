package com.tadm.activityclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Joke extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        setTitle(R.string.joke);
        Button button = findViewById(R.id.btnBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Joke.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
