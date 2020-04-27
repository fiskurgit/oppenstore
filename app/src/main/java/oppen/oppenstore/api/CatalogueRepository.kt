package oppen.oppenstore.api

import oppen.oppenstore.api.model.App
import oppen.oppenstore.api.model.Apps

class CatalogueRepository(private val datasource: CatalogueDatasource) {

    var apps: Apps? = null

    fun getStoreVersion(){

    }

    fun getApps(onError: (message: String)-> Unit, callback: (apps: Apps?) -> Unit){
        datasource.getApps({ errorMessage ->
            onError.invoke(errorMessage)
        }){ apps ->
            this.apps = apps
            callback.invoke(apps)
        }
    }

    fun getApp(oppenId: String, onError: (message: String)-> Unit, callback: (app: App) -> Unit){
        if(apps != null){
            findApp(oppenId, onError, callback)
        }else {
            datasource.getApps({ errorMessage ->
                onError.invoke(errorMessage)
            }) { apps ->
                this.apps = apps
                findApp(oppenId, onError, callback)
            }
        }
    }

    private fun findApp(oppenId: String, onError: (message: String)-> Unit, callback: (app: App) -> Unit){
        var foundApp = false
        apps?.apps?.forEach{app ->
            if(app.oppen_id == oppenId){
                callback.invoke(app)
                foundApp = true
                return@forEach
            }
        }

        if(!foundApp){
            onError.invoke("Could not find app $oppenId")
        }
    }
}