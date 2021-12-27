package com.lealpy.socialnetworkui.players

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lealpy.socialnetworkui.databinding.ItemPlayerBinding

class PlayerAdapter : ListAdapter<Player, PlayerAdapter.PlayerHolder>(DiffCallback()) {

    inner class PlayerHolder(
        private val binding: ItemPlayerBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player) {
            binding.playerName.text = player.name
            binding.playerPhoto.setImageResource(player.photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val binding = ItemPlayerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlayerHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DiffCallback: DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Player, newItem: Player) =
            oldItem == newItem
    }
}