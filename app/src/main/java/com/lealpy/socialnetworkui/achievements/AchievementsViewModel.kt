package com.lealpy.socialnetworkui.achievements

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.socialnetworkui.R

class AchievementsViewModel(application: Application) : AndroidViewModel(application) {

    private val idList = mutableListOf<Long>()

    private val initialAchievementsList = listOf(

        Achievement.SeasonItem(getId(), application.resources.getString(R.string.season_2018_19)),

        Achievement.TeamItem(getId(), application.resources.getString(R.string.man_city), R.drawable.man_city),
        Achievement.TrophyItem(getId(), application.resources.getString(R.string.english_premier_league), R.drawable.english_premiere_league),
        Achievement.TrophyItem(getId(), application.resources.getString(R.string.english_cup), R.drawable.english_cup),
        Achievement.TrophyItem(getId(), application.resources.getString(R.string.english_league_cup), R.drawable.english_league_cup),
        Achievement.TrophyItem(getId(), application.resources.getString(R.string.english_supercup), R.drawable.english_supercup),

        Achievement.TeamItem(getId(), application.resources.getString(R.string.liverpool), R.drawable.liverpool),
        Achievement.TrophyItem(getId(), application.resources.getString(R.string.champions_league), R.drawable.champions_league),

        Achievement.SeasonItem(getId(), application.resources.getString(R.string.season_2019_20)),

        Achievement.TeamItem(getId(), application.resources.getString(R.string.man_city), R.drawable.man_city),
        Achievement.TrophyItem(getId(), application.resources.getString(R.string.english_league_cup), R.drawable.english_league_cup),
        Achievement.TrophyItem(getId(), application.resources.getString(R.string.english_supercup), R.drawable.english_supercup),

        Achievement.TeamItem(getId(), application.resources.getString(R.string.liverpool), R.drawable.liverpool),
        Achievement.TrophyItem(getId(), application.resources.getString(R.string.english_premier_league), R.drawable.english_premiere_league),

        Achievement.SeasonItem(getId(), application.resources.getString(R.string.season_2020_21)),

        Achievement.TeamItem(getId(), application.resources.getString(R.string.chelsea), R.drawable.chelsea),
        Achievement.TrophyItem(getId(), application.resources.getString(R.string.champions_league), R.drawable.champions_league),

        Achievement.TeamItem(getId(), application.resources.getString(R.string.man_city), R.drawable.man_city),
        Achievement.TrophyItem(getId(), application.resources.getString(R.string.english_premier_league), R.drawable.english_premiere_league),
        Achievement.TrophyItem(getId(), application.resources.getString(R.string.english_league_cup), R.drawable.english_league_cup),

    )

    private val _achievements = MutableLiveData (initialAchievementsList)
    val achievements : LiveData<List<Achievement>> = _achievements

    private fun getId() : Long {
        var id : Long = 0
        if(idList.isNotEmpty()) {
            id = idList[idList.lastIndex] + 1
            while (idList.contains(id)) id++
        }
        idList.add(id)
        return id
    }

    fun onDeleteItemClicked(trophyItem: Achievement.TrophyItem) {
        val achievementsList = mutableListOf<Achievement>()

        _achievements.value?.let { achievements ->
            achievementsList.addAll(achievements)
        }

        achievementsList.remove(trophyItem)

        _achievements.value = achievementsList
    }

}
