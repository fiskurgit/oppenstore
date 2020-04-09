package oppen.oppenstore.ui.catalogue

import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cell_catalogue.view.*
import oppen.oppenstore.R
import oppen.oppenstore.api.model.App


class CatalogueAdapter(private val context: Context, private val apps: List<App>, private val callback: (app:App, image: ImageView) -> Unit ): RecyclerView.Adapter<CatalogueAdapter.CatalogueViewHolder>() {

    class CatalogueViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueViewHolder {
        return CatalogueViewHolder(
            LayoutInflater.from(context).inflate(R.layout.cell_catalogue, parent, false)
        )
    }

    override fun getItemCount(): Int = apps.size

    override fun onBindViewHolder(holder: CatalogueViewHolder, position: Int) {
        val app = apps[position]

        holder.itemView.cell_title.text = app.title
        holder.itemView.cell_description.text = app.short_description

        if(installed(app.oppen_id)){
            holder.itemView.installed_label.visibility = View.VISIBLE
        }else{
            holder.itemView.installed_label.visibility = View.GONE
        }

        when(app.type.toLowerCase()){
            "pwa" -> holder.itemView.cell_app_type_image.setImageResource(R.drawable.logo_pwa)
            "apk" -> holder.itemView.cell_app_type_image.setImageResource(R.drawable.logo_android)
            else -> holder.itemView.cell_app_type_image.setImageDrawable(null)
        }

        Glide.with(context).load(app.image).into(holder.itemView.cell_image)

        holder.itemView.cell_card.setOnClickListener {
            callback.invoke(apps[holder.adapterPosition], holder.itemView.cell_image)
        }
    }

    private fun installed(packageName: String): Boolean{
        return try {
            context.packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}