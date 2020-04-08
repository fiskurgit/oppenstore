package oppen.oppenstore.ui.detail

import oppen.oppenstore.api.CatalogueRepository


class DetailPresenter(private val view: DetailView, private val repository: CatalogueRepository) {

    fun getApp(oppenId: String){
        repository.getApp(oppenId, { error ->
            view.showError(error)
        }){app ->
            view.showApp(app)
        }
    }
}