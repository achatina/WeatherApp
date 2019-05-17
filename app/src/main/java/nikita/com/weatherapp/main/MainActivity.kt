package nikita.com.weatherapp.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import nikita.com.weatherapp.R
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainView {

    private val presenter: MainPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
