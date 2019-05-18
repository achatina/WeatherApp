package nikita.com.weatherapp

import android.app.AlertDialog
import android.support.annotation.StringRes

interface AlertView : BaseView {

    fun showAlert(title: String, message: String) {
        AlertDialog.Builder(viewContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(viewContext()?.getString(R.string.dialog_close)) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    fun showAlert(@StringRes title: Int, message: String) {
        showAlert(title = viewContext()?.getString(title) ?: "Error", message = message)
    }

    fun showAlert(@StringRes title: Int, @StringRes message: Int) {
        showAlert(
            title = viewContext()?.getString(title) ?: "Error",
            message = viewContext()?.getString(message) ?: "Error"
        )
    }

    fun showAlert(title: String, @StringRes message: Int) {
        showAlert(title, message = viewContext()?.getString(message) ?: "Error")
    }

    fun showAlert(
        @StringRes title: Int,
        @StringRes message: Int,
        @StringRes positiveButtonText: Int,
        @StringRes negativeButtonText: Int,
        isCancelable: Boolean,
        onPositiveButtonClicked: () -> Unit,
        onNegativeButtonClicked: () -> Unit
    ) {

        AlertDialog.Builder(viewContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { dialog, _ ->
                onPositiveButtonClicked()
                dialog.dismiss()
            }
            .setNegativeButton(negativeButtonText) { dialog, _ ->
                onNegativeButtonClicked()
                dialog.dismiss()
            }
            .setCancelable(isCancelable)
            .show()
    }
}