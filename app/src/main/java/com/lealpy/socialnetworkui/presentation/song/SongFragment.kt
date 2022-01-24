package com.lealpy.socialnetworkui.presentation.song

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.databinding.FragmentSongBinding
import com.lealpy.socialnetworkui.presentation.favorite_songs.FavoriteSongsFragment.Companion.ARGS_KEY

class SongFragment : Fragment(R.layout.fragment_song) {

    private lateinit var binding : FragmentSongBinding

    private val viewModel : SongViewModel by activityViewModels()

    private val songDialogFragment = SongDialogFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSongBinding.bind(view)
        getArgs()
        initToolbar()
        initObservers()
    }

    private fun getArgs() {
        val id = requireArguments().getString(ARGS_KEY)
        viewModel.onArgumentsReceived(id)
    }

    private fun initObservers() {
        viewModel.title.observe(viewLifecycleOwner) { title ->
            binding.title.text = title
        }

        viewModel.singer.observe(viewLifecycleOwner) { singer ->
            binding.singer.text = singer
        }

        viewModel.text.observe(viewLifecycleOwner) { text ->
            binding.text.text = text
        }

        viewModel.toolbarTitle.observe(viewLifecycleOwner) { toolbarTitle ->
            (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = toolbarTitle
        }

        viewModel.url.observe(viewLifecycleOwner) { url ->
            binding.playButton.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    String.format(getString(R.string.favorite_songs_toast), url),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        viewModel.playButtonVisibility.observe(viewLifecycleOwner) { urlVisibility ->
            binding.playButton.visibility = urlVisibility
        }
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.song_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu,inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
            }
            R.id.songToolbarInfo -> {
                songDialogFragment.show(childFragmentManager, SongDialogFragment.SONG_DIALOG_TAG)
            }
        }
        return true
    }

}