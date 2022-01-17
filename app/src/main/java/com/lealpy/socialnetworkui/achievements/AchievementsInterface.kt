package com.lealpy.socialnetworkui.achievements

import android.os.Bundle
import android.os.Parcelable
import com.lealpy.socialnetworkui.achievements.model.Achievement
import java.util.ArrayList

interface AchievementsInterface {

    interface AchievementsView {
        fun showAchievements(achievements : List<Achievement>)
        fun showProgress()
        fun hideProgress()
    }

    interface AchievementsPresenter {
        fun viewIsReady(savedState: Bundle?)
        fun detachView()
        fun onDeleteItemClicked(achievement : Achievement)
        fun onViewAsksOutState(): ArrayList<out Parcelable>?
    }

    interface AchievementsModel {
        fun getAchievements() : List<Achievement>
        fun setAchievements(achievements : List<Achievement>)
        fun removeAchievement(achievement : Achievement)
    }

}
