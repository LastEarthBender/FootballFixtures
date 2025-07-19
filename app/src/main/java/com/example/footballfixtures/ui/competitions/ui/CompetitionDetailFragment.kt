package com.example.footballfixtures.ui.competitions.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.viewpager2.widget.ViewPager2
import com.example.footballfixtures.R
import com.example.footballfixtures.databinding.FragmentCompetitionDetailBinding

class CompetitionDetailFragment : Fragment() {
    private var _binding: FragmentCompetitionDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCompetitionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bottomNavView = activity?.findViewById<View>(R.id.bottom_nav)
        bottomNavView?.visibility = View.GONE
        val competitionCode = arguments?.getString("competition_code")
        val competitionName = arguments?.getString("competition_name")
        val topBar = activity?.findViewById<Toolbar>(R.id.topAppBar)
        topBar?.apply {
            title = competitionName
            titleMarginStart = 0
            setTitleTextAppearance(requireContext(), android.R.style.TextAppearance_DeviceDefault_Medium)
        }

        val pagerAdapter = CompetitionDetailPagerAdapter(requireActivity(), competitionCode)

        binding.apply {
            viewPagerCompDetail.adapter = pagerAdapter
            val tabTitles = listOf("Table", "Fixtures", "Teams")
            TabLayoutMediator(tabLayoutCompDetail, viewPagerCompDetail) { tab, position ->
                tab.text = tabTitles[position]
            }.attach()
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        val bottomNavView = activity?.findViewById<View>(R.id.bottom_nav)
        val topBar = activity?.findViewById<Toolbar>(R.id.topAppBar)
        topBar?.apply {
            titleMarginStart = 16
            setTitleTextAppearance(requireContext(), android.R.style.TextAppearance_Material_Headline)
        }
        bottomNavView?.visibility = View.VISIBLE
    }
}
