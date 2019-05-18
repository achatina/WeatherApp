package nikita.com.weatherapp.geo

import android.location.Location

interface GeoRepository {

    var currentLocation: GeoLocation?

    /**
     * Define GeoLocation by Location parameters and save it to current location
     *
     * @return location if defined or previous known location and null if there're no location
     */
    fun Location.defineLocation(): GeoLocation?

}

data class GeoLocation(
    val lat: Double,
    val lng: Double,
    val cityName: String
)