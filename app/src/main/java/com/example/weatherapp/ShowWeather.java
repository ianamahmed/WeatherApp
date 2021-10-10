package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ShowWeather extends AppCompatActivity {

    TextView tvTemp, tvCityName, tvHumidity,tvWindSpeed,tvWindDirection,tvSunset,tvSunrise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_weather);

        tvTemp = findViewById(R.id.Temperature);
        tvCityName = findViewById(R.id.cityName);
        tvHumidity = findViewById(R.id.humidity);
        tvWindSpeed = findViewById(R.id.windSpeed);
        tvWindDirection = findViewById(R.id.windDirection);
        tvWindSpeed = findViewById(R.id.windSpeed);
        tvSunset = findViewById(R.id.sunset);
        tvSunrise = findViewById(R.id.sunrise);

        String city = getIntent().getExtras().getString("CITY_NAME");

        makeCall(city);

    }

    // function to get url
    String getUrl(String city){
        String url = "https://api.weatherbit.io/v2.0/current?"
                   + "city=" + city
                   + "&key=" + getResources().getString(R.string.key);
        return url;
        /// https://api.weatherbit.io/v2.0/current?city=lucknow&key=9665146544vjchfxhchjv
    }
    //function to make a call to server using api
    void makeCall(String city){
        String url = getUrl(city);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // take temperature from response
                            int temperature = response.getJSONArray("data").getJSONObject(0).getInt("temp");
                            String cityName = response.getJSONArray("data").getJSONObject(0).getString("city_name");
                            int humidity = response.getJSONArray("data").getJSONObject(0).getInt("rh");
                            double windSpeed = response.getJSONArray("data").getJSONObject(0).getDouble("wind_spd");
                            String windDirection = response.getJSONArray("data").getJSONObject(0).getString("wind_cdir_full");
                            String sunset = response.getJSONArray("data").getJSONObject(0).getString("sunset");
                            String sunrise = response.getJSONArray("data").getJSONObject(0).getString("sunrise");

                            // now display temperature on screen
                            tvTemp.setText(temperature+" °C ");
                            tvCityName.setText(cityName);
                            tvHumidity.setText(humidity+"%");
                            tvWindSpeed.setText(windSpeed+" m/s ");
                            tvWindDirection.setText(windDirection);
                            tvSunset.setText(sunset+" HH:MM");
                            tvSunrise.setText(sunrise+" HH:MM");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }

}