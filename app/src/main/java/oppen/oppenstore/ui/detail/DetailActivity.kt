package oppen.oppenstore.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import oppen.oppenstore.R

import kotlinx.android.synthetic.main.activity_detail.*
import oppen.oppenstore.api.CatalogueRepository
import oppen.oppenstore.api.DebugCatalogue
import oppen.oppenstore.api.model.App

class DetailActivity : AppCompatActivity(), DetailView {

    companion object{
        private const val EXTRA_OPPEN_ID = "oppen.oppenstore.ui.detail.EXTRA_OPPEN_ID"

        fun createIntent(context: Context, oppenId: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_OPPEN_ID, oppenId)
            return intent
        }
    }

    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(detail_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener {
            //todo - download the app - or open the pwa
        }

        presenter = DetailPresenter(this, CatalogueRepository(DebugCatalogue(this)))
        presenter.getApp(intent.getStringExtra(EXTRA_OPPEN_ID)!!)
    }

    override fun showApp(app: App) {
        toolbar_layout.title = app.title

        Glide.with(this).load(app.image).into(detail_banner_image)
    }

    override fun showError(error: String) {
        Snackbar.make(detail_toolbar, error, Snackbar.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        close()
        return true
    }

    override fun onBackPressed() {
        close()
    }

    private fun close(){
        finish()
        overridePendingTransition(-1,  R.anim.fade_out)
    }

}
