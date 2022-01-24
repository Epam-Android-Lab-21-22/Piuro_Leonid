package com.lealpy.socialnetworkui.data.repository

import com.lealpy.socialnetworkui.data.SongsList
import com.lealpy.socialnetworkui.domain.repository.FavoriteSongsRepository
import com.lealpy.socialnetworkui.domain.model.SongPreview

class FavoriteSongsRepositoryImpl : FavoriteSongsRepository {
    override fun getSongPreviewList() : List<SongPreview> {
        return SongsList.songsList.map { song ->
            SongPreview(
                id = song.id,
                title = song.title,
                singer = song.singer,
                url = song.url
            )
        }
    }
}