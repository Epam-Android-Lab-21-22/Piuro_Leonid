package com.lealpy.socialnetworkui.achievements.model

import android.content.Context
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.achievements.AchievementsInterface

class AchievementsModel(context : Context) : AchievementsInterface.AchievementsModel {

    private val idList = mutableListOf<Long>()

    private var achievementsList = mutableListOf(
        Achievement.SeasonItem(getId(), context.resources.getString(R.string.season_2018_19)),

        Achievement.TeamItem(getId(),
            context.resources.getString(R.string.man_city),
            R.drawable.man_city),
        Achievement.TrophyItem(getId(),
            context.resources.getString(R.string.english_premier_league),
            R.drawable.english_premiere_league),
        Achievement.TrophyItem(getId(),
            context.resources.getString(R.string.english_cup),
            R.drawable.english_cup),
        Achievement.TrophyItem(getId(),
            context.resources.getString(R.string.english_league_cup),
            R.drawable.english_league_cup),
        Achievement.TrophyItem(getId(),
            context.resources.getString(R.string.english_supercup),
            R.drawable.english_supercup),

        Achievement.TeamItem(getId(),
            context.resources.getString(R.string.liverpool),
            R.drawable.liverpool),
        Achievement.TrophyItem(getId(),
            context.resources.getString(R.string.champions_league),
            R.drawable.champions_league),

        Achievement.SeasonItem(getId(), context.resources.getString(R.string.season_2019_20)),

        Achievement.TeamItem(getId(),
            context.resources.getString(R.string.man_city),
            R.drawable.man_city),
        Achievement.TrophyItem(getId(),
            context.resources.getString(R.string.english_league_cup),
            R.drawable.english_league_cup),
        Achievement.TrophyItem(getId(),
            context.resources.getString(R.string.english_supercup),
            R.drawable.english_supercup),

        Achievement.TeamItem(getId(),
            context.resources.getString(R.string.liverpool),
            R.drawable.liverpool),
        Achievement.TrophyItem(getId(),
            context.resources.getString(R.string.english_premier_league),
            R.drawable.english_premiere_league),

        Achievement.SeasonItem(getId(), context.resources.getString(R.string.season_2020_21)),

        Achievement.TeamItem(getId(),
            context.resources.getString(R.string.chelsea),
            R.drawable.chelsea),
        Achievement.TrophyItem(getId(),
            context.resources.getString(R.string.champions_league),
            R.drawable.champions_league),

        Achievement.TeamItem(getId(),
            context.resources.getString(R.string.man_city),
            R.drawable.man_city),
        Achievement.TrophyItem(getId(),
            context.resources.getString(R.string.english_premier_league),
            R.drawable.english_premiere_league),
        Achievement.TrophyItem(getId(),
            context.resources.getString(R.string.english_league_cup),
            R.drawable.english_league_cup),
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

    override fun setAchievements(achievements : List<Achievement>) {
        achievementsList = achievements.toMutableList()
    }

    override fun getAchievements() : List<Achievement> {
        return achievementsList
    }

    override fun removeAchievement(achievement : Achievement) {
        val achievements = getAchievements().toMutableList()
        achievements.remove(achievement)
        setAchievements(achievements)
    }

}
