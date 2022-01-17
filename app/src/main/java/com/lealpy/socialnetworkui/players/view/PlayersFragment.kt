package com.lealpy.socialnetworkui.players.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.databinding.FragmentPlayersBinding
import com.lealpy.socialnetworkui.players.PlayersInterface
import com.lealpy.socialnetworkui.players.presenter.PlayersPresenter
import com.lealpy.socialnetworkui.players.model.Player
import com.lealpy.socialnetworkui.players.model.PlayersModel

class PlayersFragment : Fragment(R.layout.fragment_players), PlayersInterface.PlayerView {

    private lateinit var binding : FragmentPlayersBinding
    private var presenter : PlayersPresenter? = null
    private val adapter = PlayerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayersBinding.bind(view)
        presenter = PlayersPresenter(this, PlayersModel(requireContext()))
        initViews(savedInstanceState)
    }

    override fun onDestroyView() {
        presenter?.detachView()
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(presenter != null) {
            outState.putParcelableArrayList(PLAYERS_KEY, presenter?.onViewAsksOutState())
        }
    }

    private fun initViews(savedInstanceState : Bundle?) {
        binding.addPlayerButton.setOnClickListener {
            presenter?.onAddButtonClicked()
        }

        binding.playersRecyclerView.adapter = adapter

        val playerItemDecoration = activity?.resources?.getDimension(R.dimen.dimen_8_dp)?.let { space ->
            PlayerItemDecoration(SPAN_COUNT, space.toInt())
        }

        if(playerItemDecoration != null) {
            binding.playersRecyclerView.addItemDecoration(playerItemDecoration)
        }

        presenter?.viewIsReady(savedInstanceState)
    }

    override fun showPlayers(players : List<Player>) {
        adapter.submitList(players)
    }

    override fun showProgress() {
        binding.playersRecyclerView.visibility = View.GONE
        binding.playersProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.playersRecyclerView.visibility = View.VISIBLE
        binding.playersProgressBar.visibility = View.GONE
    }

    companion object {
        private const val SPAN_COUNT = 3
        const val PLAYERS_KEY = "PLAYERS_KEY"
    }

}
