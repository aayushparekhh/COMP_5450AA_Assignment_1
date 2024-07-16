package com.example.weatherapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : ComponentActivity() {
    private lateinit var btnAddCity: ImageButton
    private lateinit var listViewWeathers: ListView
    private lateinit var weatherAdapter: WeatherAdapter
    private val weatherList: MutableList<Map<String, String>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddCity = findViewById(R.id.btnAddCity)
        listViewWeathers = findViewById(R.id.listViewWeathers)

        weatherAdapter = WeatherAdapter(this, weatherList)
        listViewWeathers.adapter = weatherAdapter

        btnAddCity.setOnClickListener {
            val intent = Intent(this, AddWeatherActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_WEATHER)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_WEATHER && resultCode == Activity.RESULT_OK) {
            val date = data?.getStringExtra("date")
            val weatherCondition = data?.getStringExtra("weatherCondition")
            val minTemp = data?.getStringExtra("minTemp")
            val maxTemp = data?.getStringExtra("maxTemp")

            if (date != null && weatherCondition != null && minTemp != null && maxTemp != null) {
                val weatherData = mapOf(
                    "date" to date,
                    "weatherCondition" to weatherCondition,
                    "minTemp" to minTemp,
                    "maxTemp" to maxTemp
                )
                weatherList.add(weatherData)
                weatherAdapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_ADD_WEATHER = 1
    }
}
