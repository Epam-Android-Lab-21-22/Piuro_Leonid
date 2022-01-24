package com.lealpy.socialnetworkui.domain.repository

import com.lealpy.socialnetworkui.domain.model.Song

interface SongRepository {
    fun getSongById(id : String) : Song?
}