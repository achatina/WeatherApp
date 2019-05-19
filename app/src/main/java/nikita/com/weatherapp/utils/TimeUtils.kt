package nikita.com.weatherapp.utils

import android.text.format.Time
import java.util.*

fun getTimeByLocalTimezone(time: Long) : Long {
    val timeFormat = Time()
    timeFormat.set(time + TimeZone.getDefault().getOffset(time))
    return timeFormat.toMillis(true)
}

fun areTheSameDays(date1: Long, date2: Long): Boolean {
    val calendar1 = Calendar.getInstance()
    val calendar2 = Calendar.getInstance()


    calendar1.timeInMillis = date1
    calendar2.timeInMillis = date2

    return calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
}