package com.lealpy.socialnetworkui.data.repository

import com.lealpy.socialnetworkui.data.file_providers.ExternalStorageFileProvider
import com.lealpy.socialnetworkui.domain.repository.MessageRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.Exception
import java.lang.IllegalArgumentException
import javax.inject.Inject

class MessageRepositoryExternalStorage @Inject constructor(
    fileProvider : ExternalStorageFileProvider
) : MessageRepository {

    private val externalFile = fileProvider.getFile()

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
