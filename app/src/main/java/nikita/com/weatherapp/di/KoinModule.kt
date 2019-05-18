package nikita.com.weatherapp.di

import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import nikita.com.weatherapp.BuildConfig
import nikita.com.weatherapp.R
import nikita.com.weatherapp.api.retrofitClient
import nikita.com.weatherapp.geo.GeoDataSource
import nikita.com.weatherapp.geo.GeoRepository
import nikita.com.weatherapp.main.MainPresenter
import nikita.com.weatherapp.weather.WeatherDataSource
import nikita.com.weatherapp.weather.WeatherRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.*

private const val BASE_URL = "baseUrl"
private const val API_VERSION = "apiVersion"
private const val OPEN_WEATHER_TOKEN = "openWeatherToken"

val weatherModule = module {
    single<WeatherRepository> { WeatherDataSource(get()) }
    single { MainPresenter(get(), get(), get()) }
}

val geoModule = module {
    single { Geocoder(androidContext(), Locale.getDefault()) }
    single<GeoRepository> { GeoDataSource(get()) }
    single { LocationServices.getFusedLocationProviderClient(androidContext()) }
}

val networkModule = module {
    single { retrofitClient(get(), get(named(BASE_URL))) }
    single(named(BASE_URL)) { BuildConfig.BASE_URL }
    single(named(API_VERSION)) { BuildConfig.API_VERSION }
    single(named(OPEN_WEATHER_TOKEN)) { androidContext().getString(R.string.openweather_token) }
    single { Gson() }
}