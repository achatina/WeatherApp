package nikita.com.weatherapp.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nikita.com.weatherapp.R
import nikita.com.weatherapp.models.ForecastWeather

class HourlyWeatherAdapter : RecyclerView.Adapter<HourlyWeatherViewHolder>() {

    private val weatherItems = mutableListOf<ForecastWeather>()

    fun addWeatherItems(weatherItems: List<ForecastWeather>) {
        this.weatherItems.addAll(weatherItems)
    }

    fun replaceWeatherItems(weatherItems: List<ForecastWeather>) {
        this.weatherItems.clear()
        this.weatherItems.addAll(weatherItems)
    }

    override fun onCreateViewHolder(vg: ViewGroup, type: Int): HourlyWeatherViewHolder {
        return HourlyWeatherViewHolder(LayoutInflater.from(vg.context).inflate(R.layout.item_weather_hourly, vg, false))
    }

    override fun getItemCount(): Int = weatherItems.size

    override fun onBindViewHolder(h: HourlyWeatherViewHolder, p: Int) {
        h.bind(weatherItems[p])
    }
}

class HourlyWeatherViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    fun bind(weather: ForecastWeather) {
        //todo implement
    }

}