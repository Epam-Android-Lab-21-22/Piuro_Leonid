package com.lealpy.socialnetworkui.achievements.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Achievement (val achievementId : Long) : Parcelable {

    @Parcelize
    data class SeasonItem (
        val id : Long,
        val name : String
    ) : Achievement(id)

    @Parcelize
    data class TeamItem (
        val id : Long,
        val name : String,
        val image : Int
    ) : Achievement(id)

    @Parcelize
    data class TrophyItem (
        val id : Long,
        val name : String,
        val image : Int
    ) : Achievement(id)

}
