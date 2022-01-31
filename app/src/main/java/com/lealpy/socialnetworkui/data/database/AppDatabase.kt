package com.lealpy.socialnetworkui.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        MessageEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao() : MessageDao
}
