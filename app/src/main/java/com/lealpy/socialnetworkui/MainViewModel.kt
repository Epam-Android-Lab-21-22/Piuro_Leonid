package com.lealpy.socialnetworkui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _clickCounter = MutableLiveData(0)
    val clickCounter : LiveData<Int> = _clickCounter

    private val _countersProgress = MutableLiveData(0)
    val countersProgress : LiveData<Int> = _countersProgress

    private val _symbolsCounter = MutableLiveData(0)
    val symbolsCounter : LiveData<Int> = _symbolsCounter

    private val _symbolsProgress = MutableLiveData(0)
    val symbolsProgress : LiveData<Int> = _symbolsProgress

    fun onClicked() {
        var clickCounter = _clickCounter.value ?: 0

        if (clickCounter < CLICKS_MAX_VALUE - 1) {
            clickCounter ++
        }
        else {
            clickCounter = 0
        }

        _clickCounter.value = clickCounter
        _countersProgress.value = clickCounter * 100 / CLICKS_MAX_VALUE
    }

    fun onSymbolEntered(length: Int) {
        _symbolsCounter.value = length
        _symbolsProgress.value = length * 100 / SYMBOLS_MAX_LENGTH
    }

    companion object {
        private const val SYMBOLS_MAX_LENGTH = 140
        private const val CLICKS_MAX_VALUE = 100
    }

}
