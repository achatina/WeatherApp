package nikita.com.weatherapp

interface BaseView

interface BasePresenter<T : BaseView > {

    var view: T?

    fun attachView(view: T) {
        this.view = view
    }

    fun detachView() {
        view = null
    }
}