package com.lealpy.socialnetworkui.data.repository

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

class MessageRepositoryExternalStorage @Inject constructor(
    @Named("externalFile") private val externalFile : File?
) : MessageRepository {

    override fun insertMessageToStorage(message : String) : Completable {
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

    override fun getMessageFromStorage() : Single<String> {
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

}
