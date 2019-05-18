package nikita.com.weatherapp.api

import nikita.com.weatherapp.models.ForecastWeatherResponse
import nikita.com.weatherapp.models.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/{api_version}/weather")
    fun todayWeather(
        @Path("api_version") apiVersion: String,
        @Query("lat") lat: Double,
        @Query("lon") lng: Double,
        @Query("lang") lang: String,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Call<WeatherResponse>

    @GET("data/{api_version}/forecast")
    fun hourlyWeather(
        @Path("api_version") apiVersion: String,
        @Query("lat") lat: Double,
        @Query("lon") lng: Double,
        @Query("lang") lang: String,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Call<ForecastWeatherResponse>
}