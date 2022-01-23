package com.lealpy.socialnetworkui.presentation.favorite_songs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lealpy.socialnetworkui.domain.model.SongPreview
import com.lealpy.socialnetworkui.domain.use_cases.GetFavoriteSongsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteSongsViewModel @Inject constructor(
    getFavoriteSongsUseCase: GetFavoriteSongsUseCase
) : ViewModel() {

    private val _favoriteSongs = MutableLiveData(getFavoriteSongsUseCase.execute())
    val favoriteSongs : LiveData<List<SongPreview>> = _favoriteSongs

}
