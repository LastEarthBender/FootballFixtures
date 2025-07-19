package com.example.footballfixtures.ui.competitions.teams.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.footballfixtures.databinding.TeamDetailBottomSheetBinding
import com.example.footballfixtures.ui.competitions.teams.vm.TeamDetailUiState
import com.example.footballfixtures.ui.competitions.teams.vm.TeamDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TeamDetailBottomSheet : BottomSheetDialogFragment() {
    private var _binding: TeamDetailBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TeamDetailViewModel by viewModels()
    private lateinit var squadAdapter: TeamSquadAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = TeamDetailBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {
        val teamId = requireArguments().getInt("team_id")
        squadAdapter = TeamSquadAdapter()
        binding.rvSquad.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSquad.adapter = squadAdapter
        binding.btnClose.setOnClickListener { dismiss() }
        viewModel.loadTeamDetails(teamId)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is TeamDetailUiState.Loading -> {
                        binding.apply {
                            progressBar.visibility = View.VISIBLE
                            rvSquad.visibility = View.GONE
                        }

                    }

                    is TeamDetailUiState.Success -> {
                        binding.apply {
                            progressBar.visibility = View.GONE
                            rvSquad.visibility = View.VISIBLE
                            tvTeamName.text = state.team.name
                            Glide.with(requireContext()).load(state.team.crest).into(ivTeamBadge)
                            squadAdapter.submitList(state.team.squad ?: emptyList())
                        }
                    }

                    is TeamDetailUiState.Error -> {
                        dismiss()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(teamId: Int): TeamDetailBottomSheet {
            val sheet = TeamDetailBottomSheet()
            sheet.arguments = Bundle().apply { putInt("team_id", teamId) }
            return sheet
        }
    }
}