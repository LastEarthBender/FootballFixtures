package com.example.footballfixtures.ui.competitions.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballfixtures.data.model.Competition
import com.example.footballfixtures.databinding.ItemCompetitionBinding

class CompetitionsAdapter(
    private var competitions: List<Competition>,
    var onItemClick: ((Competition) -> Unit)? = null,
) :
    RecyclerView.Adapter<CompetitionsAdapter.CompetitionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionViewHolder {
        val binding =
            ItemCompetitionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompetitionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompetitionViewHolder, position: Int) {
        holder.bind(competitions[position], onItemClick)
    }

    override fun getItemCount(): Int = competitions.size

    fun submitList(newList: List<Competition>) {
        competitions = newList
        notifyDataSetChanged()
    }

    class CompetitionViewHolder(val binding: ItemCompetitionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(competition: Competition, onItemClick: ((Competition) -> Unit)?) {
            Log.d("Competition gotten", "$competition")
            binding.apply {
                tvCompetitionName.text = competition.name
                itemView.setOnClickListener { onItemClick?.invoke(competition) }
            }

        }
    }
}
