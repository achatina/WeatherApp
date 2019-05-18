package nikita.com.weatherapp.main

import nikita.com.weatherapp.BaseView
import nikita.com.weatherapp.models.ForecastWeatherResponse
import nikita.com.weatherapp.models.WeatherResponse

interface MainView : BaseView {

    fun handleLocationPermissions()

    fun showWeather(
        today: WeatherResponse,
        hourly: ForecastWeatherResponse
    )

}