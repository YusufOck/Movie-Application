package com.example.retrofit_usage.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.retrofit_usage.BuildConfig
import androidx.navigation.fragment.findNavController

import com.example.retrofit_usage.databinding.FragmentDetailPageBinding
import com.example.retrofit_usage.model.db.MovieEntity
import com.example.retrofit_usage.model.db.WatchLaterEntity
import com.example.retrofit_usage.viewmodel.MovieViewModel
import com.example.retrofit_usage.viewmodel.WatchLaterViewModel
import com.example.retrofit_usage.viewmodel.factory.MovieViewModelFactory
import com.example.retrofit_usage.viewmodel.factory.WatchLaterViewModelFactory

// DetailFragment: Seçilen filmin detaylarını kullanıcıya gösterir.
// Sadece veri gösterimi ve kullanıcı etkileşimi içerir.

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailPageBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var watchLaterViewModel: WatchLaterViewModel
    val ApiKey = BuildConfig.TMDB_API_KEY

    private var currentMovie: MovieEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = MovieViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)

        val watchLaterFactory = WatchLaterViewModelFactory(requireActivity().application)
        watchLaterViewModel = ViewModelProvider(this, watchLaterFactory).get(WatchLaterViewModel::class.java)

        binding.fabAddToWatchLater.setOnClickListener {
            addCurrentMovieToWatchLater()
        }

        val movieId = arguments?.getInt("movieId") ?: 0
        if (movieId != 0) {
            viewModel.getMovieDetails(movieId, ApiKey)
            viewModel.getMovieById(movieId).observe(viewLifecycleOwner) { movie ->
                movie?.let {
                    currentMovie = it
                    bindMovie(it)
                }
            }
        }
    }

    private fun bindMovie(movie: MovieEntity) {
        binding.apply {
            titleTextView.text = movie.title
            releaseDateTextView.text = movie.releaseDate
            overviewTextView.text = movie.overview
            ratingTextView.text = "${movie.voteAverage}/10 (${movie.voteCount} votes)"

            movie.posterPath?.let { posterPath ->
                Glide.with(requireContext())
                    .load("https://image.tmdb.org/t/p/w500$posterPath")
                    .into(posterImageView)
            }
        }
    }

    fun addCurrentMovieToWatchLater() {
        currentMovie?.let {
            val entity = WatchLaterEntity(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath
            )
            watchLaterViewModel.addToWatchLater(entity)
            android.widget.Toast.makeText(requireContext(), "Added to Watch Later List", android.widget.Toast.LENGTH_SHORT).show()
        }
    }
}