package com.lealpy.socialnetworkui.domain.repository

import io.reactivex.Completable
import io.reactivex.Single

interface MessageRepository {
    fun insertMessageToStorage(message : String) : Completable
    fun getMessageFromStorage() : Single<String>
}
