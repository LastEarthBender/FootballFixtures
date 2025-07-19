package com.example.footballfixtures.ui.competitions.table.ui

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
import com.example.footballfixtures.databinding.FragmentCompetitionTableBinding
import com.example.footballfixtures.ui.competitions.table.vm.StandingsUiState
import com.example.footballfixtures.ui.competitions.table.vm.StandingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CompetitionTableFragment : Fragment() {

    private var _binding: FragmentCompetitionTableBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StandingsViewModel by viewModels()
    private lateinit var adapter: StandingsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCompetitionTableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {
        adapter = StandingsAdapter(emptyList())
        binding.standingsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.standingsRecyclerView.adapter = adapter

        val competitionCode = arguments?.getString("competition_code") ?: return
        viewModel.loadStandings(competitionCode)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is StandingsUiState.Loading -> {
                            binding.apply {
                                progressBarStandings.visibility = View.VISIBLE
                                tvErrorStandings.visibility = View.GONE
                                standingsRecyclerView.visibility = View.GONE
                            }

                        }

                        is StandingsUiState.Success -> {
                            binding.apply {
                                progressBarStandings.visibility = View.GONE
                                tvErrorStandings.visibility = View.GONE
                                standingsRecyclerView.visibility = View.VISIBLE
                                adapter.submitList(state.standings)
                            }

                        }

                        is StandingsUiState.Error -> {
                            binding.apply {
                                progressBarStandings.visibility = View.GONE
                                tvErrorStandings.visibility = View.VISIBLE
                                standingsRecyclerView.visibility = View.GONE
                                tvErrorStandings.text = state.message
                            }

                        }
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(competitionCode: String?): CompetitionTableFragment {
            val frag = CompetitionTableFragment()
            frag.arguments = Bundle().apply { putString("competition_code", competitionCode) }
            return frag
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}