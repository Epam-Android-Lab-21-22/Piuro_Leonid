package com.lealpy.socialnetworkui.domain.repository

import com.lealpy.socialnetworkui.domain.model.SongPreview

interface FavoriteSongsRepository {
    fun getSongPreviewList() : List<SongPreview>
}