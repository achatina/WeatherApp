package nikita.com.weatherapp.main

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import nikita.com.weatherapp.R
import nikita.com.weatherapp.geo.GeoLocation
import nikita.com.weatherapp.models.ForecastWeatherResponse
import nikita.com.weatherapp.models.WeatherResponse
import nikita.com.weatherapp.utils.calcWeatherIcon
import nikita.com.weatherapp.utils.getTimeByLocalTimezone
import nikita.com.weatherapp.utils.permissionsToRequest
import org.koin.android.ext.android.inject
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity(), MainView {

    private val presenter: MainPresenter by inject()
    private val permissionsToRequest by lazy { mutableListOf<String>() }

    companion object {
        private const val LOCATION_PERMISSION_RESULT = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)

        weather_location_icon.setOnClickListener { presenter.findLocation() }

    }

    override fun onResume() {
        super.onResume()
        presenter.findLocation()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun handleLocationPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkLocationPermissions()) {
                presenter.getLocationAfterPermissionsWasGranted()
            } else {
                requestLocationPermissions()
            }
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            presenter.getLocationAfterPermissionsWasGranted()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkLocationPermissions(): Boolean {
        return checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestLocationPermissions() {
        permissionsToRequest.addAll(
            permissionsToRequest(
                listOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        )
        if (permissionsToRequest.isNotEmpty()) {
            requestPermissions(permissionsToRequest.toTypedArray(), LOCATION_PERMISSION_RESULT)
        } else {
            presenter.getLocationAfterPermissionsWasGranted()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        var wasGranted = true
        if (requestCode == LOCATION_PERMISSION_RESULT) {
            for (i in 0 until permissions.size) if (grantResults[i] != Activity.RESULT_OK) wasGranted = false
            if (wasGranted) {
                presenter.findLocation()
            } else {
                presenter.locationPermissionNotGranted()
            }
        }
    }

    override fun close() {
        finish()
    }

    override fun viewContext() = this

    override fun progressView(): View? = weather_progress

    override fun showWeather(today: WeatherResponse, hourly: ForecastWeatherResponse, geoLocation: GeoLocation) {

        weather_place_text.text = geoLocation.cityName
        weather_temp.text = String.format(
            getString(R.string.weather_temp),
            today.main.temp_min.roundToInt(),
            today.main.temp_max.roundToInt()
        )
        weather_humidity.text = String.format(getString(R.string.weather_humidity), today.main.humidity)
        weather_wind.text = String.format(getString(R.string.weather_wind), today.wind.speed)

        if (today.weather.isNotEmpty())
            weather_image.setImageResource(
                calcWeatherIcon(
                    today.weather[0].id,
                    getTimeByLocalTimezone(today.sys.sunrise * 1000),
                    getTimeByLocalTimezone(today.sys.sunset * 1000)
                )
            )
        else
            weather_image.setImageResource(R.drawable.ic_white_day_cloudy)
    }
}
