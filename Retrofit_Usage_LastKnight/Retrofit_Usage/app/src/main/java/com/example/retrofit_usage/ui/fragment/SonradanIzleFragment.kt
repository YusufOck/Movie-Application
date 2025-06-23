package com.example.retrofit_usage.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.retrofit_usage.databinding.FragmentSonradanIzleBinding
import com.example.retrofit_usage.viewmodel.WatchLaterViewModel
import com.example.retrofit_usage.viewmodel.factory.WatchLaterViewModelFactory
import com.example.retrofit_usage.ui.adapter.WatchLaterAdapter
import com.example.retrofit_usage.R

class SonradanIzleFragment : Fragment() {
    private lateinit var binding: FragmentSonradanIzleBinding
    private lateinit var viewModel: WatchLaterViewModel
    private lateinit var adapter: WatchLaterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSonradanIzleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = WatchLaterViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(WatchLaterViewModel::class.java)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = WatchLaterAdapter(
            onDeleteClick = { movie ->
                viewModel.deleteFromWatchLater(movie)
                android.widget.Toast.makeText(requireContext(), "Removed from Watch Later List", android.widget.Toast.LENGTH_SHORT).show()
            }
        )
        binding.recyclerViewWatchLater.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewWatchLater.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.watchLaterMovies.observe(viewLifecycleOwner) { movies ->
            adapter.submitList(movies)
        }
    }
} 