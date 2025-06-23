package com.example.retrofit_usage.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofit_usage.BuildConfig
import com.example.retrofit_usage.R
import com.example.retrofit_usage.databinding.FragmentMainBinding
import com.example.retrofit_usage.ui.adapter.MovieAdapter
import com.example.retrofit_usage.viewmodel.MovieViewModel
import com.example.retrofit_usage.viewmodel.factory.MovieViewModelFactory

// MainFragment: Kullanıcıya film listesini gösterir ve kullanıcı etkileşimlerini ViewModel'a iletir.
// Popüler filmler, arama ve 'Sonradan İzlenenler' butonu burada yönetilir.

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter
    val ApiKey = BuildConfig.TMDB_API_KEY
    private var isLoading = false
    private var visibleThreshold = 2
    private var lastVisibleItem = 0
    private var totalItemCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = MovieViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)

        setupRecyclerView()
        observeViewModel()
        setupSearchView()

        viewModel.fetchPopularMovies(ApiKey)
    }

    private fun calculateSpanCount(): Int {
        val displayMetrics = resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return when {
            screenWidthDp >= 900 -> 4 // Tablet ve üstü
            screenWidthDp >= 600 -> 3 // Büyük telefonlar/tablet
            else -> 2 // Normal telefonlar
        }
    }

    private fun setupRecyclerView() {
        adapter = MovieAdapter { movie ->
            findNavController().navigate(
                R.id.action_mainFragment_to_detailFragment,
                Bundle().apply { putInt("movieId", movie.id) }
            )
        }
        val spanCount = calculateSpanCount()
        val layoutManager = GridLayoutManager(requireContext(), spanCount)
        binding.recyclerView.apply {
            this.layoutManager = layoutManager
            adapter = this@MainFragment.adapter
            addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    totalItemCount = layoutManager.itemCount
                    lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        isLoading = true
                        viewModel.loadNextPage(ApiKey)
                    }
                }
            })
        }
    }

    private fun observeViewModel() {
        viewModel.filteredMovies.observe(viewLifecycleOwner) { movies ->
            isLoading = false

            adapter.submitList(movies)
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setSearchQuery(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchQuery(newText ?: "")
                return true
            }
        })
    }
}