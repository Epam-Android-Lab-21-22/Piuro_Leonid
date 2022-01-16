package com.lealpy.socialnetworkui.players

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player (
    val id : Long,
    val name : String,
    val photo : Int
) : Parcelable
