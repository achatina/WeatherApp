package nikita.com.weatherapp.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nikita.com.weatherapp.R
import nikita.com.weatherapp.models.ForecastWeather

class DayWeatherAdapter : RecyclerView.Adapter<DayWeatherViewHolder>() {

    private val weatherItems = mutableListOf<ForecastWeather>()

    fun addWeatherItems(weatherItems: List<ForecastWeather>) {
        this.weatherItems.addAll(weatherItems)
    }

    fun replaceWeatherItems(weatherItems: List<ForecastWeather>) {
        this.weatherItems.clear()
        this.weatherItems.addAll(weatherItems)
    }

    override fun onCreateViewHolder(vg: ViewGroup, type: Int): DayWeatherViewHolder {
        return DayWeatherViewHolder(LayoutInflater.from(vg.context).inflate(R.layout.item_weather_days, vg, false))
    }

    override fun getItemCount(): Int = weatherItems.size

    override fun onBindViewHolder(h: DayWeatherViewHolder, p: Int) {
        h.bind(weatherItems[p])
    }
}

class DayWeatherViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    fun bind(weather: ForecastWeather) {
        //todo implement
    }

}