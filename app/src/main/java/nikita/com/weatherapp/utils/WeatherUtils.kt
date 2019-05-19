package nikita.com.weatherapp.utils

import android.support.annotation.DrawableRes
import nikita.com.weatherapp.R

@DrawableRes
fun calcWeatherIcon(code: Int, sunrise: Long, sunset: Long): Int {
    return if (System.currentTimeMillis() in sunrise..sunset) calcDayIcon(code) else calcNightIcon(code)
}

@DrawableRes
fun calcDayIcon(code: Int): Int {
    return when {
        code == 800 -> R.drawable.ic_white_day_bright
        code > 800 || code in 700..799 -> R.drawable.ic_white_day_cloudy
        code in 600..699 -> R.drawable.ic_white_day_shower //todo snow icon
        code in 520..599 -> R.drawable.ic_white_day_shower
        code in 500..519 -> R.drawable.ic_white_day_rain
        code in 300..399 -> R.drawable.ic_white_day_shower
        code in 200..299 ->R.drawable.ic_white_day_thunder
        else -> R.drawable.ic_white_day_cloudy
    }
}

@DrawableRes
fun calcNightIcon(code: Int): Int {
    return when {
        code == 800 -> R.drawable.ic_white_night_bright
        code > 800 || code in 700..799 -> R.drawable.ic_white_night_cloudy
        code in 600..699 -> R.drawable.ic_white_night_shower //todo snow icon
        code in 520..599 -> R.drawable.ic_white_night_shower
        code in 500..519 -> R.drawable.ic_white_night_rain
        code in 300..399 -> R.drawable.ic_white_night_shower
        code in 200..299 ->R.drawable.ic_white_night_thunder
        else -> R.drawable.ic_white_night_cloudy
    }
}