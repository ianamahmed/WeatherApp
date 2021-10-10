package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView  button = findViewById(R.id.nextbutton);
        EditText   text = findViewById(R.id.enterCityName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  city = String.valueOf(text.getText());
                Intent nextpg = new Intent(MainActivity.this,ShowWeather.class);
                nextpg.putExtra("CITY_NAME", city);
                startActivity(nextpg);
            }
        });

    }
}