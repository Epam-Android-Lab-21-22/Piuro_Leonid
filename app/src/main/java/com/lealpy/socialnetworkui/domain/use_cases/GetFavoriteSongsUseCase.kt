package com.lealpy.socialnetworkui.domain.use_cases

import com.lealpy.socialnetworkui.domain.model.SongPreview
import com.lealpy.socialnetworkui.domain.repository.FavoriteSongsRepository
import javax.inject.Inject

class GetFavoriteSongsUseCase @Inject constructor(
    private val repository: FavoriteSongsRepository
) {
    fun execute() : List<SongPreview> {
        return repository.getSongPreviewList()
    }
}
