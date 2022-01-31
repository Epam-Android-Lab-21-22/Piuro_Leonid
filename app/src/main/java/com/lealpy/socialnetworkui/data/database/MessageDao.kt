package com.lealpy.socialnetworkui.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(messageEntity: MessageEntity) : Completable

    @Query("SELECT * FROM Message_entities WHERE id = :id")
    fun getMessage(id : Long) : Single<MessageEntity>

}
