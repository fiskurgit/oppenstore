package oppen.oppenstore.api

import oppen.oppenstore.api.model.App

class CatalogueRepository(private val datasource: CatalogueDatasource) {

    fun getApps(onError: (message: String)-> Unit, callback: (apps: List<App>) -> Unit){
        datasource.getApps({ errorMessage ->
            onError.invoke(errorMessage)
        }){ apps ->
            callback.invoke(apps)
        }
    }

    fun getApp(oppenId: String, onError: (message: String)-> Unit, callback: (app: App) -> Unit){
        datasource.getApps({ errorMessage ->
            onError.invoke(errorMessage)
        }){ apps ->
            apps.forEach{app ->
                if(app.oppen_id == oppenId){
                    callback.invoke(app)
                    return@forEach
                }
            }
        }
    }
}