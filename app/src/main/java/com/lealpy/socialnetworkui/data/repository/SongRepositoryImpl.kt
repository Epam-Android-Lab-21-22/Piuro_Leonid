package com.lealpy.socialnetworkui.data.repository

import com.lealpy.socialnetworkui.data.SongsList
import com.lealpy.socialnetworkui.domain.repository.SongRepository
import com.lealpy.socialnetworkui.domain.model.Song

class SongRepositoryImpl : SongRepository {
    override fun getSongById(id : String) : Song? {
        return SongsList.songsList.find { it.id == id }
    }
}