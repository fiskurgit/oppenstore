package oppen.oppenstore.ui.catalogue

import oppen.oppenstore.api.CatalogueRepository
import oppen.oppenstore.ui.catalogue.CatalogueView

class CataloguePresenter(private val view: CatalogueView, private val repository: CatalogueRepository) {

    fun getApps(){
        repository.getApps({ error ->
            view.showError(error)
        }){apps ->
            view.showApps(apps)
        }
    }
}