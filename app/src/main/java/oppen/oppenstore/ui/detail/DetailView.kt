package oppen.oppenstore.ui.detail

import oppen.oppenstore.api.model.App

interface DetailView {
    fun showApp(app: App)
    fun showError(error: String)
}