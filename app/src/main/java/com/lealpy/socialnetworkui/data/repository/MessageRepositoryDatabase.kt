package com.lealpy.socialnetworkui.data.repository

import com.lealpy.socialnetworkui.data.database.MessageDao
import com.lealpy.socialnetworkui.data.database.MessageEntity
import com.lealpy.socialnetworkui.domain.repository.MessageRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class MessageRepositoryDatabase @Inject constructor(
    private val messageDao: MessageDao
) : MessageRepository {

    override fun insertMessageToStorage(message : String) : Completable {
        val messageEntity = MessageEntity(MESSAGE_ENTITY_ID, message)
        return messageDao.insertMessage(messageEntity)
    }

    override fun getMessageFromStorage() : Single<String> {
        return messageDao.getMessage(MESSAGE_ENTITY_ID).map { messageEntity ->
            messageEntity.message
        }
    }

    companion object {
        private const val MESSAGE_ENTITY_ID = 0L
    }

}
