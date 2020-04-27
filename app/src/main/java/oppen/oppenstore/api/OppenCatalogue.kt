package oppen.oppenstore.api

import oppen.oppenstore.api.model.Apps

class OppenCatalogue : CatalogueDatasource{

    override fun getApps(onError: (message: String) -> Unit, callback: (apps: Apps?) -> Unit) {
        //todo
    }
}