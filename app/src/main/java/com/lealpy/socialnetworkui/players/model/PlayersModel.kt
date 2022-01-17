package com.lealpy.socialnetworkui.players.model

import android.content.Context
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.players.PlayersInterface
import io.github.serpro69.kfaker.Faker

class PlayersModel(context : Context) : PlayersInterface.PlayerModel {

    private val faker = Faker()

    private val idList = mutableListOf<Long>()

    private var playersList = mutableListOf(
        Player(getId(), context.resources.getString(R.string.michael_jordan), R.drawable.michael_jordan),
        Player(getId(), context.resources.getString(R.string.kevin_durant), R.drawable.kevin_durant),
        Player(getId(), context.resources.getString(R.string.lebron_james), R.drawable.lebron_james),
        Player(getId(), context.resources.getString(R.string.magic_johnson), R.drawable.magic_johnson),
        Player(getId(), context.resources.getString(R.string.shaquille_o_neal), R.drawable.shaquille_o_neal),
        Player(getId(), context.resources.getString(R.string.kevin_garnett), R.drawable.kevin_garnett)
    )

    private fun getId() : Long {
        var id : Long = 0
        if(idList.isNotEmpty()) {
            id = idList[idList.lastIndex] + 1
            while (idList.contains(id)) id++
        }
        idList.add(id)
        return id
    }

    override fun addRandomPlayer() {
        val playerNames = playersList.map { it.name }
        var playerName = faker.basketball.players()
        while(playerNames.contains(playerName)) playerName = faker.basketball.players()
        playersList.add( Player(getId(), playerName, R.drawable.player_no_photo) )
    }

    override fun setPlayers(players: List<Player>) {
        playersList = players.toMutableList()
    }

    override fun getPlayers(): List<Player> {
        return playersList
    }
}
