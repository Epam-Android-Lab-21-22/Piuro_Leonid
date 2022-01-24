package com.lealpy.socialnetworkui.domain.use_cases

import com.lealpy.socialnetworkui.domain.model.Song
import com.lealpy.socialnetworkui.domain.repository.SongRepository
import javax.inject.Inject

class GetSongUseCase @Inject constructor(
    private val repository: SongRepository
) {
    fun execute(id : String) : Song? {
        return repository.getSongById(id)
    }
}
