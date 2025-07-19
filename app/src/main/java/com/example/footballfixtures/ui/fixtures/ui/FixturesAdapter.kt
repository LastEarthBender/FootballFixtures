package com.example.footballfixtures.ui.fixtures.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballfixtures.data.model.Match
import com.example.footballfixtures.databinding.ItemFixtureBinding

class FixturesAdapter(private var fixtures: List<Match>) :
    RecyclerView.Adapter<FixturesAdapter.FixtureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureViewHolder {
        val binding = ItemFixtureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FixtureViewHolder(binding)
    }


    override fun onBindViewHolder(holder: FixtureViewHolder, position: Int) {
        holder.bind(fixtures[position])
    }

    override fun getItemCount(): Int = fixtures.size

    fun submitList(newFixtures: List<Match>) {
        fixtures = newFixtures
        notifyDataSetChanged()
    }

    inner class FixtureViewHolder(val binding: ItemFixtureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(match: Match) {

            Log.d("FixtureVcccciewHolder", "Binding match: $match")
            binding.apply {
                footballStatus.text = match.status
                matchTime.text = match.utcDate.substringAfter('T').substring(0, 5)
                tvMatchDay.text = "MD: 39"
                tvHomeTeam.text = match.homeTeam.name ?: "-"
                tvAwayTeam.text = match.awayTeam.name ?: "-"
                tvHomeScore.text = match.score.fullTime?.home?.toString() ?: "0"
                tvAwayScore.text = match.score.fullTime?.away?.toString() ?: "0"
                tvMinute.text = match.utcDate.substringAfter('T').substringBefore('Z').substring(6, 8)
            }
        }
    }


}
