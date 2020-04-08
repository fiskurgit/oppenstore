package oppen.oppenstore.api

import oppen.oppenstore.api.model.App

interface CatalogueDatasource {
    fun getApps(onError: (message: String)-> Unit,  callback: (apps: List<App>) -> Unit)
}