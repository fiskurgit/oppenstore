package oppen.oppenstore.ui.catalogue

import oppen.oppenstore.api.CatalogueRepository
import oppen.oppenstore.ui.catalogue.CatalogueView

class CataloguePresenter(private val view: CatalogueView, private val repository: CatalogueRepository) {

    fun getStore(){
        repository.getApps({ error ->
            view.showError(error)
        }){apps ->
            view.checkVersion(apps?.store_version ?: "", apps?.store_apk ?: "")
            view.showApps(apps?.apps ?: arrayListOf())
        }
    }
}