package nikita.com.weatherapp.weather

import nikita.com.weatherapp.api.NetworkResponse
import nikita.com.weatherapp.api.WeatherApi
import nikita.com.weatherapp.api.processCall
import nikita.com.weatherapp.geo.GeoLocation
import nikita.com.weatherapp.models.ForecastWeatherResponse
import nikita.com.weatherapp.models.WeatherResponse
import retrofit2.Retrofit
import java.util.*

class WeatherDataSource(
    private val apiVersion: String,
    private val units: String,
    private val openWeatherToken: String,
    retrofit: Retrofit
) : WeatherRepository {

    private val api = retrofit.create(WeatherApi::class.java)

    override fun getTodayWeather(geoLocation: GeoLocation): NetworkResponse<WeatherResponse> {
        return processCall(
            api.todayWeather(
                apiVersion,
                geoLocation.lat,
                geoLocation.lng,
                Locale.getDefault().language,
                units,
                openWeatherToken
            )
        )
    }

    override fun getForecastWeather(geoLocation: GeoLocation): NetworkResponse<ForecastWeatherResponse> {
        return processCall(
            api.hourlyWeather(
                apiVersion,
                geoLocation.lat,
                geoLocation.lng,
                Locale.getDefault().language,
                units,
                openWeatherToken
            )
        )
    }
}