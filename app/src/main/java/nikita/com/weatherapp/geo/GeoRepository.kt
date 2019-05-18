package nikita.com.weatherapp.geo

import android.location.Location

interface GeoRepository {

    var currentLocation: GeoLocation?

    /**
     * Define GeoLocation by Location parameters and save it to current location
     *
     * @return true if defined, false if it failed
     */
    fun Location.defineLocation(): Boolean

}

data class GeoLocation(
    val lat: Double,
    val lng: Double,
    val cityName: String
)