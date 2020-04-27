package oppen.oppenstore.api

import android.content.Context
import okio.buffer
import okio.source
import oppen.oppenstore.api.model.App
import oppen.oppenstore.api.model.Apps

class DebugCatalogue(private val context: Context): CatalogueDatasource {
    override fun getApps(onError: (message: String) -> Unit, callback: (apps: Apps?) -> Unit) {
        val input = context.assets.open("oppenstore.json")
        val bufferedSource = input.source().buffer()
        val appsWrapper = Apps.generate(bufferedSource)
        callback.invoke(appsWrapper)
    }
}