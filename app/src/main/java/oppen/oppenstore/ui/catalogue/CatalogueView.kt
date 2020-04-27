package oppen.oppenstore.ui.catalogue

import oppen.oppenstore.api.model.App

interface CatalogueView {
    fun checkVersion(storeVersion: String, storeUrl: String)
    fun storeGitRepository(url: String)
    fun showApps(apps: List<App>)
    fun showError(error: String)
}