package com.example.footballfixtures.ui.competitions.table.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.footballfixtures.R
import com.example.footballfixtures.data.model.TableRow
import com.example.footballfixtures.databinding.ItemTableRowBinding

class StandingsAdapter(private var rows: List<TableRow>) :
    RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingsViewHolder {
        val binding = ItemTableRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StandingsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StandingsViewHolder, position: Int) {
        holder.bind(rows[position])
    }

    override fun getItemCount() = rows.size
    fun submitList(newRows: List<TableRow>) {
        rows = newRows
        notifyDataSetChanged()
    }

   inner class StandingsViewHolder(val binding: ItemTableRowBinding)
       : RecyclerView.ViewHolder(binding.root) {
        fun bind(row: TableRow) {
            Log.d("StandingsViewHolder", "bind: $row")
            binding.apply {
                tvPosition.text = row.position.toString()
                tvTeamName.text = row.team.name
                tvGamesPlayed.text = row.playedGames.toString()
                tvGoalDiff.text = row.goalDifference.toString()
                tvPoints.text = row.points.toString()
                Glide.with(itemView)
                    .load(row.team.crest)
                    .apply(RequestOptions().error(R.drawable.soccer)) // fallback icon
                    .into(crest)
            }
        }
    }
}