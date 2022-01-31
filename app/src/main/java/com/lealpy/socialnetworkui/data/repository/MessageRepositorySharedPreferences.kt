package com.lealpy.socialnetworkui.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.lealpy.socialnetworkui.domain.repository.MessageRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class MessageRepositorySharedPreferences @Inject constructor(
    private val prefs : SharedPreferences
) : MessageRepository {

    override fun insertMessageToStorage(message : String) : Completable {
        return Completable.create{ emitter ->
            prefs.edit{
                putString(MESSAGE_PREFS_KEY, message)
            }
            emitter.onComplete()
        }
    }

    override fun getMessageFromStorage() : Single<String> {
        return Single.create{ emitter ->
            val message = prefs.getString(MESSAGE_PREFS_KEY, MESSAGE_DEFAULT_VALUE) ?: MESSAGE_DEFAULT_VALUE
            emitter.onSuccess(message)
        }
    }

    companion object {
        private const val MESSAGE_PREFS_KEY = "MESSAGE_PREFS_KEY"
        private const val MESSAGE_DEFAULT_VALUE = ""
    }

}
