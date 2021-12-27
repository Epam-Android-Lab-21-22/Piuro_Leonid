package com.lealpy.socialnetworkui.players

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.socialnetworkui.R
import io.github.serpro69.kfaker.Faker

class PlayersViewModel(application: Application) : AndroidViewModel(application) {

    private val faker = Faker()

    private val idList = mutableListOf<Long>()

    private val initialPlayerList = listOf(
        Player(getId(), application.resources.getString(R.string.michael_jordan), R.drawable.michael_jordan),
        Player(getId(), application.resources.getString(R.string.kevin_durant), R.drawable.kevin_durant),
        Player(getId(), application.resources.getString(R.string.lebron_james), R.drawable.lebron_james),
        Player(getId(), application.resources.getString(R.string.magic_johnson), R.drawable.magic_johnson),
        Player(getId(), application.resources.getString(R.string.shaquille_o_neal), R.drawable.shaquille_o_neal),
        Player(getId(), application.resources.getString(R.string.kevin_garnett), R.drawable.kevin_garnett)
    )

    private val _players = MutableLiveData (initialPlayerList)
    val players : LiveData<List<Player>> = _players

    fun onAddButtonClicked() {
        addRandomPlayer()
    }

    private fun addRandomPlayer() {

        val playersList = mutableListOf<Player>()

        _players.value?.let { players ->
            playersList.addAll(players)
        }

        val playerNames = mutableListOf<String>()

        playersList.forEach { player ->
            playerNames.add(player.name)
        }

        var playerName = faker.basketball.players()

        while(playerNames.contains(playerName)) playerName = faker.basketball.players()

        playersList.add( Player(getId(), playerName, R.drawable.player_no_photo) )

        _players.value = playersList
    }

    private fun getId() : Long {
        var id : Long = 0
        if(idList.isNotEmpty()) {
            id = idList[idList.lastIndex] + 1
            while (idList.contains(id)) id++
        }
        idList.add(id)
        return id
    }

}
