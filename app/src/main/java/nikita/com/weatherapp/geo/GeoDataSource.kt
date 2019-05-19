package nikita.com.weatherapp.geo

import android.location.Geocoder
import android.location.Location


class GeoDataSource(
    private val geocoder: Geocoder
) : GeoRepository {

    private var _currentLocation: GeoLocation? = null
    override val currentLocation: GeoLocation?
        get() = _currentLocation

    override fun Location.defineLocation(): GeoLocation? {
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        if (addresses.isNotEmpty()) {
            _currentLocation = GeoLocation(
                latitude, longitude, addresses[0].locality
            )
        }

        return currentLocation
    }
}