package com.example.footballfixtures.ui.competitions.teams.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballfixtures.data.model.SquadMember
import com.example.footballfixtures.databinding.ItemTeamSquadBinding

class TeamSquadAdapter : RecyclerView.Adapter<TeamSquadAdapter.SquadViewHolder>() {

    private var squad: List<SquadMember> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquadViewHolder {
        val binding =
            ItemTeamSquadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SquadViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SquadViewHolder, position: Int) {
        holder.bind(squad[position])
    }

    override fun getItemCount(): Int = squad.size

    fun submitList(newSquad: List<SquadMember>) {
        squad = newSquad ?: emptyList()
        notifyDataSetChanged()
    }

    inner class SquadViewHolder(private val binding: ItemTeamSquadBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(member: SquadMember) {
            Log.d("SquadViewHolder", "Binding member: $member")
            binding.apply {
                tvShirtNumber.text = member.shirtNumber?.toString() ?: (squad.indexOf(member) + 1).toString()
                tvPlayerName.text = member.name ?: "-"
                tvPosition.text = member.position ?: "-"
            }
        }
    }
}
