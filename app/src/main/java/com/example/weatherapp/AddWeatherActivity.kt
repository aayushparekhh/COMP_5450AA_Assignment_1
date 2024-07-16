package com.example.weatherapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity


class AddWeatherActivity : ComponentActivity() {

    private lateinit var dateEditText: EditText
    private lateinit var weatherConditionSpinner: Spinner
    private lateinit var minTempEditText: EditText
    private lateinit var maxTempEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_weather)

        // Initialize views
        dateEditText = findViewById(R.id.editTextDate)
        weatherConditionSpinner = findViewById(R.id.spinnerWeatherCondition)
        minTempEditText = findViewById(R.id.editTextMinTemp)
        maxTempEditText = findViewById(R.id.editTextMaxTemp)
        saveButton = findViewById(R.id.buttonSave)

        // Setup Spinner with weather conditions
        ArrayAdapter.createFromResource(
            this,
            R.array.weather_conditions,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            weatherConditionSpinner.adapter = adapter
        }

        // Set up Save button click listener
        saveButton.setOnClickListener {
            saveWeatherInfo()
        }
    }

    private fun saveWeatherInfo() {
        // Get input values
        val date = dateEditText.text.toString().trim()
        val weatherCondition = weatherConditionSpinner.selectedItem.toString()
        val minTemp = minTempEditText.text.toString().trim()
        val maxTemp = maxTempEditText.text.toString().trim()

        // Validate inputs
        if (date.isEmpty() || minTemp.isEmpty() || maxTemp.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Pass the weather data back to the calling activity
        val resultIntent = Intent().apply {
            putExtra("date", date)
            putExtra("weatherCondition", weatherCondition)
            putExtra("minTemp", minTemp)
            putExtra("maxTemp", maxTemp)
        }
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}
