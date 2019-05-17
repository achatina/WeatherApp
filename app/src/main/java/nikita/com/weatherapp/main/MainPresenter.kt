package nikita.com.weatherapp.main

import nikita.com.weatherapp.BasePresenter
import nikita.com.weatherapp.weather.WeatherRepository

class MainPresenter(
    private val weatherRepository: WeatherRepository
) : BasePresenter<MainView> {

    override var view: MainView? = null

}