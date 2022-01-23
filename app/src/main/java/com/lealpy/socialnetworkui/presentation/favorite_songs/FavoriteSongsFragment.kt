package com.lealpy.socialnetworkui.presentation.favorite_songs

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.domain.model.SongPreview
import com.lealpy.socialnetworkui.databinding.FragmentFavoriteSongsBinding

class FavoriteSongsFragment : Fragment(R.layout.fragment_favorite_songs) {

    private lateinit var binding : FragmentFavoriteSongsBinding

    private val viewModel : FavoriteSongsViewModel by activityViewModels()

    private val songAdapter = SongItemAdapter(
        object : SongItemAdapter.OnItemClickListener {
            override fun onItemClick(songItem: SongPreview) {
                val args = Bundle()
                args.putString(ARGS_KEY, songItem.id)

                findNavController().navigate(
                    R.id.actionFavoriteSongsFragmentToSongFragment,
                    args
                )
            }
        },
        object : SongItemAdapter.OnPlayButtonClickListener {
            override fun onplayButtonClick(url: String) {
                Toast.makeText(
                    requireContext(),
                    String.format(getString(R.string.favorite_songs_toast), url),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteSongsBinding.bind(view)
        initToolbar()
        initViews()
        initObservers()
    }

    private fun initObservers() {
        viewModel.favoriteSongs.observe(viewLifecycleOwner) { favoriteSongs ->
            songAdapter.submitList(favoriteSongs)
        }
    }

    private fun initViews() {
        binding.recyclerView.adapter = songAdapter

        val songItemDivider = requireContext().getDrawable(R.drawable.recycler_view_divider)?.let { drawable ->
            SongItemDecoration(
                drawable,
            )
        }

        if (songItemDivider != null) {
            binding.recyclerView.addItemDecoration(songItemDivider)
        }
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        val appCompatActivity = (requireActivity() as? AppCompatActivity)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        appCompatActivity?.supportActionBar?.title = getString(R.string.favorite_songs_toolbar_title)
    }

    companion object {
        const val ARGS_KEY = "SONG_ARGS_KEY"
    }

}