package nikita.com.weatherapp.weather

import nikita.com.weatherapp.api.NetworkResponse
import nikita.com.weatherapp.geo.GeoLocation
import nikita.com.weatherapp.models.ForecastWeatherResponse
import nikita.com.weatherapp.models.WeatherResponse

interface WeatherRepository {

    fun getTodayWeather(geoLocation: GeoLocation): NetworkResponse<WeatherResponse>

    fun getForecastWeather(geoLocation: GeoLocation): NetworkResponse<ForecastWeatherResponse>

}