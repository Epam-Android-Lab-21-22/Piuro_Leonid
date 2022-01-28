package com.lealpy.socialnetworkui.domain.use_cases

import com.lealpy.socialnetworkui.domain.repository.MessageRepository
import io.reactivex.Completable
import javax.inject.Inject

class InsertMessageToDbUseCase @Inject constructor(
    private val repository: MessageRepository
) {

    fun execute(message : String) : Completable {
        return repository.insertMessageToDb(message)
    }

}