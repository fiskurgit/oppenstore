package oppen.oppenstore

import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.browser.customtabs.CustomTabsIntent
import oppen.oppenstore.api.model.App


fun Context.installed(packageName: String): Boolean{
    return try {
        this.packageManager.getPackageInfo(packageName, 0)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

//This is domain specific and includes app dependencies but I'll allow it in this app
fun Context.showUrl(url: String?){
    CustomTabsIntent.Builder()
        .setToolbarColor(this.resources.getColor(R.color.colorPrimary, null))
        .enableUrlBarHiding()
        .setShowTitle(true)
        .setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left)
        .setExitAnimations(this, -1, R.anim.fade_out)
        .build().launchUrl(this, Uri.parse(url ?: ""))
}

fun Context.appVersion(): String{
    val pInfo: PackageInfo = this.packageManager.getPackageInfo(packageName, 0)
    return pInfo.versionName
}

fun Context.installApk(url: String, title: String){
    val request = DownloadManager.Request(Uri.parse(url))
    request.setDescription("ÖppenStore software install")
    request.setTitle(title)
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, url.split("/").last())
    val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    manager.enqueue(request)
}