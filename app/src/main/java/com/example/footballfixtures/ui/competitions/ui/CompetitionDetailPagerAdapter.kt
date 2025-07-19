package com.example.footballfixtures.ui.competitions.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.footballfixtures.ui.competitions.fixtures.CompetitionFixturesFragment
import com.example.footballfixtures.ui.competitions.table.ui.CompetitionTableFragment
import com.example.footballfixtures.ui.competitions.teams.ui.CompetitionTeamsFragment

class CompetitionDetailPagerAdapter(
    fa: FragmentActivity,
    private val competitionCode: String?,
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CompetitionTableFragment.newInstance(competitionCode)
            1 -> CompetitionFixturesFragment.newInstance(competitionCode)
            2 -> CompetitionTeamsFragment.newInstance(competitionCode)
            else -> throw IllegalStateException()
        }
    }
}
