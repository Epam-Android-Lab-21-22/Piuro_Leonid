package com.lealpy.socialnetworkui.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.lealpy.socialnetworkui.data.database.MessageDao
import com.lealpy.socialnetworkui.data.database.MessageEntity
import com.lealpy.socialnetworkui.domain.repository.MessageRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.Exception
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Named

class MessageRepositoryImpl @Inject constructor(
    private val messageDao: MessageDao,
    private val prefs : SharedPreferences,
    @Named("internalFile") private val internalFile : File,
    @Named("externalFile") private val externalFile : File?
) : MessageRepository {

    /** Database */

    override fun insertMessageToDb(message : String) : Completable {
        val messageEntity = MessageEntity(MESSAGE_ENTITY_ID, message)
        return messageDao.insertMessage(messageEntity)
    }

    override fun getMessageFromDb() : Single<String> {
        return messageDao.getMessage(MESSAGE_ENTITY_ID).map { messageEntity ->
            messageEntity.message
        }
    }

    /** Shared preferences */

    override fun insertMessageToPrefs(message : String) : Completable {
        return Completable.create{ emitter ->
            prefs.edit{
                putString(MESSAGE_PREFS_KEY, message)
            }
            emitter.onComplete()
        }
    }

    override fun getMessageFromPrefs() : Single<String> {
        return Single.create{ emitter ->
            val message = prefs.getString(MESSAGE_PREFS_KEY, MESSAGE_DEFAULT_VALUE) ?: MESSAGE_DEFAULT_VALUE
            emitter.onSuccess(message)
        }
    }

    /** Internal storage */

    override fun insertMessageToInternalStorage(message : String) : Completable {
        return Completable.create{ emitter ->
            FileOutputStream(internalFile).use { fos ->
                fos.write(message.toByteArray())
            }
            emitter.onComplete()
        }
    }

    override fun getMessageFromInternalStorage() : Single<String> {
        return Single.create{ emitter ->
            if(externalFile != null) {
                val message = FileInputStream(internalFile).bufferedReader().use { reader ->
                    reader.readText()
                }
                emitter.onSuccess(message)
            } else {
                emitter.onError(Exception())
            }
        }
    }

    /** External storage */

    override fun insertMessageToExternalStorage(message : String) : Completable {
        return Completable.create{ emitter ->
            if(externalFile != null) {
                FileOutputStream(externalFile).use { fos ->
                    fos.write(message.toByteArray())
                }
                emitter.onComplete()
            } else {
                emitter.onError(IllegalArgumentException())
            }
        }
    }

    override fun getMessageFromExternalStorage() : Single<String> {
        return Single.create{ emitter ->
            if(externalFile != null) {
                val message = FileInputStream(externalFile).bufferedReader().use { reader ->
                    reader.readText()
                }
                emitter.onSuccess(message)
            } else {
                emitter.onError(Exception())
            }
        }
    }

    companion object {
        private const val MESSAGE_ENTITY_ID = 0L
        private const val MESSAGE_PREFS_KEY = "MESSAGE_PREFS_KEY"
        private const val MESSAGE_DEFAULT_VALUE = ""
    }

}
