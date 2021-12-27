package com.lealpy.socialnetworkui.achievements

sealed class Achievement (val achievementId : Long) {

    data class SeasonItem (
        val id : Long,
        val name : String
    ) : Achievement(id)

    data class TeamItem (
        val id : Long,
        val name : String,
        val image : Int
    ) : Achievement(id)

    data class TrophyItem (
        val id : Long,
        val name : String,
        val image : Int
    ) : Achievement(id)

}