package com.example.footballfixtures.ui.competitions.teams.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.footballfixtures.databinding.FragmentTeamsBinding
import com.example.footballfixtures.ui.competitions.teams.vm.CompetitionTeamsViewModel
import com.example.footballfixtures.ui.competitions.teams.vm.TeamsUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CompetitionTeamsFragment : Fragment() {
    private var _binding: FragmentTeamsBinding? = null
    private val binding get() = _binding!!
    private lateinit var teamsAdapter: TeamsAdapter

    private val viewModel: CompetitionTeamsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTeamsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
    }

    private fun setupUi() {
        teamsAdapter = TeamsAdapter(emptyList()) { team ->
            openTeamBottomSheet(team.id)
        }
        binding.rvTeams.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvTeams.adapter = teamsAdapter

        val competitionCode = arguments?.getString("competition_code")
        if (!competitionCode.isNullOrEmpty()) {
            viewModel.loadTeams(competitionCode)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collectLatest { state ->
                when (state) {
                    is TeamsUiState.Loading -> {
                        binding.apply {
                            progressBar.visibility = View.VISIBLE
                            rvTeams.visibility = View.GONE
                        }

                    }

                    is TeamsUiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvTeams.visibility = View.VISIBLE
                        teamsAdapter.submitList(state.teams)
                    }

                    is TeamsUiState.Error -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun openTeamBottomSheet(teamId: Int) {
        TeamDetailBottomSheet.newInstance(teamId).show(parentFragmentManager, "TeamDetailBottomSheet")
    }

    companion object {
        fun newInstance(competitionCode: String?): CompetitionTeamsFragment {
            val frag = CompetitionTeamsFragment()
            frag.arguments = Bundle().apply { putString("competition_code", competitionCode) }
            return frag
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}