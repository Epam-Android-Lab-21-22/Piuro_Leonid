package com.lealpy.socialnetworkui.presentation.message

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lealpy.socialnetworkui.R
import com.lealpy.socialnetworkui.domain.use_cases.*
import com.lealpy.socialnetworkui.presentation.message.MessageFragment.Companion.DATABASE_POSITION
import com.lealpy.socialnetworkui.presentation.message.MessageFragment.Companion.EXTERNAL_STORAGE_POSITION
import com.lealpy.socialnetworkui.presentation.message.MessageFragment.Companion.INTERNAL_STORAGE_POSITION
import com.lealpy.socialnetworkui.presentation.message.MessageFragment.Companion.PREFS_POSITION
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    application: Application,
    private val insertMessageToDbUseCase: InsertMessageToDbUseCase,
    private val getMessageFromDbUseCase: GetMessageFromDbUseCase,
    private val insertMessageToPrefsUseCase: InsertMessageToPrefsUseCase,
    private val getMessageFromPrefsUseCase: GetMessageFromPrefsUseCase,
    private val insertMessageToInternalStorageUseCase: InsertMessageToInternalStorageUseCase,
    private val getMessageFromInternalStorageUseCase: GetMessageFromInternalStorageUseCase,
    private val insertMessageToExternalStorageUseCase: InsertMessageToExternalStorageUseCase,
    private val getMessageFromExternalStorageUseCase: GetMessageFromExternalStorageUseCase
) : AndroidViewModel(application) {

    private val _message = MutableLiveData<String> ()
    val message : LiveData<String> = _message

    fun onSaveClicked(selectedItemPosition: Int, message : String) {
        when(selectedItemPosition) {
            DATABASE_POSITION -> insertMessageToDb(message)
            PREFS_POSITION -> insertMessageToPrefs(message)
            INTERNAL_STORAGE_POSITION -> insertMessageToInternalStorage(message)
            EXTERNAL_STORAGE_POSITION -> insertMessageToExternalStorage(message)
        }
    }

    fun onLoadClicked(selectedItemPosition: Int) {
        when(selectedItemPosition) {
            DATABASE_POSITION -> getMessageFromDb()
            PREFS_POSITION -> getMessageFromPrefs()
            INTERNAL_STORAGE_POSITION -> getMessageFromInternalStorage()
            EXTERNAL_STORAGE_POSITION -> getMessageFromExternalStorage()
        }
    }

    private fun insertMessageToDb(message : String) {
        insertMessageToDbUseCase.execute(message)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    showToast(getApplication<Application>().getString(R.string.saved_to_database_toast_text))
                },
                { error ->
                    Log.e(LOG_TAG, error.message.toString())
                    showToast(getApplication<Application>().getString(R.string.not_saved_to_database_toast_text))
                }
            )
    }

    private fun getMessageFromDb() {
        getMessageFromDbUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { message ->
                    _message.value = message
                    showToast(getApplication<Application>().getString(R.string.loaded_from_database_toast_text))
                },
                { error ->
                    Log.e(LOG_TAG, error.message.toString())
                    showToast(getApplication<Application>().getString(R.string.not_loaded_from_database_toast_text))
                }
            )
    }

    private fun insertMessageToPrefs(message: String) {
        insertMessageToPrefsUseCase.execute(message)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    showToast(getApplication<Application>().getString(R.string.saved_to_prefs_toast_text))
                },
                { error ->
                    Log.e(LOG_TAG, error.message.toString())
                    showToast(getApplication<Application>().getString(R.string.not_saved_to_prefs_toast_text))
                }
            )
    }

    private fun getMessageFromPrefs() {
        getMessageFromPrefsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { message ->
                    _message.value = message
                    showToast(getApplication<Application>().getString(R.string.loaded_from_prefs_toast_text))
                },
                { error ->
                    Log.e(LOG_TAG, error.message.toString())
                    showToast(getApplication<Application>().getString(R.string.not_loaded_from_prefs_toast_text))
                }
            )
    }

    private fun insertMessageToInternalStorage(message: String) {
        insertMessageToInternalStorageUseCase.execute(message)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    showToast(getApplication<Application>().getString(R.string.saved_to_internal_storage_toast_text))
                },
                { error ->
                    Log.e(LOG_TAG, error.message.toString())
                    showToast(getApplication<Application>().getString(R.string.not_saved_to_internal_storage_toast_text))
                }
            )
    }

    private fun getMessageFromInternalStorage() {
        getMessageFromInternalStorageUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { message ->
                    _message.value = message
                    showToast(getApplication<Application>().getString(R.string.loaded_from_internal_storage_toast_text))
                },
                { error ->
                    Log.e(LOG_TAG, error.message.toString())
                    showToast(getApplication<Application>().getString(R.string.not_loaded_from_internal_storage_toast_text))
                }
            )
    }

    private fun insertMessageToExternalStorage(message: String) {
        insertMessageToExternalStorageUseCase.execute(message)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    showToast(getApplication<Application>().getString(R.string.saved_to_external_storage_toast_text))
                },
                { error ->
                    Log.e(LOG_TAG, error.message.toString())
                    showToast(getApplication<Application>().getString(R.string.not_saved_to_external_storage_toast_text))
                }
            )
    }

    private fun getMessageFromExternalStorage() {
        getMessageFromExternalStorageUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { message ->
                    _message.value = message
                    showToast(getApplication<Application>().getString(R.string.loaded_from_external_storage_toast_text))
                },
                { error ->
                    Log.e(LOG_TAG, error.message.toString())
                    showToast(getApplication<Application>().getString(R.string.not_loaded_from_external_storage_toast_text))
                }
            )
    }

    private fun showToast(text : String) {
        Toast.makeText(getApplication(), text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val LOG_TAG = "LOG_TAG"
    }

}
