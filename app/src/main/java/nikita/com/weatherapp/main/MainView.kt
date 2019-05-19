package nikita.com.weatherapp.main

import nikita.com.weatherapp.AlertView
import nikita.com.weatherapp.BaseView
import nikita.com.weatherapp.ProgressView
import nikita.com.weatherapp.geo.GeoLocation
import nikita.com.weatherapp.models.ForecastWeatherResponse
import nikita.com.weatherapp.models.WeatherResponse

interface MainView : BaseView, ProgressView, AlertView {

    fun handleLocationPermissions()

    fun showWeather(
        today: WeatherResponse,
        hourly: ForecastWeatherResponse,
        geoLocation: GeoLocation,
        dayModels: List<DayModel>
    )

}