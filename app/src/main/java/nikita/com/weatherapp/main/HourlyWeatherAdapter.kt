package nikita.com.weatherapp.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_weather_hourly.view.*
import nikita.com.weatherapp.R
import nikita.com.weatherapp.models.ForecastWeather
import nikita.com.weatherapp.utils.calcDayIcon
import java.util.*
import java.util.Calendar.HOUR_OF_DAY
import java.util.Calendar.MINUTE
import kotlin.math.roundToInt

class HourlyWeatherAdapter : RecyclerView.Adapter<HourlyWeatherViewHolder>() {

    private val weatherItems = mutableListOf<ForecastWeather>()

    fun setWeatherItems(weatherItems: List<ForecastWeather>) {
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
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = weather.date * 1000
        itemView.item_weather_hourly_icon.setImageResource(
            if (weather.weather.isNotEmpty()) {
                (calcDayIcon(weather.weather[0].id)) //unfortunately, getting sunrise and sunset from forecast is paid only feature.
            } else {
                R.drawable.ic_white_day_cloudy
            }
        )

        itemView.context.apply {
            itemView.item_weather_hourly_time_hours.text =
                String.format(getString(R.string.time_with_leading_zero), calendar.get(HOUR_OF_DAY))
            itemView.item_weather_hourly_time_minutes.text =
                String.format(getString(R.string.time_with_leading_zero), calendar.get(MINUTE))
            itemView.item_weather_hourly_temp.text =
                String.format(getString(R.string.weather_hourly_temp), weather.main.temp.roundToInt())
        }
    }

}