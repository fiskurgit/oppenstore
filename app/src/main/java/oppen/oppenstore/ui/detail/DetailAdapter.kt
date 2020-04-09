package oppen.oppenstore.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_row_image.view.*
import kotlinx.android.synthetic.main.detail_row_text.view.*
import oppen.oppenstore.R
import oppen.oppenstore.api.model.Content

class DetailAdapter(private val context: Context, private val sections: List<Content>): RecyclerView.Adapter<DetailAdapter.DetailViewHolder>(){

    companion object{
        private const val TYPE_UNKNOWN = -1
        private const val TYPE_TEXT = 0
        private const val TYPE_IMAGE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return when(viewType){
            TYPE_TEXT -> {
                TextDetailViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.detail_row_text, parent, false)
                )
            }
            TYPE_IMAGE -> {
                ImageDetailViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.detail_row_image, parent, false)
                )
            }
            else -> DetailViewHolder(View(context))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (sections[position].type) {
            "text" -> {
                TYPE_TEXT
            }
            "image" -> {
                TYPE_IMAGE
            }
            else -> {
                TYPE_UNKNOWN
            }
        }
    }

    override fun getItemCount(): Int = sections.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val section = sections[position]

        when (holder) {
            is TextDetailViewHolder -> {
                holder.itemView.content_text.text = section.text
            }
            is ImageDetailViewHolder -> {
                Glide.with(context).load(section.image).into(holder.itemView.content_image)
            }
        }
    }

    open class DetailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    class TextDetailViewHolder(itemView: View): DetailViewHolder(itemView)
    class ImageDetailViewHolder(itemView: View): DetailViewHolder(itemView)
}