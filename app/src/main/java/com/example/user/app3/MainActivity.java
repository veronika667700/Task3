package com.example.user.app3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getWeather();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getWeather() {

        new AsyncTask<Void, Void, API.ApiResponse>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected API.ApiResponse doInBackground(Void... x) {
                ArrayList<String> params = new ArrayList<String>();
                params.add("id");
                params.add("498677");
                params.add("APPID");
                params.add("0408201c1a78c86fd9433b7efbc51b4f");
                params.add("lang");
                params.add("ru");
                params.add("units");
                params.add("metric");

                return API.execute( API.ApiMethod.GET_WEATHER.format(),
                        API.HttpMethod.GET,
                        params.toArray(new String[params.size()])
                );
            }

            @Override
            protected void onPostExecute(API.ApiResponse apiResponse) {
                super.onPostExecute(apiResponse);

                try {
                    if (apiResponse.isSuccess()) {
                        android.util.Log.d("Weather",apiResponse.getJson().toString());

                    }

                } catch (Exception e) {
                    android.util.Log.e("Weather", "ALERT! ALERT! Exception!", e);
                } finally {

                }

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("info", apiResponse.getJson().toString());
                startActivity(intent);
            }
        }.execute();
    }
}
