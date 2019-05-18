package nikita.com.weatherapp.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord")
    val coordinates: Coordinates,
    val weather: List<Weather>,
    val main: WeatherMain,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    @SerializedName("dt")
    val date: Long,
    val sys: Sys,
    val id: Long,
    val name: String,
    @SerializedName("cod")
    val code: Int
)

data class Coordinates(
    val lat: Double,
    @SerializedName("lon")
    val lng: Double
)

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)

data class WeatherMain(
    val temp: Float,
    val pressure: Float,
    val humidity: Int,
    val temp_min: Float,
    val temp_max: Float
)

data class Wind(
    val speed: Float,
    @SerializedName("deg")
    val degree: Float
)

data class Clouds(
    val all: Int
)

data class Sys(
    val type: Int,
    val id: Long,
    val message: Float,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)