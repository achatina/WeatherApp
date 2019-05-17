package nikita.com.weatherapp.models

import com.google.gson.annotations.SerializedName

data class ForecastWeatherResponse(
    @SerializedName("cod")
    val code: Int,
    val cnt: Int,
    val list: List<ForecastWeather>
)

data class ForecastWeather(
    @SerializedName("dt")
    val date: Long,
    val main: WeatherMain,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    @SerializedName("dt_txt")
    val dateText: String
)