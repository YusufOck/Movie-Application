// WatchLaterAdapter: Sonradan izlenecek filmleri RecyclerView'da gösterir.
// Silme ve detay tıklama olaylarını yönetir.
package com.example.retrofit_usage.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit_usage.R
import com.example.retrofit_usage.model.db.WatchLaterEntity

class WatchLaterAdapter(
    private val onDeleteClick: (WatchLaterEntity) -> Unit,
    private val onItemClick: ((WatchLaterEntity) -> Unit)? = null
) : ListAdapter<WatchLaterEntity, WatchLaterAdapter.WatchLaterViewHolder>(
    DIFF_CALLBACK
) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WatchLaterEntity>() {
            override fun areItemsTheSame(oldItem: WatchLaterEntity, newItem: WatchLaterEntity): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: WatchLaterEntity, newItem: WatchLaterEntity): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchLaterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_watch_later, parent, false)
        return WatchLaterViewHolder(view)
    }

    override fun onBindViewHolder(holder: WatchLaterViewHolder, position: Int) {
        holder.bind(getItem(position), onDeleteClick, onItemClick)
    }

    class WatchLaterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.watchLaterImageView)
        private val titleView: TextView = itemView.findViewById(R.id.watchLaterTitleTextView)
        private val deleteButton: View = itemView.findViewById(R.id.btnDeleteWatchLater)
        fun bind(movie: WatchLaterEntity, onDeleteClick: (WatchLaterEntity) -> Unit, onItemClick: ((WatchLaterEntity) -> Unit)?) {
            titleView.text = movie.title
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500" + (movie.posterPath ?: ""))
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
            deleteButton.setOnClickListener { onDeleteClick(movie) }
            itemView.setOnClickListener { onItemClick?.invoke(movie) }
        }
    }
} 