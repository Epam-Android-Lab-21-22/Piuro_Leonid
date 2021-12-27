package com.lealpy.socialnetworkui.achievements

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lealpy.socialnetworkui.databinding.ItemSeasonBinding
import com.lealpy.socialnetworkui.databinding.ItemTeamBinding
import com.lealpy.socialnetworkui.databinding.ItemTrophyBinding

class AchievementAdapter (
    private val onCrossClickListener: OnCrossClickListener,
) : ListAdapter<Achievement, RecyclerView.ViewHolder>(DiffCallback()) {

    inner class SeasonItemHolder(
        private val binding: ItemSeasonBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(seasonItem: Achievement.SeasonItem) {
            binding.seasonName.text = seasonItem.name
        }
    }

    inner class TeamItemHolder(
        private val binding: ItemTeamBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(teamItem: Achievement.TeamItem) {
            binding.teamName.text = teamItem.name
            binding.teamImage.setImageResource(teamItem.image)
        }
    }

    inner class TrophyItemHolder(
        private val binding: ItemTrophyBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(trophyItem: Achievement.TrophyItem) {
            binding.trophyName.text = trophyItem.name
            binding.trophyImage.setImageResource(trophyItem.image)
            binding.trophyDeleteButton.setOnClickListener {
                val item = getItem(adapterPosition) as Achievement.TrophyItem
                onCrossClickListener.onCrossClick(item)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is Achievement.SeasonItem -> ViewType.SEASON_ITEM.ordinal
            is Achievement.TeamItem -> ViewType.TEAM_ITEM.ordinal
            is Achievement.TrophyItem -> ViewType.TROPHY_ITEM.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ViewType.SEASON_ITEM.ordinal -> {
                 SeasonItemHolder(
                     ItemSeasonBinding.inflate(
                         LayoutInflater.from(parent.context),
                         parent,
                         false
                     )
                 )
            }

            ViewType.TEAM_ITEM.ordinal -> {
                TeamItemHolder(
                    ItemTeamBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            ViewType.TROPHY_ITEM.ordinal -> {
                TrophyItemHolder(
                    ItemTrophyBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> throw Exception(EXCEPTION_MESSAGE)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when(holder) {
            is SeasonItemHolder -> holder.bind(item as Achievement.SeasonItem)
            is TeamItemHolder -> holder.bind(item as Achievement.TeamItem)
            is TrophyItemHolder -> holder.bind(item as Achievement.TrophyItem)
        }
    }

    interface OnCrossClickListener {
        fun onCrossClick(trophyItem: Achievement.TrophyItem)
    }

    class DiffCallback: DiffUtil.ItemCallback<Achievement>() {
        override fun areItemsTheSame(oldItem: Achievement, newItem: Achievement) =
            oldItem.achievementId == newItem.achievementId

        override fun areContentsTheSame(oldItem: Achievement, newItem: Achievement) =
            oldItem == newItem
    }

    private enum class ViewType {
        SEASON_ITEM,
        TEAM_ITEM,
        TROPHY_ITEM
    }

    companion object {
        private const val EXCEPTION_MESSAGE = "Unknown ViewType"
    }

}
