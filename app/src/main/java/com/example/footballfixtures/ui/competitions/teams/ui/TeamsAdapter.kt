package com.example.footballfixtures.ui.competitions.teams.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.footballfixtures.R
import com.example.footballfixtures.data.model.TeamShort
import com.example.footballfixtures.databinding.ItemTeamBinding

class TeamsAdapter(
    private var teams: List<TeamShort>,
    private val onItemClick: (TeamShort) -> Unit
) : RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teams[position])
        holder.binding.root.setOnClickListener {
            onItemClick(teams[position])
        }
    }

    override fun getItemCount() = teams.size

    fun submitList(newTeams: List<TeamShort>) {
        teams = newTeams
        notifyDataSetChanged()
    }

    inner class TeamViewHolder(val binding: ItemTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(team: TeamShort) {
            binding.apply {
                tvTeamName.text = team.name
                Glide.with(itemView)
                    .load(team.crest)
                    .apply(RequestOptions().error(R.drawable.soccer))
                    .into(ivCrest)
            }

        }
    }
}