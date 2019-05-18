package nikita.com.weatherapp.main

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import nikita.com.weatherapp.BasePresenter
import nikita.com.weatherapp.geo.GeoRepository
import nikita.com.weatherapp.weather.WeatherRepository

class MainPresenter(
    private val weatherRepository: WeatherRepository,
    private val geoRepository: GeoRepository,
    private val fusedLocationClient: FusedLocationProviderClient
) : BasePresenter<MainView> {

    override var view: MainView? = null


    private fun saveLocation(location: Location?) {
        //todo save
    }

    fun presenterReceiveLocation() {
        view?.handleLocationPermissions()
    }

    fun locationPermissionNotGranted() {
        //todo show info dialog
    }

    @SuppressLint("MissingPermission")
    fun getLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            saveLocation(location)
        }
    }

}