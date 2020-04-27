package oppen.oppenstore.ui.catalogue

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_catalogue.*
import oppen.oppenstore.R
import oppen.oppenstore.api.CatalogueRepository
import oppen.oppenstore.api.DebugCatalogue
import oppen.oppenstore.api.model.App
import oppen.oppenstore.appVersion
import oppen.oppenstore.installApk
import oppen.oppenstore.showUrl
import oppen.oppenstore.ui.detail.DetailActivity

class CatalogueActivity : AppCompatActivity(), CatalogueView {

  private lateinit var presenter: CataloguePresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_catalogue)
    setSupportActionBar(catalogue_toolbar)
    supportActionBar?.setDisplayShowTitleEnabled(false)

    catalogue_recycler.layoutManager = LinearLayoutManager(this)

    launch_web_button.setOnClickListener {
      showUrl("https://www.oppenlab.net/blog/")
    }

    presenter = CataloguePresenter(this, CatalogueRepository(DebugCatalogue(this)))
    presenter.getStore()
  }

    override fun checkVersion(storeVersion: String, storeUrl: String) {
      val installedVersion = appVersion()
      if(installedVersion != storeVersion){
        val message = "New version available: $storeVersion (Installed: $installedVersion)"
        AlertDialog.Builder(this)
          .setTitle(R.string.update_dialog_title)
          .setMessage(message)
          .setPositiveButton("Download"){ _, _ ->
            installApk(storeUrl, "Öppen Store")
          }
          .setNegativeButton("Cancel"){ _, _ -> }
          .show()
      }
    }

  override fun storeGitRepository(url: String) {
    git_button.setOnClickListener {
      showUrl(url)
    }
  }

  override fun showApps(apps: List<App>) {
    catalogue_recycler.adapter = CatalogueAdapter(this, apps){ app, image ->
      val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, image, resources.getString(R.string.poster_image_transition))
      startActivity(DetailActivity.createIntent(this, app.oppen_id), activityOptionsCompat.toBundle())
    }
  }

  override fun showError(error: String) {
    Snackbar.make(catalogue_toolbar, error, Snackbar.LENGTH_LONG).show()
  }
}
