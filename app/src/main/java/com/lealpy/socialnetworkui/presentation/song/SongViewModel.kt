package com.lealpy.socialnetworkui.presentation.song

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.domain.use_cases.GetSongUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    application: Application,
    private val getSongUseCase: GetSongUseCase
) : AndroidViewModel(application) {

    private val _title = MutableLiveData<String>()
    val title : LiveData<String> = _title

    private val _singer = MutableLiveData<String>()
    val singer : LiveData<String> = _singer

    private val _text = MutableLiveData<String>()
    val text : LiveData<String> = _text

    private val _url = MutableLiveData<String>()
    val url : LiveData<String> = _url

    private val _playButtonVisibility = MutableLiveData<Int>()
    val playButtonVisibility : LiveData<Int> = _playButtonVisibility

    private val _toolbarTitle = MutableLiveData<String>()
    val toolbarTitle : LiveData<String> = _toolbarTitle

    val titleInDialog : LiveData<String> = Transformations.map(title) { title ->
        String.format(getApplication<Application>().resources.getString(R.string.song_dialog_title), title)
    }

    val singerInDialog : LiveData<String> = Transformations.map(singer) { singer ->
        String.format(getApplication<Application>().resources.getString(R.string.song_dialog_singer), singer)
    }

    private val _albumInDialog = MutableLiveData<String>()
    val albumInDialog : LiveData<String> = _albumInDialog

    private val _yearInDialog = MutableLiveData<String>()
    val yearInDialog : LiveData<String> = _yearInDialog

    private val _genreInDialog = MutableLiveData<String>()
    val genreInDialog : LiveData<String> = _genreInDialog

    fun onArgumentsReceived(id: String?) {
        if(id != null) {
            val song = getSongUseCase.execute(id)

            _toolbarTitle.value = "${song?.singer} - ${song?.title}"
            _title.value = song?.title
            _singer.value = song?.singer
            _text.value = song?.text
            if(song?.url != null) {
                _url.value = song.url
                _playButtonVisibility.value = View.VISIBLE
            } else {
                _playButtonVisibility.value = View.INVISIBLE
            }

            _albumInDialog.value = String.format(getApplication<Application>().resources.getString(R.string.song_dialog_album), song?.album)
            _yearInDialog.value = String.format(getApplication<Application>().resources.getString(R.string.song_dialog_year), song?.year)
            _genreInDialog.value = String.format(getApplication<Application>().resources.getString(R.string.song_dialog_genre), song?.genre)
        }
    }

}
