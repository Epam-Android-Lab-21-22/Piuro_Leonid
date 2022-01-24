package com.lealpy.socialnetworkui.presentation.song

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.lealpy.socialnetworkui.databinding.DialogFragmentSongBinding

class SongDialogFragment : DialogFragment() {

    private lateinit var binding : DialogFragmentSongBinding

    private val viewModel : SongViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogFragmentSongBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(context)
        builder.setView(binding.root)
        initObservers()
        return builder.create()
    }

    private fun initObservers() {
        viewModel.titleInDialog.observe(this) { title ->
            binding.title.text = title
        }
        viewModel.singerInDialog.observe(this) { singer ->
            binding.singer.text = singer
        }
        viewModel.albumInDialog.observe(this) { album ->
            binding.album.text = album
        }
        viewModel.yearInDialog.observe(this) { year ->
            binding.year.text = year
        }
        viewModel.genreInDialog.observe(this) { genre ->
            binding.genre.text = genre
        }
    }

    companion object {
        const val SONG_DIALOG_TAG = "SONG_DIALOG_TAG"
    }

}