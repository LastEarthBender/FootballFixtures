package com.example.footballfixtures.ui.competitions.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.footballfixtures.R
import com.example.footballfixtures.databinding.FragmentCompetitionDetailBinding
import com.example.footballfixtures.databinding.FragmentCompetitionsBinding
import com.example.footballfixtures.ui.competitions.vm.CompetitionsViewModel
import com.example.footballfixtures.ui.competitions.vm.CompetitionsUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CompetitionsFragment : Fragment() {
    private var _binding: FragmentCompetitionsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CompetitionsViewModel by viewModels()
    private lateinit var adapter: CompetitionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCompetitionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        adapter = CompetitionsAdapter(emptyList())
        binding.competitionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.competitionsRecyclerView.adapter = adapter

        adapter.onItemClick = { competition ->
            val bundle = Bundle()
            bundle.putString("competition_code", competition.code)
            bundle.putString("competition_name", competition.name)
            findNavController().navigate(
                R.id.nav_competition_detail,
                bundle
            )
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is CompetitionsUiState.Loading -> {
                            binding.apply {
                                progressBarCompetitions.visibility = View.VISIBLE
                                tvErrorCompetitions.visibility = View.GONE
                                competitionsRecyclerView.visibility = View.GONE
                            }
                        }

                        is CompetitionsUiState.Success -> {
                            binding.apply {
                                progressBarCompetitions.visibility = View.GONE
                                tvErrorCompetitions.visibility = View.GONE
                                competitionsRecyclerView.visibility = View.VISIBLE
                                adapter.submitList(state.competitions)
                            }

                        }

                        is CompetitionsUiState.Error -> {
                            binding.apply {
                                progressBarCompetitions.visibility = View.GONE
                                tvErrorCompetitions.visibility = View.VISIBLE
                                competitionsRecyclerView.visibility = View.GONE
                                tvErrorCompetitions.text = state.message
                            }

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
