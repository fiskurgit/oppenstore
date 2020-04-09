package oppen.oppenstore

import android.content.Context
import android.content.pm.PackageManager

 fun Context.installed(packageName: String): Boolean{
    return try {
        this.packageManager.getPackageInfo(packageName, 0)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}