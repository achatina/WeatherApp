package nikita.com.weatherapp

import android.view.View
import android.view.WindowManager

interface ProgressView : BaseView {

    fun progressView(): View?

    fun showProgress(blockUI: Boolean = true) {
        progressView()?.visibility = View.VISIBLE
        if (blockUI)
            viewContext()?.window?.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    fun hideProgress() {
        progressView()?.visibility = View.GONE
        viewContext()?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

}