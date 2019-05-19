package nikita.com.weatherapp.utils

import android.text.format.Time
import java.util.*

fun getTimeByLocalTimezone(time: Long) : Long {
    val timeFormat = Time()
    timeFormat.set(time + TimeZone.getDefault().getOffset(time))
    return timeFormat.toMillis(true)
}