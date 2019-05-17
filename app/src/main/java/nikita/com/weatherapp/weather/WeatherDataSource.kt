package nikita.com.weatherapp.weather

import nikita.com.weatherapp.api.NetworkResponse
import nikita.com.weatherapp.api.WeatherApi
import nikita.com.weatherapp.models.ForecastWeatherResponse
import nikita.com.weatherapp.models.WeatherResponse
import retrofit2.Retrofit

class WeatherDataSource(retrofit: Retrofit) : WeatherRepository {

    private val api = retrofit.create(WeatherApi::class.java)

    override fun getTodayWeather(): NetworkResponse<WeatherResponse> {
        TODO("Not implemented")
    }

    override fun getForecastWeather(): NetworkResponse<ForecastWeatherResponse> {
        TODO("Not implemented")
    }
}