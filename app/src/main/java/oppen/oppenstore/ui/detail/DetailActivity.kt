package oppen.oppenstore.ui.detail

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail.*
import oppen.oppenstore.R
import oppen.oppenstore.api.CatalogueRepository
import oppen.oppenstore.api.DebugCatalogue
import oppen.oppenstore.api.model.App
import oppen.oppenstore.installApk
import oppen.oppenstore.installed

class DetailActivity : AppCompatActivity(), DetailView {

    companion object{
        private const val EXTRA_OPPEN_ID = "oppen.oppenstore.ui.detail.EXTRA_OPPEN_ID"

        fun createIntent(context: Context, oppenId: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_OPPEN_ID, oppenId)
            return intent
        }
    }

    var downloadComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            Log.d("oppen", "downloadComplete...")
            startActivity(Intent(DownloadManager.ACTION_VIEW_DOWNLOADS))
        }
    }

    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(detail_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        content_recycler.layoutManager = LinearLayoutManager(this)

        presenter = DetailPresenter(this, CatalogueRepository(DebugCatalogue(this)))
        presenter.getApp(intent.getStringExtra(EXTRA_OPPEN_ID)!!)

        registerReceiver(downloadComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    override fun onDestroy() {
        unregisterReceiver(downloadComplete)
        super.onDestroy()
    }

    override fun showApp(app: App) {
        toolbar_layout.title = app.title

        Glide.with(this).load(app.image).into(detail_banner_image)

        content_recycler.adapter = DetailAdapter(this, app.content)

        when (app.type) {
            "apk" -> {
                fab.setImageResource(R.drawable.get_app)
            }
            "pwa" -> {
                fab.setImageResource(R.drawable.open_pwa)
            }
        }

        val installed = installed(app.oppen_id)
        if(installed) fab.setImageResource(R.drawable.open_pwa)

        fab.setOnClickListener {
            when (app.type) {
                "apk" -> {
                    when {
                        installed -> {
                            val launchIntent = packageManager.getLaunchIntentForPackage(app.oppen_id)
                            startActivity(launchIntent)
                        }
                        else -> {
                            installApk(app.url, app.title)
                            Toast.makeText(this, "Downloading ${app.title}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                "pwa" -> {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(app.url)))
                }
            }
        }
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
