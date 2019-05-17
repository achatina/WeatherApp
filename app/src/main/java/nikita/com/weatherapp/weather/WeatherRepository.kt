package nikita.com.weatherapp.weather

import nikita.com.weatherapp.api.NetworkResponse
import nikita.com.weatherapp.models.ForecastWeatherResponse
import nikita.com.weatherapp.models.WeatherResponse

interface WeatherRepository {

    fun getTodayWeather(): NetworkResponse<WeatherResponse>

    fun getForecastWeather(): NetworkResponse<ForecastWeatherResponse>

}