package nikita.com.weatherapp.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_weather_days.view.*
import nikita.com.weatherapp.R
import nikita.com.weatherapp.utils.calcDayIcon
import java.text.SimpleDateFormat
import java.util.*

class DayWeatherAdapter(
    private val onDayPicked:(DayModel) -> Unit
) : RecyclerView.Adapter<DayWeatherViewHolder>() {

    private val weatherItems = mutableListOf<DayModel>()
    private var selected: DayModel? = null

    fun setDayItems(weatherItems: List<DayModel>) {
        this.weatherItems.clear()
        this.weatherItems.addAll(weatherItems)
    }

    fun selectDay(targetDay: DayModel) {
        selected = targetDay
        onDayPicked(targetDay)
    }

    override fun onCreateViewHolder(vg: ViewGroup, type: Int): DayWeatherViewHolder {
        return DayWeatherViewHolder(LayoutInflater.from(vg.context).inflate(R.layout.item_weather_days, vg, false))
    }

    override fun getItemCount(): Int = weatherItems.size

    override fun onBindViewHolder(h: DayWeatherViewHolder, p: Int) {
        h.bind(weatherItems[p]) {
            onDayPicked(it)
            selected = it
        }
    }
}

class DayWeatherViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    fun bind(dayModel: DayModel, onDayPicked: (DayModel) -> Unit) {

        itemView.setOnClickListener {
            onDayPicked(dayModel)
        }

        itemView.item_weather_day_ic.setImageResource(calcDayIcon(dayModel.weatherCode))
        itemView.item_weather_day_title.text = SimpleDateFormat("EEE", Locale.getDefault()).format(Date(dayModel.time)).toUpperCase()

        itemView.item_weather_day_temp.text =
            String.format(itemView.context.getString(R.string.weather_temp), dayModel.minTemp, dayModel.maxTemp)
    }
}

data class DayModel(
    val time: Long, //time in mills, not unix
    val minTemp: Int,
    val maxTemp: Int,
    val weatherCode: Int
)