package nikita.com.weatherapp.main

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import nikita.com.weatherapp.BasePresenter
import nikita.com.weatherapp.R
import nikita.com.weatherapp.api.SUCCESS
import nikita.com.weatherapp.geo.GeoLocation
import nikita.com.weatherapp.geo.GeoRepository
import nikita.com.weatherapp.utils.areTheSameDays
import nikita.com.weatherapp.weather.WeatherRepository
import kotlin.math.roundToInt

class MainPresenter(
    private val weatherRepository: WeatherRepository,
    private val geoRepository: GeoRepository,
    private val fusedLocationClient: FusedLocationProviderClient
) : BasePresenter<MainView> {

    override var view: MainView? = null

    private var getWeatherJob: Job? = null

    private fun saveLocation(location: Location?) {
        if (location != null) {
            geoRepository.apply {
                val geoLocation = location.defineLocation()
                if (geoLocation != null) {
                    loadWeather(geoLocation)
                } else {
                    showCantGetLocationDialog()
                }
            }
        } else {
            showCantGetLocationDialog()
        }
    }

    private fun showCantGetLocationDialog() {
        view?.showAlert(
            R.string.dialog_title_error,
            R.string.dialog_location_error,
            R.string.dialog_retry,
            R.string.dialog_close,
            false,
            {
                getLocationAfterPermissionsWasGranted()
            },
            {
                view?.close()
            }
        )
    }

    fun findLocation() {
        view?.handleLocationPermissions()
    }

    fun locationPermissionNotGranted() {
        view?.showAlert(
            R.string.dialog_title_error,
            R.string.dialog_permission_error,
            R.string.dialog_ok,
            R.string.dialog_close,
            false,
            {
                findLocation()
            },
            {
                view?.close()
            }
        )
    }

    @SuppressLint("MissingPermission")
    fun getLocationAfterPermissionsWasGranted() {
        view?.showProgress()
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            saveLocation(location)
        }
    }

    private fun loadWeather(geoLocation: GeoLocation) {
        getWeatherJob = GlobalScope.launch(Main) {

            view?.showProgress()

            val todayResult = withContext(IO) {
                weatherRepository.getTodayWeather(geoLocation)
            }

            val hourlyResult = withContext(IO) {
                weatherRepository.getForecastWeather(geoLocation)
            }

            if (todayResult.code == SUCCESS && hourlyResult.code == SUCCESS
                && todayResult.data != null && hourlyResult.data != null
            ) {

                val days = mutableListOf<DayModel>()

                hourlyResult.data.list.forEach { forecastWeather ->
                    if (!days.any { areTheSameDays(it.time, forecastWeather.date * 1000) })
                        days.add(
                            DayModel(
                                forecastWeather.date * 1000,
                                forecastWeather.main.temp_min.roundToInt(),
                                forecastWeather.main.temp_max.roundToInt(),
                                if (forecastWeather.weather.isNotEmpty()) forecastWeather.weather[0].id else 0
                            )
                        )
                }

                view?.showWeather(todayResult.data, hourlyResult.data, geoLocation, days)
            } else {
                view?.showAlert(
                    R.string.dialog_title_error,
                    R.string.dialog_weather_error,
                    R.string.dialog_retry,
                    R.string.dialog_close,
                    false,
                    {
                        loadWeather(geoLocation)
                    },
                    {
                        view?.close()
                    }
                )
            }

            view?.hideProgress()
        }
    }

    override fun detachView() {
        super.detachView()
        if (getWeatherJob?.isActive == true)
            getWeatherJob?.cancel()
    }
}