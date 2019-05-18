package nikita.com.weatherapp.main

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import nikita.com.weatherapp.BasePresenter
import nikita.com.weatherapp.api.SUCCESS
import nikita.com.weatherapp.geo.GeoLocation
import nikita.com.weatherapp.geo.GeoRepository
import nikita.com.weatherapp.weather.WeatherRepository

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
                    //todo show error
                }
            }
        } else {
            //todo show error
        }
    }

    fun presenterReceiveLocation() {
        view?.handleLocationPermissions()
    }

    fun locationPermissionNotGranted() {
        //todo show info dialog
        getLocation()
    }

    @SuppressLint("MissingPermission")
    fun getLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            saveLocation(location)
        }
    }

    private fun loadWeather(geoLocation: GeoLocation) {
        getWeatherJob = GlobalScope.launch(Main) {
            //todo show progress

            val todayResult = withContext(IO) {
                weatherRepository.getTodayWeather(geoLocation)
            }

            val hourlyResult = withContext(IO) {
                weatherRepository.getForecastWeather(geoLocation)
            }

            if (todayResult.code == SUCCESS && hourlyResult.code == SUCCESS
                && todayResult.data != null && hourlyResult.data != null) {
                view?.showWeather(todayResult.data, hourlyResult.data)
            } else {
                //todo handle error
            }

            //todo hide progress
        }
    }

    override fun detachView() {
        super.detachView()
        if (getWeatherJob?.isActive == true)
            getWeatherJob?.cancel()
    }
}