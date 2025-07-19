package com.example.footballfixtures.ui.competitions.fixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.footballfixtures.databinding.CompetitionFixturesEmptyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompetitionFixturesFragment : Fragment() {

    private var _binding: CompetitionFixturesEmptyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        _binding = CompetitionFixturesEmptyBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {
        fun newInstance(competitionCode: String?): CompetitionFixturesFragment {
            val frag = CompetitionFixturesFragment()
            frag.arguments = Bundle().apply { putString("competition_code", competitionCode) }
            return frag
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}