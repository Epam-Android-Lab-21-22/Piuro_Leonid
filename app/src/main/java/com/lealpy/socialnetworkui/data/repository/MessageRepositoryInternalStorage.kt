package com.lealpy.socialnetworkui.data.repository

import com.lealpy.socialnetworkui.data.file_providers.InternalStorageFileProvider
import com.lealpy.socialnetworkui.domain.repository.MessageRepository
import io.reactivex.Completable
import io.reactivex.Single
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject

class MessageRepositoryInternalStorage @Inject constructor(
    fileProvider : InternalStorageFileProvider
) : MessageRepository {

    private val internalFile = fileProvider.getFile()

    override fun insertMessageToStorage(message : String) : Completable {
        return Completable.create{ emitter ->
            FileOutputStream(internalFile).use { fos ->
                fos.write(message.toByteArray())
            }
            emitter.onComplete()
        }
    }

    override fun getMessageFromStorage() : Single<String> {
        return Single.create{ emitter ->
            val message = FileInputStream(internalFile).bufferedReader().use { reader ->
                reader.readText()
            }
            emitter.onSuccess(message)
        }
    }

}
