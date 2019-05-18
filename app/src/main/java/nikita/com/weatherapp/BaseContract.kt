package nikita.com.weatherapp

import android.app.Activity

interface BaseView {

    fun viewContext(): Activity?

    fun close()

}

interface BasePresenter<T : BaseView > {

    var view: T?

    fun attachView(view: T) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}