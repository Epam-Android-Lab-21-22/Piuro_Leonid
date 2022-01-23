package com.lealpy.socialnetworkui.presentation.favorite_songs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lealpy.socialnetworkui.domain.model.SongPreview
import com.lealpy.socialnetworkui.databinding.ItemSongBinding

class SongItemAdapter (
    private val onItemClickListener: OnItemClickListener,
    private val onPlayButtonClickListener: OnPlayButtonClickListener
): ListAdapter<SongPreview, SongItemAdapter.SongItemHolder>(DiffCallback()) {

    inner class SongItemHolder(
        private val binding: ItemSongBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                binding.root.setOnClickListener {
                    val position = layoutPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val songItem = getItem(position)
                        onItemClickListener.onItemClick(songItem)
                    }
                }
            }
        }

        fun bind(songItem: SongPreview) {
            binding.title.text = songItem.title
            binding.singer.text = songItem.singer
            if(songItem.url != null) {
                binding.playButton.isEnabled = true
                binding.playButton.setOnClickListener {
                    onPlayButtonClickListener.onplayButtonClick(songItem.url)
                }
            } else {
                binding.playButton.isEnabled = false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongItemHolder {
        val binding = ItemSongBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SongItemHolder(binding)
    }

    override fun onBindViewHolder(holder: SongItemHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    interface OnItemClickListener {
        fun onItemClick(songItem: SongPreview)
    }

    interface OnPlayButtonClickListener {
        fun onplayButtonClick(url : String)
    }

    class DiffCallback: DiffUtil.ItemCallback<SongPreview>() {
        override fun areItemsTheSame(oldItem: SongPreview, newItem: SongPreview) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SongPreview, newItem: SongPreview) =
            oldItem == newItem
    }
}
