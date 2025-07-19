package com.example.footballfixtures.ui.fixtures.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballfixtures.databinding.FragmentFixturesBinding
import com.example.footballfixtures.ui.fixtures.vm.FixturesUiState
import com.example.footballfixtures.ui.fixtures.vm.FixturesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FixturesFragment : Fragment() {
    private var _binding: FragmentFixturesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FixturesViewModel by viewModels()
    private lateinit var adapter: FixturesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFixturesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()

    }

    private fun setUpUi() {
        adapter = FixturesAdapter(emptyList())

        binding.fixturesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.fixturesRecyclerView.adapter = adapter


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is FixturesUiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.tvError.visibility = View.GONE
                            binding.fixturesRecyclerView.visibility = View.GONE
                        }

                        is FixturesUiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.tvError.visibility = View.GONE
                            binding.fixturesRecyclerView.visibility = View.VISIBLE
                            adapter.submitList(state.fixtures.toList()) // Defensive copy
                        }

                        is FixturesUiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.tvError.visibility = View.VISIBLE
                            binding.fixturesRecyclerView.visibility = View.GONE
                            binding.tvError.text = state.message
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
