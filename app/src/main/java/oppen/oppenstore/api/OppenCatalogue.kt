package oppen.oppenstore.api

import oppen.oppenstore.api.model.App

class OppenCatalogue : CatalogueDatasource{

    override fun getApps(onError: (message: String) -> Unit, callback: (apps: List<App>) -> Unit) {

    }
}