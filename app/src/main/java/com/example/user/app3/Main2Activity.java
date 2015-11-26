package com.example.user.app3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;


public class Main2Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent starter = getIntent();
        String info = starter.getStringExtra("info");

        TextView cityView = (TextView) Main2Activity.this.findViewById(R.id.textCity);
        TextView dateView = (TextView) Main2Activity.this.findViewById(R.id.textDate);
        TextView tempView = (TextView) Main2Activity.this.findViewById(R.id.textTemp);

        try {
            JSONObject resp = new JSONObject(info);
            JSONObject city = resp.getJSONObject("city");
            JSONArray weatherList = resp.getJSONArray("list");
            JSONObject today = weatherList.getJSONObject(0);
            JSONObject mainInfo = today.getJSONObject("main");
            double temp = mainInfo.getDouble("temp");
            String date = today.getString("dt_txt");
            String cityName = city.getString("name");

            cityView.setText(cityName);
            dateView.setText(date);
            tempView.setText("temperature: " + temp);
        } catch (Exception e) { /* Emptiness */ }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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
}
