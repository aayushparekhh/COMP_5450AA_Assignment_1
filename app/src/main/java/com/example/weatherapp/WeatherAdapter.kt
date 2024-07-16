package com.example.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class WeatherAdapter(private val context: Context, private val weatherList: List<Map<String, String>>) : BaseAdapter() {

    override fun getCount(): Int = weatherList.size

    override fun getItem(position: Int): Any = weatherList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.weather_list_item, parent, false)

        val weather = weatherList[position]

        val dateTextView: TextView = view.findViewById(R.id.date)
        val weatherIconImageView: ImageView = view.findViewById(R.id.weather_icon)
        val minTempTextView: TextView = view.findViewById(R.id.min_temp)
        val maxTempTextView: TextView = view.findViewById(R.id.max_temp)

        dateTextView.text = weather["date"]
        // Assuming you have different weather icons for different conditions
        val iconResId = when (weather["weatherCondition"]) {
            "Sunny" -> R.drawable.ic_sunny
            "Cloudy" -> R.drawable.ic_cloudy
            "Rainy" -> R.drawable.ic_rainy
            "Stormy" -> R.drawable.ic_stormy
            "Snowy" -> R.drawable.ic_snowy
            else -> R.drawable.ic_sunny // default icon
        }
        weatherIconImageView.setImageResource(iconResId)
        minTempTextView.text = weather["minTemp"]
        maxTempTextView.text = weather["maxTemp"]

        return view
    }
}
