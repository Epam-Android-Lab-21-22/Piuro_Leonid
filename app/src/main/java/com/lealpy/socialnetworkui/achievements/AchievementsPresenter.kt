package com.lealpy.socialnetworkui.achievements

import android.os.Bundle
import android.os.Parcelable
import com.lealpy.socialnetworkui.achievements.AchievementsFragment.Companion.ACHIEVEMENTS_KEY
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception
import java.util.ArrayList

class AchievementsPresenter(
    private var view: AchievementsInterface.AchievementsView?,
    private val model: AchievementsModel
    ) : AchievementsInterface.AchievementsPresenter {

    private var achievements : List<Achievement>? = null

    override fun viewIsReady(savedState: Bundle?) {
        if(savedState == null) {
            loadAchievements()
        } else {
            val achievements = savedState.getParcelableArrayList<Achievement>(ACHIEVEMENTS_KEY)?.toList()
            if(achievements != null) {
                this.achievements = achievements
                model.setAchievements(achievements)
                view?.showAchievements(achievements)
            } else {
                loadAchievements()
            }
        }
    }

    override fun detachView() {
        view = null
    }

    override fun onDeleteItemClicked(achievement : Achievement) {
        model.removeAchievement(achievement)
        loadAchievements()
    }

    override fun onViewAsksOutState(): ArrayList<out Parcelable>? {
        return achievements as? ArrayList<Parcelable>
    }

    private fun loadAchievements() {
        view?.showProgress()

        Observable.create<List<Achievement>> { emitter ->
            Thread.sleep(THREAD_SLEEP_TIME)
            val achievements = model.getAchievements()
            if (!emitter.isDisposed) {
                emitter.onNext(achievements)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { achievements ->
                    this.achievements = achievements
                    view?.showAchievements(achievements)
                    view?.hideProgress()
                },
                { error ->
                    throw Exception(error.message)
                }
            )
    }

    companion object {
        private const val THREAD_SLEEP_TIME : Long = 2000
    }

}
