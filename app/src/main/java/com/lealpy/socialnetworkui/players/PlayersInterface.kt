package com.lealpy.socialnetworkui.players

import android.os.Bundle
import android.os.Parcelable
import com.lealpy.socialnetworkui.players.model.Player
import java.util.ArrayList

interface PlayersInterface {

    interface PlayerView {
        fun showPlayers(players : List<Player>)
        fun showProgress()
        fun hideProgress()
    }

    interface PlayerPresenter {
        fun viewIsReady(savedState: Bundle?)
        fun detachView()
        fun onAddButtonClicked()
        fun onViewAsksOutState(): ArrayList<out Parcelable>?
    }

    interface PlayerModel {
        fun getPlayers(): List<Player>
        fun setPlayers(players: List<Player>)
        fun addRandomPlayer()
    }

}
