package nikita.com.weatherapp.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

fun Context.permissionsToRequest(targetPermissions: List<String>): List<String> {
    return targetPermissions.filter { permission -> !hasPermission(permission) }
}

fun Context.hasPermission(permission: String): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    } else true
}