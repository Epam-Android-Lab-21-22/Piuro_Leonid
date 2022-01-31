package com.lealpy.socialnetworkui.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Message_entities")
data class MessageEntity (
    @PrimaryKey
    val id : Long,
    val message : String
)
