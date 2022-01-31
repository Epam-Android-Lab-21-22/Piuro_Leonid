package com.lealpy.socialnetworkui.domain.repository

import io.reactivex.Completable
import io.reactivex.Single

interface MessageRepository {
    fun insertMessageToDb(message : String) : Completable
    fun insertMessageToPrefs(message : String) : Completable
    fun insertMessageToInternalStorage(message : String) : Completable
    fun insertMessageToExternalStorage(message : String) : Completable
    fun getMessageFromDb() : Single<String>
    fun getMessageFromPrefs() : Single<String>
    fun getMessageFromExternalStorage() : Single<String>
    fun getMessageFromInternalStorage() : Single<String>
}
