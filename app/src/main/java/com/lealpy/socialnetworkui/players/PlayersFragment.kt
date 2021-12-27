package com.lealpy.socialnetworkui.players

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.databinding.FragmentPlayersBinding

class PlayersFragment : Fragment(R.layout.fragment_players) {

    private lateinit var binding : FragmentPlayersBinding
    private val viewModel : PlayersViewModel by activityViewModels()
    private val playerAdapter = PlayerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayersBinding.bind(view)
        initObservers()
        initViews()
    }

    private fun initObservers() {
        viewModel.players.observe(viewLifecycleOwner) { players ->
            playerAdapter.submitList(players)
        }
    }

    private fun initViews() {
        binding.addPlayerButton.setOnClickListener {
            viewModel.onAddButtonClicked()
        }

        binding.playersRecyclerView.adapter = playerAdapter

        val playerItemDecoration = activity?.resources?.getDimension(R.dimen.dimen_8_dp)?.let { space ->
            PlayerItemDecoration(SPAN_COUNT, space.toInt())
        }

        if(playerItemDecoration != null) {
            binding.playersRecyclerView.addItemDecoration(playerItemDecoration)
        }

    }
    companion object {
        private const val SPAN_COUNT = 3
    }

}
