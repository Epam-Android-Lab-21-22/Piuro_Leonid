package com.lealpy.socialnetworkui.players.presenter

import android.os.Bundle
import android.os.Parcelable
import com.lealpy.socialnetworkui.players.PlayersInterface
import com.lealpy.socialnetworkui.players.model.Player
import com.lealpy.socialnetworkui.players.model.PlayersModel
import com.lealpy.socialnetworkui.players.view.PlayersFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception
import java.util.ArrayList

class PlayersPresenter(
    private var view: PlayersInterface.PlayerView?,
    private val model: PlayersModel
) : PlayersInterface.PlayerPresenter {

    private var players : List<Player>? = null

    override fun viewIsReady(savedState: Bundle?) {
        if(savedState == null) {
            loadAchievements()
        } else {
            val players = savedState.getParcelableArrayList<Player>(PlayersFragment.PLAYERS_KEY)?.toList()
            if(players != null) {
                this.players = players
                model.setPlayers(players)
                view?.showPlayers(players)
            } else {
                loadAchievements()
            }
        }
    }

    override fun detachView() {
        view = null
    }

    override fun onAddButtonClicked() {
        model.addRandomPlayer()
        loadAchievements()
    }

    override fun onViewAsksOutState(): ArrayList<out Parcelable>? {
        return players as? ArrayList<Parcelable>
    }

    private fun loadAchievements() {
        view?.showProgress()

        Observable.create<List<Player>> { emitter ->
            Thread.sleep(THREAD_SLEEP_TIME)
            val players = model.getPlayers()
            if (!emitter.isDisposed) {
                emitter.onNext(players)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { players ->
                    this.players = players
                    view?.showPlayers(players)
                    view?.hideProgress()
                },
                { error ->
                    throw Exception(error.message)
                }
            )
    }

    companion object {
        private const val THREAD_SLEEP_TIME : Long = 1000
    }

}
