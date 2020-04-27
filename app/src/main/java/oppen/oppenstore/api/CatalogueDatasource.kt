package oppen.oppenstore.api

import oppen.oppenstore.api.model.Apps

interface CatalogueDatasource {
    fun getApps(onError: (message: String)-> Unit,  callback: (apps: Apps?) -> Unit)
}