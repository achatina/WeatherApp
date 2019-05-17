package nikita.com.weatherapp

import android.app.Application
import nikita.com.weatherapp.di.networkModule
import nikita.com.weatherapp.di.weatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApp)

            modules(
                weatherModule,
                networkModule
            )
        }
    }

}