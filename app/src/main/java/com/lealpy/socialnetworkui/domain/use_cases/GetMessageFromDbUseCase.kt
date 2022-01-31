package com.lealpy.socialnetworkui.domain.use_cases

import com.lealpy.socialnetworkui.domain.repository.MessageRepository
import io.reactivex.Single
import javax.inject.Inject

class GetMessageFromDbUseCase @Inject constructor(
    private val repository: MessageRepository
) {

    fun execute() : Single<String> {
        return repository.getMessageFromDb()
    }

}
