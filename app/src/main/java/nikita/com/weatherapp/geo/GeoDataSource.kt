package nikita.com.weatherapp.geo

import android.location.Geocoder
import android.location.Location


class GeoDataSource(
    private val geocoder: Geocoder
) : GeoRepository {

    override var currentLocation: GeoLocation? = null

    override fun Location.defineLocation(): Boolean {
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        if (addresses.isNotEmpty()) {
            currentLocation = GeoLocation(
                latitude, longitude, addresses[0].locality
            )
            return true
        }

        return false
    }
}