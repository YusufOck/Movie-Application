package com.example.retrofit_usage.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit_usage.databinding.ItemMovieBinding
import com.example.retrofit_usage.model.db.MovieEntity

// MovieAdapter: Film listesini RecyclerView'da göstermek için kullanılır.
// Film kartlarının görsel ve metinlerini bağlar, tıklama olaylarını yönetir.
class MovieAdapter(
    private val onClick: (MovieEntity) -> Unit
) : ListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieEntity) {
            binding.apply {
                movieTitle.text = movie.title
                movieReleaseDate.text = movie.releaseDate

                movie.posterPath?.let { posterPath ->
                    Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w500$posterPath")
                        .into(moviePoster)
                }

                root.setOnClickListener { onClick(movie) }
            }
        }
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<MovieEntity>() {
    override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
        return oldItem == newItem
    }
}