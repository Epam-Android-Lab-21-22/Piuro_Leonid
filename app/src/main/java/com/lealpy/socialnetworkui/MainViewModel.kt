package com.lealpy.socialnetworkui

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class MainViewModel (application : Application) : AndroidViewModel(application) {

    private val _prediction = MutableLiveData<String>()
    val prediction : LiveData<String> = _prediction

    private val _predictionVisibility = MutableLiveData(View.INVISIBLE)
    val predictionVisibility : LiveData<Int> = _predictionVisibility

    private var clickCounter = 0

    private val _clickCounterText = MutableLiveData(
        getApplication<Application>().resources.getString(R.string.got_predictions) + " 0"
    )
    val clickCounterText : LiveData<String> = _clickCounterText

    private val firstPhrase = getApplication<Application>()
        .resources
        .getStringArray(R.array.firstPhrase)
        .toList()

    private val secondPhrase = getApplication<Application>()
        .resources
        .getStringArray(R.array.secondPhrase)
        .toList()

    fun onBtnClicked() {
        _predictionVisibility.value = View.VISIBLE
        clickCounter ++
        _clickCounterText.value = getApplication<Application>()
            .resources.getString(R.string.got_predictions) + " " + clickCounter
        generatePhrase()
    }

    private fun generatePhrase() {
        val random = Random()
        val indexOfFirstPhrase = random.nextInt(firstPhrase.lastIndex + 1)
        val indexOfSecondPhrase = random.nextInt(secondPhrase.lastIndex + 1)
        _prediction.value = firstPhrase[indexOfFirstPhrase] + " " + secondPhrase[indexOfSecondPhrase]
    }

}