package nikita.com.weatherapp.main

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import nikita.com.weatherapp.R
import nikita.com.weatherapp.utils.permissionsToRequest
import org.koin.android.ext.android.inject


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

    }

    override fun onResume() {
        super.onResume()
        presenter.presenterReceiveLocation()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun handleLocationPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkLocationPermissions()) {
                presenter.getLocation()
            } else {
                requestLocationPermissions()
            }
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            presenter.getLocation()
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
            presenter.getLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        var wasGranted = true
        if (requestCode == LOCATION_PERMISSION_RESULT) {
            for (i in 0 until permissions.size) if (grantResults[i] != Activity.RESULT_OK) wasGranted = false
            if (wasGranted) {
                presenter.presenterReceiveLocation()
            } else {
                presenter.locationPermissionNotGranted()
            }
        }
    }
}
